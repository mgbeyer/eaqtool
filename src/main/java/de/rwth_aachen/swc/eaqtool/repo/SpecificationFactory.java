package de.rwth_aachen.swc.eaqtool.repo;

abstract public class SpecificationFactory {

    abstract public Specification getSpecification();
    abstract public void loadParam(Object o);

}
