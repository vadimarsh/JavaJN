/*
 * jntask.java
 *
 * Created on 18 ��� 2010 �., 10:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javajn;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * ����� ������������ ��� ���������� JN-������ � �� �������
 *
 * @author Arsh
 */
public class jntask {

    private jnequalizer jne = new jnequalizer();
    private jndictionary dict;
    private jnbranch branch;
    private jnsolution sol = new jnsolution();
    private Properties vars = new Properties();

    /**
     * �������� ������ �� ��������� �� ������ �������
     */
    public jntask() {
        this(jnsimpledict.class);
    }

    /**
     * �������� ������ �� ��������� �� ������ �������
     *
     * @param c ����� �������
     */
    public jntask(Class c) {
        try {
            dict = (jndictionary) c.newInstance();
            branch = new jnbranch(dict);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * ���������� JN-��������
     *
     * @param el ����� �������
     * @return ��������� �������
     */
    public jnelement addElement(jnelement el) {
        return branch.addjnelement(el);
    }

    /**
     * ���������� JN-�������� �� ������ ������
     *
     * @param start �������� �������. �������� "avbv-c&(dva&b)"
     * @param flag  �������� �������. �������� "a=0,b=1;a=0,c=1;a=0,b=1,c=1"
     * @return ��������� �������
     */
    public jnelement addElement(String start, String flag) {
        return branch.addjnelement(start, flag);
    }

    /**
     * ��������� ������ JN-���������
     *
     * @return ������ JN-���������
     */
    public Vector<jnelement> getElements() {
        return branch.getElements();
    }

    /**
     * ��������� �������� ����������
     *
     * @param var ��� ����������
     * @param val �������� ����������
     */
    public void setValue(String var, String val) {
        vars.setProperty(var, val);
    }

    /**
     * ��������� �������� ����������
     *
     * @param var ��� ����������
     * @return �������� ����������
     */
    public String getValue(String var) {
        return vars.getProperty(var);
    }

    /**
     * �������������� ����������
     *
     * @param var1 ����������1
     * @param var2 ����������2
     */
    public void addEqual(String var1, String var2) {
        jne.varsEqual(var1, var2);
    }

    /**
     * ������� ����������
     */
    public void clearValue() {
        vars.clear();
    }

    /**
     * �������� ���� JN-��������
     */
    public void clearElements() {
        branch.clear();
    }

    /**
     * �������� ���� ��������������
     */
    public void clearEquals() {
        jne.clear();
    }

    /**
     * ������� �������
     */
    public void clearAll() {
        clearElements();
        clearEquals();
        clearValue();
    }

    /**
     * ���������� ����� ���� ���������
     *
     * @return ����� ����������
     */
    public Vector<String> getValues() {
        return dict.getNames();
    }

    /**
     * ���������� ������-�������
     *
     * @return ������� ���� ������
     */
    public jnsolution getSolution() {
        return sol;
    }

    /**
     * ���������� ��������������
     *
     * @return �������������� ���� ������
     */
    public jnequalizer getEquals() {
        return jne;
    }

    /**
     * ���������� ������ �������
     *
     * @return ������ �������
     */
    public Vector<jndictionary> getSolutions() {
        return sol.getSolution();
    }

    /**
     * ������ ������� ������������ ������
     *
     * @param maxsteps  ����������� ���������� �����
     * @param maxbranch ����������� ����� ���������
     */
    public void solve(int maxsteps, int maxbranch) {
        // ���������� �������
        dict.setEqualizer(null);
        dict.clear();
        dict.setEqualizer(jne);
        // ��������� ������� � ������ Equals
        Enumeration e = vars.keys();
        while (e.hasMoreElements()) {
            String k = (String) e.nextElement();
            dict.setValue(k, vars.getProperty(k));
        }
        // ������ �������
        sol.startSolution(branch, maxsteps, maxbranch);
    }

    /**
     * �������� ��� ������� ���� ������
     */
    public void ShowAllSolution() {
        System.err.print("There are " + sol.getSolution().size() + " solutions");
        for (int i = 0; i < sol.getSolution().size(); i++) {
            System.err.println("\n\n--- " + i + " ---");
            sol.getSolution().get(i).ShowAllElements();
        }
    }
}
