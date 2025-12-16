package pattern.builder;

public class TradeOrder {
    // -------------------------------------------------------------------
    // 1. THE PRODUCT (TradeOrder)
    // All fields are final to ensure immutability once the object is built.
    // -------------------------------------------------------------------

    // Required fields
    private final String accountId;
    private final String symbol;
    private final int quantity;

    // Optional fields
    private final double limitPrice;
    private final String orderType;
    private final boolean isDayOrder;

    // 🔑 Private Constructor: Only the Builder can call this.
    private TradeOrder(Builder builder) {
        this.accountId = builder.accountId;
        this.symbol = builder.symbol;
        this.quantity = builder.quantity;
        this.limitPrice = builder.limitPrice;
        this.orderType = builder.orderType;
        this.isDayOrder = builder.isDayOrder;
    }

    @Override
    public String toString() {
        return "TradeOrder [\n" +
                "  - Account ID: " + accountId + "\n" +
                "  - Symbol: " + symbol + "\n" +
                "  - Quantity: " + quantity + "\n" +
                "  - Order Type: " + orderType + "\n" +
                "  - Limit Price: " + (limitPrice > 0 ? "$" + limitPrice : "N/A") + "\n" +
                "  - Day Order: " + isDayOrder + "\n" +
                "]";
    }


// -------------------------------------------------------------------
// 2. THE BUILDER (Static Nested Class)
// -------------------------------------------------------------------

    public static class Builder {
        // Copies of all fields from TradeOrder
        // Required fields
        private final String accountId;
        private final String symbol;
        private final int quantity;

        // Optional fields (with default values)
        private double limitPrice = 0.0;
        private String orderType = "MARKET";
        private boolean isDayOrder = true;

        // 🎯 Builder Constructor: Handles ONLY the required fields.
        public Builder(String accountId, String symbol, int quantity) {
            this.accountId = accountId;
            this.symbol = symbol;
            this.quantity = quantity;
        }

        // 🔗 Methods for Optional Fields (Return 'this' for chaining)

        public Builder withLimitPrice(double price) {
            this.limitPrice = price;
            // Also update the order type since a price was provided
            this.orderType = "LIMIT";
            return this;
        }

        public Builder isGoodTillCanceled(boolean isGTC) {
            this.isDayOrder = !isGTC; // If GTC, it's NOT a Day Order
            return this;
        }

        // 🔨 Build Method: Creates and returns the final immutable Product.
        public TradeOrder build() {
            // Optional: Add validation logic here before creating the object
            if (this.quantity <= 0) {
                throw new IllegalStateException("Order quantity must be positive.");
            }
            return new TradeOrder(this);
        }
    }
}