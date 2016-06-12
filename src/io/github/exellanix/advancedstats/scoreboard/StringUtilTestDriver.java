package io.github.exellanix.advancedstats.scoreboard;

/**
 * Created by Mac on 5/18/2016.
 */
public class StringUtilTestDriver {
    public static void main(String[] args) {
        //String message = "§9" + "§l" + "Hello everyone! Welcome to " + "§a" + "Minecon!     ";
        //String message = "§9§lKRD: §f§l2";
        //String message = "§2§lBalance: §f§l999780.0";
        String message = "§a§lKills: §f§l2";
        int width = 0;
        //loop(message, width);
        singleCase(message, width);
        //int minWidth = StringUtils.calaculateMinWidth(message, width);
        //loop(message, minWidth);
    }

    public static void loop(String message, int width) {
        for (int i = 0; i < StringUtils.getLengthNoCharCodes(message); i++) {
            String prefix;
            String suffix;
            String[] array = StringUtils.splitCycleNoWrap(i, width, message);
            prefix = array[0];
            suffix = array[1];
            System.out.println("Using " + i + " as the start position.");
            System.out.println("Prefix: " + prefix);
            System.out.println("Suffix: " + suffix);
            System.out.println(StringUtils.getLengthNoCharCodes(prefix + suffix) + "");
            System.out.println("");
        }
    }

    public static void singleCase(String message,int caseNum) {
        String prefix;
        String suffix;
        int width = 16;
        String[] array = StringUtils.splitCycleNoWrap(caseNum, width, message);
        prefix = array[0];
        suffix = array[1];
        System.out.println("Using " + caseNum + " as the start position.");
        System.out.println("Prefix: " + prefix);
        System.out.println("Suffix: " + suffix);
        System.out.println("");

    }
}
