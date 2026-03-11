package psp.hilos.prodcons;

public class Consumidor implements Runnable {

    private BufferProductos bufferProductos;
    private final int idConsumidor;

    public Consumidor(int idConsumidor, BufferProductos buffer) {
        this.bufferProductos= buffer;
        this.idConsumidor= idConsumidor;
    }

    @Override
    public void run(){

        int producto;

        while(true) {
            try {
                producto= bufferProductos.consumir();
                System.out.println("Consumidor" + idConsumidor + " consumió producto " + producto);
                Thread.sleep((long)(Math.random()*100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumidor"+idConsumidor + " interrumpido y finalizado.");
                return;
            }

        }

    }
}


