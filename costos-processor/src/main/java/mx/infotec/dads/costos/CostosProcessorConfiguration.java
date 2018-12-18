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

package mx.infotec.dads.costos;

import static mx.infotec.dads.costos.context.CostoContextFactory.buildContext;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.integration.annotation.ServiceActivator;

import mx.infotec.dads.costos.context.CostoContext;
import mx.infotec.dads.costos.domain.Costo;
import mx.infotec.dads.costos.domain.DataFrameItem;
import mx.infotec.dads.costos.domain.DataFrameType;
import mx.infotec.dads.costos.repository.DataFrameTypeRepository;
import mx.infotec.dads.costos.repository.DfItemRepository;
import mx.infotec.dads.kukulkan.rules.DefaultRulesApplier;
import mx.infotec.dads.kukulkan.rules.RulesApplier;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
@EnableBinding(Processor.class)
@EnableConfigurationProperties(CostosProcessorProperties.class)
@EnableMongoRepositories(basePackages = { "mx.infotec.dads.costos" })
public class CostosProcessorConfiguration {

    private final Logger logger = LoggerFactory.getLogger(CostosProcessorConfiguration.class);

    @Autowired
    private CostosProcessorProperties properties;

    @Autowired
    private DfItemRepository dfItemRepo;

    @Autowired
    private DataFrameTypeRepository dfTypeRepo;

    @ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public Costo process(DataFrameItem dfItem) {
        logger.info("Processing dfItem: {}", dfItem);
        CostoContext context = buildContext(dfItem);
        String dataFrameType = dfItem.getDataFrame().getDataFrameType().getName();
        Optional<DataFrameType> maybeDfType = dfTypeRepo.findOneByName(dataFrameType);
        if (maybeDfType.isPresent()) {
            RulesApplier rulesApplier = new DefaultRulesApplier(RuleMapper.mapToRulesList(maybeDfType.get().getRules()),
                    new SpelExpressionParser());
            rulesApplier.apply(new StandardEvaluationContext(context));
        } else {
            logger.debug("No rules to apply for data frame type {}", dataFrameType);
        }

        dfItem.setProcessed(true);
        dfItemRepo.save(dfItem);
        return context.getCosto();
    }

}
