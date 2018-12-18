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
import java.util.stream.Collectors;

import mx.infotec.dads.costos.domain.ActionPersistable;
import mx.infotec.dads.costos.domain.RulePersistable;
import mx.infotec.dads.kukulkan.rules.Action;
import mx.infotec.dads.kukulkan.rules.Rule;

public class RuleMapper {

    private RuleMapper() {
    }

    public static Rule mapToRule(RulePersistable rulePersistable) {
        return new Rule(rulePersistable.getName(), rulePersistable.getCondition(),
                mapToActionList(rulePersistable.getActions()), rulePersistable.getOrder());
    }

    public static Action mapToAction(ActionPersistable actionPersistable) {
        return new Action(actionPersistable.getActionExpression(), actionPersistable.getOrder());
    }

    public static List<Rule> mapToRulesList(List<RulePersistable> rulesPersistable) {
        return rulesPersistable.stream().map(RuleMapper::mapToRule).collect(Collectors.toList());
    }

    public static List<Action> mapToActionList(List<ActionPersistable> actionsPersistable) {
        return actionsPersistable.stream().map(RuleMapper::mapToAction).collect(Collectors.toList());
    }

}
