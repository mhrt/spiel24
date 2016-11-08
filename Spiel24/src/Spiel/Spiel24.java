package Spiel;

import java.util.Random;
import java.util.Scanner;

/**
  *
  * 24 (Spiel)
  *
  * @version 1.01 vom 08.11.2016
  * @author Michael Hartmann
  */
  
public class Spiel24 {
  // Configuration start
  private static int howManyNumbers = 4; // minimum 2, default 4
  private static int sumToReach = 24; // value is arbitrary, originally 24 is used
  // Configuration end
  private static int[] randomNumbers;
  private static char[] operators;

  public static void main(String[] args) {
    randomNumbers = createRandomNumbers(howManyNumbers);
    printInfoScreen();
    promptPlayer();
    checkPlayerInput();
    } // end of main
  
  // Create an integer array containing random numbers (1-9)
  static int[] createRandomNumbers(int quantity) {
    Random r = new Random();
    int[] numbers = new int[quantity];
    for (int i = 0; i < quantity; i++)
        numbers[i] = r.nextInt(9) + 1;
    return numbers;
  } // end of createRandomNumbers
  
  static void printInfoScreen() {
    System.out.println(sumToReach);
    System.out.println("==\n");
    System.out.println("Nachfolgend werden dir mehrere Ziffern gezeigt. ");
    System.out.println("Deine Aufgabe ist es, die Operatoren +, -, * oder / ");
    System.out.println("so zwischen die Ziffern zu setzen, dass das Ergebnis ");
    System.out.println(sumToReach + " ergibt.");
    System.out.println("Achtung: Punkt-vor-Strich-Rechnung gilt hier nicht!");
    System.out.println("Viel Erfolg!\n");
  } // end of printInformationScreen
  
  // Present numbers and ask for operators
  static void promptPlayer() {
    // Present numbers
    System.out.print("Deine Ziffern: ");
    for (int i = 0; i < howManyNumbers ; i++) {
      System.out.print(randomNumbers[i] + " ");
    } 
    System.out.println();
    operators = new char[howManyNumbers-1];
    // Aks for operators
    for (int i = 0; i < howManyNumbers-1; i++) {
      System.out.print("Operator " + (i+1) + ": ");
      operators[i] = getOperator();
    } 
  } // end of promptPlayer
  
  // Read a single operator (+,-,*,/) from console
  static char getOperator() {
    Scanner getInput = new Scanner(System.in);
    boolean invalidInput = true;
    char operator = '+'; // Just to make sure it's not empty; will be overwritten
    do {  
      String input = getInput.nextLine();
      // String of length 1 can be used as a char
      if (input.length() == 1) {
        operator = input.charAt(0);
        // Check if input is a valid operator
        if (operator == '+' || operator == '-' || operator == '*' || operator == '/')  {
          invalidInput = false;
        } 
      }
    } while (invalidInput);
    return operator;
  } // end of getOperator
  
  static void checkPlayerInput() {
    System.out.println();
    // Present numbers with player's operators
    System.out.print("Deine Eingabe: ");
    for (int i = 0; i < howManyNumbers; i++) {
      System.out.print(randomNumbers[i] + " ");
      // Print operator after each number except for the last number
      if (i < howManyNumbers-1) {
        System.out.print(operators[i] + " ");
      } 
    } 
    // Check sum based on player's input
    float calculatedSum = calculateSum();
    System.out.print("= " + calculatedSum);
    if (calculatedSum == sumToReach) {
      System.out.print("   Super, das passt!");
    }
    else {
      System.out.print("   Schade, das passt nicht.");
    }
    System.out.println();
  } // end of checkPlayerInput
  
  // Calculates the sum based on player's input
  static float calculateSum() {
    float result = 0;
    for (int i = 0; i < howManyNumbers-1; i++) {
      // first operator:
      if (i == 0) {
        result = applyOperator(randomNumbers[i], randomNumbers[i+1], operators[i]);  
      } 
      // next operators:
      else {
        result = applyOperator((int)result, randomNumbers[i+1], operators[i]);  
      } 
    } 
    return result;
  } // end of calculateSum
  
  // Apply operator to two integers and return result
  static float applyOperator(int a, int b, char operator) {
    switch (operator) {
      case '+': 
        return a + b;
      case '-': 
        return a - b;
      case '*':
        return a * b;
      case '/':
        return a / b;
      default: 
        return 0;
    } 
  } // end of applyOperator
  
}