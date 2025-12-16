package demo.pattern.proxy;

import java.util.Objects;

// --- 1. Subject Interface (The common contract) ---
interface TradeService {
    void executeTrade(String accountId, String symbol, int quantity);
}

// --- 2. Real Subject (The sensitive and expensive operation) ---
class HighValueTradeServiceImpl implements TradeService {
    @Override
    public void executeTrade(String accountId, String symbol, int quantity) {
        // This is the resource-intensive, core business logic.
        System.out.println("  ✅ TRADE EXECUTED: Account " + accountId +
                " successfully traded " + quantity + " shares of " + symbol);
    }
}

// --- 3. Proxy (The Access Controller/Gatekeeper) ---
class TradeServiceProxy implements TradeService {
    // 🔑 Holds a reference to the Real Subject
    private final HighValueTradeServiceImpl realTradeService;
    private final String userRole; // Example of a security context

    public TradeServiceProxy(String userRole) {
        // Initialize the Real Subject when the Proxy is created
        this.realTradeService = new HighValueTradeServiceImpl();
        this.userRole = userRole;
        System.out.println("🛡️ Proxy created for user role: " + userRole);
    }

    // 🔑 Implements the Subject Interface method
    @Override
    public void executeTrade(String accountId, String symbol, int quantity) {
        System.out.println("\n--- Proxy intercepts call for " + symbol + " ---");

        // 🎯 Value-add logic: Security Check (Pre-Delegation)
        if (isUserAuthorized()) {
            // If the security check passes, delegate the call to the Real Subject
            System.out.println("  🔒 Authorization successful. Delegating to Real Service...");
            realTradeService.executeTrade(accountId, symbol, quantity);
        } else {
            // Security check fails, block the request
            System.err.println("  ❌ ACCESS DENIED: User role '" + userRole +
                    "' is not authorized to execute high-value trades.");
        }
    }

    private boolean isUserAuthorized() {
        // Simulate a role-based access check
        return Objects.equals(this.userRole, "Trader");
    }
}

// --- 4. Demo Application ---
class BrokerageProxyDemo {
    public static void main(String[] args) {

        // Scenario 1: Authorized User (Trader)
        TradeService authorizedService = new TradeServiceProxy("Trader");
        System.out.println("\n*** Attempting trade with AUTHORIZED user (Trader) ***");
        authorizedService.executeTrade("A100", "MSFT", 500);

        System.out.println("\n========================================================");

        // Scenario 2: Unauthorized User (Viewer)
        TradeService unauthorizedService = new TradeServiceProxy("Viewer");
        System.out.println("\n*** Attempting trade with UNAUTHORIZED user (Viewer) ***");
        unauthorizedService.executeTrade("A200", "AMZN", 100);
    }
}