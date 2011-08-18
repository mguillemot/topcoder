import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Erhune
 * Date: 28 oct. 2009
 * Time: 21:20:23
 */
class Clas implements Comparable<Clas> {
    private static final Pattern CLASS_PATTERN = Pattern.compile("([A-Z]+)([0-9]{3})");
    private static final HashMap<String, Clas> allClasses = new HashMap<String, Clas>();

    public final String department;
    public final int number;
    public final ArrayList<Clas> prereq = new ArrayList<Clas>();
    public boolean taken = false;

    public static Clas build(String totalRepr) {
        String[] parts = totalRepr.split("[ :]+");
        Clas root = find(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            Clas dep = find(parts[i]);
            root.prereq.add(dep);
        }
        return root;
    }

    public static Clas find(String repr) {
        Clas c = allClasses.get(repr);
        if (c == null) {
            c = new Clas(repr);
            allClasses.put(repr, c);
        }
        return c;
    }

    private Clas(String repr) {
        Matcher m = CLASS_PATTERN.matcher(repr);
        m.find();
        department = m.group(1);
        number = Integer.parseInt(m.group(2));
    }

    public boolean reqFulfilled() {
        for (Clas req : prereq) {
            if (!req.taken) {
                return false;
            }
        }
        return true;
    }

    public int compareTo(Clas o) {
        int delta = number - o.number;
        if (delta != 0) {
            return delta;
        }
        return department.compareTo(o.department);
    }

    @Override
    public String toString() {
        return department + number;
    }
}

public class Prerequisites {
    public String[] orderClasses(String[] params) {
        try {
            ArrayList<Clas> workingSet = new ArrayList<Clas>();
            for (String param : params) {
                workingSet.add(Clas.build(param));
            }
            ArrayList<String> result = new ArrayList<String>();
            while (!workingSet.isEmpty()) {
                ArrayList<Clas> possibleClasses = new ArrayList<Clas>();
                for (Clas clas : workingSet) {
                    if (clas.reqFulfilled()) {
                        possibleClasses.add(clas);
                    }
                }
                Collections.sort(possibleClasses);
                Clas clas = possibleClasses.get(0);
                clas.taken = true;
                result.add(clas.toString());
                workingSet.remove(clas);
            }
            return result.toArray(new String[result.size()]);
        } catch (Exception e) {
            return new String[0];
        }
    }

    public static void main(String[] args) {
        String[] test = new String[]{
                "CSE258: CSE244 CSE243 INTR100",
                "CSE221: CSE254 INTR100",
                "CSE254: CSE111 MATH210 INTR100",
                "CSE244: CSE243 MATH210 INTR100",
                "MATH210: INTR100",
                "CSE101: INTR100",
                "CSE111: INTR100",
                "ECE201: CSE111 INTR100",
                "ECE111: INTR100",
                "CSE243: CSE254",
                "INTR100:"
        };
        test = (new Prerequisites()).orderClasses(test);
        for (String s : test) {
            System.out.println(s);
        }
    }
}
