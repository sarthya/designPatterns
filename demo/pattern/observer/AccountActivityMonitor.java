package demo.pattern.observer;

// --- 1. The Observer Interface (AccountActivityMonitor) ---
interface AccountActivityMonitor {
    /**
     * Defines the contract for all watchers to receive updates.
     * @param accountId The ID of the account that changed.
     * @param transactionType e.g., "DEPOSIT", "WITHDRAWAL", "TRADE"
     * @param newBalance The updated account balance.
     */
    void update(String accountId, String transactionType, double amount, double newBalance);
}