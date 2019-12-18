package de.rwth_aachen.swc.eaqtool.persist;

import java.io.Reader;

abstract class PojoDeserializer<M, R extends Reader> {

    private M mapper;
    private R reader;

    protected PojoDeserializer(M mapper, R reader) {
        this.mapper = mapper;
        this.reader = reader;
    }

    abstract public M getMapper();
    abstract public R getReader();
    abstract public Object deserialize();

}
