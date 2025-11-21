package com.expensetracker;

import com.expensetracker.service.ExpenseService;
import com.expensetracker.model.Transaction;
import com.expensetracker.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ExpenseService service = new ExpenseService();
        Scanner sc = new Scanner(System.in);
        System.out.println("Expense Tracker (Hibernate + H2) - simple CLI demo");
        boolean running = true;
        while (running) {
            System.out.println("\nChoose: 1-add, 2-list, 3-find, 4-delete, 0-exit");
            int c = Integer.parseInt(sc.nextLine());
            switch (c) {
                case 1 -> {
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Amount: ");
                    Double amt = Double.parseDouble(sc.nextLine());
                    System.out.print("Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(sc.nextLine());
                    Transaction t = service.addTransaction(desc, amt, date);
                    System.out.println("Saved: " + t);
                }
                case 2 -> {
                    List<Transaction> all = service.listAll();
                    all.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Id: ");
                    Long id = Long.parseLong(sc.nextLine());
                    Transaction t = service.findById(id);
                    System.out.println(t);
                }
                case 4 -> {
                    System.out.print("Id to delete: ");
                    Long id = Long.parseLong(sc.nextLine());
                    service.delete(id);
                    System.out.println("Deleted if existed.");
                }
                case 0 -> running = false;
                default -> System.out.println("Unknown option");
            }
        }
        HibernateUtil.shutdown();
        sc.close();
        System.out.println("Exiting.");
    }
}
