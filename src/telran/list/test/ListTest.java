package telran.list.test;

import org.junit.jupiter.api.Test;
import telran.list.interfaces.IList;
import telran.list.model.MyLinkedList;

import java.util.Iterator;

public class ListTest {

    @Test
    void test() {
        IList<Integer> list = new MyLinkedList<>();
        System.out.println(list.size());//0
        System.out.println(list.isEmpty());//true
        list.add(2);
        list.add(7);
        list.add(5);
        list.add(3);// 2 7 5 3
        System.out.println(list.size());//4
        System.out.println(list.isEmpty());//false
        list.add(7);
        list.add(4, null);//2 7 5 3 null 7
        System.out.println(list.size());//6
        System.out.println("=== get ===");
        System.out.println(list.get(2));//5
        System.out.println(list.get(4));//null
        try {
            System.out.println(list.get(6));
        } catch (Exception e) {
            System.out.println("o-o-ops");
        }
        System.out.println("=== indexOf ===");
        System.out.println(list.indexOf(7));//1
        System.out.println(list.indexOf(10));//-1
        System.out.println(list.indexOf(null));//4
        System.out.println(list.contains(5));//+
        System.out.println(list.contains(1));//-
        System.out.println(list.contains(null));//+
        System.out.println("=== lstIndexOf ===");
        System.out.println(list.lastIndexOf(7));//5
        System.out.println("===== iterator =====");
        for (Integer integer : list) {
            System.out.println(integer);//2 7 5 3 null 7
        }
        System.out.println("===== set =====");
        Integer num = list.set(4, 11);//2 7 5 3 11 7
        System.out.println(num);//null
        System.out.println(list.get(4));//11
        System.out.println("===== remove =====");
        num = list.remove(2); //2 7 3 11 7
        System.out.println(num);//5
        System.out.println(list.size());//5
        System.out.println(list.remove((Integer) 11));//2 7 3 7
        System.out.println(list.size());//4
        System.out.println("===== clear =====");
        list.clear();
        System.out.println(list.isEmpty());//+
        System.out.println("===== List of String =====");
        IList<String> myList = new MyLinkedList<>();
        myList.add("Boston");
        myList.add("Atlanta");
        myList.add("Chicago");
        myList.add("Boston");
        myList.add("New York");
        Iterator<String> iterator = myList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
