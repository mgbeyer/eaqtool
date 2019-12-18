package de.rwth_aachen.swc.eaqtool.repo;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class XMLFileReader implements XMLReader {

    private String basePath;
    private String sourceId;

    public XMLFileReader(String basePath) {
        this.basePath = basePath;
        this.sourceId = DigestUtils.sha256Hex(basePath);
    }

    public void setSourceId(String id) { this.sourceId = sourceId; }

    public String getSourceId() { return this.sourceId; }

    public String getBasePath() {
        return FilenameUtils.normalize(basePath);
    }

    /**
     * Get StreamSource from XML file.
     * @return StreamSource.
     */
    @Override
    public StreamSource getXmlSource() {
        StreamSource ret = null;

        try {
            FileReader reader = new FileReader(this.basePath);
            ret = new StreamSource(reader);
        } catch (FileNotFoundException fnfex) {
            System.out.println(fnfex);
        }

        return ret;
    }

    /**
     * Helper method to get age of model file of reader.
     * @return Age of file in days.
     */
    public Integer getAgeOfModelFileInDays() {
        File f = new File(getBasePath());
        Long retval = (System.currentTimeMillis() - f.lastModified()) / 24 / 60 / 60 / 1000;
        return retval.intValue();
    }


}
