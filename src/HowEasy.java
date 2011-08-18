import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HowEasy {
	public int pointVal(String text) {
		if (text == null || text.length() == 0) {
			return 250;
		}
		String[] tokens = text.split("\\s");
		Pattern wordVerifier = Pattern.compile("^[a-zA-Z]+\\.?$");
		int totalLen = 0;
		int matchCount = 0;
		for (String token : tokens) {
			Matcher matcher = wordVerifier.matcher(token);
			if (matcher.matches()) {
//                System.out.println(token);
				totalLen += token.length();
				if (token.endsWith(".")) {
					totalLen--;
				}
                matchCount++;
			}
		}
		int avgLen = 0;
		if (matchCount > 0) {
			avgLen = totalLen / matchCount;
		}
		if (avgLen <= 3) {
			return 250;
		} else if (avgLen <= 5) {
			return 500;
		}
		return 1000;
	}

    public static void main(String[] args) {
        HowEasy t = new HowEasy();
        System.out.println(t.pointVal("aa.....aaaaaaxxxxax"));
    }
}