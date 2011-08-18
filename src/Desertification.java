public class Desertification {
    private char get(int i, int j) {
        if (i < 0 || i >= II || j < 0 || j >= JJ) {
            return '-';
        }
        return _island[i][j];
    }

    public int desertArea(String[] island, int T) {
        II = island.length;
        JJ = island[0].length();
        _island = new char[II][JJ];
        for (int i = 0; i < II; i++) {
            for (int j = 0; j < JJ; j++) {
                _island[i][j] = island[i].charAt(j);
            }
        }

        for (int t = 0; t < T; t++) {
            for (int i = 0; i < II; i++) {
                for (int j = 0; j < JJ; j++) {
                    if (_island[i][j] == 'F' && (get(i - 1, j) == 'D' || get(i + 1, j) == 'D' || get(i, j - 1) == 'D' || get(i, j + 1) == 'D')) {
                        _island[i][j] = 'd';
                    }
                }
            }
            for (int i = 0; i < II; i++) {
                for (int j = 0; j < JJ; j++) {
                    if (_island[i][j] == 'd') {
                        _island[i][j] = 'D';
                    }
                }
            }
            int c = count();
            if (c == 0 || c == II*JJ) {
                return c;
            }
        }


        return count();
    }

    private int count() {
        int count = 0;
        for (int i = 0; i < II; i++) {
            for (int j = 0; j < JJ; j++) {
                if (_island[i][j] == 'D') {
                    count++;
                }
            }
        }
        return count;
    }

    public void dump() {
        for (int i = 0; i < II; i++) {
            for (int j = 0; j < JJ; j++) {
                System.out.print(_island[i][j]);
            }
            System.out.println();
        }
    }

    private char[][] _island;
    private int II;
    private int JJ;

    public static void main(String[] args) {
        Desertification t = new Desertification();
        int res = t.desertArea(new String[]{"FFF",
                "FDF",
                "FFF"}, 1);
        t.dump();
        System.out.println(res);
    }
}
