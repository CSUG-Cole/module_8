package com.csc320.module_8;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AutomobileInventory {
    /* This list will hold the inventory of the program */
    private static final List<Automobile> inventory = new ArrayList<Automobile>();

    public static void main(String[] args) {
        /* Create some default automobiles */
        Automobile defaultAuto = new Automobile();
        Automobile customAuto = new Automobile("Toyota", "Tacoma", 1999, "Silver", 80_000);

        /* Fill the inventor with default automobiles */
        if (!inventory.add(defaultAuto) || ! inventory.add(customAuto)) {
            System.out.println("ERROR: Failed to initialize AutomobileInventory. Exiting.");
            System.exit(-1); /* -1 to indicate an error with the program */
        }

        try {
            runInventoryLoop();
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Program encountered a critical error: " + e.getMessage());
            System.out.println("Exiting.");
            System.exit(-1); /* -1 to indicate an error with the program */
        }
    }

    /* Method to add an automobile to the class inventory */
    private static void addAutomobile(Automobile auto) {
        /* Store the result to return later and check for errors */
        boolean result = inventory.add(new Automobile(auto));

        if (result) {
            System.out.println("Successfully added automobile to inventory.");
            auto.printDetails();
        } else {
            System.out.println("Failed to add automobile to inventory.");
            auto.printDetails();
        }
    }

    private static void printAvailableCommands() {
        System.out.println("Available Commands:");
        System.out.println("add:     Begin adding a new automobile to the inventory.");
        System.out.println("cancel:  You can call the cancel command to end an add or " +
                                    "remove operation.");
        System.out.println("help:    Display this help message.");
        System.out.println("list:    Display a numbered list of automobiles in the inventory.");
        System.out.println("quit:    Quit the program.");
        System.out.println("remove:  Begin removing an automobile from the inventory.");
        System.out.println("save:    Save the contents of the inventory to the file 'Autos.txt' " +
                                    "in the current directory.");
    }

    private static void printPrompt() {
        System.out.print("> ");
    }

    private static void printGreeting() {
        System.out.println("Hello! This program will help you manage your automobile inventory.");
        System.out.println("Please see the list of commands available below to get started!");
        printAvailableCommands();
        printPrompt();
    }

    private static String getResponse(Scanner scanner, String prompt) throws CancelException {
        System.out.print(prompt);
        try {
            String response = scanner.next();
            if (response.toLowerCase().equals("cancel")) {
                throw new CancelException();
            }
            return response;
        } catch (NoSuchElementException | IllegalStateException e) {
            /* NoSuchElementException - if no more tokens are available
             * IllegalStateException - if this scanner is closed
             */
            System.out.println(e.getMessage());
            System.out.println("ERROR: Failed to scan next token. Exiting.");
            System.exit(-1);
        }
        return "";
    }

    private static String getResponseString(Scanner scanner, String prompt) throws CancelException {
        return getResponse(scanner, prompt);
    }

    private static int getResponseInt(Scanner scanner, String prompt, String errorMsg)
        throws CancelException
    {
        String response = getResponse(scanner, prompt);
        try {
            return Integer.parseInt(response);
        } catch (NumberFormatException e) {
            System.out.println(errorMsg + String.format("'%s'.", response));
        }
        return getResponseInt(scanner, prompt, errorMsg);
    }

    private static void handleAdd(Scanner scanner) throws CancelException {
        System.out.println("Adding automobile to inventory:");
        String make = getResponseString(scanner, "Please enter the automobile make: ");
        String model = getResponseString(scanner, "Please enter the automobile model: ");
        int year = getResponseInt(
            scanner, "Please enter the automobile year: ", "Year must be an integer. Got "
        );
        String color = getResponseString(scanner, "Please enter the automobile color: ");
        int mileage = getResponseInt(
            scanner, "Please enter the automobile mileage: ", "Mileage must be an integer. Got "
        );
        Automobile auto = new Automobile(make, model, year, color, mileage);
        addAutomobile(auto);
    }

    private static void handleList() {
        System.out.println("Automobile inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(String.format("%03d", i) + ": " + inventory.get(i).toString());
        }
    }

    private static void handleRemove(Scanner scanner) throws CancelException {
        System.out.println("Handling inventory removal.");
    }

    private static void handleSave() {
        System.out.println("Handling inventory save.");
    }

    private static void handleCommand(String command, Scanner scanner) {
        try {
            switch (command.toLowerCase()) {
                case "add":
                    handleAdd(scanner);
                    break;
                case "cancel":
                    System.out.println(
                        "WARNING: The cancel command is only valid during an add or remove operation."
                    );
                    break;
                case "help":
                    printAvailableCommands();
                    break;
                case "list":
                    handleList();
                    break;
                case "quit":
                    /* handled in main event loop */
                    break;
                case "remove":
                    handleRemove(scanner);
                    break;
                case "save":
                    handleSave();
                    break;
                default:
                    System.out.println("WARNING: Got an invalid command: '" + command + "'");
                    printAvailableCommands();
                    break;
            }
        } catch (CancelException e) {
            System.out.println("Cancelling " + command.toLowerCase() + "...");
        }
    }

    private static void runInventoryLoop() {
        Scanner scanner = new Scanner(System.in);
        printGreeting();

        /* Main event loop to respond to user commands. */
        while (true) {
            String command = "";

            /* Try to get the next token. */
            try {
                command = scanner.next();
            } catch (NoSuchElementException | IllegalStateException e) {
                /* NoSuchElementException - if no more tokens are available
                 * IllegalStateException - if this scanner is closed
                 */
                 System.out.println(e.getMessage());
                 System.out.println("ERROR: Failed to scan next token. Exiting.");
                 System.exit(-1);
            }

            /* If user wants to quit handle that here. */
            if (command.toLowerCase().equals("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            handleCommand(command, scanner);
            printPrompt();
        }

        scanner.close();
    }
}
