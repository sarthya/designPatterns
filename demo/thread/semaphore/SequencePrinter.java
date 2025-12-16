package demo.thread.semaphore;

import java.util.concurrent.Semaphore;

class SequencePrinter implements Runnable {

    private static final int MAX_COUNT = 10;
    private static final int TOTAL_THREADS = 3;

    private static int currentNumber = 1;

    // One semaphore per thread
    private static final Semaphore[] semaphores =
            new Semaphore[TOTAL_THREADS];

    static {
        for (int i = 0; i < TOTAL_THREADS; i++) {
            semaphores[i] = new Semaphore(0);
        }
        semaphores[0].release(); // Thread-1 starts
    }

    private final int threadId; // 0-based index

    public SequencePrinter(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Wait for your turn
                semaphores[threadId].acquire();

                if (currentNumber > MAX_COUNT) {
                    // Wake next thread so it can exit cleanly
                    semaphores[(threadId + 1) % TOTAL_THREADS].release();
                    return;
                }

                display(currentNumber++);
                
                // Pass turn to next thread
                semaphores[(threadId + 1) % TOTAL_THREADS].release();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private void display(int number) {
        System.out.println("JavaThread=" + Thread.currentThread().getName() +
                " | logicalThreadId=" + threadId + " prints: " + number);
    }
}
