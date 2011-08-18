public class RecursiveFigures {
    public double getArea(int sideLength, int K) {
        double area = 0;
        double sl = sideLength;
        for (int i = 0; i < K; i++) {
            System.out.println("i="+i+" sl="+sl);
            double R = sl / 2;
            double sR = R * Math.sqrt(2);
            if (i != 0) {
                area -= sl * sl;
            }
            area += Math.PI * R * R;
            sl = sR;
        }
        return area;
    }

    public static void main(String[] args) {
        RecursiveFigures t = new RecursiveFigures();
        System.out.println(t.getArea(10, 10));
    }
}
