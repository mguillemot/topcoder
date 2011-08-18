public class TheTournamentDivTwo {

    public int find(int[] points) {
        int sum = 0;
        for (int point : points) {
            sum += point;
        }
        if (sum % 2 != 0) {
            return -1;
        }
        return sum/2;
    }

    public static void main(String[] args) {
        System.out.println((new TheTournamentDivTwo()).find(new int[]{10, 1, 1})); //6
        System.out.println((new TheTournamentDivTwo()).find(new int[]{1, 1, 1}));      //-1
        System.out.println((new TheTournamentDivTwo()).find(new int[]{0, 0, 0, 0, 0, 0, 0})); //0
        System.out.println((new TheTournamentDivTwo()).find(new int[]{13,8,7})); //14
    }

}
