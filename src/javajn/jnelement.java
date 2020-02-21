/*
 * jnelement.java
 *
 * Created on 5 Май 2010 г., 16:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javajn;

import java.util.StringTokenizer;

/**
 * @author Arsh
 */
public class jnelement {

    /**
     * Creates a new instance of jnelement
     */
    private String start;
    private jnaction flag;

    public jnelement(String start, jnaction flag) {
        this.setStart(start);
        this.setFlag(flag);
    }

    private char calc(String s) {
        String last = s;
        String r = s;
        do {
            last = s;
            r = s.replaceAll("\\(0\\)", "0");
            if (!r.equals(s)) {
                s = r;
                continue;
            }
            r = s.replaceAll("\\(1\\)", "1");
            if (!r.equals(s)) {
                s = r;
                continue;
            }
            r = s.replaceAll("-0", "1");
            if (!r.equals(s)) {
                s = r;
                continue;
            }
            r = s.replaceAll("-1", "0");
            if (!r.equals(s)) {
                s = r;
                continue;
            }


            r = s.replaceAll("0&0", "0");
            if (!r.equals(s)) {
                s = r;
                continue;
            }
            r = s.replaceAll("0&1", "0");
            if (!r.equals(s)) {
                s = r;
                continue;
            }
            r = s.replaceAll("1&0", "0");
            if (!r.equals(s)) {
                s = r;
                continue;
            }
            r = s.replaceAll("1&1", "1");
            if (!r.equals(s)) {
                s = r;
                continue;
            }

            r = s.replaceAll("0+0", "0");
            if (!r.equals(s)) {
                s = r;
                continue;
            }
            r = s.replaceAll("0+1", "1");
            if (!r.equals(s)) {
                s = r;
                continue;
            }
            r = s.replaceAll("1+0", "1");
            if (!r.equals(s)) {
                s = r;
                continue;
            }
            r = s.replaceAll("1+1", "1");
            if (!r.equals(s)) {
                s = r;
                continue;
            }

        }
        while (!last.equals(s));

        if (s.equals("0")) return '0';
        if (s.equals("1")) return '1';
        return 'E';
    }

    public char testJNElement(jndictionary dict) {
        String toCalc = "";
        String delims = "()-&+";
        String consts = "01";
        StringTokenizer t = new StringTokenizer(start, delims, true);
        while (t.hasMoreTokens()) {
            String token = t.nextToken();
            //System.err.println("Token = "+token);
            if ((delims.indexOf(token) == -1) && (consts.indexOf(token) == -1)) {
                token = dict.getValue(token);
            }
            toCalc = toCalc + token;
        }
        return calc(toCalc);
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        start = start.replaceAll(" ", "");// удалили пробелы
        //System.err.println(start);
        this.start = start;
    }

    public jnaction getFlag() {
        return flag;
    }

    public void setFlag(jnaction flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "jnelement{" +
                "start='" + start +
                '}';
    }
}
