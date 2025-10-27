import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class InterfazDeUsuario {
    //Clase abstracta que establece los métodos presentes en las clases de vista.

    public abstract void llamarMenu();
    //Clase abstracta a sobreescribir con el menú propio de cada interfaz.

    protected int verificarEntrada (int a, int b) {
        //Método que aúna las múltiples necesidades de controlar el ingreso de datos

        Scanner entrada = new Scanner(System.in);
        int dato = 0;//Almacena temporalmente el dato ingresado

        do {
            try {
                dato = entrada.nextInt();//Posible InputMismatchException

                if (dato >= a && dato <= b) {
                    //Valor y tipo de dato correctos
                    break;

                } else {
                    //Valor erróneo, se reitera la operación
                    System.out.print("Error, número inadecuado. A continuación intente nuevamente.\n: ");
                }

            } catch (InputMismatchException e) {
                //Tipo de dato erróneo, se reitera la operación
                entrada.next();
                System.out.print("Error, tipo de dato erróneo. A continuación intente nuevamente.\n: ");
            }
        } while (true);
        return dato;
    }
}
