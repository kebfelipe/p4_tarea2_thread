package threads;

/**
 *
 * @author KebFelipe
 */
public class Semaforo {
    private int permiso = 1;

    public Semaforo(int permiso) {
        this.permiso = permiso;
    }
    
    public synchronized void bloquear() throws InterruptedException{
        while(permiso == 0){
            wait();
        }
        permiso--;
    }
    

    public synchronized void liberar() {
        permiso++;
        notify();
    }
}
