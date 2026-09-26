import java.util.Scanner;
import java.util.ArrayList;

// public class Main {
//         public static void main(String[] args) {
//             Scanner scanner = new Scanner(System.in);

//             System.out.print("Enter expense name: ");
//             String expenseName = scanner.nextLine();

//             System.out.print("Enter amount: ");
//             double amount = scanner.nextDouble();

//             System.out.println();
//             System.out.println("Expense: " + expenseName);
//             System.out.println("Amount: $" + amount);
            
//             if (amount >= 100) {
//                 System.out.println("This is a large expense.");
//             } else {
//                 System.out.println("This is a regular expense.");
//             }

//             scanner.close();
//         }
// }

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();

        displayMenu();

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addExpense(scanner, expenses);
                break;
            
            case 2:
                System.out.println("View Expenses selected.");
                break;

            case 3:
                System.out.println("View Total selected.");
                break;

            case 4:
                System.out.println("Goodbye!");
                break;

            default:
                System.out.println("Invalid option.");
        }

        //displayExpense("Groceries", 42.50);

        // double total = calculateTotal(42.50, 15.25);
        // System.out.println("Total: $" + total);

        // double tax =calculateTax(100);
        // System.out.println("Tax: $" + tax);

        // Expense expense1 = new Expense("Groceries", 42.50, "Food");
        // Expense expense2 = new Expense("Gas", 35.00, "Transportation");

        // expenses.add(expense1);
        // expenses.add(expense2);

        // System.out.println(expense1.name + " - " + expense1.category + " - " + expense1.amount);
        // System.out.println(expense2.name + " - " + expense2.category + " - " + expense2.amount);

        for (Expense expense : expenses) {
            System.out.println(
                expense.name + " - " +
                expense.category + " - " +
                expense.amount
            );
        }

        scanner.close();
    }

    // Displays the main menu options
    public static void displayMenu() {
        System.out.println("=== Expense Tracker ===");
        System.out.println("1. Add expense");
        System.out.println("2. View expense");
        System.out.println("3. View total");
        System.out.println("4.Exit");
    }

    public static void displayExpense(String name, double amount) {
        System.out.println("Expense: " + name);
        System.out.println("Amount: $" + amount);
    }

    public static double calculateTotal(double expense1, double expense2) {
        double total = expense1 + expense2;
        return total;
    }

    public static double calculateTax(double amount) {
        double tax = amount * 0.06;
        return tax;
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

}