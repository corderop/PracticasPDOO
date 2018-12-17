/**
 * Practica de:
 * 
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;

public class Especulador extends Jugador{
    private int fianza;

    protected Especulador(Jugador jugador, int fza){
        super(jugador);
        this.fianza = fza;
    }

    @Override
    protected void pagarImpuesto(){
        super.modificarSaldo(-super.getCasillaActual().getCoste()/2);
    }
    
    @Override
    protected boolean deboIrACarcel(){
        return (super.deboIrACarcel() && !pagarFianza());
    }

    @Override
    protected Especulador convertirme(int fza){
        return this;
    }
    
    private boolean pagarFianza(){
        boolean salida = super.getSaldo() >= fianza;
        
        if(salida){
            super.modificarSaldo(-fianza);
        }
        
        return salida;
    }
    
    @Override
    protected boolean puedoEdificarCasa(TituloPropiedad titulo){
        int numCasas = titulo.getNumCasas();

        return numCasas<8;
    }
    
    @Override
    protected boolean puedoEdificarHotel(TituloPropiedad titulo){
        int numHoteles = titulo.getNumHoteles();
        int numCasas = titulo.getNumCasas();

        return (numHoteles<8 && numCasas>=4);
    }

    @Override
    public String toString() {
        return "\n-----\nEspeculador:" + super.toString() + "\n\tfianza=" + fianza;
    }
}
