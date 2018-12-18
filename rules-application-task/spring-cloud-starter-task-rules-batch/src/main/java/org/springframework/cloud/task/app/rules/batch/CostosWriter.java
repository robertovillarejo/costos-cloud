/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2018 Roberto Villarejo Martínez
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.springframework.cloud.task.app.rules.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import mx.infotec.dads.costos.domain.Costo;
import mx.infotec.dads.costos.repository.CostoRepository;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
public class CostosWriter implements ItemWriter<Costo>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(CostosWriter.class);

    @Autowired
    private CostoRepository repo;

    @Override
    public void beforeStep(StepExecution arg0) {
        logger.info("Before writing costos...");
    }

    @Override
    public void write(List<? extends Costo> costos) throws Exception {
        logger.info("Saving costos...");
        repo.saveAll(costos);
    }

    @Override
    public ExitStatus afterStep(StepExecution arg0) {
        logger.info("After writing costos...");
        return ExitStatus.COMPLETED;
    }

}
