/*
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;

public class TituloPropiedad {
    private int alquilerBase;
    private float factorRevalorizacion;
    private int hipotecaBase;
    private boolean hipotecada = false;
    private String nombre;
    private int numCasas = 0;
    private int numHoteles = 0;
    private int precioCompra;
    private int precioEdificar;
    private Jugador propietario = null;
    
    /**
     * MÉTODOS SIN IMPLEMENTAR
     * 
     * int calcularCosteCancelar();
     * int calcularCosteHipotecar();
     * int calcularImporteAlquiler();
     * int calcularPrecioVenta();
     * void cancelarHipoteca();
     * void cobrarAlquiler(int coste);
     * void edificarCasa();
     * void edificarHotel();
     * int hipotecar();
     * int pagarAlquiler();
     * boolean propietarioEncarcelado();
     * boolean tengoPropietario();
     */
    
    TituloPropiedad(String nombre, int pCompra, int aBase, float fRevalorizacion, int hBase, int pEdificar) {
        this.nombre = nombre;
        
        if( pCompra>=500 && pCompra<=1000)
            this.precioCompra = pCompra;
        
        if( aBase>=50 && aBase<=100)
            this.alquilerBase = aBase;
        
        if( ( fRevalorizacion>=10 && fRevalorizacion<=20 ) || ( fRevalorizacion>=-20 && fRevalorizacion<=-10 ))
            this.factorRevalorizacion = fRevalorizacion;
        
        if( hBase>=150 && hBase<=1000)
            this.hipotecaBase = hBase;
            
        if( pEdificar>=250 && pEdificar<=750)
            this.precioEdificar = pEdificar;
    }

    // get y set ---------------------------
    String getNombre() {
        return nombre;
    }

    boolean getHipotecada() {
        return hipotecada;
    }

    int getPrecioCompra() {
        return precioCompra;
    }

    int getAlquilerBase() {
        return alquilerBase;
    }

    float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

    int getHipotecaBase() {
        return hipotecaBase;
    }

    int getPrecioEdificar() {
        return precioEdificar;
    }

    int getNumHoteles() {
        return numHoteles;
    }

    int getNumCasas() {
        return numCasas;
    }

    Jugador getPropietario(){
        return propietario;
    }

    void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }
    
    void setPropietario( Jugador propietario){
        this.propietario = propietario;
    }
    // get y set ---------------------------

    @Override
    public String toString() {
        return "TituloPropiedad{" + "nombre=" + nombre + ", hipotecada=" + hipotecada + ", precioCompra=" + precioCompra + ", alquilerBase=" + alquilerBase + ", factorRevalorizacion=" + factorRevalorizacion + ", hipotecaBase=" + hipotecaBase + ", precioEdificar=" + precioEdificar + ", numHoteles=" + numHoteles + ", numCasas=" + numCasas + '}';
    }    
}
