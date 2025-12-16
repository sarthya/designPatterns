package pattern.observer;

// --- 5. Concrete Observer 2 (Compliance Alert System) ---
class ComplianceAlertSystem implements AccountActivityMonitor {
    private final double largeTransactionThreshold = 5000.0;

    @Override
    public void update(String accountId, String transactionType, double amount, double newBalance) {
        if (amount >= largeTransactionThreshold) {
            System.err.println("  🚨 COMPLIANCE ALERT: Large " + transactionType + " ($" + amount + ") detected for Account " + accountId + ".");
            // In a real system, this would trigger a manual review.
        } else {
            System.out.println("  ✅ Compliance Check: Transaction amount $" + amount + " is safe.");
        }
    }
}
