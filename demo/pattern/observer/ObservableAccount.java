package demo.pattern.observer;

// --- 2. The Subject Interface (ObservableAccount) ---
interface ObservableAccount {
    void register(AccountActivityMonitor monitor);
    void unregister(AccountActivityMonitor monitor);
    void notifyMonitors(String transactionType, double amount);
}