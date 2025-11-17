import java.util.Scanner;

public class InterfazEncuentro extends InterfazDeUsuario{

    @Override
    public void llamarInterfaz() {

        Encuentro encuentro = GestorEncuentros.encuentro;

        int eleccion, puntosC1, puntosC2;
        boolean continuar = false;
        Scanner entrada = new Scanner(System.in);

        //Menú de operaciones
        do {
            puntosC1 = encuentro.getPuntosC1() - encuentro.getFaltasC1()/2;
            puntosC2 = encuentro.getPuntosC2() - encuentro.getFaltasC2()/2;
            System.out.println    ("\n  --Menú de Encuentros--" +
                    "\n       Competidor 1 | Competidor 2" +
                    "\nNombre: " + encuentro.getCompetidor1().getNombre() + " " + encuentro.getCompetidor1().getApellido() +
                    " | " + encuentro.getCompetidor2().getNombre() + " " + encuentro.getCompetidor2().getApellido() +
                    "\nPts:         " + puntosC1 + "     |     " + puntosC2 +
                    "\nFaltas:      " + encuentro.getFaltasC1() + "     |     " + encuentro.getFaltasC2());

            //Presentar opciones
            System.out.print("\nIngrese:" +
                    "\n1, para sumar puntos" +
                    "\n2, para restar puntos" +
                    "\n3, para sumar faltas" +
                    "\n4, para restar faltas." +
                    "\n5, para finalización" +
                    "\n6, para remover finalización" +
                    "\n0, para detener el encuentro." +
                    "\n: ");
            eleccion = entrada.nextInt();
            System.out.print("\n");

            //Derivar opción con estructura condicional switch
            switch (eleccion) {

                case 1:
                    System.out.print("\nIngrese:" +
                            "\n1, para Competidor 1" +
                            "\n2, para Competidor 2" +
                            "\n: ");
                    eleccion = entrada.nextInt();

                    if (eleccion == 1) {
                        System.out.print("\nIngrese cantidad a sumar: ");
                        encuentro.sumarPuntosC1(entrada.nextInt());
                    } else if (eleccion == 2) {
                        System.out.print("\nIngrese cantidad a sumar: ");
                        encuentro.sumarPuntosC2(entrada.nextInt());
                    }
                    break;

                case 2:
                    System.out.print("\nIngrese:" +
                            "\n1, para Competidor 1" +
                            "\n2, para Competidor 2" +
                            "\n: ");
                    eleccion = entrada.nextInt();

                    if (eleccion == 1) {
                        System.out.print("\nIngrese cantidad a restar: ");
                        encuentro.restarPuntosC1(entrada.nextInt());
                    } else if (eleccion == 2) {
                        System.out.print("\nIngrese cantidad a restar: ");
                        encuentro.restarPuntosC2(entrada.nextInt());
                    }
                    break;

                case 3:
                    System.out.print("\nIngrese:" +
                            "\n1, para Competidor 1" +
                            "\n2, para Competidor 2" +
                            "\n: ");
                    eleccion = entrada.nextInt();

                    if (eleccion == 1) {
                        System.out.print("\nIngrese cantidad a sumar: ");
                        encuentro.sumarFaltasC1(entrada.nextInt());
                    } else if (eleccion == 2) {
                        System.out.print("\nIngrese cantidad a sumar: ");
                        encuentro.sumarFaltasC2(entrada.nextInt());
                    }
                    break;

                case 4:
                    System.out.print("\nIngrese:" +
                            "\n1, para Competidor 1" +
                            "\n2, para Competidor 2" +
                            "\n: ");
                    eleccion = entrada.nextInt();

                    if (eleccion == 1) {
                        System.out.print("\nIngrese cantidad a restar: ");
                        encuentro.restarFaltasC1(entrada.nextInt());
                    } else if (eleccion == 2) {
                        System.out.print("\nIngrese cantidad a restar: ");
                        encuentro.restarFaltasC2(entrada.nextInt());
                    }
                    break;

                case 5:
                    System.out.print("\nIngrese:" +
                            "\n1, para Competidor 1" +
                            "\n2, para Competidor 2" +
                            "\n: ");
                    eleccion = entrada.nextInt();

                    if (eleccion == 1) {
                        encuentro.sumarFinalizacionC1(entrada.nextInt());
                        System.out.print("\nFinalización asignada");
                    } else if (eleccion == 2) {
                        encuentro.sumarFinalizacionC2(entrada.nextInt());
                        System.out.print("\nFinalización asignada");
                    }
                    break;

                case 6:
                    System.out.print("\nIngrese:" +
                            "\n1, para Competidor 1" +
                            "\n2, para Competidor 2" +
                            "\n: ");
                    eleccion = entrada.nextInt();

                    if (eleccion == 1) {
                        encuentro.restarFinalizacionC1(entrada.nextInt());
                        System.out.print("\nFinalización removida");
                    } else if (eleccion == 2) {
                        encuentro.restarFinalizacionC2(entrada.nextInt());
                        System.out.print("\nFinalización removida");
                    }
                    break;

                case 0:
                    //Finaliza el bucle

                    continuar = true;
                    break;
            }
        } while (!continuar);
    }
}
