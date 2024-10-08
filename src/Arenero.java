import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Arenero {
    private char[][] mapa;
    private int jugadorX;
    private int jugadorY;
    private Kitty gato;
    private Scanner scanner; // Scanner compartido
    private final int TAMANO_MAPA = 10; // Tamaño del mapa (cuadrado), final para no modificarse
private int enemigosrestantes=0;
    public Arenero(Kitty gato, Scanner scanner) {
        this.gato = gato;
        this.scanner = scanner;
        this.mapa = new char[TAMANO_MAPA][TAMANO_MAPA]; // Mapa cuadrado
        inicializarMapa();
        this.jugadorX = 1; // Posición inicial del jugador
        this.jugadorY = 1;
    }

    // Método para inicializar el mapa
    private void inicializarMapa() {
        Random rand = new Random();        // Inicializamos el mapa con más espacios abiertos y menos paredes
        for (int i = 0; i < TAMANO_MAPA; i++) {
            for (int j = 0; j < TAMANO_MAPA; j++) {
                // Solo una pequeña posibilidad de ser una pared (15%)
                if (rand.nextDouble() < 0.15) {
                    mapa[i][j] = '#'; // Pared
                } else {
                    mapa[i][j] = ','; // Espacio abierto (camino)
                }
            }
        }
        
        mapa[TAMANO_MAPA - 2][TAMANO_MAPA - 2] = 'S'; // Salida

        // Colocar más objetos, enemigos y armas aleatoriamente en el camino
        for (int i = 0; i < 6; i++) { // Incrementamos a 8 la cantidad de enemigos, objetos y armas
        	 // Colocar enemigos
            int perroX = rand.nextInt(TAMANO_MAPA - 2) + 1;
            int perroY = rand.nextInt(TAMANO_MAPA - 2) + 1;
            if (mapa[perroX][perroY] == ',') {
                mapa[perroX][perroY] = 'P'; // Colocar enemigo
                enemigosrestantes++;
            }
        }
        for(int i=0;i<6;i++) {
        	 // Colocar objetos
            int objetoX = rand.nextInt(TAMANO_MAPA - 2) + 1;
            int objetoY = rand.nextInt(TAMANO_MAPA - 2) + 1;
            if (mapa[objetoX][objetoY] == ',') {
                mapa[objetoX][objetoY] = 'O'; // Colocar objeto
            }
        }
        for(int i=0;i<2;i++) {
            // Colocar armas
            int garraX = rand.nextInt(TAMANO_MAPA - 2) + 1;
            int garraY = rand.nextInt(TAMANO_MAPA - 2) + 1;
            if (mapa[garraX][garraY] == ',') {
                mapa[garraX][garraY] = 'G'; // Colocar Garras
            }
        }
        for(int i=0;i<2;i++) {
            int rascadorX = rand.nextInt(TAMANO_MAPA - 2) + 1;
            int rascadorY = rand.nextInt(TAMANO_MAPA - 2) + 1;
            if (mapa[rascadorX][rascadorY] == ',') {
                mapa[rascadorX][rascadorY] = 'A'; // Colocar Afilador
            }
        }
        for(int i=0;i<6;i++) {
            int ratonX = rand.nextInt(TAMANO_MAPA - 2) + 1;
            int ratonY = rand.nextInt(TAMANO_MAPA - 2) + 1;
            if (mapa[ratonX][ratonY] == ',') {
                mapa[ratonX][ratonY] = 'R'; // Colocar Raton
                enemigosrestantes++;
            }
        }
    }
    // Método para mover al jugador
    public void moverJugador() {
        System.out.print("Elige tu acción: (w)Arriba, (s)Abajo, (d)Derecha, (a)Izquierda, (m)Menú, (p) Menú Principal: ");
        String accion = scanner.nextLine();

        if (accion.equals("m")) {
            mostrarMenuJugador(); // Mostramos el menú del jugador
        } else if (accion.equals("p")) {
            Main.mostrarMenuPrincipal(); // Volvemos al menú principal
        } else {
            mover(accion);
        }
    }

    // Método para mover al jugador por el mapa
 // Método para mover al jugador por el mapa
    private void mover(String direccion) {
        int nuevaX = jugadorX;
        int nuevaY = jugadorY;

        // Determinar nueva posición basada en la dirección
        switch (direccion) {
            case "w":
                nuevaX--;
                break;
            case "s":
                nuevaX++;
                break;
            case "d":
                nuevaY++;
                break;
            case "a":
                nuevaY--;
                break;
            default:
            	for(int i=0;i<50;i++) {
            		System.out.println();
            	}
              System.out.println("Dirección no valida");
                return;
        }

        // Verificar si la nueva posición está dentro de los límites del mapa
        if (nuevaX >= 0 && nuevaX < TAMANO_MAPA && nuevaY >= 0 && nuevaY < TAMANO_MAPA) {
            // Verificar si no hay una pared en la nueva posición
            if (mapa[nuevaX][nuevaY] != '#') {
                jugadorX = nuevaX;
                jugadorY = nuevaY;
                interactuarConCasilla(mapa[jugadorX][jugadorY]);
            } else {
            	for(int i=0;i<50;i++) {
            		System.out.println();
            	}
                System.out.println("Hay una pared!");
            }
        } else {
        	for(int i=0;i<50;i++) {
        		System.out.println();
        	}
           System.out.println("No puedes salir del arenero por aqui!");
        }
    }

        

    // Método para interactuar con el contenido de la casilla en la que se encuentra el jugador
 // Método para interactuar con el contenido de la casilla en la que se encuentra el jugador
    private void interactuarConCasilla(char casilla) {
        String mensaje = "";
        switch (casilla) {
            case 'P':
                mensaje = "¡Te has encontrado con un Perro enemigo!";
                Perro perro = new Perro();
                combatePorTurnos(perro);
                if (!perro.estaVivo()) {
                    mapa[jugadorX][jugadorY] = ','; // El enemigo desaparece del mapa
                    mensaje = "¡Has derrotado al perro enemigo!";
                    enemigosrestantes--;
                }
                break;
            case 'R':
                mensaje = "¡Te has encontrado con un Ratón enemigo!";
                Raton raton = new Raton();
                combatePorTurnos(raton);
                if (!raton.estaVivo()) {
                    mapa[jugadorX][jugadorY] = ','; // El enemigo desaparece del mapa
                    mensaje = "¡Has derrotado al ratón enemigo!";
                    enemigosrestantes--;
                }
                break;
            case 'O':
                mensaje = "¡Has encontrado un Estambre!";
                Estambre estambre = new Estambre("Estambre de curación", 20);
                gato.recogerObjeto(estambre);
                mapa[jugadorX][jugadorY] = ','; // El objeto desaparece del mapa
                break;
            case 'G':
                mensaje = "¡Has encontrado unas Garritas!";
                Arma garritas = new Arma("Garritas", 5);
                gato.recogerObjeto(garritas);
                mapa[jugadorX][jugadorY] = ','; // El arma desaparece del mapa
                break;
            case 'A':
                mensaje = "¡Has encontrado un Afilador!";
                Arma rascador = new Arma("Afilador", 10);
                gato.recogerObjeto(rascador);
                mapa[jugadorX][jugadorY] = ','; // El arma desaparece del mapa
                break;
            case 'S':
            	if(enemigosrestantes>0) {
            		mensaje="Aun quedan " +enemigosrestantes +" enemigos por derrotar";
            		System.out.println(mensaje);
            	}else {
                mensaje = "¡Has encontrado la salida! ¡Felicidades, ganaste!";           
               System.out.println(mensaje);
                System.exit(0);
            	}
                break;
            default:
                mensaje = "No hay nada aquí.";
        }      
for(int i=0;i<50;i++) {
	System.out.println();
}
        System.out.println(mensaje);

    }

    // Método para manejar el combate por turnos entre el jugador y el enemigo
    private void combatePorTurnos(Enemigo enemigo) {
        while (gato.estaVivo() && enemigo.estaVivo()) {
            System.out.println("Es tu turno. ¿Qué quieres hacer?");
            System.out.println("1. Atacar");
            System.out.println("2. Usar jugete");

            String accion = scanner.nextLine();

            if (accion.equals("1")) {
                gato.atacar(enemigo);
                System.out.println("Atacas al enemigo. Salud del enemigo: " + enemigo.getSalud());
            } else if (accion.equals("2")) {
                usarObjeto();
            } else {
                System.out.println("Acción no válida.");
                continue;
            }

            // Verificar si el enemigo sigue vivo
            if (!enemigo.estaVivo()) {
                System.out.println("¡Has derrotado al enemigo!");
                break;
            }

            // Turno del enemigo
            System.out.println("Turno del enemigo. El enemigo te ataca.");
            enemigo.atacar(gato);
            System.out.println("Tu salud: " + gato.getSalud());

            // Verificar si el jugador sigue vivo
            if (!gato.estaVivo()) {
                System.out.println("Has sido derrotado. Fin del juego.");
                System.exit(0);
            }
        }
    }
    public void mostrarMapa(String mensaje) {
    	
        // Mostrar el mensaje anterior (por ejemplo, lo que ocurrió en la casilla)
               // Imprimir el mapa con espaciado uniforme
        System.out.println(mensaje);
        for (int i = 0; i < TAMANO_MAPA; i++) {
            for (int j = 0; j < TAMANO_MAPA; j++) {
                if (i == jugadorX && j == jugadorY) {
                    System.out.print("J  "); // Representa al jugador con espaciado adicional
                } else {
                    System.out.print(mapa[i][j] + "  "); // Añadimos dos espacios adicionales para uniformidad
                }
            }
            System.out.println(); // Salto de línea al final de cada fila
        }
    }

    
    // Método que muestra el menú del jugador
    private void mostrarMenuJugador() {
        System.out.println("----- Menú del Gatito -----");
        System.out.println("1. Ver estado del Gatito");
        System.out.println("2. Usar objeto");
        System.out.println("3. Volver al juego");
        System.out.println("----------------------------");

        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                mostrarEstadoJugador(); // Muestra el estado del jugador
                break;
            case "2":
                usarObjeto(); // Permite al jugador usar un objeto
                break;
            case "3":
                System.out.println("Volviendo al juego...");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    // Método para mostrar el estado del jugador (salud y ataque)
    private void mostrarEstadoJugador() {
        System.out.println("----- Estado del Gatito -----");
        System.out.println("Salud: " + gato.getSalud());
        System.out.println("Puntos de ataque: " + gato.getAtaque());
        System.out.println("------------------------------");
    }

    // Método para que el jugador use un objeto
    private void usarObjeto() {
        gato.mostrarInventario();

        if (gato.getInventario().isEmpty()) {
            System.out.println("Tu inventario está vacío.");
            return;
        }

        System.out.println("Elige el número del objeto que deseas usar:");

        try {
            int indiceObjeto = scanner.nextInt();
            scanner.nextLine(); // Capturar el salto de línea

            if (indiceObjeto < 0 || indiceObjeto >= gato.getInventario().size()) {
                System.out.println("Número de objeto no válido.");
                return;
            }

            Objeto objeto = gato.getInventario().get(indiceObjeto);
            gato.usarObjeto(objeto);
        } catch (InputMismatchException e) {
            // Capturar si el usuario ingresa algo que no es un número entero
            System.out.println("Entrada no válida. Por favor, ingresa un número.");
            scanner.nextLine(); // Limpiar la entrada del scanner en caso de error
        }
    }
}


