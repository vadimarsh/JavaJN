/*
 * jndictionary.java
 *
 * Created on 5 Май 2010 г., 16:51
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javajn;

import java.util.Vector;

/**
 * Словарь для вычисления стартовых функций
 *
 * @author Arsh
 */
public interface jndictionary {

    void clear();

    Vector<String> getNames();

    String getValue(String variable);

    void setValue(String variable, String val);

    jndictionary getCopy();

    boolean canMoreStep();

    void setCanMoreStep(boolean flag);

    void visitElement(jnelement el);

    void stepFinish();

    void setEqualizer(jnequalizer eq);

    void ShowAllElements();
}
