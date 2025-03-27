import java.util.*;

public class pboproject1 {
    public static void main(String[] args) {
        Set set = new HashSet(); // Hashset berguna seperti array tapi outputnya bakalan tetap sama seperti urutan di kodingan
        set.add("Sistem");
        set.add("Teknologi Informasi");
        set.add("Bisnis");
        set.add("Digital");
        set.add("Kewirausahaan");

        System.out.print("Elemen pada HashSet : ");
        System.out.println(set);

        Set sortSet = new TreeSet(set); // treeset mengurutkan berdasarkan abjad
        System.out.print("Elemen pada TreeSet : ");
        System.out.println(sortSet);
    }
}