import java.util.*;

public class kuis2 {
    public static void main(String[] args) {
        List warna = new ArrayList();
        warna.add("Magenta");
        warna.add("Red");
        warna.add("White");
        warna.add("Blue");
        warna.add("Cyan");
        System.out.println("Warna : " + warna);

        List warnaDihapus = new ArrayList();
        warnaDihapus.add("Red");
        warnaDihapus.add("White");
        warnaDihapus.add("Blue");
        System.out.println("Warna yang dihapus : " + warnaDihapus);

        warna.removeAll(warnaDihapus);
        System.out.println("Warna : " + warna);
    }
}

