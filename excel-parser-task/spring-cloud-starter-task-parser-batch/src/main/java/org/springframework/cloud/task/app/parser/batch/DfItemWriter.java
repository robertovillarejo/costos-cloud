package org.springframework.cloud.task.app.parser.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import mx.infotec.dads.costos.domain.DataFrameItem;
import mx.infotec.dads.costos.repository.DfItemRepository;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
public class DfItemWriter implements ItemWriter<DataFrameItem>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(DfItemWriter.class);

    @Autowired
    private DfItemRepository repository;

    @Override
    public void beforeStep(StepExecution arg0) {
        logger.info("Before writing data frame items...");
    }

    @Override
    public void write(List<? extends DataFrameItem> dfItems) throws Exception {
        logger.info("Saving data frame items...");
        repository.saveAll(dfItems);
    }

    @Override
    public ExitStatus afterStep(StepExecution arg0) {
        logger.info("After writing data frame items...");
        return ExitStatus.COMPLETED;
    }

}
