package mx.infotec.dads.costos;

import java.util.List;
import java.util.stream.Collectors;

import mx.infotec.dads.costos.ActionPersistable;
import mx.infotec.dads.costos.RulePersistable;
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
