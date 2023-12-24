package com.stc.filemanager.problemsolving;

import java.util.Stack;

public class ReverseParentheses {

    public static String reverseParentheses(String s) {
        Stack<Integer> openIndices = new Stack<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                openIndices.push(i);
            } else if (chars[i] == ')') {
                reverseSubstring(chars, openIndices.pop() + 1, i - 1);
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            if (c != '(' && c != ')') {
                result.append(c);
            }
        }

        return result.toString();
    }

    private static void reverseSubstring(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start++] = chars[end];
            chars[end--] = temp;
        }
    }

    public static void main(String[] args) {
        String[] testCases = {
            "abd(jnb)asdf",
            "abdjnbasdf",
            "dd(df)a(ghhh)"
        };

        for (String test : testCases) {
            System.out.println("Input: " + test);
            System.out.println("Output: " + reverseParentheses(test));
            System.out.println("------------------");
        }
    }
}
