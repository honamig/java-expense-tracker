import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();
        
        loadExpenses(expenses);

        boolean running = true;

        while (running) {
            displayMenu();

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addExpense(scanner, expenses);
                    break;

                case 2:
                    viewExpenses(expenses);
                    break;

                case 3: 
                    double total = calculateTotal(expenses);
                    //% = 値, .2 = 小数点以下２桁, f = float, %n = 改行
                    System.out.printf("Total: $%.2f%n", total);
                    break;

                case 4:
                    saveExpenses(expenses);
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        }

            scanner.close();
    
    }

    // Displays the main menu options
    public static void displayMenu() {
        System.out.println("=== Expense Tracker ===");
        System.out.println("1. Add expense");
        System.out.println("2. View expense");
        System.out.println("3. View total");
        System.out.println("4. Exit");
    }
   
    public static void addExpense(Scanner scanner, ArrayList<Expense> expenses) {
        scanner.nextLine();

        System.out.print("Enter expense name: ");
        String name = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        Expense expense = new Expense(name, amount, category);
        expenses.add(expense);

        System.out.println("Expense added successfully.");
    }

    public static void viewExpenses(ArrayList<Expense> expenses) {
        System.out.println("=== Expenses ===");

        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            for (Expense expense : expenses) {
            System.out.printf(
                // %s = string
                "%s - %s - $%.2f%n",
                expense.name,
                expense.category,
                expense.amount
            );
        }
     }
    
    }

    public static double calculateTotal(ArrayList<Expense> expenses) {
        double total = 0;

        for (Expense expense : expenses) {
            total += expense.amount;
        }

        return total;
    }

    public static void saveExpenses(ArrayList<Expense> expenses) {
    try {
        FileWriter writer = new FileWriter("expenses.txt");

        for (Expense expense : expenses) {
            writer.write(
                expense.name + "," +
                expense.category + "," +
                expense.amount + "\n"
            );
        }

        writer.close();
        System.out.println("Expenses saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving expenses.");
        }
    }

    public static void loadExpenses(ArrayList<Expense> expenses) {
        try {
            File file = new File("expenses.txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                String name = parts[0];
                String category = parts[1];
                double amount = Double.parseDouble(parts[2]);

                Expense expense = new Expense(name, amount, category);
                expenses.add(expense);
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("No saved expenses found.");
        }
    }
}
