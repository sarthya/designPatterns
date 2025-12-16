package demo.pattern.observer;

import java.util.ArrayList;
import java.util.List;

// --- 3. Concrete Subject (CustomerBrokerageAccount) ---
class CustomerBrokerageAccount implements ObservableAccount {
    private final String accountId;
    private double balance;
    private List<AccountActivityMonitor> monitors = new ArrayList<>();

    public CustomerBrokerageAccount(String accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
        System.out.println("✅ Account " + accountId + " created with initial balance $" + balance);
    }

    @Override
    public void register(AccountActivityMonitor monitor) {
        monitors.add(monitor);
        System.out.println("   > REGISTERED: " + monitor.getClass().getSimpleName());
    }

    @Override
    public void unregister(AccountActivityMonitor monitor) {
        monitors.remove(monitor);
        System.out.println("   > UNREGISTERED: " + monitor.getClass().getSimpleName());
    }

    // Crucial: This method triggers the notification when state changes
    @Override
    public void notifyMonitors(String transactionType, double amount) {
        // Broadcast the updated state to all registered observers
        for (AccountActivityMonitor monitor : monitors) {
            monitor.update(accountId, transactionType, amount, balance);
        }
    }

    // The state-changing business method
    public void deposit(double amount) {
        this.balance += amount;
        System.out.println("\n*** New Activity: Deposit of $" + amount + " to Account " + accountId + " ***");
        // After the state changes, notify everyone!
        notifyMonitors("DEPOSIT", amount);
    }
}