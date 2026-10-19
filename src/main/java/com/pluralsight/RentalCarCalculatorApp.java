package com.pluralsight;

import java.util.Scanner;

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
 *   1. pickup date ........ String
 *   2. number of days ..... int
 *   3. toll tag ........... boolean (yes/no)
 *   4. GPS ................ boolean (yes/no)
 *   5. roadside assist .... boolean (yes/no)
 *   6. age ................ int
 *
 * Outputs (4):
 *   1. basic car rental
 *   2. options cost
 *   3. underage driver surcharge
 *   4. total cost
 *
 * Prices:
 *   basic rental $29.99/day | toll tag $3.95/day
 *   GPS $2.95/day           | roadside $3.95/day
 *   under 25: 30% surcharge on basic rental
 *
 * Structure:
 *   main                → inputs → calculations → output
 *   input methods       → ask a question, read the answer, return it
 *   calculation methods → take in only what they need, return a number
 */

public class RentalCarCalculatorApp {
    public static void main(String[] args) {

        // One Scanner for the whole app, passed into every input method
        Scanner scanner = new Scanner(System.in);

        System.out.println("** Welcome to Rental Car Calculator **\n");

        // ================== Inputs ==================
        String pickupDate = askString(scanner, "Enter pickup date (MM/DD/YYYY): ");
        int days = askInt(scanner, "How many days will you rent the car? ");
        boolean toll = askYesNo(scanner, "Would you like an electronic toll tag for $3.95/day? (yes/no): ");
        boolean gps = askYesNo(scanner, "Would you like a GPS for $2.95/day? (yes/no): ");
        boolean roadside = askYesNo(scanner, "Would you like roadside assistance for $3.95/day? (yes/no): ");
        int age = askInt(scanner, "What is your current age? ");

        // ================== Calculations ==================
        // The order matters here: surcharge uses basic rental and total uses all three results.
        double basicRentalCost = calculateBasicRental(days);
        double optionsCost = calculateOptionsCost(toll, gps, roadside, days);
        double surchargeCost = calculateSurcharge(age, basicRentalCost);
        double totalCost = calculateTotalCost(basicRentalCost, optionsCost, surchargeCost);

        // ================== Output ==================
        // Display cost summary, money formatted to 2 decimal places
        System.out.println();
        System.out.println("********** Cost Summary **********");
        System.out.println("Pickup date: " + pickupDate);
        System.out.printf("Basic car rental:   $%.2f%n", basicRentalCost);
        System.out.printf("Options cost:       $%.2f%n", optionsCost);
        System.out.printf("Underage surcharge: $%.2f%n", surchargeCost);
        System.out.printf("Total cost:         $%.2f%n", totalCost);
    }

    // ================== Input methods ==================

    /**
     * Asks a question and returns the user's full line of text.
     *
     * @param scanner  the shared Scanner
     * @param question the prompt to display
     * @return the text the user typed
     */
    public static String askString(Scanner scanner, String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Asks a question and returns the user's whole-number answer.
     *
     * @param scanner  the shared Scanner
     * @param question the prompt to display
     * @return the number the user typed
     */
    public static int askInt(Scanner scanner, String question) {
        System.out.print(question);
        int number = scanner.nextInt();
        scanner.nextLine(); // consume the leftover Enter so the next nextLine() isn't skipped
        return number;
    }

    /**
     * Asks a yes/no question and converts the answer to a boolean.
     * If the answer is not yes or no, it shows a message and asks again.
     *
     * @param scanner  the shared Scanner
     * @param question the prompt to display
     * @return true for "yes", false for "no"
     */
    public static boolean askYesNo(Scanner scanner, String question) {
        System.out.print(question);
        String answer = scanner.nextLine();

        // Convert yes/no to a boolean (any capitalization)
        if (answer.equalsIgnoreCase("Yes")) {
            return true;
        } else if (answer.equalsIgnoreCase("No")) {
            return false;
        } else {
            System.out.println("Please type Yes or No.");
            return askYesNo(scanner, question); // invalid answer: ask the same question again
        }
    }

    // ================== Calculation methods ==================

    /**
     * Calculates the basic rental cost: the daily rate times the number of days.
     *
     * @param days number of rental days
     * @return the basic rental cost
     */
    public static double calculateBasicRental(int days) {
        double pricePerDay = 29.99;
        double totalBasicRental = pricePerDay * days;
        return totalBasicRental;
    }

    /**
     * Calculates the total cost of the selected options.
     * Adds the daily price of each option the user said yes to,
     * then multiplies that daily total by the number of rental days.
     *
     * @param toll     true if the user wants a toll tag ($3.95/day)
     * @param gps      true if the user wants a GPS ($2.95/day)
     * @param roadside true if the user wants roadside assistance ($3.95/day)
     * @param days     number of rental days
     * @return the total options cost
     */
    public static double calculateOptionsCost(boolean toll, boolean gps, boolean roadside, int days) {
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

    /**
     * Calculates the underage driver surcharge.
     * Drivers under 25 pay an extra 30% of the basic rental.
     * Drivers 25 and older pay no surcharge.
     *
     * @param age             the driver's age
     * @param basicRentalCost the basic rental cost
     * @return the surcharge amount (0 if the driver is 25 or older)
     */
    public static double calculateSurcharge(int age, double basicRentalCost) {
        double totalSurchargeCost = 0;
        if (age < 25) {
            totalSurchargeCost = basicRentalCost * 0.3; // 30%
        }
        return totalSurchargeCost;
    }

    /**
     * Calculates the total cost by adding the three cost parts together.
     *
     * @param basicRentalCost the basic rental cost
     * @param optionsCost     the total options cost
     * @param surchargeCost   the underage surcharge
     * @return the total cost
     */
    public static double calculateTotalCost(double basicRentalCost, double optionsCost, double surchargeCost) {
        double totalCostAmount = basicRentalCost + optionsCost + surchargeCost;
        return totalCostAmount;
    }
}