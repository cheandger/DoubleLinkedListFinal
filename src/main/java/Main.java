import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        String a = "Kowalski";
        String b ="Skipper";
        String c ="Rico_kaboom";
        String d ="Private";
        String e = "Sub-Zero";
        String f ="Scorpion";
        String g ="Kano";
        String h ="Jax";
        String j ="Chip";
        String k ="Dale";
        String l = "Monty";
        String m ="Gadget";
        String n ="Zipper";
        String o ="FatCat";


        DoubleLinkedList <String> doubleLinkedList = new DoubleLinkedList<>(3);
        doubleLinkedList.add(a);
        doubleLinkedList.add(b);
        doubleLinkedList.add(c);
        doubleLinkedList.add(d);
        doubleLinkedList.add(e);
        doubleLinkedList.add(f);
        doubleLinkedList.add(g);
        doubleLinkedList.add(h);
        doubleLinkedList.add(j);
        doubleLinkedList.add(k);
        doubleLinkedList.add(l);
        doubleLinkedList.add(m);
        doubleLinkedList.add(n);
        doubleLinkedList.add(o);


        DoubleLinkedList <String> doubleLinkedList1 = new DoubleLinkedList<>(2);
        doubleLinkedList1.add("Трус");
        doubleLinkedList1.add("Балбес");
        doubleLinkedList1.add("Бывалый");
        doubleLinkedList1.add("Шурик");
        doubleLinkedList1.add("Жора");
        doubleLinkedList1.add("Гоша");
        doubleLinkedList1.add("Гога");
        doubleLinkedList1.add("Георгий_Иваныч");
        doubleLinkedList1.add("Уэф");
        doubleLinkedList1.add("Би");
        doubleLinkedList1.add("Гедеван_Александрович");






        DoubleLinkedList<String> doubleLinkedList2 = new DoubleLinkedList<>(3);
        doubleLinkedList2.add(a);
        doubleLinkedList2.add(b);
        doubleLinkedList2.add(c);
        doubleLinkedList2.add(d);
        doubleLinkedList2.add(e);
        doubleLinkedList2.add(f);
        doubleLinkedList2.add(g);
        doubleLinkedList2.add(h);
        doubleLinkedList2.add(j);
        doubleLinkedList2.add(k);
        doubleLinkedList2.add(l);
        doubleLinkedList2.add(m);
        doubleLinkedList2.add(n);
        doubleLinkedList2.add(o);

        /*doubleLinkedList2.add(f);
        doubleLinkedList2.add(b);
        doubleLinkedList2.add(m);
        doubleLinkedList2.add(d);
        doubleLinkedList2.add(n);
        doubleLinkedList2.add(a);
        doubleLinkedList2.add(g);
        doubleLinkedList2.add(h);
        doubleLinkedList2.add(j);
        doubleLinkedList2.add(k);
        doubleLinkedList2.add(l);
        doubleLinkedList2.add(c);
        doubleLinkedList2.add(e);
        doubleLinkedList2.add(o);*/


        System.out.println("**********************");
       for(String str:doubleLinkedList){
       System.out.println(str);}
        System.out.println("**********************");


        LinkedList<String> nLl = new LinkedList<>();
        nLl.add(a);
        nLl.add(b);
        nLl.add(c);
        nLl.add(d);
        nLl.add(e);
        nLl.add(j);
        nLl.add(k);
        nLl.add(l);
        nLl.add(m);
        nLl.add(n);
        nLl.add(o);




       // doubleLinkedList.removeAll(nLl);
       //for (String str1:doubleLinkedList){System.out.println(str1);}
       // System.out.println("**********************");

        System.out.println(doubleLinkedList.equals(doubleLinkedList2));
        System.out.println("**********************");

        System.out.println(Arrays.deepToString(doubleLinkedList.toArray()));
        System.out.println("**********************");

        System.out.println(Arrays.deepToString(doubleLinkedList1.toArray()));
        System.out.println("**********************");

        System.out.println(Arrays.deepToString(doubleLinkedList2.toArray()));
        System.out.println("**********************");

        System.out.println( doubleLinkedList.contains("Kano"));
        System.out.println("**********************");

        doubleLinkedList.remove("Kano");



        System.out.println(doubleLinkedList.size());

        System.out.println("**********************");
        doubleLinkedList.addAll(doubleLinkedList1);
       for(String str2:doubleLinkedList){System.out.println(str2);}

        System.out.println("doubleLinkedList.containsAll(nLl)  " + doubleLinkedList.containsAll(nLl));

        System.out.println("**********************");


         System.out.println("результат вызова метода ( <String> String[] toArray(String[] a))" + Arrays.deepToString(doubleLinkedList.toArray()));
        System.out.println("**********************");

        Iterator <String> iterator = doubleLinkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("**********************");
        doubleLinkedList.clear();
        System.out.println(doubleLinkedList.size());


    }
}

