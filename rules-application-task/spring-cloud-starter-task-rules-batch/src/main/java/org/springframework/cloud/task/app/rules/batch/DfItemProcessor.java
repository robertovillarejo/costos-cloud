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

import static mx.infotec.dads.costos.service.mapper.RuleMapper.mapToRulesList;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.task.app.rules.batch.context.CostoContext;
import org.springframework.cloud.task.app.rules.batch.context.DtCostoContext;
import org.springframework.cloud.task.app.rules.batch.context.RhCostoContext;
import org.springframework.cloud.task.app.rules.batch.context.SigaifCostoContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import mx.infotec.dads.costos.domain.Costo;
import mx.infotec.dads.costos.domain.DataFrameItem;
import mx.infotec.dads.costos.domain.DataFrameType;
import mx.infotec.dads.costos.domain.dataframe.DfItemDt;
import mx.infotec.dads.costos.domain.dataframe.DfItemRh;
import mx.infotec.dads.costos.domain.dataframe.DfItemSigaif;
import mx.infotec.dads.costos.repository.DataFrameTypeRepository;
import mx.infotec.dads.costos.repository.DfItemRepository;
import mx.infotec.dads.costos.service.PartidaConceptoService;
import mx.infotec.dads.costos.service.ProveedorService;
import mx.infotec.dads.kukulkan.rules.DefaultRulesApplier;
import mx.infotec.dads.kukulkan.rules.RulesApplier;

public class DfItemProcessor implements ItemProcessor<DataFrameItem, Costo>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(DfItemProcessor.class);

    @Autowired
    private DfItemRepository dfItemRepo;

    @Autowired
    private DataFrameTypeRepository dfTypeRepo;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private PartidaConceptoService partidaConceptoService;

    @Override
    public void beforeStep(StepExecution stepExecution) {
    }

    @Override
    public Costo process(DataFrameItem dfItem) throws Exception {
        logger.info("Processing dfItem: {}", dfItem);
        CostoContext context = buildContext(dfItem);
        String dataFrameType = dfItem.getDataFrame().getDataFrameType().getName();
        Optional<DataFrameType> maybeDfType = dfTypeRepo.findOneByName(dataFrameType);
        if (maybeDfType.isPresent()) {
            RulesApplier rulesApplier = new DefaultRulesApplier(mapToRulesList(maybeDfType.get().getRules()),
                    new SpelExpressionParser());
            rulesApplier.apply(new StandardEvaluationContext(context));
        } else {
            logger.debug("No rules to apply for data frame type {}", dataFrameType);
        }
        dfItem.setProcessed(true);
        //Descomente la siguiente línea si desea persistir las modificaciones que el DataFrameItem sufre durante la aplicación de las reglas.        
        //dfItemRepo.save(dfItem);
        Costo costo = context.getCosto();
        costo.setDataFrameItem(dfItem);
        return costo;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

    public CostoContext buildContext(DataFrameItem dfItem) {
        if (dfItem instanceof DfItemRh) {
            return new RhCostoContext((DfItemRh) dfItem, proveedorService, partidaConceptoService);
        } else if (dfItem instanceof DfItemSigaif) {
            return new SigaifCostoContext((DfItemSigaif) dfItem, proveedorService, partidaConceptoService);
        } else if (dfItem instanceof DfItemDt) {
            return new DtCostoContext((DfItemDt) dfItem, proveedorService, partidaConceptoService);
        }
        return null;
    }

}
