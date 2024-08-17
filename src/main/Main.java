package main;

import issues.deadlock.SyncThread;
import issues.racecondition.Contador;
import issues.racecondition.HiloContador;
import threads.Semaforo;

/**
 *
 * @author KebFelipe
 */
public class Main {

    static Semaforo semaforo = new Semaforo(1);
    
    public static void main(String[] args) throws InterruptedException {
        deadlock();
        //racecondition();
    }
    
    public static void racecondition() throws InterruptedException{
        Contador contador = new Contador();

        Thread hilo1 = new Thread(new HiloContador(semaforo, contador));
        Thread hilo2 = new Thread(new HiloContador(semaforo, contador));
        //Thread hilo3 = new Thread(new HiloContador(semaforo, contador));

        hilo1.start();
        hilo2.start();
        //hilo3.start();

        hilo1.join();
        hilo2.join();
        //hilo3.join();
        
        System.out.println("Valor final del contador: " + contador.getContador());
    }
    
    public static void deadlock() throws InterruptedException{
        Object ob1 = new Object();
        Object ob2 = new Object();
        Object ob3 = new Object();

        Thread t1 = new Thread(new SyncThread(ob2, ob3, semaforo), "hilo1");
        Thread t2 = new Thread(new SyncThread(ob2, ob3, semaforo), "hilo2");
        Thread t3 = new Thread(new SyncThread(ob2, ob3, semaforo), "hilo3");

        t1.start();
        Thread.sleep(5000);
        t2.start();
        Thread.sleep(5000);
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Finalizado");
    }
}
