package l2.poo3.Other;

public class StringUtil {

    public static String center(String text, int length){
        for (int i = 0; i < length - text.length(); i++) {
            text += " ";
        }
        for(int i = text.length(); i < length; i++){
            text = " " + text;
        }
        return text;
    }

    public static String right(String text, int length){
        int newLength = length - text.length();
        for(int i = 0; i < newLength; i++){
            text = " " + text;
        }
        return text;
    }

    public static String left(String text, int length){
        int newLength = length - text.length();
        for (int i = 0; i < newLength; i++) {
            text += " ";
        }
        return text;
    }

    public static String buildTablePattern(String pattern, int repeat){
        String t = "+";
        int length = repeat;
        if(length % 2 != 0){
            length++;
        }
        for(int i = 0; i < (length/2); i++){
            t += pattern;
        }
        return t;
    }

    public static String buildTableWithFirstLinePattern(String firstLine, String pattern, int repeat){
        String t = firstLine;
        for(int i = 0; i < repeat; i++){
            t += pattern;
        }
        return t;
    }
}
