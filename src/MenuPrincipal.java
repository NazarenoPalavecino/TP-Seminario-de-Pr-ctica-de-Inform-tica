import java.util.Scanner;

public class MenuPrincipal extends InterfazDeUsuario{

    public static void main(String[] args) {
        //Método principal del sistema. Instancia y deriva en un objeto del menú principal

        MenuPrincipal menu = new MenuPrincipal();//Constructor por defecto
        menu.llamarMenu();
    }

    @Override
    public void llamarMenu() {
        //Menu principal del sistema. Construye instancias de los distintos menus y deriva
        // a ellos según las necesidades del actor

        Scanner entrada = new Scanner(System.in);
        InterfazFichaje interfazFichaje = new InterfazFichaje();
        InterfazPesaje interfazPesaje = new InterfazPesaje();
        int eleccion;
        boolean continuar = false;

        //Menu de operaciones
        do {
            System.out.println    ("\n-Menú Principal-");

            //Presentar opciones
            System.out.print("\nIngrese:" +
                    "\n1, para opciones de Fichaje" +
                    "\n2, para opciones de Pesaje" +
                    "\n3, para opciones de Llaves" +
                    "\n0, para cerrar el programa." +
                    "\n: ");
            eleccion = verificarEntrada(0,3);
            System.out.print("\n");

            //Derivar opción con estructura condicional switch
            switch (eleccion) {

                case 1:
                    //Deriva a
                    interfazFichaje.llamarMenu();
                    break;

                case 2:
                    //Deriva a
                    interfazPesaje.llamarMenu();
                    break;

                case 3:
                    System.out.println("No disponible."); //Por definir
                    break;

                case 0:
                    //Finaliza el bucle
                    continuar = true;
                    break;
            }
        } while (!continuar);
    }
}