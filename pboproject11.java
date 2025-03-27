import java.util.*;

public class pboproject11 {
    public static void main(String[] args) {
        PriorityQueue<String> stringQueue = new PriorityQueue<String>();
        stringQueue.add("ab");
        stringQueue.add("abcd");
        stringQueue.add("abc");
        stringQueue.add("a");

        while(stringQueue.size() > 0) {
            System.out.println(stringQueue.remove());
        }
    }
}