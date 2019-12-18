package de.rwth_aachen.swc.eaqtool.repo;

import java.util.List;

abstract public class EAModelRepository<R extends XMLReader, C> {

    abstract public List query(Specification spec);
    abstract public R getReader();
    abstract public C getContext();

}
