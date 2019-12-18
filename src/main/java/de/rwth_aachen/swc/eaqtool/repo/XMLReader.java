package de.rwth_aachen.swc.eaqtool.repo;

import javax.xml.transform.stream.StreamSource;

public interface XMLReader {

    public StreamSource getXmlSource();

    // A unique source id can be used by a persistence layer
    // to recognize if a data source has changed, which is
    // needed to keep a measurement log consistent!
    public void setSourceId(String id);
    public String getSourceId();

}
