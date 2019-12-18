package de.rwth_aachen.swc.eaqtool.persist;

public abstract class PojoPersistenceFactory {

    abstract public PojoSerializer getPojoSerializer();
    abstract public PojoDeserializer getPojoDeserializer();

}
