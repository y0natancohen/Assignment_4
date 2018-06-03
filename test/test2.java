package test;

public class test2 {
    public static void main(String[] args) {
        boolean number = isNumber(("012"));
        System.out.println(number);
    }
    private static boolean isNumber(String input) {
        String validInput = "0123456789";
        for (char c : input.toCharArray()) {
            if (!validInput.contains(String.valueOf(c))) return false;
        }
        return true;
    }
}
