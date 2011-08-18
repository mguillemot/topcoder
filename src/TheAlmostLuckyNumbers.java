import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TheAlmostLuckyNumbers {
    private long getIthLucky(int i) {
        long result = 0;
        long shift = 1;
        int higherBit = -1;
        for (int bit = 32; bit >= 0; bit--) {
            if ((i & (1L << bit)) != 0) {
                higherBit = bit;
                break;
            }
        }
        for (int bit = 0; bit < higherBit; bit++) {
            if ((i & (1 << bit)) != 0) {
                result += 7 * shift;
            } else {
                result += 4 * shift;
            }
            shift *= 10;
        }
        return result;
    }

    private ArrayList<Long> m_luckies = new ArrayList<Long>(2046);

    private long estim(long max) {
        long estim = 0;
        final int LS = m_luckies.size();
        for (int i = 0; i < LS; i++) {
            if (m_luckies.get(i) > max) {
                break;
            }
            double fact = max / m_luckies.get(i);
            Stack<Long> stack = new Stack<Long>();
            stack.push(m_luckies.get(i));
            System.out.println(stack + " + " + (long) Math.floor(fact));
            if (fact >= 1) {
                estim += (long) Math.floor(fact);
                for (int i1 = 0; i1 < i; i1++) {
                    double fact1 = fact / m_luckies.get(i1);
                    if (fact1 >= 1) {
                        stack.push(m_luckies.get(i1));
                        System.out.println(stack + " - " + (long) Math.floor(combinedPgcd(stack) * fact1) + " PGCD=" + combinedPgcd(stack) + " vraiPGCD=" + truePgcd(stack));
                        estim -= (long) Math.floor(combinedPgcd(stack) * fact1);
                        for (int i2 = 0; i2 < i1; i2++) {
                            double fact2 = fact1 / m_luckies.get(i2);
                            if (fact2 >= 1) {
                                stack.push(m_luckies.get(i2));
                                System.out.println(stack + " + " + (long) Math.floor(combinedPgcd(stack) * fact2) + " PGCD=" + combinedPgcd(stack) + " vraiPGCD=" + truePgcd(stack));//3
                                estim += (long) Math.floor(combinedPgcd(stack) * fact2);
                                for (int i3 = 0; i3 < i2; i3++) {
                                    double fact3 = fact2 / m_luckies.get(i3);
                                    if (fact3 >= 1) {
                                        stack.push(m_luckies.get(i3));
                                        System.out.println(stack + " - " + (long) Math.floor(combinedPgcd(stack) * fact3) + " PGCD=" + combinedPgcd(stack) + " vraiPGCD=" + truePgcd(stack));
                                        estim -= (long) Math.floor(combinedPgcd(stack) * fact3);
                                        for (int i4 = 0; i4 < i3; i4++) {
                                            double fact4 = fact3 / m_luckies.get(i4);
                                            if (fact4 >= 1) {
                                                stack.push(m_luckies.get(i4));
                                                System.out.println(stack + " + " + (long) Math.floor(combinedPgcd(stack) * fact4) + " PGCD=" + combinedPgcd(stack) + " vraiPGCD=" + truePgcd(stack));
                                                estim += (long) Math.floor(combinedPgcd(stack) * fact4);
                                                stack.pop();
                                            }
                                        }
                                        stack.pop();
                                    }
                                }
                                stack.pop();
                            }
                        }
                        stack.pop();
                    }
                }
            }


            /*int mterms = Math.min(i, 12);
            for (int k = 1; k < (1 << mterms); k++) {
                ArrayList<Long> indices = new ArrayList<Long>();
                indices.add(m_luckies.get(i));
                for (int kk = 0; kk < mterms; kk++) {
                    if ((k & (1 << kk)) != 0) {
                        fact /= m_luckies.get(kk);
                        indices.add(m_luckies.get(kk));
                    }
                }
                if (fact >= 1) {
                    long adjust = (long) Math.floor(fact * combinedPgcd(indices));
                    if (indices.size() % 2 != 0) {
                        estim += adjust;
                        System.out.println(indices + "    + " + adjust);
                    } else {
                        estim -= adjust;
                        System.out.println(indices + "    - " + adjust);
                    }
                }
            }*/
        }
        return estim;
    }

    public long count(long a, long b) {

        long[] luckies = new long[2046];
        boolean[] multiples = new boolean[2046];

        for (int i = 0; i < luckies.length; i++) {
            luckies[i] = getIthLucky(i + 2);
        }

        for (int i = 0; i < luckies.length; i++) {
            for (int j = i + 1; j < luckies.length; j++) {
                if (luckies[j] % luckies[i] == 0) {
                    multiples[j] = true;
                    //break;
                }
            }
        }
        int c = 0;
        for (int i = 0; i < luckies.length; i++) {
            if (multiples[i]) {
                c++;
            } else {
                m_luckies.add(luckies[i]);
            }
        }

//        System.out.println("bf=" + bruteForce(b));
//        System.out.println("********");
//        System.out.println("eb=" + estim(b));
//        return 0;
        long eb = estim(b);
        long ea = estim(a - 1);
        System.out.println("ea=" + ea);
        return eb - ea;
    }

    private static long pgcd(long a, long b) {
        if (b > a) {
            return pgcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        return pgcd(b, a % b);
    }

    private static long combinedPgcd(List<Long> numbers) {
        Long[] a = numbers.toArray(new Long[numbers.size()]);
        long result = 1;
        for (int i = 1; i < numbers.size(); i++) {
            for (int j = 0; j < i; j++) {
                long p = pgcd(a[i], a[j]);
                if (p != 1) {
                    result *= p;
                    a[i] /= p;
                }
            }
        }
        return result;
    }

     private static long truePgcd(List<Long> numbers) {
        int result = 1;
        for (int i = 1; i < numbers.size(); i++) {
            for (int j = 0; j < i; j++) {
                result *= pgcd(numbers.get(i), numbers.get(j));
            }
        }
        if (result%8==0) {
            result /= 2;
        }
        if (result%27==0) {
            result /= 3;
        }
        return result;
    }

    private long bruteForce(long max) {
        long c = 0;
        ArrayList<Long> cpt = new ArrayList<Long>();
        for (long i = 1; i <= max; i++) {
            if (i%747==0&&i%477==0&&i%474==0&&i%4==0) {
                cpt.add(i);
            }
            for (int k = 0; k < m_luckies.size(); k++) {
                if (i % m_luckies.get(k) == 0) {
                    c++;
                    break;
                }
                if (m_luckies.get(k) > i) {
                    break;
                }
            }
        }
        System.out.println(cpt.size() + "/cpt=" + cpt);
        return c;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        TheAlmostLuckyNumbers t = new TheAlmostLuckyNumbers();
        long res = t.count(123456789L,987654321L);
//         1,4321 => 1649
        // 123456789,987654321 => 330386840
        long total = System.currentTimeMillis() - start;
        System.out.println("res=" + res);
        System.out.println("took " + total);
    }
}
