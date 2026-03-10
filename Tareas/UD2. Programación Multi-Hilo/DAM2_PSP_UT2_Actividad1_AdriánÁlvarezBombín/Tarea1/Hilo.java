package psp.hilos.Tarea1;

public class Hilo extends Thread{
    private float base;
    private float altura;

    public Hilo(float base, float altura, String nombre) {
        this.base = base;
        this.altura = altura;
        this.setName(nombre);
    }

    @Override
    public void run(){
        float area = 0;
        for(int i = 0; i < base; i++) {
            area += altura;
        }
        area = area / 2;

        synchronized(System.out) {
            System.out.println("--- " + getName() + " ---");
            System.out.println("Base: " + base);
            System.out.println("Altura: " + altura);
            System.out.println("Prioridad: " + getPriority());
            System.out.println("Área: " + area);
            System.out.println();
        }
    }
}