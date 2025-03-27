import java.util.*;

public class pboproject3 {
    public static void main(String[] args) {
        String[] words = {"saya", "ingin", "lulus", "saya", "akan", "berusaha"};
        Set<String> uniques = new HashSet<String>();

        Set<String> dups = new HashSet<String>();

        for (String a : words)
        if (!uniques.add(a))
        dups.add(a);

        //destructive set-difference
        uniques.removeAll(dups);
        System.out.println("Unique words : " + uniques);
        System.err.println("Duplicate words : " + dups);
    }
}