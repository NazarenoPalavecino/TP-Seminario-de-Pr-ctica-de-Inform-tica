public class Encuentro {

    private Competidor competidor1, competidor2;
    private int puntosC1 = 0, puntosC2 = 0, ventajasC1 = 0, ventajasC2 = 0, faltasC1 = 0, faltasC2 = 0, finalizacionC1 = 0, finalizacionC2 = 0;

    public Encuentro(Competidor competidor1, Competidor competidor2) {
        this.competidor1 = competidor1;
        this.competidor2= competidor2;
    }

    public int getPuntosC1() {
        return puntosC1;
    }

    public int getPuntosC2() {
        return puntosC2;
    }

    public int getFaltasC1() {
        return faltasC1;
    }

    public int getFaltasC2() {
        return faltasC2;
    }

    public int getFinalizacionC1() {
        return finalizacionC1;
    }

    public int getFinalizacionC2() {
        return finalizacionC2;
    }

    public Competidor getCompetidor1() {
        return competidor1;
    }

    public Competidor getCompetidor2() {
        return competidor2;
    }

    public void sumarPuntosC1(int n) {
         puntosC1 = puntosC1 + n;
    }

    public void restarPuntosC1(int n) {
        puntosC1 = puntosC1 - n;
    }

    public void sumarPuntosC2(int n) {
        puntosC2 = puntosC2 + n;
    }

    public void restarPuntosC2(int n) {
        puntosC2 = puntosC2 - n;
    }

    public void sumarFaltasC1(int n) {
        faltasC1 = faltasC1 + n;
    }

    public void restarFaltasC1(int n) {
        faltasC1 = faltasC1 - n;
    }

    public void sumarFaltasC2(int n) {
        faltasC2 = faltasC2 + n;
    }

    public void restarFaltasC2(int n) {
        faltasC2 = faltasC2 - n;
    }

    public void sumarFinalizacionC1(int n) {
        finalizacionC1 = 1;
    }

    public void restarFinalizacionC1(int n) {
        finalizacionC1 = 0;
    }

    public void sumarFinalizacionC2(int n) {
        finalizacionC2 = 1;
    }

    public void restarFinalizacionC2(int n) {
        finalizacionC2 = 0;
    }
}
