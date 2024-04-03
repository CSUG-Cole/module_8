package com.csc320.module_8;

import java.util.ArrayList;
import java.util.List;

public class AutomobileInventory {
    /* This list will hold the inventory of the program */
    private static final List<Automobile> inventory = new ArrayList<Automobile>();

    public static void main(String[] args) {
        /* Fill the inventory with some Automobiles */
        Automobile defaultAuto = new Automobile();
        Automobile customAuto = new Automobile("Toyota", "Tacoma", 1999, "Silver", 80_000);
        if (addAutomobile(defaultAuto) || addAutomobile(customAuto)) {
            System.out.println("ERROR: Failed to initialize AutomobileInventory. Exiting.");
        }
    }

    private static boolean addAutomobile(Automobile auto) {
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
}
