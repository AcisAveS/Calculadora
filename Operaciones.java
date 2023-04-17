
public class Operaciones {

    public double RealizarOperacion(String operador, double primerValor, double segundoValor) {
        double resultado = 0;
        switch (operador) {
            case "+":
                resultado = Suma(primerValor, segundoValor);
                break;
            case "-":
                resultado = Resta(primerValor, segundoValor);
                break;
            case "/":
                resultado = Division(primerValor, segundoValor);
                break;
            case "x":
                resultado = Multiplicacion(primerValor, segundoValor);
                break;
            case "x\u00B2":
                resultado = Cuadratica(primerValor);
                break;
            case "\u221Ax":
                resultado = Raiz(primerValor);
                break;
            case "x\u02B8":
                resultado = ElevadaY(primerValor, segundoValor);
                break;
            case "%":
                resultado = Porcentaje(primerValor);
                break;
        }
        return resultado;
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
        double nuevoValor = 1;
        while ((segundoValor--) > 0) {
            nuevoValor *= primerValor;
        }
        return nuevoValor;
    }

    private double Cuadratica(double primerValor) {
        return primerValor * primerValor;
    }

    private double Raiz(double primerValor) {
        return Math.sqrt(primerValor);
    }

    private double Porcentaje(double primerValor) {
        return primerValor / 100;
    }

}