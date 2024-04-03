package com.csc320.module_8;

import java.util.ArrayList;
import java.util.List;

public class AutomobileInventory {
    /* This list will hold the inventory of the program */
    private static final List<Automobile> inventory = new ArrayList<Automobile>();

    public static void main(String[] args) {
        /* Create some default automobiles */
        Automobile defaultAuto = new Automobile();
        Automobile customAuto = new Automobile("Toyota", "Tacoma", 1999, "Silver", 80_000);

        /* Fill the inventor with default automobiles */
        if (addAutomobile(defaultAuto) || addAutomobile(customAuto)) {
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
    private static boolean addAutomobile(Automobile auto) {
        /* Store the result to return later and check for errors */
        boolean result = inventory.add(new Automobile(auto));

        if (result) {
            System.out.print("Successfully added automobile to inventory.");
            auto.printDetails();
        } else {
            System.out.println("Failed to add automobile to inventory.");
            auto.printDetails();
        }

        return result;
    }

    private static void printAvailableCommands() {
        System.out.println("Available Commands:");
        System.out.println("add:     Begin adding a new automobile to the inventory.");
        System.out.println("help:    Display this help message.");
        System.out.println("list:    Display a numbered list of automobiles in the inventory.");
        System.out.println("quit:    Quit the program.");
        System.out.println("remove:  Begin removing an automobile from the inventory.");
        System.out.println("save:    Save the contents of the inventory to the file 'Autos.txt'" +
                                    "in the current directory.");
    }

    private static void printPrompt() {
        System.out.print("> ");
    }

    private static void printGreeting() {
        System.out.println("Hello! This program will help you manage your automobile inventory.");
        System.out.println("Please see the list of available commands below to get started!");
        printAvailableCommands();
        printPrompt();
    }

    private static void runInventoryLoop() {
        boolean running = true;
        printGreeting();
    }
}
