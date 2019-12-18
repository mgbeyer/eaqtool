package de.rwth_aachen.swc.eaqtool.repo;

public class XMLFileReaderFactory extends XMLReaderFactory {

    private String basePath;

    public XMLFileReaderFactory(String basePath) {
        this.basePath = basePath;
    }

    public XMLFileReader getReader() {
        return new XMLFileReader(basePath);
    }
}
