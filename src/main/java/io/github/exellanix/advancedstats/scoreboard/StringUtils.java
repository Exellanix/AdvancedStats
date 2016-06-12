package io.github.exellanix.advancedstats.scoreboard;

import java.util.Arrays;

/**
 * Created by Mac on 5/18/2016.
 */
public class StringUtils {
    private static char[] colorCodes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static char[] alterCodes = {'k', 'l', 'm', 'n', 'o'};
    public static String nextCycle(int startPosition, String message) {
        return null;
    }

    public static String[] splitCycleWrap(int startPosition, int width, String message) {
        boolean shouldCycle = false;
        String first = "";
        String second = "";
        int index = findStart(startPosition, message, true);
        /*if (startPosition != 0 && message.charAt(startPosition - 1) == '§') {
            index = 1;
        }*/
        first += getColorCode(index, message);
        for (int i = index; first.length() < 16 && getLengthNoCharCodes(cleanString(first)) < width; i++) {
            index = i;
            first += message.charAt((i) % message.length());
        }
        index++;
        index = (index % message.length());
        index = findStart(index, message, false);
        /*if (index % message.length()!= 0 && message.charAt(index - 1) == '§') {
            index += 1;
        }*/
        second += getColorCode(index, message);
        for (int i = index; second.length() < 16 && getLengthNoCharCodes(cleanString(first) + cleanString(second)) < width; i++) {
            index = i;
            second += message.charAt((i) % message.length());
        }
        String[] array = new String[2];
        array[0] = cleanString(first);
        array[1] = cleanString(second);
        return array;
    }

    public static String[] splitCycleNoWrap(int startPosition, int width, String message) {
        boolean shouldCycle = false;
        String first = "";
        String second = "";
        int index = findStart(startPosition, message, true);
        first += getColorCode(index, message);
        for (int i = index; first.length() < 16 && getLengthNoCharCodes(cleanString(first)) < width && i < message.length(); i++) {
            index = i;
            first += message.charAt((i) % message.length());
        }
        index++;
        if (index < message.length()) {
            index = findStart(index, message, false);
            second += getColorCode(index, message);
            for (int i = index; second.length() < 16 && getLengthNoCharCodes(cleanString(first) + cleanString(second)) < width && i < message.length(); i++) {
                index = i;
                second += message.charAt((i) % message.length());
            }
        }
        String[] array = new String[2];
        array[0] = cleanString(first);
        array[1] = cleanString(second);
        return array;
    }

    private static String getColorCode(int startPosition, String message) {
        String colorCodes = "";
        int position = 0;
        boolean exit = false;
        for (int i = startPosition - 1; i >= 0 && !exit; i--) {
            char current = message.charAt(i);
            switch (current) {
                case '§':
                    if (message.charAt(i + 1) == 'r') {
                        return "";
                    } else if (containsChar(StringUtils.colorCodes, message.charAt(i + 1))) {
                        exit = true;
                        break;
                    }
                    break;
                default:
                    break;
            }
            position = i;
        }
        for (int i = position; i < startPosition; i++) {
            if (message.charAt(i) == '§') {
                colorCodes += message.charAt(i) + "" +  message.charAt(i + 1);
                i++;
            }
        }
        return colorCodes;
    }

    private static boolean containsChar(char[] array, char object) {
        for (char c : array) {
            if (c == object) {
                return true;
            }
        }
        return false;
    }

    private static int findStart(int currentStart, String message, boolean addEnd) {
        int tempStart = addEnd ? 0 : currentStart;
        int amount = currentStart;
        if (!addEnd && currentStart != 0 && message.charAt(tempStart - 1) == '§') {
            tempStart++;
        }
        boolean exit = false;
        for (int i = tempStart; tempStart < message.length() && !exit; i++) {
            tempStart = i;
            if (message.charAt(tempStart) == '§') {
                i++;
            } else {
                exit = true;
            }
        }
        if (addEnd) {
            while (amount > 0) {
                if (message.charAt((tempStart) % message.length()) == '§') {
                    tempStart++;
                } else {
                    amount--;
                }
                tempStart++;
            }
        }
        return tempStart % message.length();
    }

    private static String cleanString(String message) {
        String temp = message;
        if (temp.length() > 0 && temp.charAt(temp.length() - 1) == '§') {
            temp = temp.substring(0, temp.length() - 1);
        } else if (temp.length() > 1 && temp.charAt(temp.length() - 2) == '§') {
            temp = temp.substring(0, temp.length() - 2);
        }
        return temp;
    }

    public static int getLengthNoCharCodes(String message) {
        int size = 0;
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == '§') {
                i++;
            } else {
                size++;
            }
        }
        return size;
    }

    public static int calaculateMinWidth(String message, int width) {
        if (message.length() > 0) {
            int size = getLengthNoCharCodes(message);
            int[] sizeArray = new int[size];
            for (int i = 0; i < size; i++) {
                String[] split = splitCycleWrap(i, width, message);
                sizeArray[i] = getLengthNoCharCodes(split[0] + split[1]);
            }
            Arrays.sort(sizeArray);
            return sizeArray[0];
        } else {
            return -1;
        }
    }

    public static int cyclePad(String message) {
        return message.length() / 15;
    }
}
