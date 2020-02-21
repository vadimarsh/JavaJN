/*
 * Main.java
 *
 * Created on 5 May 2010 y., 16:50
 *
 */

package javajn;

/**
 * @author Arsh
 */
public class Main {

    /**
     * Creates a new instance of Main
     */
    public Main() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        jntask task = new jntask();
        task.addElement("a&-b&-c", "a=0,b=1,c=0;a=0,b=0,c=1;a=0,b=1,c=1"); //e1
        task.addElement("b&-d", "b=0,d=1"); //e2
        task.addElement("c&-e", "c=0,e=1"); //e3
        task.addElement("d&-f", "d=0,f=1"); //e4
        task.addElement("e&-k", "e=0,k=1"); //e5

        task.setValue("a", "1");
        task.setValue("b", "0");
        task.setValue("c", "0");
        task.setValue("d", "0");
        task.setValue("e", "0");
        task.setValue("f", "0");
        task.setValue("k", "0");


        task.solve(1000, 1000);

        task.ShowAllSolution();

        for (int iv = 0; iv < task.getSolutions().size(); iv++) {

            System.err.println(
                    ((jnsimpledict) task.getSolutions().get(iv)).getVisitedElements()
            );
        }


        System.err.println("one1");
        System.err.println(
                ((jnsimpledict) task.getSolutions().get(0)).getVisitedElements()
        );

        System.err.println("two2");
        System.err.println(
                ((jnsimpledict) task.getSolutions().get(1)).getVisitedElements()
        );

        System.err.println("three3");
        System.err.println(
                ((jnsimpledict) task.getSolutions().get(2)).getVisitedElements()
        );


        jnsimpledict myDict = new jnsimpledict();
        jnbranch bra = new jnbranch(myDict);
        bra.addjnelement("a", "a=0,b=1;a=0,c=1;a=0,b=1,c=1"); // OR
        bra.addjnelement("b", "b=0,bb=1");
        bra.addjnelement("c", "c=0,cc=1");

        myDict.setValue("a", "1");

        jnsolution sol = new jnsolution();
        sol.startSolution(bra, 10, 10);

        for (int i = 0; i < sol.getSolution().size(); i++) {
            System.err.println("\n\n--- " + i + " ---");
            sol.getSolution().get(i).ShowAllElements();
        }

    }

}
