import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestorCompetidores {
    //Clase de control para la gestión y proceso de competidores.

    private static HashMap<Integer, Competidor> competidores = new HashMap<>();
    //Listado de competidores. Utiliza una estructura HashMap para facilitar el acceso.

    public void registrarCompetidor() {
        //Instancia y registra un objeto Competidor en el listado

        Scanner entrada = new Scanner(System.in);
        String nombre, sexo, cinturon, escuela, categoria;
        int dni, edad;

        System.out.print("Formulario de Registro" +
                "\nNombre: ");
        nombre = entrada.nextLine().toUpperCase();

        System.out.print("\nSexo (masculino/femenino): ");
        sexo = entrada.next().toUpperCase();
        entrada.nextLine();

        System.out.print("\nDNI (sin puntos): ");
        dni = entrada.nextInt();
        entrada.nextLine();

        System.out.print("\nEdad: ");
        edad = entrada.nextInt();
        entrada.nextLine();

        System.out.print("\nCinturón (blanco, azul, violeta, cafe, negro): ");
        cinturon = entrada.next().toUpperCase();
        entrada.nextLine();

        System.out.print("\nEscuela: ");
        escuela = entrada.nextLine().toUpperCase();

        System.out.print("\nCategoría (galo, pluma, pena, leve, medio, medio pesado, pesado, super pesado): ");
        categoria = entrada.nextLine().toUpperCase();

        Competidor competidor = new Competidor(nombre, sexo, dni, edad, cinturon, escuela, categoria);
        competidores.put(dni, competidor); //Se enlista al competidor con su DNI como clave
        System.out.println("Registro exitoso!");
    }

    public void verCompetidores() {
        //Emite un listado de los competidores registrados al momento

        if (!competidores.isEmpty()) {//Si el registro no está vacío, procede
            System.out.println("\n                   Listado de Competidores" +
                    "\n   DNI   |      Nombre     |  Edad  |   Sexo   | Categoría | Cinturón");

            for (Competidor c : competidores.values()) { //Bucle a traves del listado
                System.out.println(c.getDni() + " |" + c.getNombre() + "  |" + c.getEdad() + " años |" +
                        c.getSexo() + " |" + c.getCategoria() + " |" + c.getCinturon());
            }
        } else {//Si el registro se encuentra vacío, se notifica
            System.out.println("\nNo se registran competidores");
        }
    }

    public void eliminarCompetidor() {
        //Busca y elimina un competidor en base a su dni

        Scanner entrada = new Scanner(System.in);
        int dni, eleccion;

        if (!competidores.isEmpty()) {//Si el registro no está vacío, procede
            System.out.print("\nIngrese DNI(sin puntos) del competidor a eliminar: ");
            dni = entrada.nextInt();

            if (competidores.containsKey(dni)) { //Se verifica la existencia del competidor en el registro
                Competidor competidor = competidores.get(dni);

                try {//Posible InputMismatchException
                    System.out.print("\nDesea eliminar al competidor " + competidor.getNombre() + ",DNI: " + competidor.getDni() +
                            "?\n1, SI" +
                            "\n2, NO" +
                            "\n: ");
                    eleccion = entrada.nextInt();

                    if (eleccion == 1) {
                        competidores.remove(dni);
                        System.out.println("Competidor eliminado");
                    } else if (eleccion == 2) {
                        System.out.println("Operación abortada");
                    } else {//Si se ingresa otro número
                        System.out.println("Ingreso erróneo. Vuelva a intentar ingresando '1' o '2'.");
                    }
                } catch (InputMismatchException e) { //Si se ingresa un dato no numérico
                    System.out.println("Tipo de dato erróneo. Vuelva a intentar ingresando '1' o '2'.");
                }

            } else {//Si no se encuentra al competidor
                System.out.println("Error, no se halla al competidor");
            }
        } else {//Si el registro se encuentra vacío
            System.out.println("\nNo se registran competidores");
        }
    }

    public void modificarCompetidor() {
        //Permite modificar un dato cualquiera de un competidor

        Scanner entrada = new Scanner(System.in);
        int dni, eleccion;

        if (!competidores.isEmpty()) {//Si el registro no está vacío, procede
            System.out.print("\nIngrese DNI(sin puntos) del competidor a modificar: ");
            dni = entrada.nextInt();

            if (competidores.containsKey(dni)) {//Se verifica la existencia del competidor en el registro
                Competidor competidor = competidores.get(dni);

                //Se exponen los datos de este único competidor para su visualización detallada y elección
                System.out.println("\nDatos del competidor: " +
                        "\n  1:DNI  |     2:Nombre    | 3:Edad |  4:Sexo  | 5:Categoría | 6:Cinturón | 7:Escuela \n" +
                        competidor.getDni() + " |" + competidor.getNombre() + "  |" + competidor.getEdad() + " años |" +
                        competidor.getSexo() + " |" + competidor.getCategoria() + " |" + competidor.getCinturon() +
                        " |" + competidor.getEscuela());
                System.out.println("\nIngrese número del dato a modificar (0 para ninguno): ");
                eleccion = entrada.nextInt();
                entrada.nextLine();

                switch (eleccion) { //Con base en la elección, se toma y actualiza el dato solicitado
                    case 1:

                        System.out.print("DNI: ");
                        competidor.setDni(entrada.nextInt());
                        break;

                    case 2:

                        System.out.print("Nombre: ");
                        competidor.setNombre(entrada.nextLine().toUpperCase());
                        break;

                    case 3:

                        System.out.print("Edad: ");
                        competidor.setEdad(entrada.nextInt());
                        break;

                    case 4:

                        System.out.print("Sexo (masculino/femenino): ");
                        competidor.setSexo(entrada.nextLine().toUpperCase());
                        break;

                    case 5:

                        System.out.print("Categoría (galo, pluma, pena, leve, medio, medio pesado, pesado, super pesado): ");
                        competidor.setCategoria(entrada.nextLine().toUpperCase());
                        break;

                    case 6:

                        System.out.print("Cinturón (blanco, azul, violeta, cafe, negro): ");
                        competidor.setCinturon(entrada.nextLine().toUpperCase());
                        break;

                    case 7:

                        System.out.print("Escuela: ");
                        competidor.setEscuela(entrada.nextLine().toUpperCase());
                        break;

                    case 0:

                        break;
                }
            } else {//Si no se encuentra al competidor
                System.out.println("Error, no se halla al competidor");
            }


        } else {//Si el registro se encuentra vacío
            System.out.println("\nNo se registran competidores");
        }
    }

    public void verificarPeso() {
        //Comprueba la categoría (en peso) de un competidor a partir de su peso actual

        Scanner entrada = new Scanner(System.in);
        int dni;
        double peso;
        double[] rango = new double[2];

        if (!competidores.isEmpty()) {//Si el registro no está vacío, procede
            System.out.print("\nIngrese DNI(sin puntos) del competidor a verificar: ");
            dni = entrada.nextInt();

            if (competidores.containsKey(dni)) {//Se verifica la existencia del competidor en el registro
                Competidor competidor = competidores.get(dni);

                //Se presentan nombre y categoría inscripta del competidor para cerciorar durante el proceso
                System.out.println("Competidor: " + competidor.getNombre() + ", Categoría: " + competidor.getCategoria() +
                "\nIngrese el peso actual del competidor (ee,d):");
                peso = entrada.nextDouble();

                //Llamado al método que calcula el rango de peso aceptado para cada categoría
                rango = obtenerRango(competidor.getCategoria(), competidor.getSexo(), competidor.getEdad());

                if (peso > rango[0] && peso <= rango[1]) {
                    //Si el peso se encuentra dentro de los límites, aprueba el pesaje
                    System.out.println("Pesaje verificado con éxito en categoría " + competidor.getCategoria() + ": " +
                            rango[0] + " a " + rango[1] + "kg");
                } else {
                    //Si el pesaje no coincide, se notifica la comparativa entre el peso actual y la categoría
                    System.out.println("Error, el pesaje no coincide con la categoría inscripta. Peso actual: " +
                            peso + "kg. Peso para la categoria " + competidor.getCategoria() + ": " +
                            rango[0] + " a " + rango[1] + "kg");
                }
            } else {//Si no se encuentra al competidor
                System.out.println("Error, no se halla al competidor");
            }
        } else {//Si el registro se encuentra vacío
            System.out.println("\nNo se registran competidores");
        }

    }

    private double[] obtenerRango(String categoria, String sexo, int edad) {
        //Método privado para definir el rango de peso admitido por categoría (juveniles y adultos)

        double[] rango = new double[2];//{Límite inferior, Límite superior]

        // Adultos (18+)
        if (edad >= 18) {
            switch (categoria) {
                //Según categoría de peso, retorna el rango para masculino y femenino.
                case "GALO":
                    return sexo.equals("MASCULINO") ? new double[]{0, 57.5} : new double[]{0, 48.5};
                case "PLUMA":
                    return sexo.equals("MASCULINO") ? new double[]{57.5, 64} : new double[]{46.0, 53.5};
                case "PENA":
                    return sexo.equals("MASCULINO") ? new double[]{64, 70} : new double[]{51.0, 58.5};
                case "LEVE":
                    return sexo.equals("MASCULINO") ? new double[]{70, 76} : new double[]{58.6, 64};
                case "MEDIO":
                    return sexo.equals("MASCULINO") ? new double[]{76, 82.3} : new double[]{66.0, 69};
                case "MEDIO PESADO":
                    return sexo.equals("MASCULINO") ? new double[]{82.3, 88.3} : new double[]{74.1, 74};
                case "PESADO":
                    return sexo.equals("MASCULINO") ? new double[]{88.3, 94.3} : new double[]{74, 200};
                case "SUPER PESADO":
                    return new double[]{94.3, 100.5};
                default: //Ante una entrada desconocida, arroja error
                    throw new IllegalArgumentException("División desconocida: " + categoria);
            }
        }

        // Juveniles (16-17 años)
        else if (edad >= 16 && edad <= 17) {
            switch (categoria) {
                //Según categoría de peso, retorna el rango para masculino y femenino
                case "GALO":
                    return sexo.equals("MASCULINO") ? new double[]{0, 53.5} : new double[]{0, 44.3};
                case "PLUMA":
                    return sexo.equals("MASCULINO") ? new double[]{53.5, 58.5} : new double[]{42.1, 48.3};
                case "PENA":
                    return sexo.equals("MASCULINO") ? new double[]{58.5, 64} : new double[]{46.0, 52.5};
                case "LEVE":
                    return sexo.equals("MASCULINO") ? new double[]{64, 69} : new double[]{48.4, 56.5};
                case "MEDIO":
                    return sexo.equals("MASCULINO") ? new double[]{69, 74} : new double[]{52.0, 60.5};
                case "MEDIO PESADO":
                    return sexo.equals("MASCULINO") ? new double[]{74, 79.3} : new double[]{58.0, 65};
                case "PESADO":
                    return sexo.equals("MASCULINO") ? new double[]{79.3, 84.3} : new double[]{65, 200};
                case "SUPER PESADO":
                    return  new double[]{84.3, 89.3};
                default: //Ante una entrada desconocida, arroja error
                    throw new IllegalArgumentException("División desconocida: " + categoria);
            }
        }
        return rango;
    }
}
