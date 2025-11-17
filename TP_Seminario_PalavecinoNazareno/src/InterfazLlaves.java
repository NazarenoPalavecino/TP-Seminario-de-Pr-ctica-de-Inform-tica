import java.util.Scanner;

public class InterfazLlaves extends InterfazDeUsuario{

    @Override
    public void llamarInterfaz() {

        GestorLlaves gestorLlaves = new GestorLlaves();
        Scanner entrada = new Scanner(System.in);
        int eleccion, idLlave;
        boolean continuar = false;

        //Menú de operaciones
        do {
            System.out.println    ("\n-Gestión de llaves-");

            //Presentar opciones
            System.out.print("\nIngrese:" +
                    "\n1, para cerrar una llave" +
                    "\n2, para ver las llaves disponibles" +
                    "\n3, para eliminar una llave" +
                    "\n4, para iniciar una llave" +
                    "\n5, para ver resultados de una llave" +
                    "\n0, para cerrar el menú." +
                    "\n: ");
            eleccion = verificarEntrada(0,5);
            System.out.print("\n");

            //Derivar opción con estructura condicional switch
            switch (eleccion) {

                case 1:
                    //Deriva al controlador para verificar peso
                    String categoriaPeso, categoriaSexo, categoriaEdad, categoriaCinturon;

                    System.out.print("-Formulario de Creación de Llaves-" +
                            "\nIngrese:" +
                            "\nCategoría de peso (galo, pluma, pena, leve, medio, medio pesado, pesado, super pesado): ");
                    categoriaPeso = entrada.nextLine().toUpperCase();

                    System.out.print("\nCategoría de edad (juvenil, adulto): ");
                    categoriaEdad = entrada.nextLine().toUpperCase();

                    System.out.print("\nCategoría de sexo (masculino/femenino): ");
                    categoriaSexo = entrada.next().toUpperCase();
                    entrada.nextLine();

                    System.out.print("\nCategoría de cinturón (blanco, azul, violeta, cafe, negro): ");
                    categoriaCinturon = entrada.next().toUpperCase();
                    entrada.nextLine();

                    gestorLlaves.crearLlave(categoriaSexo, categoriaPeso, categoriaCinturon, categoriaEdad);
                    System.out.println("Llave creada con éxito");

                    break;

                case 2:
                    //Deriva al controlador para visualizar las llaves disponibles.
                    gestorLlaves.verLlaves();

                    break;

                case 3:
                    //Deriva al controlador para eliminar una llave

                    System.out.print("\nIngrese N° de la llave a eliminar: ");
                    idLlave= entrada.nextInt();
                    entrada.nextLine();

                    gestorLlaves.eliminarLlave(idLlave);

                    break;

                case 4:
                    //Deriva al controlador para dar inicio a los encuentros de una llave.

                    System.out.print("\nIngrese N° de la llave a iniciar: ");
                    idLlave= entrada.nextInt();
                    entrada.nextLine();

                    gestorLlaves.ejecutarLlave(idLlave);

                    break;

                case 5:
                    //Deriva al controlador para consultar y mostrar resultados de una llave.

                    System.out.print("\nIngrese N° de la llave a visualizar: ");
                    idLlave= entrada.nextInt();
                    entrada.nextLine();

                    gestorLlaves.verResultados(idLlave);

                    break;

                case 0:
                    //Finaliza el bucle

                    continuar = true;
                    break;
            }
        } while (!continuar);
    }
}
