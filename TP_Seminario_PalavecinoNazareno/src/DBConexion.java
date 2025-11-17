import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexion {
    //Clase para definir

    private static String url = "jdbc:mysql://localhost:3306/torneo_jiujitsu_db", user = "root", password = "12345678";

    public static Connection conectar() {

        Connection coneccion = null;

        try {
            coneccion = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return coneccion;
    }
}
