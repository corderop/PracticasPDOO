/**
 * Practica de:
 * 
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;

public class Calle extends Casilla{
    private TituloPropiedad titulo;

    Calle(int numeroCasilla, int coste, TituloPropiedad ttl){
        super(numeroCasilla, coste);
        this.titulo = ttl;
    }

    @Override
    protected TipoCasilla getTipo(){
        return TipoCasilla.CALLE;
    }

    @Override
    protected boolean soyEdificable(){
        return true;
    }

    @Override
    protected TituloPropiedad getTitulo() {
        return titulo;
    }

    @Override
    public boolean tengoPropietario(){
        return this.titulo.tengoPropietario();
    }

    public TituloPropiedad asignarPropietario(Jugador jugador){
        titulo.setPropietario(jugador);

        return titulo;
    }

    private void setTitulo(TituloPropiedad tp){
        this.titulo = tp;
        super.setCoste(this.titulo.getPrecioCompra());
    }

    public int pagarAlquiler(){
        return titulo.pagarAlquiler();
    }

    public boolean propietarioEncarcelado(){
        return this.titulo.propietarioEncarcelado();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\ttipo= CALLE" + "\n\ttitulo=" + titulo;
    }
    
}
