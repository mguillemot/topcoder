import java.util.ArrayList;
import java.util.HashSet;

/**
 * User: Erhune
 * Date: 28 oct. 2009
 * Time: 22:37:00
 */
public class SquareDigits {
    private int s(int x) {
        int result = 0;
        while (x > 0) {
            int d = x % 10;
            result += d*d;
            x /= 10;
        }
        return result;
    }

    private HashSet<Integer> t(int x) {
        HashSet<Integer> result = new HashSet<Integer>();
        x = s(x);
        while (!result.contains(x)) {
            result.add(x);
            x = s(x);
        }
        return result;

    }

    public int smallestResult(int n) {
        int i = 0;
        while (true) {
            if (t(i).contains(n)) {
                return i;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        SquareDigits test = new SquareDigits();
        System.out.println(test.smallestResult(0)); // 0
        System.out.println(test.smallestResult(2)); // 11 
        System.out.println(test.smallestResult(10)); // 7
        System.out.println(test.smallestResult(1)); // 1
        System.out.println(test.smallestResult(19)); // 133
        System.out.println(test.smallestResult(85)); // 5
        System.out.println(test.smallestResult(112)); // 2666
    }
}
