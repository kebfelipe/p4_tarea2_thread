package threads;

/**
 *
 * @author KebFelipe
 */
public class Semaforo {
    private int permiso = 1;

    public Semaforo() {

    }
    
    public synchronized void bloquear() throws InterruptedException{
        while(permiso == 0){
            wait();
        }
        permiso = 0;
    }
    

    public synchronized void liberar() {
        permiso = 1;
        notify();
    }
}
