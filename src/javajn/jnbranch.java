/*
 * jnlist.java
 *
 * Created on 5 Май 2010 г., 17:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javajn;

import java.util.Vector;

/**
 * @author Arsh
 */
public class jnbranch {
    private Vector<jnelement> elms = null;
    private jndictionary dict = null;
    private int fromElemnt = 0;
    private int fromAction = 0;

    /**
     * Creates a new instance of jnlist
     */

    public jnbranch(jndictionary dict) {
        this.dict = dict;
        this.elms = new Vector();
    }

    public jnbranch(jnbranch src, int fromElemnt, int fromAction) {
        //System.err.println("src.dict.getCopy() Data:");        
        this.dict = src.dict.getCopy();
        //this.dict.ShowAllElements();
        elms = src.elms;
        this.fromElemnt = fromElemnt;
        this.fromAction = fromAction;
    }


    public jnelement addjnelement(String start, String jnsimpleaction) {
        jnelement el = new jnelement(start, new jnsimpleaction(jnsimpleaction));
        elms.add(el);
        return el;
    }

    public jnelement addjnelement(String start, jnaction flag) {
        jnelement el = new jnelement(start, flag);
        elms.add(el);
        return el;
    }

    public jnelement addjnelement(jnelement el) {
        elms.add(el);
        return el;
    }

    public void clear() {
        elms.clear();
    }

    public Vector<jnelement> getElements() {
        return elms;
    }

    public boolean jnCalcOneStep(jnsolution sol) {
        boolean res = jnCalcOneStep(sol, fromElemnt, fromAction);
        fromElemnt = 0;
        fromAction = 0;
        return res;
    }

    public boolean jnCalcOneStep(jnsolution sol, int fromEl, int fromAct) {
        jnelement el;
        boolean retry = false;
        if (fromAct > 0) {
            el = elms.get(fromEl);
            el.getFlag().doAction(el, dict, fromAct);
            fromEl++;
            retry = true;
        }
        for (int i = fromEl; i < elms.size(); i++) {
            el = elms.get(i);
            if (el.testJNElement(dict) == '1') {
                retry = true;
                dict.visitElement(el);
                int ActCnt = el.getFlag().getCount();
                if (ActCnt >= 1) {
                    for (int j = 1; j < ActCnt; j++) {
                        sol.copyBranch(this, i, j);
                    }
                    el.getFlag().doAction(el, dict, 0);
                }
            }
        }
        dict.setCanMoreStep(retry);
        dict.stepFinish();
        return retry;
    }

    public int getFromElemnt() {
        return fromElemnt;
    }

    public void setFromElemnt(int fromElemnt) {
        this.fromElemnt = fromElemnt;
    }

    public int getFromAction() {
        return fromAction;
    }

    public void setFromAction(int fromAction) {
        this.fromAction = fromAction;
    }

    public jndictionary getDict() {
        return dict;
    }

}
