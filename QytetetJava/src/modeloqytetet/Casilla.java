/*
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;

public class Casilla {
    private int numeroCasilla = 0;
    private int coste;
    private TipoCasilla tipo;
    private TituloPropiedad titulo;
    
    /**
     * MÉTODOS SIN IMPLEMENTAR
     * 
     * TituloPropiedad asignarPropietario(Jugador jugador);
     * int pagarAlquiler();
     * boolean propietarioEncarcelado();
     * boolean soyEdificable();
     * boolean tengoPropietario();
     */

    // Constructores para casillas que no son calle
    Casilla(int numeroCasilla, TipoCasilla tipo){
        this.tipo = tipo;
        this.numeroCasilla = numeroCasilla;
    }
    
    //Constructor para casilla que son calle
    Casilla(int numeroCasilla, TituloPropiedad titulo) {
        this.numeroCasilla = numeroCasilla;
        this.tipo = TipoCasilla.CALLE;
        setTitulo(titulo);
    }

    int getNumeroCasilla() {
        return numeroCasilla;
    }

    int getCoste() {
        return coste;
    }

    TipoCasilla getTipo() {
        return tipo;
    }

    TituloPropiedad getTitulo() {
        return titulo;
    }

    private void setTitulo(TituloPropiedad tp){
        this.titulo = tp;
        this.coste = this.titulo.getPrecioCompra();
    }
    
    @Override
    public String toString() {
        if(this.tipo==TipoCasilla.CALLE)
            return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", precioCompra=" + coste + ", tipo=" + tipo + ", titulo=" + titulo + '}';
        else
            return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", tipo=" + tipo + '}';
    }
    
}
