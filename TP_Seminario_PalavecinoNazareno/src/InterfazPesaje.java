public class InterfazPesaje extends InterfazDeUsuario{
    //Clase de vista empleada durante el proceso de pesaje

    @Override
    public void llamarInterfaz() {

        GestorCompetidores gestorCompetidores = new GestorCompetidores();
        int eleccion;
        boolean continuar = false;

        //Menú de operaciones
        do {
            System.out.println    ("\n-Área de Pesaje-");

            //Presentar opciones
            System.out.print("\nIngrese:" +
                    "\n1, para verificar peso" +
                    "\n2, para eliminar competidor" +
                    "\n3, para modificar competidor" +
                    "\n0, para cerrar el menú." +
                    "\n: ");
            eleccion = verificarEntrada(0,3);
            System.out.print("\n");

            //Derivar opción con estructura condicional switch
            switch (eleccion) {

                case 1:
                    //Deriva al controlador para verificar peso
                    gestorCompetidores.verificarPeso();

                    break;

                case 2:
                    //Deriva al controlador para eliminar competidor.
                    // Se utiliza cuando un competidor falla el pesaje.
                    gestorCompetidores.eliminarCompetidor();

                    break;

                case 3:
                    //Deriva al controlador para modificar competidor.
                    // Se utiliza para corregir datos o relocalizar al competidor en otra categoría.
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
