public class Operaciones {

    Operaciones(String operador, double primerValor, double segundoValor) {

        switch (operador) {
            case "+":
                Suma(primerValor, segundoValor);
                break;
            case "-":
                Resta(primerValor, segundoValor);
                break;
            case "/":
                Division(primerValor, segundoValor);
                break;
            case "x":
                Multiplicacion(primerValor, segundoValor);
                break;
            case "x\u00B2":
                Cuadratica(primerValor);
                break;
            case "\u221Ax":
                Raiz(primerValor);
                break;
            case "x\u02B8":
                ElevadaY(primerValor, segundoValor);
                break;
            case "%":
                Porcentaje(primerValor);
                break;
        }
    }

    private double Suma(double primerValor, double segundoValor) {
        return primerValor + segundoValor;
    }

    private double Resta(double primerValor, double segundoValor) {
        return primerValor - segundoValor;
    }

    private double Multiplicacion(double primerValor, double segundoValor) {
        return primerValor * segundoValor;
    }

    private double Division(double primerValor, double segundoValor) {
        return primerValor / segundoValor;
    }

    private double ElevadaY(double primerValor, double segundoValor) {
        double nuevoValor = 0;
        while ((segundoValor -= 1) > 0) {
            nuevoValor *= primerValor;
        }
        return primerValor;
    }

    private double Cuadratica(double primerValor) {
        return primerValor * primerValor;
    }

    private double Raiz(double primerValor) {
        return primerValor * primerValor;
    }

    private double Porcentaje(double primerValor) {
        return primerValor / 100;
    }

}