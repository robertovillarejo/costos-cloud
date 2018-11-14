package org.springframework.cloud.task.app.parser.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import mx.infotec.dads.kukulkan.costos.Costo;
import mx.infotec.dads.kukulkan.costos.repository.CostoRepository;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
public class CostoWriter implements ItemWriter<Costo>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(CostoWriter.class);

    @Autowired
    private CostoRepository repository;

    @Override
    public void beforeStep(StepExecution arg0) {
        logger.info("Before writing costo items...");
    }

    @Override
    public void write(List<? extends Costo> costos) throws Exception {
        logger.info("Saving costo items...");
        repository.saveAll(costos);
    }

    @Override
    public ExitStatus afterStep(StepExecution arg0) {
        logger.info("After writing costo items...");
        return ExitStatus.COMPLETED;
    }

}
