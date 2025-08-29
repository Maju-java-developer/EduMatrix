package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class StringUtils
 *
 * @author Majid.Hussain
 * @Date 6/19/2023
 */
public class StringUtils {


    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isObjectNull(Object object) {
        return object == null;
    }

    public static boolean isObjectNotNull(Object object) {
        return !isObjectNull(object);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    public static boolean startsWith(String str, String prefix) {
        return str != null && prefix != null && str.startsWith(prefix);
    }

    public static boolean endsWith(String str, String suffix) {
        return str != null && suffix != null && str.endsWith(suffix);
    }

    public static int indexOf(String str, String substring) {
        return str != null && substring != null ? str.indexOf(substring) : -1;
    }

    public static String concatenate(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            if (str != null) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String toUpperCase(String str) {
        return str != null ? str.toUpperCase() : null;
    }

    public static String toLowerCase(String str) {
        return str != null ? str.toLowerCase() : null;
    }

    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static int countOccurrences(String str, char target) {
        if (str == null) {
            return 0;
        }
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == target) {
                count++;
            }
        }
        return count;
    }

    public static String appendLongs(List<Long> numbers) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            sb.append(numbers.get(i));
            if (i < numbers.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static boolean isNumeric(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Utility method for breaking any string in two lines
     *
     * @param str             (String)
     * @param breakLineNumber (Integer)
     * @return Map(String, String) suffix, prefix
     * @author Majid.Hussain
     * @since 28-12-2023
     * right side
     */

    // PASS ARGU OF BOOLEAN
    // ISNEWliNEbREAK
    public static Map<String, String> lineBreak(String str, Integer breakLineNumber, Integer spaceBeforeTxt) {
        Map<String, String> data = new HashMap<>();
        StringBuilder fullString = new StringBuilder();
        int line = 1;
        int colWords = 0;
        boolean isLineBreak = false;
        // Majid Husssain Qutriyo
        // String[] = {'Majid', 'Hussain', 'Qutriyo'};

        String[] words = str.split(" ");
        for (String word : words) {
            // colWords = 0 , word = majid, 5 + 1 = 6 < 12,  12 * 1
            if ((colWords + word.length()) + 1 <= (breakLineNumber * line)) {
                fullString.append(word).append(" ");
            } else {
                isLineBreak = true;
                line = line + 1;
                //fullString.append(System.lineSeparator()).append(word).append(" ");
                fullString.append(System.lineSeparator()).append(" ".repeat(spaceBeforeTxt)).append(word).append(" ");

            }
            colWords += word.length();
        }
        // data.put("prefix", fullString.toString());
        if (isLineBreak) {
            data.put("prefix", fullString.toString());
        } else {
            data.put("prefix", fullString.toString());
        }
        data.put("suffix", "");
        return data;
    }

    public static Map<Integer, String> lineBreakForBank(String str, Integer breakLineNumber) {
        Map<Integer, String> data = new HashMap<>();
        StringBuilder prefix = new StringBuilder();
        StringBuilder fullString = new StringBuilder();
        int line = 1;
        int colWords = 0;
        String[] words = str.split(" ");
        for (String word : words) {
            if ((colWords + word.length()) + 1 <= (breakLineNumber * line)) {
                prefix.append(word).append(" ");
            } else {
                line = line + 1;
                fullString.append(word).append(" ");
            }
            colWords += word.length();
        }
        data.put(1, prefix.toString());
        data.put(2, fullString.toString());
        return data;
    }

    /*
    this method use for some fields in left side just
     */
    public static Map<String, String> lineBreak(String str, Integer breakLineNumber) {
        Map<String, String> data = new HashMap<>();

        StringBuilder suffex = new StringBuilder();
        StringBuilder prefix = new StringBuilder();
        String[] words = str.split(" ");
        int WordCount = 0;
        for (String word : words) {
            if ((WordCount + word.length()) <= breakLineNumber) {
                WordCount += word.length();
                if (WordCount >= breakLineNumber) {
                    suffex.append(word);
                } else {
                    suffex.append(word + " ");
                    WordCount += 1;
                }
            } else {
                prefix.append(word + " ");

            }
        }
        data.put("suffix", suffex.toString());
        data.put("prefix", prefix.toString());
        return data;
    }


    /**
     * Utility method for checking if object is null then show empty value
     *
     * @param obj (Object)
     * @return Object if exist otherwise Empty string will be return
     * @author Majid.HussainMajid.Hussain & Amir Hamza
     * @since 28-12-2023
     */
    public static Object isNull(Object obj) {
        return (obj == null || obj.equals("")) ? "" : obj;
    }

    public static void generateFile(String str, String filePath) {

        FileWriter fileWriter;

        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //todo just for practrice
    public static void generateFileWithTryCatch(String str, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(str);
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception appropriately based on your requirements
        }
    }

    /**
     *
     * @param str
     * @param defaultValue
     * @return String
     * @description if str is not Empty or null or not contains only whitespaces then returns Str
     *             else str is null or empty or contains only whitespaces return default value
     */
    public static String defaultIfBlank(String str, String defaultValue) {
        return isEmpty(str.trim()) ? defaultValue : str;
    }

}

