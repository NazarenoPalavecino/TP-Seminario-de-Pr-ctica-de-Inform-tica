import java.sql.*;
import java.util.Scanner;

public class GestorCompetidores {
    //Clase de control para la gestión y proceso de competidores.

    //Conexión a DB
    private Connection conexion;

    public GestorCompetidores() {
        this.conexion = DBConexion.conectar();
    }

    public void registrarCompetidor() {
        //Instancia y registra un objeto Competidor en el listado

        Scanner entrada = new Scanner(System.in);
        String nombre, apellido, sexo, cinturon, escuela, categoria, consulta;
        int dni, edad;

        System.out.print("Formulario de Registro" +
                "\nNombre: ");
        nombre = entrada.nextLine().toUpperCase();

        System.out.print("\nApellido: ");
        apellido = entrada.nextLine().toUpperCase();

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

        //TP4: Agregando Persistencia (editar)
        consulta = "INSERT INTO competidor (dni, nombre, apellido, edad, sexo, categoría, cinturón, escuela) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, dni);
            sentencia.setString(2, nombre);
            sentencia.setString(3, apellido);
            sentencia.setInt(4, edad);
            sentencia.setString(5, sexo);
            sentencia.setString(6, categoria);
            sentencia.setString(7, cinturon);
            sentencia.setString(8, escuela);
            sentencia.executeUpdate();
            System.out.println("Registro exitoso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void verCompetidores() {
        //Emite un listado de los competidores registrados al momento

        //TP4: Recuperar persistencia (editar)
        String consulta;

        consulta = "SELECT * FROM competidor";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultSet = sentencia.executeQuery();

            System.out.println("\n                   Listado de Competidores" +
                    "\n   DNI   |      Nombre     |  Edad  |   Sexo   | Categoría | Cinturón");

            while (resultSet.next()) {

                System.out.println(resultSet.getInt("dni") + " |" + resultSet.getString("nombre") +
                        " " + resultSet.getString("apellido") + "  |" + resultSet.getInt("edad") +
                        " años |" + resultSet.getString("sexo") + " |" + resultSet.getString("categoría") +
                        " |" + resultSet.getString("cinturón"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarCompetidor() {
        //Busca y elimina un competidor en base a su dni

        Scanner entrada = new Scanner(System.in);
        String consulta = "SELECT * FROM competidor WHERE dni = ?";
        int dni, eleccion;

        //TP4: Eliminar de la BD
        System.out.print("\nIngrese DNI(sin puntos) del competidor a eliminar: ");
        dni = entrada.nextInt();

        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, dni);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            System.out.print("\nDesea eliminar al competidor " + resultSet.getString("nombre") + " " +
                    resultSet.getString("apellido") + ",DNI: " + resultSet.getInt("dni") +
                    "?\n1, SI" +
                    "\n2, NO" +
                    "\n: ");
            eleccion = entrada.nextInt();

            if (eleccion == 1) {
                consulta = "DELETE FROM competidor WHERE dni = ?";
                statement = conexion.prepareStatement(consulta);
                statement.setInt(1, dni);
                statement.executeUpdate();

                System.out.println("Competidor eliminado");
            } else if (eleccion == 2) {
                System.out.println("Operación abortada");
            } else {//Si se ingresa otro número
                System.out.println("Ingreso erróneo. Vuelva a intentar ingresando '1' o '2'.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificarCompetidor() {
        //Permite modificar un dato cualquiera de un competidor

        Scanner entrada = new Scanner(System.in);
        String consulta = "SELECT * FROM competidor WHERE dni = ?";
        int dni, eleccion;

        //TP4: Actualizar datos en la BD
        System.out.print("\nIngrese DNI(sin puntos) del competidor a modificar: ");
        dni = entrada.nextInt();

        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, dni);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            //Se exponen los datos de este único competidor para su visualización detallada y elección
            System.out.println("\nDatos del competidor: " +
                    "\n  1:DNI  |   2:Nombre  | 3:Apellido | 4:Edad |  5:Sexo  | 6:Categoría | 7:Cinturón | 8:Escuela \n" +
                    resultSet.getInt("dni") + " |" + resultSet.getString("nombre") + " |" +
                    resultSet.getString("apellido") + "  |" + resultSet.getInt("edad") +
                    " años |" + resultSet.getString("sexo") + " |" + resultSet.getString("categoría") +
                    " |" + resultSet.getString("cinturón") + " |" + resultSet.getString("escuela"));
            System.out.print("\nIngrese número del dato a modificar (0 para ninguno): ");
            eleccion = entrada.nextInt();
            entrada.nextLine();

            switch (eleccion) { //Con base en la elección, se toma y actualiza el dato solicitado
                case 1:

                    consulta = "UPDATE competidor SET dni = ? WHERE dni = ?";
                    statement = conexion.prepareStatement(consulta);
                    statement.setInt(2, dni);

                    System.out.print("DNI: ");
                    statement.setInt(1, entrada.nextInt());
                    statement.executeUpdate();
                    break;

                case 2:

                    consulta = "UPDATE competidor SET nombre = ? WHERE dni = ?";
                    statement = conexion.prepareStatement(consulta);
                    statement.setInt(2, dni);

                    System.out.print("Nombre: ");
                    statement.setString(1, entrada.nextLine().toUpperCase());
                    statement.executeUpdate();
                    break;

                case 3:

                    consulta = "UPDATE competidor SET apellido = ? WHERE dni = ?";
                    statement = conexion.prepareStatement(consulta);
                    statement.setInt(2, dni);

                    System.out.print("Apellido: ");
                    statement.setString(1, entrada.nextLine().toUpperCase());
                    statement.executeUpdate();
                    break;

                case 4:

                    consulta = "UPDATE competidor SET edad = ? WHERE dni = ?";
                    statement = conexion.prepareStatement(consulta);
                    statement.setInt(2, dni);

                    System.out.print("Edad: ");
                    statement.setInt(1, entrada.nextInt());
                    statement.executeUpdate();
                    break;

                case 5:

                    consulta = "UPDATE competidor SET sexo = ? WHERE dni = ?";
                    statement = conexion.prepareStatement(consulta);
                    statement.setInt(2, dni);

                    System.out.print("Sexo (masculino/femenino): ");
                    statement.setString(1, entrada.nextLine().toUpperCase());
                    statement.executeUpdate();
                    break;

                case 6:

                    consulta = "UPDATE competidor SET categoría = ? WHERE dni = ?";
                    statement = conexion.prepareStatement(consulta);
                    statement.setInt(2, dni);

                    System.out.print("Categoría (galo, pluma, pena, leve, medio, medio pesado, pesado, super pesado): ");
                    statement.setString(1, entrada.nextLine().toUpperCase());
                    statement.executeUpdate();
                    break;

                case 7:

                    consulta = "UPDATE competidor SET cinturón = ? WHERE dni = ?";
                    statement = conexion.prepareStatement(consulta);
                    statement.setInt(2, dni);

                    System.out.print("Cinturón (blanco, azul, violeta, cafe, negro): ");
                    statement.setString(1, entrada.nextLine().toUpperCase());
                    statement.executeUpdate();
                    break;

                case 8:

                    consulta = "UPDATE competidor SET escuela = ? WHERE dni = ?";
                    statement = conexion.prepareStatement(consulta);
                    statement.setInt(2, dni);

                    System.out.print("Escuela: ");
                    statement.setString(1, entrada.nextLine().toUpperCase());
                    statement.executeUpdate();
                    break;

                case 0:

                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void verificarPeso() {
        //Comprueba la categoría (en peso) de un competidor a partir de su peso actual

        Scanner entrada = new Scanner(System.in);
        String consulta;
        int dni;
        double peso;
        double[] rango = new double[2];

        //TP4
        System.out.print("\nIngrese DNI(sin puntos) del competidor a verificar: ");
        dni = entrada.nextInt();

        consulta = "SELECT * FROM competidor WHERE dni = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, dni);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            //Se presentan nombre y categoría inscripta del competidor para cerciorar durante el proceso
            System.out.print("\nCompetidor: " + resultSet.getString("nombre") + " " + resultSet.getString("apellido") +
                    ", Categoría: " + resultSet.getString("categoría") + "\nIngrese el peso actual del competidor (e,d):");
            peso = entrada.nextDouble();

            //Llamado al método que calcula el rango de peso aceptado para cada categoría
            rango = obtenerRango(resultSet.getString("categoría"), resultSet.getString("sexo"), resultSet.getInt("edad"));

            if (peso > rango[0] && peso <= rango[1]) {
                //Si el peso se encuentra dentro de los límites, aprueba el pesaje
                System.out.println("Pesaje verificado con éxito en categoría " + resultSet.getString("categoría") +
                        ": " + rango[0] + " a " + rango[1] + "kg");
            } else {
                //Si el pesaje no coincide, se notifica la comparativa entre el peso actual y la categoría
                System.out.println("Error, el pesaje no coincide con la categoría inscripta. Peso actual: " +
                        peso + "kg. Peso para la categoria " + resultSet.getString("categoría") + ": " +
                        rango[0] + " a " + rango[1] + "kg");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
