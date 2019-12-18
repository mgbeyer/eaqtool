package de.rwth_aachen.swc.eaqtool.persist;

import java.io.Writer;

abstract class PojoSerializer<M, W extends Writer> {

    private M mapper;
    private W writer;

    protected PojoSerializer(M mapper, W writer) {
        this.mapper = mapper;
        this.writer = writer;
    }

    abstract public M getMapper();
    abstract public W getWriter();
    abstract public void serialize(Object o);

}
