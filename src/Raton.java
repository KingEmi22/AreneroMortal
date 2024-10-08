
public class Raton extends Enemigo {

	public Raton() {
		super(50,10,10);
	}

	@Override
	public void atacar(Kitty gato) {
		gato.recibirDaño(ataque);
	}

}
