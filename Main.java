import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine().trim();
        if (isValid(input)) print(input);
        else System.out.println("Invalid input");
    }

    private static boolean isValid(String s) {
        int brackets = 0;
        if (s.isEmpty()) return false;
        if (s.length() == 1 && (s.charAt(0) == '[' ||
                s.charAt(0) == ']' || isDigit(s.charAt(0)))) return false;

        char first = s.charAt(0);
        char second = s.charAt(1);
        if (isDigit(first) && !(isDigit(second) || second == '[')) return false;
        if (first == '[' || first == ']') return false;

        for (int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            char prev = s.charAt(i - 1);
            if (cur == '[') {
                brackets++;
                if (!isDigit(prev)) return false;
            } else if (cur == ']') {
                brackets--;
                if (brackets < 0) return false;
            } else if (isDigit(first) && !(isDigit(second) || second == '[')) return false;
            else {
                if (isDigit(prev)) return false;
            }
        }

        return brackets == 0;
    }

    private static void print(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isDigit(s.charAt(i))) {
                int numStart = i;
                do { i++; } while (isDigit(s.charAt(i)));
                int number = Integer.parseInt(s.substring(numStart, i));
                i++;
                int strStart = i;
                int brackets = 0;
                while (s.charAt(i) != ']' || (s.charAt(i) == ']' && brackets != 0)) {
                    if (s.charAt(i) == '[') brackets++;
                    else if (s.charAt(i) == ']') brackets--;
                    i++;
                }
                String substr = s.substring(strStart, i);
                for (int j = 0; j < number; j++) print(substr);
            } else System.out.print(s.charAt(i));
        }
    }

    private static boolean isDigit(char c) {
        return c <= '9' && c >= '0';
    }
}