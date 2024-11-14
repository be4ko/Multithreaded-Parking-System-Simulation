class Semaphore {
    protected int value = 0;

    Semaphore(int value) {
        this.value = value;
    }

    public synchronized void P() {
        value--;
        if (value < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void V() {
        value++;
        if (value <= 0) {
            notify();
        }
    }

    public synchronized boolean tryAcquire() {
        if (value > 0) {
            value--;
            return true;
        }
        return false;
    }
}