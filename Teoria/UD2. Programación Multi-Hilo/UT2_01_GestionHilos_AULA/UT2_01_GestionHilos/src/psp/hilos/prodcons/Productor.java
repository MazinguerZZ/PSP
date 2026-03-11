package psp.hilos.prodcons;

public class Productor implements Runnable {

    private BufferProductos bufferProductos;
    private final int idProductor;
    private final int numProductos;

    public Productor(int idProductor, int numProductos, BufferProductos buffer) {
        this.bufferProductos= buffer;
        this.idProductor= idProductor;
        this.numProductos= numProductos;
    }

    @Override
    public void run(){

        for(int i= 1; i<= numProductos; i++) {
            int producto= idProductor * 1000 + i;
            try {
                bufferProductos.producir(producto);
                System.out.println("Productor" + idProductor + " produjo: " + producto);
                Thread.sleep((long)(Math.random()*100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
