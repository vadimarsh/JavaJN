/*
 * jnsimpleaction.java
 *
 * Created on 17 Май 2010 г., 21:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javajn;

import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @author Alex
 */


public class jnsimpleaction implements jnaction {

    private Vector<sa_element> data = new Vector();

    /**
     * Creates a new instance of jnsimpleaction
     */

    public jnsimpleaction(String formula) {
        setFormula(formula);
    }

    public int getCount() {
        return data.size();
    }

    public void doAction(jnelement from, jndictionary dict, int no) {
        data.get(no).exec(dict);
    }

    public void setFormula(String formula) {
        data.clear();
        StringTokenizer stk = new StringTokenizer(formula, ";");
        while (stk.hasMoreTokens()) {
            sa_element sa = new sa_element(stk.nextToken());
            data.add(sa);
        }
    }

    private class sa_element {
        private Properties data = new Properties();
        private String t;

        public sa_element(String data) {
            t = data;
            StringTokenizer stk = new StringTokenizer(data, ",");
            while (stk.hasMoreTokens()) {
                StringTokenizer ste = new StringTokenizer(stk.nextToken(), "=");
                String p = ste.nextToken();
                String v = ste.nextToken();
                this.data.setProperty(p, v);
                ste = null;
            }
            stk = null;
        }

        public void exec(jndictionary dict) {
            Enumeration e = data.keys();
            while (e.hasMoreElements()) {
                String k = (String) e.nextElement();
                dict.setValue(k, data.getProperty(k));
                //System.err.println(dict+": "+t+": "+k+" = "+data.getProperty(k));
                //dict.ShowAllElements();
            }
        }
    }


}
