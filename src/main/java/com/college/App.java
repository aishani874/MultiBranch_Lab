package com.college;

public class App {
    public static String getBranchMessage(String branch) {
        return "Running build pipeline on branch: " + branch;
    }

    public static void main(String[] args) {
        System.out.println(getBranchMessage("production"));
        System.out.println("Added for branch edit");
    }
}