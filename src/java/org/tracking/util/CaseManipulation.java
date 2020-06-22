/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.util;

import java.util.Scanner;

/**
 *
 * @author root
 */
public class CaseManipulation {

   public  String toUpperCase(String inputString) {
       String result = "";
       for (int i = 0; i < inputString.length(); i++) {
           char currentChar = inputString.charAt(i);
           char currentCharToUpperCase = Character.toUpperCase(currentChar);
           result = result + currentCharToUpperCase;
       }
       return result;
   }

   public  String toLowerCase(String inputString) {
       String result = "";
       for (int i = 0; i < inputString.length(); i++) {
           char currentChar = inputString.charAt(i);
           char currentCharToLowerCase = Character.toLowerCase(currentChar);
           result = result + currentCharToLowerCase;
       }
       return result;
   }

   public  String toToggleCase(String inputString) {
       String result = "";
       for (int i = 0; i < inputString.length(); i++) {
           char currentChar = inputString.charAt(i);
           if (Character.isUpperCase(currentChar)) {
               char currentCharToLowerCase = Character.toLowerCase(currentChar);
               result = result + currentCharToLowerCase;
           } else {
               char currentCharToUpperCase = Character.toUpperCase(currentChar);
               result = result + currentCharToUpperCase;
           }
       }
       return result;
   }

   public  String toCamelCase(String inputString) {
       String result = "";
       if (inputString.length() == 0) {
           return result;
       }
       char firstChar = inputString.charAt(0);
       char firstCharToUpperCase = Character.toUpperCase(firstChar);
       result = result + firstCharToUpperCase;
       for (int i = 1; i < inputString.length(); i++) {
           char currentChar = inputString.charAt(i);
           char previousChar = inputString.charAt(i - 1);
           if (previousChar == ' ') {
               char currentCharToUpperCase = Character.toUpperCase(currentChar);
               result = result + currentCharToUpperCase;
           } else {
               char currentCharToLowerCase = Character.toLowerCase(currentChar);
               result = result + currentCharToLowerCase;
           }
       }
       return result;
   }
   /***
    * toSentenceCase
    * @param inputString
    * @return 
    */
   public  String toSentenceCase(String inputString) {
       String result = "";
       if (inputString.length() == 0) {
           return result;
       }
       char firstChar = inputString.charAt(0);
       char firstCharToUpperCase = Character.toUpperCase(firstChar);
       result = result + firstCharToUpperCase;
       boolean terminalCharacterEncountered = false;
       char[] terminalCharacters = {'.', '?', '!'};
       for (int i = 1; i < inputString.length(); i++) {
           char currentChar = inputString.charAt(i);
           if (terminalCharacterEncountered) {
               if (currentChar == ' ') {
                   result = result + currentChar;
               } else {
                   char currentCharToUpperCase = Character.toUpperCase(currentChar);
                   result = result + currentCharToUpperCase;
                   terminalCharacterEncountered = false;
               }
           } else {
               char currentCharToLowerCase = Character.toLowerCase(currentChar);
               result = result + currentCharToLowerCase;
           }
           for (int j = 0; j < terminalCharacters.length; j++) {
               if (currentChar == terminalCharacters[j]) {
                   terminalCharacterEncountered = true;
                   break;
               }
           }
       }
       return result;
   }
/**
 * 
 * @param args 
 
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Enter an input String: ");
       String inputString = scanner.nextLine();
       System.out.println("Upper Case: " + toUpperCase(inputString));
       System.out.println("Lower Case: " + toLowerCase(inputString));
       System.out.println("Toggle Case: " + toToggleCase(inputString));
       System.out.println("Camel Case: " + toCamelCase(inputString));
       System.out.println("Title Case: " + toSentenceCase(inputString));
   }
   * */
}
