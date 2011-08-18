public class AppleWord {
    public int minRep(String word) {
        if (word.length() <= 4) {
            return -1;
        }
        word = word.toLowerCase();
        int c = word.length();
        if (word.charAt(0) == 'a') {
            c--;
        }
        for (int i = 1; i < word.length()-2; i++) {
            if (word.charAt(i) == 'p') {
                c--;
            }
        }
        if (word.charAt(word.length()-2) == 'l') {
            c--;
        }
        if (word.charAt(word.length()-1) == 'e') {
            c--;
        }
        return c;
    }

    public static void main(String[] args) {
        AppleWord t = new AppleWord();
        int res = t.minRep("apppappipipple");
        System.out.println(res);
    }
}
