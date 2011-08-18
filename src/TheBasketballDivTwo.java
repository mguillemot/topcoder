public class TheBasketballDivTwo {
    private char[][] m_res;
    private int[] m_wins;

    public int find(String[] table) {
        m_res = new char[table.length][table.length];
        m_wins = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                m_res[i][j] = table[i].charAt(j);
            }
        }
        return estimate();
    }

    private int estimate() {
        for (int i = 0; i < m_wins.length; i++) {
            m_wins[i] = 0;
        }
        for (int i = 0; i < m_wins.length; i++) {
            for (int j = 0; j < m_wins.length; j++) {
                char res = m_res[i][j];
                if (res == 'W') {
                    m_wins[i]++;
                } else if (res == '?') {
                    // win case
                    m_res[i][j] = 'W';
                    int win = estimate();
                    // lose case
                    m_res[i][j] = 'L';
                    int lose = estimate();
                    //
                    m_res[i][j] = '?';
                    return Math.min(win, lose);
                } else if (res == 'L') {
                    m_wins[j]++;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < m_wins.length; i++) {
            int win = m_wins[i];
            if (win > max) {
                max = win;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        TheBasketballDivTwo t = new TheBasketballDivTwo();
        long start = System.currentTimeMillis();
        int res = t.find(new String[]{
                "X????",
                "?X???",
                "??X??",
                "???X?",
                "????X"
        });
        System.out.println(res);
        long dur = System.currentTimeMillis() - start;
        System.out.println("took " + dur);
    }
}
