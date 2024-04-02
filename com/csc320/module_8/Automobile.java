package com.csc320.module_8;

import java.time.Year;

public class Automobile {
    private String make = "Hyundai";
    private String model = "Sonata";
    private int year = 2006;
    private String color = "Black";
    private int mileage = 102_000;

    /* Need current year for validation */
    public static final int CURRENT_YEAR = Year.now().getValue();
    public static final int VALID_YEAR = CURRENT_YEAR + 1;

    /* Default constructor */
    public Automobile() { /* Members already initialized */ }

    /* Checking for invalid years */
    private boolean yearIsLow(int year) { return year < 0; }
    private boolean yearIsHigh(int year) { return year > VALID_YEAR; }

    /* Checking for invalid mileage values */
    private void checkMileage(int mileage) {
        if (mileage < 0) throw new IllegalArgumentException("Mileage cannot be negative.");
    }

    /* Parameterized constructor */
    public Automobile(String make, String model, int year, String color, int mileage) {
        /* Checking for invalid year.
         * Could make this check some arbatrary year like 1888
         * since anything earlier is equally as invalid as -1
         */
        if (yearIsLow(year)) {
            throw new IllegalArgumentException("Year cannot be negative.");
        }
        if (yearIsHigh(year)) {
            throw new IllegalArgumentException("Year cannot be newer than " + VALID_YEAR);
        }
        checkMileage(mileage);

        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
    }

    /* Getters */
    public String make() { return this.make; }
    public String model() { return this.model; }
    public int year() { return this.year; }
    public String color() { return this.color; }
    public int mileage() { return this.mileage; }

    /* Setters */
    public void setMake(String make) { this.make = make; }
    public void setModel(String model) { this.model = model; }
    public void setColor(String color) { this.color = color; }

    public void setMileage(int mileage) {
        checkMileage(mileage);
        this.mileage = mileage;
    }

    public void setYear(int year) {
        if (yearIsLow(year)) {
            throw new IllegalArgumentException("Year cannot be negative.");
        }
        if (yearIsHigh(year)) {
            throw new IllegalArgumentException("Year cannot be newer than " + VALID_YEAR);
        }
        this.year = year;
    }
}
