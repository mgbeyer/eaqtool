package de.rwth_aachen.swc.eaqtool.repo;

import de.rwth_aachen.swc.eaqtool.EAQTConf;
import de.rwth_aachen.swc.eaqtool.EAQTHelper;
import de.rwth_aachen.swc.eaqtool.meta.EAQTMeta;
import de.rwth_aachen.swc.eaqtool.metric.*;
import net.sf.saxon.s9api.*;

import java.lang.reflect.Method;
import java.util.*;

public class XQEAModelRepository extends EAModelRepository {

    public static final String PARAM_ATTRIB = "@attrib";

    private static XMLReader reader;
    private static XdmNode context;
    private static XQEAModelRepository instance;
    private static Processor proc;
    private static XQueryCompiler xqcomp;
    private static DocumentBuilder builder;

    private XQEAModelRepository(XMLReader reader) {
        this.reader = reader;
        this.proc = new Processor(false);
        this.registerExtensionFunctions();
        this.xqcomp = this.proc.newXQueryCompiler();
        this.declareNamespaces();
        this.builder = this.proc.newDocumentBuilder();
        try {
            this.context = this.builder.build(reader.getXmlSource());
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private void registerExtensionFunctions() {
        this.proc.registerExtensionFunction(execXq);
        this.proc.registerExtensionFunction(execEaMeta);
        this.proc.registerExtensionFunction(execNormalizer);
        this.proc.registerExtensionFunction(ageOfModel);
    }

    private void declareNamespaces() {
        this.xqcomp.declareNamespace(EAQTConf.NS_PREFIX_DEFAULT, EAQTConf.NAMESPACE_DEFAULT);
        this.xqcomp.declareNamespace(EAQTConf.NS_PREFIX_MATH, EAQTConf.NAMESPACE_MATH);
        this.xqcomp.declareNamespace(EAQTConf.XQ_EXTENSION_PREFIX, EAQTConf.XQ_EXTENSION_NAMESPACE);
    }

    private Method getMethod(Class<?> clazz, String method) {
        try {
            Method[] methods = clazz.getMethods();
            for (Method m : methods) {
                if (m.getName().equals(method)) {
                    return m;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lazy singleton, see constructor.
     * @return Initialized instance.
     */
    public static XQEAModelRepository getInstance(XMLReader reader) {
        if (instance == null) {
            instance = new XQEAModelRepository(reader);
        }
        return instance;
    }

    public XMLReader getReader() {
        return reader;
    }

    public XdmNode getContext() {
        return context;
    }

    public Processor getProc() {
        return proc;
    }

    public XQueryCompiler getXqcomp() {
        return xqcomp;
    }


    /**
     * See method "query"
     */
    private XdmValue queryRaw(Specification spec) {
        XdmValue result = null;

        try {
            String xquery =
                "declare variable $" + EAQTConf.XQ_EXTENSION_CONTEXT_LOCAL_NAME + " as document-node() external;" +
                "declare context item := document {$" + EAQTConf.XQ_EXTENSION_CONTEXT_LOCAL_NAME + "};";
            XQueryExecutable xqexec = this.xqcomp.compile(xquery + spec.toQuery());
            XQueryEvaluator xqeval = xqexec.load();
            //xqeval.setContextItem(getContext());
            Map<String, QName> vars = new HashMap<>();
            Map<String, Object> extvars = ((XQSpecification)spec).getExtVars();
            // map ext. variables declared in xq script
            if (extvars!=null) {
                for (Map.Entry<String, Object> e : extvars.entrySet()) {
                    String key = e.getKey();
                    XdmAtomicValue value = (e.getValue()!=null) ? XdmAtomicValue.makeAtomicValue(e.getValue()) : XdmAtomicValue.makeAtomicValue(new String(""));
                    vars.put(key, new QName(key));
                    xqeval.setExternalVariable(vars.get(key), value);
                }
            }
            QName qRoot = new QName(EAQTConf.XQ_EXTENSION_CONTEXT_LOCAL_NAME);
            xqeval.setExternalVariable(qRoot, getContext());
            result = xqeval.evaluate();
        } catch (SaxonApiException ex) { System.err.println(ex); }

        return result;
    }

    /**
     * Execute a query to the EA model. Evaluate the xq script given via the Specification object (see also method "queryRaw").
     * Sets all external (declared) variables contained in the Specification for the script prior to execution.
     * @param spec XQSpecification.
     * @return Query resultset as a List of XdmItem objects.
     */
    @Override
    public List query(Specification spec) {
        List<XdmItem> ret = new ArrayList<>();

        XdmValue result = queryRaw(spec);
        for (XdmItem it : result) {
            ret.add(it);
        }

        return ret;
    }

    /**
     * Helper method to convert a resultset List of XdmItem objects to a List of Strings.
     * @param res resultset.
     * @return Resultset as List of Strings.
     */
    public static List<String> resultset2ListofStrings(List<XdmItem> res) {
        List<String> ret = new ArrayList<>();

        for (XdmItem it : res) {
            ret.add(it.getStringValue());
        }

        return ret;
    }

    /**
     * Helper method to tell if a resultset List of XdmItem objects contains a given String.
     * @param res resultset to search.
     * @param search resultset.
     * @return true if String value of XdmItem equals 'search'.
     */
    public static boolean resultsetContains(List<XdmItem> res, String search) {
        Boolean ret = false;

        for (XdmItem it : res) {
            if (it.getStringValue().equals(search)) ret = true;
        }

        return ret;
    }


    // XQuery extension functions

    /**
     * Execute Ammp xquery script from inside another xquery script
     * Parameterization (optional) via parameter String: AMMP_SCRIPT_NAME:key1=val1,key2=val2, ... ,keyn=valn
     * Example call within XQuery script: eaqt:execXq(concat('AMMP_number-of-elem-by-vp:vp=', $vp))
     */
    ExtensionFunction execXq = new ExtensionFunction() {
        public QName getName() {
            return new QName(EAQTConf.XQ_EXTENSION_NAMESPACE, EAQTConf.XQ_EXTENSION_LEXICAL_EXECXQ);
        }

        public SequenceType getResultType() {
            return SequenceType.makeSequenceType(ItemType.ANY_ITEM, OccurrenceIndicator.ZERO_OR_MORE);
        }

        public SequenceType[] getArgumentTypes() {
            return new SequenceType[] {
                    SequenceType.makeSequenceType(ItemType.STRING, OccurrenceIndicator.ONE)
            };
        }

        public XdmValue call(XdmValue[] arguments) throws SaxonApiException {
            String raw = ((XdmAtomicValue)arguments[0].itemAt(0)).getStringValue();
            String script = EAQTHelper.getTypeNameFromJsonTypestring(raw);
            Map<String, Object> params = EAQTHelper.getParamsFromJsonTypestring(raw);
            String xquery = MSMeta.getInstance().getAmmpById(script).getScriptStr();
            XQSpecificationFactory spec_factory = new XQSpecificationFactory();
            spec_factory.loadParam(xquery); spec_factory.loadParam(params);
            XQSpecification spec = spec_factory.getSpecification();
            return queryRaw(spec);
        }
    };

    /**
     * Execute a method in the EA meta model (EAQTMeta)
     * Executes a) methods to get a list of entities (IDs) by given entity ID and b) methods to get a set of entities (IDs) of a certain type
     * Parameterization (optional) via parameter String: METHOD_NAME:id=entity_id
     * Per default the ID of an object is returned as a result, using Reflection API to execute the respective GetId method.
     * If instead XQEAModelRepository.PARAM_ATTRIB is set (e.g. as @attrib=attribName), Reflection tries to execute a getter of the form "get" + AttribName (capitalized first letter!).
     */
    ExtensionFunction execEaMeta = new ExtensionFunction() {
        public QName getName() {
            return new QName(EAQTConf.XQ_EXTENSION_NAMESPACE, EAQTConf.XQ_EXTENSION_LEXICAL_EXECEAMETA);
        }

        public SequenceType getResultType() {
            return SequenceType.makeSequenceType(ItemType.ANY_ITEM, OccurrenceIndicator.ZERO_OR_MORE);
        }

        public SequenceType[] getArgumentTypes() {
            return new SequenceType[] {
                    SequenceType.makeSequenceType(ItemType.STRING, OccurrenceIndicator.ONE)
            };
        }

        public XdmValue call(XdmValue[] arguments) throws SaxonApiException {
            List<String> res = new ArrayList<>();

            try {
                String raw = ((XdmAtomicValue)arguments[0].itemAt(0)).getStringValue();
                String method = EAQTHelper.getTypeNameFromJsonTypestring(raw);
                Map<String, Object> params = EAQTHelper.getParamsFromJsonTypestring(raw);
                String entity_id = null;
                if (!params.isEmpty()) entity_id = params.get("id")!=null ? params.get("id").toString() : "";
                String attribute_to_fetch = "";
                if (!params.isEmpty()) attribute_to_fetch = params.get(XQEAModelRepository.PARAM_ATTRIB)!=null ? params.get(XQEAModelRepository.PARAM_ATTRIB).toString(): "";
                Method m = getMethod(EAQTMeta.getInstance().getClass(), method);
                // rough param check, not much, so act responsible and pass the right method name.
                if ( (m.getParameterTypes().length==1 && m.getParameterTypes()[0]==String.class) || m.getParameterTypes().length==0 ) {
                    // return type is a List of POJOs
                    if (Collection.class.isAssignableFrom(m.getReturnType()) && m.getReturnType()==List.class) {
                        List<Object> obj = null;
                        if (entity_id!=null) {      // check if id param. is there
                            obj = (List<Object>)m.invoke(EAQTMeta.getInstance(), entity_id);
                        } else {
                            obj = (List<Object>)m.invoke(EAQTMeta.getInstance());
                        }
                        for (Object it : obj) {
                            String methodname;
                            if (attribute_to_fetch.compareTo("")==0) {
                                methodname = "getId";
                            } else {
                                methodname = "get"+EAQTHelper.capStr(attribute_to_fetch);
                            }
                            res.add(it.getClass().getMethod(methodname).invoke(it).toString());
                        }
                    }
                }
            } catch (Exception ex) {
                System.err.println(ex);
            }

            return XdmValue.makeSequence(res);
        }
    };

    /**
     * Execute a Normalizer.
     * Parameterization via parameter String: NORMALIZER_TYPE:raw=value,key1=val1,key2=val2, ..., keyn=valn
     * key/val pairs represent normalizer parameters (see metrics.json), for ratio type normalizers use divisor=value for divisor setting.
     * For supported normalizer types see NormalizerFactory class.
     */
    ExtensionFunction execNormalizer = new ExtensionFunction() {
        public QName getName() {
            return new QName(EAQTConf.XQ_EXTENSION_NAMESPACE, EAQTConf.XQ_EXTENSION_LEXICAL_EXECNORMALIZER);
        }

        public SequenceType getResultType() {
            return SequenceType.makeSequenceType(ItemType.ANY_ITEM, OccurrenceIndicator.ZERO_OR_MORE);
        }

        public SequenceType[] getArgumentTypes() {
            return new SequenceType[] {
                    SequenceType.makeSequenceType(ItemType.STRING, OccurrenceIndicator.ONE)
            };
        }

        public XdmValue call(XdmValue[] arguments) throws SaxonApiException {
            XdmValue ret = null;
            Double rawval = null;
            Double normalized = null;

            try {
                String raw = ((XdmAtomicValue)arguments[0].itemAt(0)).getStringValue();
                //String normalizer = EAQTHelper.getTypeNameFromJsonTypestring(raw);
                Normalizer no = NormalizerFactory.getNormalizer(raw);
                if (no!=null) {
                    Double divisor = null;
                    Map<String, Object> params = EAQTHelper.getParamsFromJsonTypestring(raw);
                    if (!params.isEmpty()) rawval = params.get("raw") != null ? Double.parseDouble(params.get("raw").toString()) : null;
                    if (!params.isEmpty()) divisor = params.get("divisor") != null ? Double.parseDouble(params.get("divisor").toString()) : null;
                    if (no.getType()==NormalizerFactory.NORMALIZER_TYPE_RATIOX) {
                        ((NormalizerRatioX)no).setDivisor(divisor);
                    } else if (no.getType()==NormalizerFactory.NORMALIZER_TYPE_RATIOAB) {
                        ((NormalizerRatioAb)no).setDivisor(divisor);
                    }
                    if (rawval!=null) normalized = no.normalize(rawval);
                }
            } catch (Exception ex) {
                System.err.println(ex);
            }

            if (normalized!=null) {
                ret = XdmValue.makeValue(normalized);
            } else {
                ret = XdmValue.makeValue("");
            }
            return ret;
        }
    };

    /**
     * Return age of the XML model file in days (if the repo reader is a filereader)
     */
    ExtensionFunction ageOfModel = new ExtensionFunction() {
        public QName getName() {
            return new QName(EAQTConf.XQ_EXTENSION_NAMESPACE, EAQTConf.XQ_EXTENSION_LEXICAL_AGEOFMODEL);
        }

        public SequenceType getResultType() {
            return SequenceType.makeSequenceType(ItemType.ANY_ITEM, OccurrenceIndicator.ONE);
        }

        public SequenceType[] getArgumentTypes() {
            return new SequenceType[] {};
        }

        public XdmValue call(XdmValue[] arguments) throws SaxonApiException {
            XdmValue ret = null;

            Integer model_age = null;

            if (getReader().getClass()==XMLFileReader.class) {
                model_age = ((XMLFileReader)getReader()).getAgeOfModelFileInDays();
            }

            if (model_age!=null) {
                ret = XdmValue.makeValue(model_age);
            } else {
                ret = XdmValue.makeValue("");
            }

            return ret;
        }
    };
}
