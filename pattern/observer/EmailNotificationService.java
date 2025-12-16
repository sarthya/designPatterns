package pattern.observer;

// --- 4. Concrete Observer 1 (Email Notification Service) ---
class EmailNotificationService implements AccountActivityMonitor {
    @Override
    public void update(String accountId, String transactionType, double amount, double newBalance) {
        System.out.println("  📧 Email Service: Sending confirmation for " + transactionType + " of $" + amount + ".");
        System.out.println("     Customer's current balance is now $" + newBalance);
    }
}