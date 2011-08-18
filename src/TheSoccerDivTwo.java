import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TheSoccerDivTwo {
    private int[] m_points;
    private boolean[] m_fixed;

    private int firstNonfixed() {
        for (int i = 0; i < m_points.length; i++) {
            if (!m_fixed[i]) {
                return i;
            }
        }
        return -1;
    }

    private boolean complete() {
        for (boolean b : m_fixed) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public int find(int[] points) {
        m_points = points;
        m_fixed = new boolean[points.length];
        m_results = new ArrayList<Rank>(m_points.length);
        return solve();
    }

    private int solve() {
        int a = firstNonfixed();
        m_fixed[a] = true;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m_points.length; i++) {
            if (!m_fixed[i]) {
                m_fixed[i] = true;
                int res;
                // i wins
                m_points[i] += 3;
                res = result();
                if (res < min) {
                    min = res;
                }
                m_points[i] -= 3;
                // draw
                m_points[i] += 1;
                m_points[a] += 1;
                res = result();
                if (res < min) {
                    min = res;
                }
                m_points[i] -= 1;
                m_points[a] -= 1;
                // i loses
                m_points[a] += 3;
                res = result();
                if (res < min) {
                    min = res;
                }
                m_points[a] -= 3;
                //
                m_fixed[i] = false;
            }
        }
        m_fixed[a] = false;
        return min;
    }

    public static void main(String[] args) {
        TheSoccerDivTwo t = new TheSoccerDivTwo();
        long start = System.currentTimeMillis();
        int res = t.find(new int[]{2, 1, 3, 7, 6, 5, 3, 4, 2, 6, 5, 1});
        System.out.println(res);
        long dur = System.currentTimeMillis() - start;
        System.out.println("took " + dur);
    }

    private ArrayList<Rank> m_results;

    private int result() {
        if (!complete()) {
            return solve();
        }
        m_results.clear();
        for (int i = 0; i < m_points.length; i++) {
            m_results.add(new Rank(i, m_points[i]));
        }
        Collections.sort(m_results);
        for (int rank = 0; rank < m_points.length; rank++) {
            if (m_results.get(rank).i == 0) {
                return rank + 1;
            }
        }
        throw new RuntimeException("No result ?!?");
    }

    public static class Rank implements Comparable<Rank> {
        public int i;
        public int score;

        public Rank(int i, int score) {
            this.i = i;
            this.score = score;
        }

        public int compareTo(Rank o) {
            int ds = o.score - score;
            return (ds != 0) ? ds : (i - o.i);
        }
    }
}
