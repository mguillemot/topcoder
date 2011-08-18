import java.util.HashMap;
import java.util.HashSet;

public class ToolsBox {
    public int countTools(String[] need) {
        HashSet<String> tools = new HashSet<String>();
        for (String n : need) {
            String[] t = n.split(" ");
            for (String s : t) {
                tools.add(s);
            }
        }
        return tools.size();
    }

    public static void main(String[] args) {
        ToolsBox t = new ToolsBox();
        int r = t.countTools(new String[]{"SAW SAW", "SAW"});
        System.out.println(r);
    }
}
