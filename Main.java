import java.util.Scanner;
import java.util.ArrayList;

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