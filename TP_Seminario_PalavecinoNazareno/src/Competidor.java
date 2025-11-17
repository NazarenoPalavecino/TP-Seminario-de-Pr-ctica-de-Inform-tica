public class Competidor {
    //Clase modelo del competidor, entidad principal del sistema

    //Atributos con acceso privado
    private String nombre, apellido, sexo, cinturon, escuela, categoria;
    private int dni, edad;

    //Constructor
    public Competidor(String nombre, String apellido, String sexo, int dni, int edad, String cinturon,
                      String escuela, String categoria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.dni = dni;
        this.edad = edad;
        this.cinturon = cinturon;
        this.escuela = escuela;
        this.categoria = categoria;
    }

    //Setters y Getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCinturon() {
        return cinturon;
    }

    public void setCinturon(String cinturon) {
        this.cinturon = cinturon;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

