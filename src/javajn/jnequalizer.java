/*
 * jnequalizer.java
 *
 * Created on 17 Май 2010 г., 20:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javajn;

import java.util.Enumeration;
import java.util.Properties;

/**
 * @author Alex
 */
public class jnequalizer {

    private Properties data = new Properties();

    public jnequalizer() {
    }

    public String getEqual(String forVar) {
        return data.getProperty(forVar, forVar);
    }

    public void varsEqual(String var1, String var2) {
        if (var1.compareToIgnoreCase(var2) > 0) {
            String s = var2;
            var2 = var1;
            var1 = s;
        }

        var2 = getEqual(var2);
        var1 = getEqual(var1);

        if (var1.equalsIgnoreCase(var2)) return;
        data.setProperty(var1, var2);

        Enumeration e = data.keys();
        while (e.hasMoreElements()) {
            String k = (String) e.nextElement();
            String v = data.getProperty(k);

            if (v.equalsIgnoreCase(var1)) data.setProperty(k, var2);
        }
    }

    public Properties getData() {
        return data;
    }

    public void clear() {
        data.clear();
    }

    public void ShowAllEqualsMap() {
        Enumeration e = data.keys();
        System.err.println("Equal Map:\n----------");
        while (e.hasMoreElements()) {
            String k = (String) e.nextElement();
            String v = data.getProperty(k);
            System.err.println(k + " = " + v);
        }

    }
}
