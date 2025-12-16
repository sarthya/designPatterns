package demo.pattern.builder;

// -------------------------------------------------------------------
// 3. DEMO APPLICATION
// -------------------------------------------------------------------

class BrokerageBuilderDemo {
    public static void main(String[] args) {
        System.out.println("--- Building Trades via the Builder Pattern ---\n");

        // 1. Creating a simple MARKET order (Only required fields used)
        TradeOrder marketBuy = new TradeOrder.Builder("A1001", "GOOG", 10)
                .build();
        System.out.println("Market Buy Order:");
        System.out.println(marketBuy);

        System.out.println("----------------------------------------------\n");

        // 2. Creating a complex LIMIT order (All fields configured using chaining)
        TradeOrder limitSell = new TradeOrder.Builder("B5005", "TSLA", 50)
                .withLimitPrice(185.50) // Optional 1
                .isGoodTillCanceled(true)  // Optional 2
                .build();
        System.out.println("Limit Sell Order:");
        System.out.println(limitSell);
    }
}