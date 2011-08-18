import java.util.*;

public class ProductTriplet {
    /*private ArrayList<Integer> primes = new ArrayList<Integer>();
    private HashMap<Integer,Integer> decomposition = new HashMap<Integer, Integer>();

    public long countTriplets(int minx, int maxx, int miny, int maxy, int minz, int maxz) {
        boolean[] prime = new boolean[32000];
        for (int i = 0; i < prime.length; i++) {
            prime[i] = true;
        }
        prime[0] = false;
        prime[1] = false;
        for (int i = 2; i < prime.length; i++) {
            int c = 2;
            while (c*i<prime.length) {
                prime[c*i] = false;
                c++;
            }
        }
        for (int i = 2; i < prime.length; i++) {
            if (prime[i]) {
                primes.add(i);
            }
        }
        int z = minz;
        decompose(z);
        return 0;
    }

    private void decompose(int z) {
        System.out.println("z="+z);
        decomposition.clear();
        while (z > 1) {
            for (Integer p : primes) {
                while (z % p == 0) {
                    int c = decomposition.containsKey(p) ? decomposition.get(p) : 0;
                    decomposition.put(p, c + 1);
                    z /= p;
                }
            }
        }
        for (Integer p : decomposition.keySet()) {
            System.out.println("   p="+p + " c="+decomposition.get(p));
        }
    }*/

    public long countTriplets(int minx, int maxx, int miny, int maxy, int minz, int maxz) {
        long count = 0;
        /*for (int x = minx; x <= maxx; x++) {
            int y1 = (int) Math.ceil((double)minz/x);
            int y2 = (int) Math.floor((double)maxz/x);
            int delta = Math.min(maxy, y2) - Math.max(miny, y1) + 1;
//            System.out.println("x="+x);
//            System.out.println("   y1="+y1+" y2="+y2);
//            System.out.println("   min="+Math.min(miny, y1)+" max="+Math.max(maxy, y2)+" delta="+delta);
            if (delta > 0) {
                count += delta;
            }
        }*/
        count = estim(minx,maxx,miny,maxy,maxz) - estim(minx,maxx,miny,maxy,minz-1);
        return count;
    }

    private long estim(int minx, int maxx, int miny, int maxy, int alpha) {
        double aire = alpha*(Math.log(maxx) - Math.log(minx));
        int dy = maxy-miny-1;
        aire -= (minx-1)*dy; 
        return (long)Math.floor(aire);
    }

    public static void main(String[] args) {
        final long start = System.currentTimeMillis();
        ProductTriplet t = new ProductTriplet();
//        long res = t.countTriplets(1,1000000000,1,1000000000,1,1000000000);
        long res = t.countTriplets(8176,184561,1348,43168,45814517,957843164);
        System.out.println(res);
        System.out.println("took "+(System.currentTimeMillis()-start));
    }
}
