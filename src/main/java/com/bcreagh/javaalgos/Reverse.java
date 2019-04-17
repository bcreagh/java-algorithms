package com.bcreagh.javaalgos;

public class Reverse {

    public static String reverse(String input) {
        char[] output = new char[input.length()];
        char[] inputChars = input.toCharArray();
        for (int i = 0; i < inputChars.length; i++) {
            output[output.length - (i + 1)] = inputChars[i];
        }
        return new String(output);
    }


    public static void main(String[] args) {
        String input = "Reverse me!";
        System.out.println("The input string is: ");
        System.out.println(input);
        System.out.println("The output string is: ");
        System.out.println(reverse(input));
    }
}
