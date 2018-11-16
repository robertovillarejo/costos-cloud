package mx.infotec.dads.costos;

import java.io.Serializable;

public class ActionPersistable implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3940753031119081343L;
    //
    // @Id
    // private String id;

    private String actionExpression;

    private int order;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

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
