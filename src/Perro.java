public class Perro extends Enemigo {

    public Perro() {
        super(50, 20, 2);
    }

    @Override
    public void atacar(Kitty gato) {
        gato.recibirDaño(ataque);
    }
}

