/**
 * Practica de:
 * 
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;

public abstract class Casilla {
    private int numeroCasilla = 0;
    private int coste;

    // Constructores con parámetros para casilla
    Casilla(int numeroCasilla, int coste){
        this.coste = coste;
        this.numeroCasilla = numeroCasilla;
    }

    // Constructor copia casilla
//    Casilla(Casilla casilla){
//        this.numeroCasilla = casilla.numeroCasilla;
//        this.coste = casilla.coste;
//    }   

    int getNumeroCasilla() {
        return numeroCasilla;
    }

    int getCoste() {
        return coste;
    }

    public void setCoste(int coste){
        this.coste = coste;
    }

    protected abstract TituloPropiedad getTitulo();
    protected abstract TipoCasilla getTipo();
    protected abstract boolean soyEdificable();
    public abstract boolean tengoPropietario();

    @Override
    public String toString() {
        return "\n-----\nCasilla:" + "\n\tnumeroCasilla=" + numeroCasilla + "\n\tprecioCompra=" + coste;
    }
    
}
