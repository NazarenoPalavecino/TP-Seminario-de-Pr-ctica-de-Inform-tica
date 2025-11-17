import java.sql.*;

public class GestorEncuentros {

    private Connection conexion;
    public static Encuentro encuentro;

    public GestorEncuentros() {
        this.conexion = DBConexion.conectar();
    }

    public Competidor realizarEncuentro(int id_llave,Encuentro encuentro) {

        GestorEncuentros.encuentro = encuentro;

        InterfazEncuentro interfazEncuentro = new InterfazEncuentro();
        interfazEncuentro.llamarInterfaz();

        Integer id_encuentro = -1, n;
        String consulta = "INSERT INTO encuentro (id_llave) VALUES (" + id_llave + ")";
        try {

            Statement sentencia = conexion.createStatement();
            n = sentencia.executeUpdate(consulta, Statement.RETURN_GENERATED_KEYS);
            ResultSet nuevoID = sentencia.getGeneratedKeys();
            nuevoID.next();
            id_encuentro = nuevoID.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        consulta = "INSERT INTO competidor_encuentro (id_encuentro, dni_competidor, puntos, faltas, finalización) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, id_encuentro);
            sentencia.setInt(2, encuentro.getCompetidor1().getDni());
            sentencia.setInt(3, encuentro.getPuntosC1());
            sentencia.setInt(4, encuentro.getFaltasC1());
            sentencia.setInt(5, encuentro.getFinalizacionC1());
            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, id_encuentro);
            sentencia.setInt(2, encuentro.getCompetidor2().getDni());
            sentencia.setInt(3, encuentro.getPuntosC2());
            sentencia.setInt(4, encuentro.getFaltasC2());
            sentencia.setInt(5, encuentro.getFinalizacionC2());
            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int puntosC1 = encuentro.getPuntosC1() - encuentro.getFaltasC1()/2,
                puntosC2 = encuentro.getPuntosC2() - encuentro.getFaltasC2()/2;
        if (encuentro.getFinalizacionC1() == 1 || (puntosC1 > puntosC2 && encuentro.getFinalizacionC2() != 1)) {
            //Si el competidor uno gana si finalizó a su rival o si lo superó en puntos sin ser finalizado
            return encuentro.getCompetidor1();

        } else if (encuentro.getFinalizacionC2() == 1 || (puntosC1 < puntosC2 && encuentro.getFinalizacionC1() != 1)) {
            //El competidor dos gana si finalizo a su rival o si lo superó en puntos sin ser finalizado
            return encuentro.getCompetidor2();
        }

        return encuentro.getCompetidor1();
    }

    public void sumarPuntos(int n) {

    }

    public void restarPuntos(int n) {

    }

    public void sumarFaltas(int n) {

    }

    public void restarFaltas(int n) {

    }

    public void asignarFinalizacion(int n) {

    }

    public void removerFinalización(int n) {

    }

    public Encuentro getEncuentro() {
        return encuentro;
    }

    public void setEncuentro(Encuentro encuentro) {
        this.encuentro = encuentro;
    }
}
