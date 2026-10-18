package com.pluralsight;

/*
 * Rental Car Calculator
 * Workbook 1c – Rental Car Company exercise
 *
 * Plan:
 * - Prompt the user for rental details
 * - Calculate the basic rental, options cost, underage surcharge and total
 * - Display a cost summary
 *
 * Inputs (6):
 *   pickup date ........ String
 *   number of days ..... int
 *   toll tag ........... boolean (yes/no)
 *   GPS ................ boolean (yes/no)
 *   roadside assist .... boolean (yes/no)
 *   age ................ int
 *
 * Outputs (4):
 *   basic car rental
 *   options cost
 *   underage driver surcharge
 *   total cost
 *
 * Prices:
 *   basic rental $29.99/day | toll tag $3.95/day
 *   GPS $2.95/day           | roadside $3.95/day
 *   under 25: 30% surcharge on basic rental
 */

import java.util.Scanner;

public class RentalCarCalculatorApp {
    public static void main(String[] args) {

        // One scanner to pass to methods
        Scanner scanner = new Scanner(System.in);

        // --- Inputs ----
        // Get pickup date (String method)
        // Get number of days (int method)
        // Get toll tag answer (yes/no method)
        // Get GPS answer (yes/no method)
        // Get roadside assistance answer (yes/no method)
        // Get age (int method)

        // ---- Calculations ---
        // Call basicRental
        // Call optionsCost
        // Call surcharge
        // Call totalCost


        // --- Output ----
        // Display cost summary printf , $%.2f


    }

    // ======== Input methods ==========
    // 1. String input method
    public static String askString(Scanner scanner, String question) {
        // Show question to the user
        System.out.print(question);
        // Read what they typed and send it back
        return scanner.nextLine();
    }

    // 2. int input method
    public static int askInt(Scanner scanner,String question) {
        // Show question to the user
        System.out.print(question);
        // Read what they typed and send it back
        int number = scanner.nextInt();
        scanner.nextLine(); // eat line
        return  number;
    }

    // 3. Yes/No input method
    public static boolean askYesNo(Scanner scanner, String question) {
        // Show question to the user
        System.out.print(question);
        String output = scanner.nextLine();

        // Converting YES/NO to bool
        if (output.equalsIgnoreCase("Yes")) {
            return true;
        } else if (output.equalsIgnoreCase("No")) {
            return false;
        } else {
            System.out.println("Please type Yes or No.");
            return askYesNo(scanner,question);  // Ask again in case of error
        }
    }


    // ======== Calculation methods ====
    // 1. basicRental method
    public static double basicRental(int days) {
        double pricePerDay = 29.99;
        double totalBasicRental = pricePerDay * days;
        return totalBasicRental;
    }


    // 2. optionsCost method
    public static double optionsCost(boolean toll, boolean gps, boolean roadside, int days) {
        double priceTollTagPerDay = 3.95;
        double priceGpsPerDay = 2.95;
        double priceRoadSidePerDay = 3.95;

        double dailyOptionsCost = 0;
        if (toll) {
            dailyOptionsCost += priceTollTagPerDay;
        }
        if (gps) {
            dailyOptionsCost += priceGpsPerDay;
        }
        if (roadside) {
            dailyOptionsCost += priceRoadSidePerDay;
        }

        double totalOptionsCost = dailyOptionsCost * days;
        return totalOptionsCost;
    }


    // 3. surcharge method
    public static double surcharge(int age , double basicRental) {
        double totalSurchargeCost = 0 ;
        if (age < 25){
            totalSurchargeCost = basicRental * 0.3;
        }
        return totalSurchargeCost;
    }


    // 4. totalCost method
    public static double totalCost(double basicRental , double optionsCost, double surcharge) {
        double totalCostAmount = basicRental + optionsCost + surcharge;
        return  totalCostAmount;
    }





}
