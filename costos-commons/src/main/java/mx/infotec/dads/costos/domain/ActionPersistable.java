package mx.infotec.dads.costos.domain;

import java.io.Serializable;

public class ActionPersistable implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3940753031119081343L;

    private String actionExpression;

    private int order;

    public String getActionExpression() {
        return actionExpression;
    }

    public void setActionExpression(String actionExpression) {
        this.actionExpression = actionExpression;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}
