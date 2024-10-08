public class Estambre extends Objeto {

    private int cantidadSalud;

    public Estambre(String nombre, int cantidadSalud) {
        super(nombre);
        this.cantidadSalud = cantidadSalud;
    }

    @Override
    public void usar(Kitty gato) {
        System.out.println("Usando " + nombre + " para restaurar " + cantidadSalud + " puntos de salud.");
        gato.curar(cantidadSalud);
    }
}



