public class SilverDistance {
    public int minSteps(int sx, int sy, int gx, int gy) {
        int dx = gx - sx;
        int dy = gy - sy;
        if (dy >= dx && dy >= -dx) {
            System.out.println("*");
            return Math.abs(dy);
        }
        if (dy >= dx || dy >= -dx) {
            System.out.println("**");
            int res = Math.abs(dx);
            if (dy%2!=0) {
                res++;
            }
            return res;
        }
        System.out.println("***");
        return sub(Math.abs(dx), Math.abs(dy));
    }

    private int sub(int a, int b) {
        int d = b-a;
        if (d%2==0) {
            return b;
        }
        return b+2;
    }

    public static void main(String[] args) {
        SilverDistance t = new SilverDistance();
        /*int res = t.minSteps(-487617,
                826524,
                892309,
                -918045);
        System.out.println(res);*/
        int res = t.minSteps(0,
                0,
//                1379926,
//                -1744569);
                -4,
               3);
        System.out.println(res);
    }
}
