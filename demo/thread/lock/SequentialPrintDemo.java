package demo.thread.lock;

public class SequentialPrintDemo {
    public static void main(String[] args) throws InterruptedException {

        // Create 3 threads, each with a unique ID
        Thread t1 = new Thread(new SequencePrinter(1));
        Thread t2 = new Thread(new SequencePrinter(2));
        Thread t3 = new Thread(new SequencePrinter(3));

        // Start them all simultaneously
        t1.start();
        t2.start();
        t3.start();

        // Wait for all threads to finish (optional, for clean shutdown)
        t1.join();
        t2.join();
        t3.join();

        System.out.println("\nSequencing complete.");
    }
}
