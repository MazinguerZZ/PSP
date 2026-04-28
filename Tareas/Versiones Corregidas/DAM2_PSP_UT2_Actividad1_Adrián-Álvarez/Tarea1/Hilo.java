package psp.hilos.Tarea1;

public class Hilo extends Thread {
    private float base;
    private float altura;

    public Hilo(float base, float altura, String nombre) {
        this.base = base;
        this.altura = altura;
        this.setName(nombre);
    }

    @Override
    public void run() {
        float area = 0;

        int parteEntera = (int) base;
        for (int i = 0; i < parteEntera; i++) {
            area += altura;
        }

        float parteDecimal = base - parteEntera;
        if (parteDecimal > 0) {
            area += parteDecimal * altura;
        }

        area = area / 2;

        synchronized (System.out) {
            System.out.println("--- " + getName() + " ---");
            System.out.println("Base: " + base);
            System.out.println("Altura: " + altura);
            System.out.println("Prioridad: " + getPriority());
            System.out.println("Área: " + area);
            System.out.println();
        }
    }
}