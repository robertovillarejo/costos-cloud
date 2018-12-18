package mx.infotec.dads.costos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
@Document("dataFrame")
public class DataFrame implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4244293458368515272L;

    @Id
    private String id;

    private byte[] file;

    @DBRef
    private DataFrameType dataFrameType;

    private boolean processed;

    private String fileName;

    private List<Error> errors = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DataFrameType getDataFrameType() {
        return dataFrameType;
    }

    public void setDataFrameType(DataFrameType dataFrameType) {
        this.dataFrameType = dataFrameType;
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

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public boolean addError(Error error) {
        return errors.add(error);
    }

    @Override
    public String toString() {
        return "DataFrame [id=" + id + "," + ", dataFrameType=" + dataFrameType + ", processed=" + processed
                + ", fileName=" + fileName + ", errors=" + errors + "]";
    }

}
