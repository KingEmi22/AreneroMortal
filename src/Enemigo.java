public abstract class Enemigo {
    protected int salud;
    protected int ataque;
    protected int defensa;

    public Enemigo(int salud, int ataque, int defensa) {
        this.salud = salud;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public abstract void atacar(Kitty gato);

    public void recibirDaño(int daño) {
        int dañoRecibido = daño - defensa;
        if (dañoRecibido > 0) {
            salud -= dañoRecibido;
        }
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public int getSalud() {
        return salud;
    }
}


