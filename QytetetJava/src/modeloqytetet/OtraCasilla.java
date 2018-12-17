/**
 * Practica de:
 * 
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;

public class OtraCasilla extends Casilla{
    TipoCasilla tipo;

    OtraCasilla(int numeroCasilla, int coste, TipoCasilla tp){
        super(numeroCasilla, coste);
        this.tipo = tp;
    }

    @Override
    protected TipoCasilla getTipo(){
        return tipo;
    }

    @Override
    protected boolean soyEdificable(){
        return false;
    }
    
    @Override
    protected TituloPropiedad getTitulo() {
        return null;
    }

    @Override
    public boolean tengoPropietario(){
        return false;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\n\ttipo=" + tipo;
    }
}
