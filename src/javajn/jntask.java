/*
 * jntask.java
 *
 * Created on 18 Май 2010 г., 10:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javajn;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * Класс предназначен для постановки JN-задачи и ее решения
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
     * Создание задачи по умолчанию на основе словаря
     */
    public jntask() {
        this(jnsimpledict.class);
    }

    /**
     * Создание задачи по умолчанию на основе словаря
     *
     * @param c Класс словаря
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
     * Добавление JN-элемента
     *
     * @param el Новый элемент
     * @return Созданный элемент
     */
    public jnelement addElement(jnelement el) {
        return branch.addjnelement(el);
    }

    /**
     * Добавление JN-элемента на основе класса
     *
     * @param start Пусковая функция. Например "avbv-c&(dva&b)"
     * @param flag  Флаговая функция. Например "a=0,b=1;a=0,c=1;a=0,b=1,c=1"
     * @return Созданный элемент
     */
    public jnelement addElement(String start, String flag) {
        return branch.addjnelement(start, flag);
    }

    /**
     * Получение списка JN-элементов
     *
     * @return Список JN-элементов
     */
    public Vector<jnelement> getElements() {
        return branch.getElements();
    }

    /**
     * Установка значения переменной
     *
     * @param var Имя переменной
     * @param val Значение переменной
     */
    public void setValue(String var, String val) {
        vars.setProperty(var, val);
    }

    /**
     * Получение значения переменной
     *
     * @param var Имя переменной
     * @return Значение переменной
     */
    public String getValue(String var) {
        return vars.getProperty(var);
    }

    /**
     * Отождествление переменных
     *
     * @param var1 Переменная1
     * @param var2 Переменная2
     */
    public void addEqual(String var1, String var2) {
        jne.varsEqual(var1, var2);
    }

    /**
     * Очистка переменных
     */
    public void clearValue() {
        vars.clear();
    }

    /**
     * Удаление всех JN-элеменов
     */
    public void clearElements() {
        branch.clear();
    }

    /**
     * Удаление всех отождествлений
     */
    public void clearEquals() {
        jne.clear();
    }

    /**
     * Очистка решения
     */
    public void clearAll() {
        clearElements();
        clearEquals();
        clearValue();
    }

    /**
     * Возвращает имена всех перменных
     *
     * @return Имена переменных
     */
    public Vector<String> getValues() {
        return dict.getNames();
    }

    /**
     * Возвращает объект-решение
     *
     * @return Решение этой задачи
     */
    public jnsolution getSolution() {
        return sol;
    }

    /**
     * Возвращает отождествления
     *
     * @return Отождествления этой задачи
     */
    public jnequalizer getEquals() {
        return jne;
    }

    /**
     * Возвращает список решений
     *
     * @return Список решений
     */
    public Vector<jndictionary> getSolutions() {
        return sol.getSolution();
    }

    /**
     * Начало решения поставленной задачи
     *
     * @param maxsteps  Ограничение количетсва шагов
     * @param maxbranch Огреничение числа ветвлений
     */
    public void solve(int maxsteps, int maxbranch) {
        // Подготовка словаря
        dict.setEqualizer(null);
        dict.clear();
        dict.setEqualizer(jne);
        // Заполнене словаря с учетом Equals
        Enumeration e = vars.keys();
        while (e.hasMoreElements()) {
            String k = (String) e.nextElement();
            dict.setValue(k, vars.getProperty(k));
        }
        // Запуск решения
        sol.startSolution(branch, maxsteps, maxbranch);
    }

    /**
     * Показать все решения этой задачи
     */
    public void ShowAllSolution() {
        System.err.print("There are " + sol.getSolution().size() + " solutions");
        for (int i = 0; i < sol.getSolution().size(); i++) {
            System.err.println("\n\n--- " + i + " ---");
            sol.getSolution().get(i).ShowAllElements();
        }
    }
}
