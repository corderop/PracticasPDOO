/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;
import java.util.ArrayList;

public class Jugador {
    private boolean encarcelado = false;
    private String nombre;
    private int saldo = 7500;
    private Sorpresa cartaLibertad = null;
    private Casilla casillaActual;
    private ArrayList<TituloPropiedad> propiedades;
    
    /**
     * MÉTODOS SIN IMPLEMENTAR
     * 
     * boolean cancelarHipoteca(TituloPropiedad titulo);
     * boolean comprarTituloPropiedad();
     * int cuantasCasasHotelesTengo();
     * boolean deboPagarAlquiler();
     * Sorpresa devolverCartaLibertad();
     * boolean edificarCasa( TituloPropiedad titulo);
     * boolean edificarHotel( TituloPropiedad titulo);
     * private void eliminarDeMisPropiedades(TituloPropiedad titulo);
     * private boolean esDeMiPropiedad(TituloPropiedad titulo);
     * boolean estoyEnCalleLibre();
     * boolean hipotecarPropiedad(TituloPropiedad titulo);
     * void irACarcel(Casilla casilla);
     * int modificarSaldo( int cantidad);
     * int obtenerCapital();
     * ArrayList<TituloPropiedad> obtenerPropiedades( boolean hipotecada);
     * void pagarAlquiler();
     * void pagarImpuesto();
     * void pagarLibertad(int cantidad);
     * boolean tengoCartaLibertad();
     * private boolean tengoSaldo(int cantidad);
     * boolean venderPropiedad(Casilla casilla);
     */
    
    Jugador(String nombre, Casilla casillaActual, ArrayList<TituloPropiedad> propiedades){
        this.nombre = nombre;
        this.casillaActual = casillaActual;
        this.propiedades = propiedades;
    }

    boolean getEncarcelado() {
        return encarcelado;
    }

    String getNombre() {
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    Sorpresa getCartaLibertad() {
        return cartaLibertad;
    }

    Casilla getCasillaActual() {
        return casillaActual;
    }

    ArrayList<TituloPropiedad> getPropiedades() {
        return propiedades;
    }

    void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }

    void setCartaLibertad(Sorpresa cartaLibertad) {
        this.cartaLibertad = cartaLibertad;
    }

    void setCasillaActual(Casilla casillaActual) {
        this.casillaActual = casillaActual;
    }

    @Override
    public String toString() {
        return "Jugador{" + "encarcelado=" + encarcelado + ", nombre=" + nombre + ", saldo=" + saldo + ", cartaLibertad=" + cartaLibertad + ", casillaActual=" + casillaActual + ", propiedades=" + propiedades + '}';
    }
}
