/*
 * NewClass.java
 *
 * Created on 5 Май 2010 г., 17:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javajn;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * @author Arsh
 */
public class jnsimpledict implements jndictionary {

    private Properties data = new Properties();
    private boolean stop = false;
    private jnequalizer e = null;
    private Vector<jnelement> visited = new Vector();

    public jnsimpledict() {

    }

    public Vector<String> getNames() {
        Vector<String> res = new Vector();
        Enumeration n = data.propertyNames();
        while (n.hasMoreElements()) {
            res.add((String) n.nextElement());
        }
        return res;
    }

    public String getValue(String variable) {
        if (e != null) variable = e.getEqual(variable);
        return data.getProperty(variable);
    }

    public void setValue(String variable, String val) {
        if (e != null) variable = e.getEqual(variable);
        //System.err.println(variable);        
        data.setProperty(variable, val);
    }

    public jndictionary getCopy() {
        jnsimpledict ndata = new jnsimpledict();
        ndata.data.putAll(data);
        for (int i = 0; i < visited.size(); i++) {
            ndata.visited.add(visited.get(i));
        }
        ndata.e = this.e;
        return ndata;
    }

    public boolean canMoreStep() {
        return stop;
    }

    public void setCanMoreStep(boolean flag) {
        stop = flag;
    }

    public void stepFinish() {
    }

    public void visitElement(jnelement el) {
        //System.err.println("visit "+el.getStart());
        visited.add(el);
    }

    public Vector<jnelement> getVisitedElements() {
        return visited;
    }

    public void setEqualizer(jnequalizer eq) {
        this.e = eq;
    }

    public void ShowAllElements() {
        System.err.println("Dictonary:\n----------");
        Enumeration a = this.getNames().elements();
        while (a.hasMoreElements()) {
            String n = (String) a.nextElement();
            System.err.println(n + " = " + getValue(n));
        }
    }

    public void clear() {
        data.clear();
    }
}
