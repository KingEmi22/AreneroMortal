import java.util.ArrayList;

public class Kitty {
    private String nombre;
    private int salud;
    private int ataque;
    private int defensa;
    private ArrayList<Objeto> inventario;

    public Kitty(String nombre) {
        this.nombre = nombre;
        this.salud = 100;
        this.ataque = 10; // Valor inicial de ataque
        this.defensa = 5;
        this.inventario = new ArrayList<>();
    }

    // Getter para el nombre del jugador
    public String getNombre() {
        return nombre;
    }

    // Getter para la salud del jugador
    public int getSalud() {
        return salud;
    }

    // Getter para los puntos de ataque del jugador
    public int getAtaque() {
        return ataque;
    }

    // Método para incrementar los puntos de ataque del jugador
    public void incrementarAtaque(int cantidad) {
        ataque += cantidad;
        System.out.println("Tu ataque ha aumentado a " + ataque + " puntos.");
    }

    // Método para recibir daño (se resta de la salud)
    public void recibirDaño(int daño) {
        int dañoRecibido = daño - defensa;
        if (dañoRecibido > 0) {
            salud -= dañoRecibido;
        }
        if (salud < 0) {
            salud = 0; // Para evitar que la salud sea negativa
        }
    }
    
    public void curar(int cura) {
    	int nuevaSalud = salud + cura;
    	salud = nuevaSalud;
    	if(salud>100) {
    		salud=100;//Evitar supere los 100 de vida
    	}
    }

    // Método para atacar a un enemigo
    public void atacar(Enemigo enemigo) {
        enemigo.recibirDaño(ataque);
    }

    // Método para recoger un objeto y agregarlo al inventario
    public void recogerObjeto(Objeto objeto) {
        inventario.add(objeto);
        System.out.println("Has recogido un " + objeto.getNombre());
    }

    // Método para usar un objeto y removerlo del inventario
    public void usarObjeto(Objeto objeto) {
        objeto.usar(this);
        inventario.remove(objeto);
    }

    // Getter para obtener el inventario
    public ArrayList<Objeto> getInventario() {
        return inventario;
    }

    // Método para mostrar el inventario del jugador
    public void mostrarInventario() {
        System.out.println("Inventario:");
        for (int i = 0; i < inventario.size(); i++) {
            System.out.println(i + ". " + inventario.get(i).getNombre());
        }
    }

    // Método para verificar si el jugador sigue vivo
    public boolean estaVivo() {
        return salud > 0;
    }
}
