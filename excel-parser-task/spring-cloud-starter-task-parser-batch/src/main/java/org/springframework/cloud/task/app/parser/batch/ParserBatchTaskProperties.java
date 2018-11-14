package org.springframework.cloud.task.app.parser.batch;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
@ConfigurationProperties("parser")
public class ParserBatchTaskProperties {

    /**
     * The chunk size for the excel parsing job
     */
    private int chunkSize = 10;

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }
}
