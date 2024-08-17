package issues.racecondition;

import java.util.logging.Level;
import java.util.logging.Logger;
import threads.Semaforo;

/**
 *
 * @author KebFelipe
 */
public class HiloContador implements Runnable {

    private final Contador contador;
    private final Semaforo semaforo;

    public HiloContador(Semaforo semaforo, Contador contador) {
        this.semaforo = semaforo;
        this.contador = contador;
    }

    @Override
    public void run() {
        try {
            semaforo.bloquear();
            for (int i = 0; i < 1000; i++) {
                contador.incrementarContador();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloContador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            semaforo.liberar();
        }
    }

}
