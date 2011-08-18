import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ColoringRectangle {
    private ArrayList<Integer> m_red = new ArrayList<Integer>();
    private ArrayList<Integer> m_blue = new ArrayList<Integer>();
    private double m_width;
    private double m_demiHeight;

    public int chooseDisks(int width, int height, int[] red, int[] blue) {
        m_width = width;
        m_demiHeight = height/2.0;
        int redFirst = chooseDisks2(red, blue);
        System.out.println("*****************************redFirst="+redFirst);
        int blueFirst = chooseDisks2(blue, red);
        System.out.println("*****************************blueFirst="+blueFirst);
        if (redFirst == -1) {
            return blueFirst;
        }
        if (blueFirst == -1) {
            return redFirst;
        }
        return Math.min(redFirst, blueFirst);
    }

    private int chooseDisks2(int[] red, int[] blue) {
        m_red.clear();
        for (int i : red) {
            if (i >= 2*m_demiHeight) {
                m_red.add(i);
            }
        }
        m_blue.clear();
        for (int i : blue) {
            if (i >= 2*m_demiHeight) {
                m_blue.add(i);
            }
        }
        Collections.sort(m_red);
        Collections.reverse(m_red);
        Collections.sort(m_blue);
        Collections.reverse(m_blue);

        double progression = 0;
        boolean isred = true;
        int iteration = 0;
        while (progression < m_width) {
            System.out.println("iteration " + iteration + " red?=" + isred + " progression=" + progression);
            System.out.println(m_red);
            System.out.println(m_blue);
            double r;
            if (isred) {
                if (m_red.isEmpty()) {
                    return -1;
                }
                r = m_red.get(0);
                m_red.remove(0);
                System.out.println("get red " + r);
            } else {
                if (m_blue.isEmpty()) {
                    return -1;
                }
                r = m_blue.get(0);
                m_blue.remove(0);
                System.out.println("get blue " + r);
            }
            double d = Math.sqrt((r/2)*(r/2)-m_demiHeight*m_demiHeight);
            progression += 2*d;
            System.out.println("final progression is " + progression);
            isred = !isred;
            iteration++;
        }
        return iteration;
    }

    public static void main(String[] args) {
        ColoringRectangle t = new ColoringRectangle();
//        int res = t.chooseDisks(11,3,new int[]{5,5}, new int[]{2,5});
//        int res = t.chooseDisks(30,5,new int[]{4,10,7,8,10}, new int[]{5,6,11,7,5});
        int res = t.chooseDisks(10,5,new int[]{10,10}, new int[]{4,3});
//        int res = t.chooseDisks(11,3,new int[]{5,5}, new int[]{2,5});
        System.out.println(res);
    }
}
