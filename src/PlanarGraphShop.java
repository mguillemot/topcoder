import java.lang.reflect.Array;
import java.util.*;

public class PlanarGraphShop {
    public int bestCount(int N) {
        HashSet<Integer> possibilities = new HashSet<Integer>();
        possibilities.add(1);
        possibilities.add(8);
        possibilities.add(9);
        for (int x = 3; x <= 223; x++) {
            for (int y = 0; y <= (3 * x - 6); y++) {
                int cost = x * x * x + y * y;
                if (cost <= 50000) {
                    possibilities.add(cost);
                }
            }
        }
        ArrayList<Integer> poss = new ArrayList<Integer>(possibilities.size());
        poss.addAll(possibilities);
        Collections.sort(poss);
        int pcount = poss.size();
//        System.out.println(poss);

        int[] count = new int[50001];
        count[0] = 0;
        count[1] = 1;
        int c = 0;
        for (int i = 2; i < count.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int p = 0; p < pcount; p++) {
                int pp = poss.get(p);
                if (pp > i) {
                    break;
                }
                c++;
                int val = count[i - pp] + 1;
                if (val < min) {
                    min = val;
                }
            }
            count[i] = min;
        }
        System.out.println("c="+c);

//        for (int i = 0; i < 40; i++) {
//            int i1 = count[i];
//            System.out.println((i+1) + "=" + i1);
//        }
        return count[N];
    }

    public static void main(String[] args) {
        PlanarGraphShop t = new PlanarGraphShop();
        long start = System.currentTimeMillis();
        int res = t.bestCount(42);
        System.out.println("res="+res);
        long total = System.currentTimeMillis() - start;
        System.out.println("took " + total);
    }
}
