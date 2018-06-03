

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static String readFile(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static boolean isInt(String input) {
        String validInput = "0123456789";
        for (char c : input.toCharArray()) {
            if (!validInput.contains(String.valueOf(c))) return false;
        }
        return true;
    }

    public static String parseMessage(String rawMessage) {
        return "";
    }

    public static Boolean isAfter(String str1, String str2) {
        return (str1.compareTo(str2) > 0);
    }

    public static Boolean isAfterEqul(String str1, String str2) {
        return (str1.compareTo(str2) >= 0);
    }

    public static Boolean isBefore(String str1, String str2) {
        return (str1.compareTo(str2) < 0);
    }

    public static Boolean isBeforeEqual(String str1, String str2) {
        return (str1.compareTo(str2) <= 0);
    }

}
 

   