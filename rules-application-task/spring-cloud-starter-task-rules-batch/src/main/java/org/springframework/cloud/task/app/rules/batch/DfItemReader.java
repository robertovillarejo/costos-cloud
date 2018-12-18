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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import mx.infotec.dads.costos.domain.DataFrameItem;
import mx.infotec.dads.costos.repository.DfItemRepository;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
public class DfItemReader implements ItemReader<DataFrameItem>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(DfItemReader.class);

    @Autowired
    private DfItemRepository dfItemRepo;

    private int pageNumber = 0;

    private Page<DataFrameItem> pageDfItem;

    @Override
    public void beforeStep(StepExecution stepExecution) {
    }

    @Override
    public DataFrameItem read() throws Exception {
        if (pageNumber == 0 || pageDfItem.hasNext()) {
            pageDfItem = dfItemRepo.findByProcessedFalse(PageRequest.of(pageNumber, 1));
            pageNumber++;
            if (pageDfItem.hasContent()) {
                return pageDfItem.getContent().get(0);
            }
        }
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

}
