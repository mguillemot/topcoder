import java.util.ArrayList;

public class LotteryCheating {
    public int minimalChange(String ID) {
        long n = Long.parseLong(ID);
        if (n == 0 || check(n)) {
            return 0;
        }

        int mindist = 100;
        for (long i = 0; i < 99999; i++) {
            String s = ""+(i*i);
            int d = distance(ID, s);
            if (d < mindist) {
                System.out.println("d=" + d + " for s=" + s);
                mindist = d;
            }
        }
        return mindist;
    }

    public boolean check(long n) {
        double s = Math.sqrt(n);
        return (s == Math.floor(s));
    }

    public int distance(String a, String b) {
        if (a.length() < b.length()) {
            return distance(b, a);
        }
        String bb = "";
        for (int i = 0; i < a.length()-b.length();i++) {
            bb += "0";
        }
        bb += b;
        b = bb;

        int d = 0;
        for (int i = 0; i < a.length(); i++) {
            char ca = a.charAt(a.length() - 1 - i);
            char cb = b.charAt(b.length() - 1 - i);
            if (ca != cb) {
                d++;
            }
        }
        return d;
    }

    public static void main(String[] args) {
        LotteryCheating t = new LotteryCheating();
        System.out.println(t.minimalChange("49637"));
    }
}
