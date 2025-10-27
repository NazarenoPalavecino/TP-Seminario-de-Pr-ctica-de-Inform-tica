public class InterfazFichaje extends InterfazDeUsuario{
    //Clase de vista empleada durante el proceso de fichajes

    @Override
    public void llamarMenu() {

        GestorCompetidores gestorCompetidores = new GestorCompetidores();
        int eleccion;
        boolean continuar = false;

        //Menu de operaciones
        do {
            System.out.println    ("\n-Área de Fichajes-");

            //Presentar opciones
            System.out.print("\nIngrese:" +
                    "\n1, para registrar competidor" +
                    "\n2, para ver competidores" +
                    "\n3, para eliminar competidor" +
                    "\n4, para modificar competidor" +
                    "\n0, para cerrar el menú." +
                    "\n: ");
            eleccion = verificarEntrada(0,4);
            System.out.print("\n");

            //Derivar opción con estructura condicional switch
            switch (eleccion) {

                case 1:
                    //Deriva al controlador
                    gestorCompetidores.registrarCompetidor();

                    break;

                case 2:
                    //Deriva al controlador
                    gestorCompetidores.verCompetidores();

                    break;

                case 3:
                    //Deriva al controlador
                    gestorCompetidores.eliminarCompetidor();

                    break;

                case 4:
                    //Deriva al controlador
                    gestorCompetidores.modificarCompetidor();

                    break;

                case 0:
                    //Finaliza el bucle

                    continuar = true;
                    break;
            }

        } while (!continuar);
    }
}
