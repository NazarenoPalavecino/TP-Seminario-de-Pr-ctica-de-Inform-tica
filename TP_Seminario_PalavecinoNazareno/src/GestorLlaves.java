import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class GestorLlaves {

    private Connection conexion;

    public GestorLlaves(){
        this.conexion = DBConexion.conectar();
    }

    public void crearLlave(String categoriaSexo, String categoriaPeso,String categoriaCinturon, String categoriaEdad) {

        Llave llave = new Llave(categoriaPeso, categoriaSexo, categoriaEdad, categoriaCinturon);

        String consulta = "INSERT INTO llave (categoría_sexo, categoría_peso, categoría_cinturón, categoría_edad) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, categoriaSexo);
            sentencia.setString(2, categoriaPeso);
            sentencia.setString(3, categoriaCinturon);
            sentencia.setString(4, categoriaEdad);

            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarLlave(int id) {

        String consulta = "DELETE FROM llave WHERE id_llave = ?";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1,id);
            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void verLlaves() {

        String consulta = "SELECT * FROM llave";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultSet = sentencia.executeQuery();

            System.out.println("\n            Listado de Llaves Disponibles" +
                    "\nN° | Cinturón |  Edad  |  Categoría  |  Sexo  | Estado");

            while (resultSet.next()) {

                System.out.print("\n" + resultSet.getInt("id_llave") + "  |" + resultSet.getString("categoría_cinturón") +
                        "    |" + resultSet.getString("categoría_edad") + "  |" + resultSet.getString("categoría_peso") +
                        " |" + resultSet.getString("categoría_sexo"));

                if (resultSet.getInt("dni_ganador") != 0) {
                    System.out.print(" | Finalizada");
                } else {
                    System.out.print(" | A definir");
                }

            }
            System.out.println(" ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ejecutarLlave(int id_llave) {

        GestorEncuentros gestorEncuentros = new GestorEncuentros();
        ResultSet resultSet1, resultSet2;
        PreparedStatement sentencia1, sentencia2;
        ArrayList<Competidor> competidores = new ArrayList<>();
        String consulta1 = "SELECT * FROM llave WHERE id_llave = ?", consulta2 = "SELECT * FROM competidor WHERE categoría = ? AND cinturón = ? AND sexo = ?";

        try {
            sentencia1 = conexion.prepareStatement(consulta1);
            sentencia1.setInt(1, id_llave);
            resultSet1 = sentencia1.executeQuery();
            resultSet1.next();

            sentencia2 = conexion.prepareStatement(consulta2);
            sentencia2.setString(1, resultSet1.getString("categoría_peso"));
            sentencia2.setString(2, resultSet1.getString("categoría_cinturón"));
            sentencia2.setString(3, resultSet1.getString("categoría_sexo"));
            resultSet2 = sentencia2.executeQuery();

            while (resultSet2.next()) {
                int edad = resultSet2.getInt("edad");

                if ((edad == 16 || edad == 17) && Objects.equals(resultSet1.getString("categoría_edad"), "JUVENIL")) {
                    Competidor competidor = new Competidor(resultSet2.getString("nombre"), resultSet2.getString("apellido"),
                            resultSet2.getString("sexo"), resultSet2.getInt("dni"), resultSet2.getInt("edad"),
                            resultSet2.getString("cinturón"), resultSet2.getString("escuela"),
                            resultSet2.getString("categoría"));
                    competidores.add(competidor);

                } else if (edad >= 18 && Objects.equals(resultSet1.getString("categoría_edad"), "ADULTO")) {
                    Competidor competidor = new Competidor(resultSet2.getString("nombre"), resultSet2.getString("apellido"),
                            resultSet2.getString("sexo"), resultSet2.getInt("dni"), resultSet2.getInt("edad"),
                            resultSet2.getString("cinturón"), resultSet2.getString("escuela"),
                            resultSet2.getString("categoría"));
                    competidores.add(competidor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Competidor> ganadoresLlave = new ArrayList<>(competidores); //Listado de los competidores que ascienden en la llave

        //Se iteran las rondas de la llave hasta obtener un ganador
        while (ganadoresLlave.size() > 1) {
            ArrayList<Competidor> ganadoresRonda = new ArrayList<>(); //Sobreescribe a "ganadoresLlave" para actualizarlo cada ronda

            for (int i = 0; i < ganadoresLlave.size(); i = i+2) {

                //Se llama al gestor de encuentros para definir y almacenar el encuentro. Devuelve al ganador
                Encuentro encuentro = new Encuentro(ganadoresLlave.get(i), ganadoresLlave.get(i+1));

                Competidor ganador = gestorEncuentros.realizarEncuentro(id_llave, encuentro);
                ganadoresRonda.add(ganador);
            }
            ganadoresLlave = ganadoresRonda;
        }
        consulta1 = "UPDATE llave SET dni_ganador = ? WHERE id_llave = ?";
        try {
            sentencia1 = conexion.prepareStatement(consulta1);
            sentencia1.setInt(1, ganadoresLlave.getFirst().getDni());
            sentencia1.setInt(2, id_llave);
            sentencia1.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("-Llave finalizada-" +
                "\nEl ganador es: " + ganadoresLlave.getFirst().getNombre() + " " + ganadoresLlave.getFirst().getApellido());
    }

    public void verResultados(int id_llave) {

        String consulta1, consulta2, consulta3;
        PreparedStatement sentencia1, sentencia2, sentencia3;
        ResultSet rSetLlave, rSetGanador, rSetEncuentros;

        consulta1 = "SELECT * FROM llave WHERE id_llave = ?";
        try {
            sentencia1 = conexion.prepareStatement(consulta1);
            sentencia1.setInt(1, id_llave);
            rSetLlave = sentencia1.executeQuery();
            rSetLlave.next();

            if (rSetLlave.getInt("dni_ganador") == 0) {
                System.out.println("Esta llave aún no posee resultados");
            } else {
                consulta2 = "SELECT * FROM competidor WHERE dni = ?";
                sentencia2 = conexion.prepareStatement(consulta2);
                sentencia2.setInt(1, rSetLlave.getInt("dni_ganador"));
                rSetGanador = sentencia2.executeQuery();
                rSetGanador.next();

                System.out.println("\n                   Resultados de Llave" +
                        "\nCategoría: Peso " + rSetLlave.getString("categoría_peso") + ", " +
                        rSetLlave.getString("categoría_edad") + ", " + rSetLlave.getString("categoría_sexo") +
                        "\nGANADOR: " + rSetGanador.getString("nombre") + " " + rSetGanador.getString("apellido") +
                        " (" + rSetGanador.getInt("dni") + ")" +
                        "\n" +
                        "\nEnc N° | Competidor |  Puntos  |  Faltas  |  Finalización  ");

                consulta3 = "SELECT * FROM encuentro, competidor_encuentro, competidor WHERE encuentro.id_llave = ? " +
                        "AND encuentro.id_encuentro = competidor_encuentro.id_encuentro " +
                        "AND competidor.dni = competidor_encuentro.dni_competidor";
                sentencia3 = conexion.prepareStatement(consulta3);
                sentencia3.setInt(1, id_llave);
                rSetEncuentros = sentencia3.executeQuery();

                while (rSetEncuentros.next()) {
                    System.out.println("\n  " + rSetEncuentros.getInt("id_encuentro") + "   |" +
                            rSetEncuentros.getString("nombre") + " " +
                            rSetEncuentros.getString("apellido")+ "    |  " +
                            rSetEncuentros.getInt("puntos") + "  |  " +
                            rSetEncuentros.getInt("faltas") + "  |  " +
                            rSetEncuentros.getInt("finalización"));

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
