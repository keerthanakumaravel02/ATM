import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleATMWithRegistration {
    private static Map<String, Double> accountBalanceMap = new HashMap<>();
    private static Map<String, String> accountPasswordMap = new HashMap<>();

    public static void register(String username, String password) {
        if (!accountBalanceMap.containsKey(username)) {
            accountBalanceMap.put(username, 0.0);
            accountPasswordMap.put(username, password);
            System.out.println("Registration successful for " + username);
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }

    public static boolean login(String username, String password) {
        if (accountBalanceMap.containsKey(username) && accountPasswordMap.get(username).equals(password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Invalid username or password. Login failed.");
            return false;
        }
    }

    public static void deposit(String username, double amount) {
        double currentBalance = accountBalanceMap.get(username);
        currentBalance += amount;
        accountBalanceMap.put(username, currentBalance);
        System.out.println(amount + " Rs deposited successfully.");
    }

    public static void withdraw(String username, double amount) {
        double currentBalance = accountBalanceMap.get(username);
        if (amount <= currentBalance) {
            currentBalance -= amount;
            accountBalanceMap.put(username, currentBalance);
            System.out.println(amount + " Rs withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public static void checkBalance(String username) {
        System.out.println("Current balance for " + username + ": " + accountBalanceMap.get(username) + " Rs");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String newUsername = scanner.next();
                    System.out.print("Enter password: ");
                    String newPassword = scanner.next();
                    register(newUsername, newPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    if (login(username, password)) {
                        while (true) {
                            System.out.println("\n1. Deposit\n2. Withdraw\n3. Check Balance\n4. Logout");
                            System.out.print("Enter your choice: ");
                            int userChoice = scanner.nextInt();

                            switch (userChoice) {
                                case 1:
                                    System.out.print("Enter amount to deposit: ");
                                    double depositAmount = scanner.nextDouble();
                                    deposit(username, depositAmount);
                                    break;
                                case 2:
                                    System.out.print("Enter amount to withdraw: ");
                                    double withdrawAmount = scanner.nextDouble();
                                    withdraw(username, withdrawAmount);
                                    break;
                                case 3:
                                    checkBalance(username);
                                    break;
                                case 4:
                                    System.out.println("Logged out successfully.");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }

                            if (userChoice == 4) {
                                break;
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
