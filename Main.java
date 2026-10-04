import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    private static final String FILE_NAME = "expenses.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();

        // Load previously saved expenses when the program starts.
        loadExpenses(expenses);

        boolean running = true;

        while (running) {
            displayMenu();

            int choice = getMenuChoice(scanner);

            switch (choice) {
                case 1:
                    addExpense(scanner, expenses);
                    break;

                case 2:
                    viewExpenses(expenses);
                    break;

                case 3:
                    displayTotal(expenses);
                    break;

                case 4:
                    saveExpenses(expenses);
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Please choose an option from 1 to 4.");
            }

            System.out.println();
        }

        scanner.close();
    }

    // Displays the main menu.
    public static void displayMenu() {
        System.out.println("=== Expense Tracker ===");
        System.out.println("1. Add expense");
        System.out.println("2. View expenses");
        System.out.println("3. View total");
        System.out.println("4. Exit");
    }

    // Gets a valid integer for the menu without crashing on invalid input.
    public static int getMenuChoice(Scanner scanner) {
        System.out.print("Choose an option: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a number.");
            scanner.nextLine();
            System.out.print("Choose an option: ");
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        return choice;
    }

    // Creates a new Expense object from user input and adds it to the list.
    public static void addExpense(
            Scanner scanner,
            ArrayList<Expense> expenses) {

        System.out.print("Enter expense name: ");
        String name = scanner.nextLine().trim();

        while (name.isEmpty()) {
            System.out.println("Expense name cannot be empty.");
            System.out.print("Enter expense name: ");
            name = scanner.nextLine().trim();
        }

        double amount = getValidAmount(scanner);

        System.out.print("Enter category: ");
        String category = scanner.nextLine().trim();

        while (category.isEmpty()) {
            System.out.println("Category cannot be empty.");
            System.out.print("Enter category: ");
            category = scanner.nextLine().trim();
        }

        Expense expense = new Expense(name, amount, category);
        expenses.add(expense);

        System.out.println("Expense added successfully.");
    }

    // Gets a positive number from the user for the expense amount.
    public static double getValidAmount(Scanner scanner) {
        while (true) {
            System.out.print("Enter amount: ");

            if (scanner.hasNextDouble()) {
                double amount = scanner.nextDouble();
                scanner.nextLine();

                if (amount > 0) {
                    return amount;
                }

                System.out.println("Amount must be greater than 0.");
            } else {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    // Displays all expenses currently stored in the ArrayList.
    public static void viewExpenses(ArrayList<Expense> expenses) {
        System.out.println("=== Expenses ===");

        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        int number = 1;

        for (Expense expense : expenses) {
            System.out.printf(
                "%d. %s - %s - $%.2f%n",
                number,
                expense.name,
                expense.category,
                expense.amount
            );

            number++;
        }
    }

    // Calculates the total amount of all expenses.
    public static double calculateTotal(ArrayList<Expense> expenses) {
        double total = 0;

        for (Expense expense : expenses) {
            total += expense.amount;
        }

        return total;
    }

    // Displays the total using standard currency formatting.
    public static void displayTotal(ArrayList<Expense> expenses) {
        double total = calculateTotal(expenses);
        System.out.printf("Total Expenses: $%.2f%n", total);
    }

    // Saves all expenses to a text file before the program exits.
    public static void saveExpenses(ArrayList<Expense> expenses) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);

            for (Expense expense : expenses) {
                writer.write(
                    expense.name + "," +
                    expense.category + "," +
                    expense.amount +
                    System.lineSeparator()
                );
            }

            writer.close();
            System.out.println("Expenses saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving expenses.");
        }
    }

    // Loads previously saved expenses from the text file.
    public static void loadExpenses(ArrayList<Expense> expenses) {
        try {
            File file = new File(FILE_NAME);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length == 3) {
                    try {
                        String name = parts[0];
                        String category = parts[1];
                        double amount = Double.parseDouble(parts[2]);

                        Expense expense =
                            new Expense(name, amount, category);

                        expenses.add(expense);

                    } catch (NumberFormatException e) {
                        System.out.println(
                            "Skipped an invalid saved expense."
                        );
                    }
                }
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(
                "No saved expenses found. Starting a new expense list."
            );
        }
    }
}