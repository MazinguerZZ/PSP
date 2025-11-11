package psp.hilos.prodcons;

public class BufferProductos {

    private static final int DEFAULT_MAX_CAPACIDAD = 10;
    private int[] buffer;
    private int siguiente;

    private boolean estaVacio;
    private boolean estaLleno;

    public BufferProductos(int capacidad) {
        buffer = new int[capacidad];
        siguiente = 0;
        estaVacio = true;
        estaLleno = false;
    }

    public BufferProductos(){
        this(DEFAULT_MAX_CAPACIDAD);
    }

    public synchronized int consumir() throws InterruptedException {

        while (estaVacio) {
            System.out.println("Buffer vacio.");
            wait();
        }

        siguiente--;
        if (siguiente == 0) {
            estaVacio = true;
        }

        System.out.println("Se ha consumido el producto: " + buffer[siguiente]);
        estaLleno = false;
        notifyAll();

        return (buffer[siguiente]);
    }

    public synchronized void producir(int producto) throws InterruptedException{

        while (estaLleno) {
            System.out.println("Buffer vacio.");
            wait();
        }

        buffer[siguiente] = producto;

        siguiente++;

        if (siguiente == buffer.length) {
            estaLleno = true;
        }
        System.out.println("Se ha producido el producto: " + producto);
        estaVacio = false;
        notifyAll();

    }

    public boolean estaVacio() {
        return estaVacio;
    }
}
