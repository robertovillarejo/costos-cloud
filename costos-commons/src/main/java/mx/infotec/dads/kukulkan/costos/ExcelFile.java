package mx.infotec.dads.kukulkan.costos;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
@Document("excelFile")
public class ExcelFile implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4244293458368515272L;

    @Id
    private String id;

    private byte[] file;

    private boolean processed;

    private String fileName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
