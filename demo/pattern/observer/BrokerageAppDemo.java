package demo.pattern.observer;

// --- 6. The Demo Application ---
public class BrokerageAppDemo {
    public static void main(String[] args) {
        // 1. Create the Subject (The data source)
        CustomerBrokerageAccount johnsAccount = new CustomerBrokerageAccount("A1001", 10000.00);

        // 2. Create the independent Observers (The watchers)
        EmailNotificationService emailer = new EmailNotificationService();
        ComplianceAlertSystem compliance = new ComplianceAlertSystem();

        // 3. Register the Observers with the Subject
        johnsAccount.register(emailer);
        johnsAccount.register(compliance);

        System.out.println("\n--- SIMULATION 1: Standard Deposit ---");
        // State change triggers updates for BOTH registered observers
        johnsAccount.deposit(500.00);

        System.out.println("\n--- SIMULATION 2: Large Deposit ---");
        // State change triggers updates for BOTH observers, Compliance will throw an alert
        johnsAccount.deposit(6000.00);

        // 4. An Observer is temporarily removed (e.g., during maintenance)
        System.out.println("\n--- SIMULATION 3: Removing Compliance Monitor ---");
        johnsAccount.unregister(compliance);

        // State change only triggers the remaining observer (Emailer)
        johnsAccount.deposit(200.00);
    }
}