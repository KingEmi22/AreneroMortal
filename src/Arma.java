public class Arma extends Objeto {
    private int aumentoAtaque;

    public Arma(String nombre, int aumentoAtaque) {
        super(nombre);
        this.aumentoAtaque = aumentoAtaque;
    }

    @Override
    public void usar(Kitty gato) {
        System.out.println("Usando " + nombre + ". Aumenta el ataque en " + aumentoAtaque + " puntos.");
        gato.incrementarAtaque(aumentoAtaque); // Incrementa el ataque del jugador
    }
}
