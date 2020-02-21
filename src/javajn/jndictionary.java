/*
 * jndictionary.java
 *
 * Created on 5 ��� 2010 �., 16:51
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javajn;

import java.util.Vector;

/**
 * ������� ��� ���������� ��������� �������
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
