import java.util.ArrayList;
import java.util.Collections;

public class Inequalities {
    private ArrayList<Integer> greater = new ArrayList<Integer>();
    private ArrayList<Integer> greaterEqual = new ArrayList<Integer>();
    private ArrayList<Integer> lesser = new ArrayList<Integer>();
    private ArrayList<Integer> lesserEqual = new ArrayList<Integer>();
    private ArrayList<Integer> equal = new ArrayList<Integer>();
    private ArrayList<Integer> potentialValues = new ArrayList<Integer>();

    public int maximumSubset(String[] inequalities) {
        for (String inequality : inequalities) {
            final String[] p = inequality.split(" ");
            int c = Integer.parseInt(p[2]);
            if ("=".equals(p[1])) {
                equal.add(c);
            } else if (">".equals(p[1])) {
                greater.add(c);
            } else if (">=".equals(p[1])) {
                greaterEqual.add(c);
            } else if ("<".equals(p[1])) {
                lesser.add(c);
            } else {
                lesserEqual.add(c);
            }
            potentialValues.add(c);
        }
        Collections.sort(potentialValues);
        ArrayList<Float> newPotentialValues = new ArrayList<Float>();
        for (Integer value : potentialValues) {
            newPotentialValues.add(0f+value);
        }
        for (int i = 0; i < potentialValues.size() - 1; i++) {
            newPotentialValues.add((potentialValues.get(i)+potentialValues.get(i+1))/2f);
        }
        newPotentialValues.add(potentialValues.get(0)-.5f);
        newPotentialValues.add(potentialValues.get(potentialValues.size()-1)+.5f);
        System.out.println("potentiel="+potentialValues);
        int max = 0;
        for (float value : newPotentialValues) {
            int count = 0;
            for (int g : greater) {
                if (value > g) {
                    count++;
                }
            }
            for (int g : greaterEqual) {
                if (value >= g) {
                    count++;
                }
            }
            for (int g : lesser) {
                if (value < g) {
                    count++;
                }
            }
            for (int g : lesserEqual) {
                if (value <= g) {
                    count++;
                }
            }
            for (int g : equal) {
                if (value == g) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
                System.out.println("max " + max + " for "+value);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Inequalities t = new Inequalities();
        System.out.println(t.maximumSubset(new String[]{
                "X < 0","X <= 0"
        }));
    }
}
