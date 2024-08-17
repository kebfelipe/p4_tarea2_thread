package issues.deadlock;

import static java.lang.Thread.currentThread;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.Semaforo;

public class SyncThread implements Runnable {

    private Semaforo semaforo;
    private Object ob1;
    private Object ob2;

    public SyncThread(Object ob1, Object ob2, Semaforo semaforo) {
        this.ob1 = ob1;
        this.ob2 = ob2;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            semaforo.bloquear();
            System.out.println(currentThread().getName() + " generando lock en " + ob1);
            synchronized (ob1) {
                System.out.println(currentThread().getName() + " lock generado en " + ob1);
                work();
                System.out.println(currentThread().getName() + " generando lock en " + ob2);
                synchronized (ob2) {
                    System.out.println(currentThread().getName() + " lock generado en " + ob2);
                    work();
                }
                System.out.println(currentThread().getName() + " lock liberado en " + ob2);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(SyncThread.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            semaforo.liberar();
        }
        System.out.println(currentThread().getName() + " lock liberado en " + ob1);
        System.out.println("Finalizó ejecución");
    }

    private void work() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
