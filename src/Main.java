import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in); // Crear un solo Scanner para todo el juego

    public static void main(String[] args) {
        mostrarMenuPrincipal(); // Mostrar el menú principal al iniciar el programa
    }

    public static void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Iniciar nueva partida");
            System.out.println("2. Salir del juego");
            System.out.println("--------------------------");
            System.out.print("Elige una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    iniciarNuevaPartida();
                    break;
                case "2":
              
                    System.out.println("Saliendo del juego...");
                    System.exit(0); // Cerrar el programa
                    break;
                default:
                    System.out.println("Opción no válida, por favor elige otra.");
                    break;
            }
        }
    }

    public static void iniciarNuevaPartida() {
        System.out.print("Ingresa tu nombre: ");
        String nombregato = scanner.nextLine();

        Kitty gato = new Kitty(nombregato);
        Arenero arenero = new Arenero(gato, scanner); // Pasar el Scanner como argumento

        System.out.println("¡Bienvenido " + gato.getNombre() + " a el arenero!");

        // Mensaje inicial
        String mensaje = "Comienza tu aventura en el arenero.";

        while (gato.estaVivo()) {
            arenero.mostrarMapa(mensaje); // Pasar el mensaje a mostrar junto con el mapa
            arenero.moverJugador(); // Se permite usar objeto o moverse
        }

        System.out.println("Has muerto. Fin del juego.");
        mostrarMenuPrincipal(); // Volver al menú principal después de que el jugador muera
    }
}

