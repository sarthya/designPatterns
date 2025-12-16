package demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SequencePrinter implements Runnable {

    private static final int MAX_COUNT = 10;
    private static final int TOTAL_THREADS = 3;

    private static int currentNumber = 1;
    private static int currentTurn = 1;   // 👈 explicit turn


    private static final Lock lock = new ReentrantLock(true);
    private static final Condition turnCondition = lock.newCondition();

    private final int threadId;

    public SequencePrinter(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        write();
    }

    private void write() {
        while (true) {
            lock.lock();
            try {
                while (currentNumber <= MAX_COUNT && currentTurn != threadId) {
                    turnCondition.await();
                }
                if (currentNumber > MAX_COUNT) {
                    turnCondition.signalAll();
                    return;
                }
                display(currentNumber++);

                currentTurn = (currentTurn % TOTAL_THREADS) + 1;

                turnCondition.signalAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } finally {
                lock.unlock();
            }
        }
    }

    private void display(int number) {
        System.out.println("Thread-" + threadId + " prints: " + number);
    }
}
