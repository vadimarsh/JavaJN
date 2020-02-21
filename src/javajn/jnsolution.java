/*
 * jnsolution.java
 *
 * Created on 17 ��� 2010 �., 15:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javajn;

import java.util.Vector;

/**
 * @author Arsh
 */
public class jnsolution {

    private Vector<jnbranch> branches = new Vector();
    private int maxbranch = 0;

    public jnsolution() {
    }

    public void copyBranch(jnbranch src, int fromEl, int fromAct) {
        if ((maxbranch > 0) && (branches.size() >= maxbranch)) return;
        jnbranch nb = new jnbranch(src, fromEl, fromAct);
        branches.add(nb);
    }

    public void startSolution(jnbranch src, int maxsteps, int maxbranch) {
        src.setFromAction(0);
        src.setFromElemnt(0);
        branches.clear();
        branches.add(src);
        this.maxbranch = maxbranch;
        int step = 0;
        boolean retry = true;
        while ((step < maxsteps) && (retry)) {
            step++;
            retry = false;
            // ������ ����
            System.err.println("Step == " + step);
            int i = 0;
            //  �� ���� ������
            while (i < branches.size()) {
                //System.err.println("new branch");
                if (branches.get(i).jnCalcOneStep(this))
                    retry = true;
                i++;
            }
            // ����� ����
        }
    }

    public Vector<jnbranch> getBranches() {
        return branches;
    }

    public Vector<jndictionary> getSolution() {
        Vector<jndictionary> res = new Vector();
        for (int i = 0; i < branches.size(); i++) {
            res.add(branches.get(i).getDict());
        }
        return res;
    }
}
