package preprocess;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanUp {

    // 提取文本中的英文单词
    public static String chararctorClean(String input) {
        StringBuilder sb = new StringBuilder();

        Pattern pattern = Pattern.compile("[a-z|A-Z]+");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            //trim()的作用是去掉字符串两端的多余的空格
            sb.append(matcher.group().trim());
            sb.append(" ");
        }
        return sb.toString();
    }

    // 取长度大于等于三的字符串
    public static String lengthFilter(String input, int len) {
        StringBuilder sb = new StringBuilder();

        String words[] = input.split(" ");

        for (String word : words) {
            if (word.length() >= len) {
                sb.append(word);
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    // 把单词转化成小写
    public static String tolowerCase(String input) {
        StringBuilder sb = new StringBuilder();

        String words[] = input.split(" ");

        for (String word : words) {
            sb.append(word.toLowerCase());
            sb.append(" ");
        }
        return sb.toString();
    }
}
