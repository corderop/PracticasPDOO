/**
 * Practica de:
 * 
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
    
    boolean propietarioEncarcelado(){
        return propietario.getEncarcelado();
    }
    
    boolean tengoPropietario(){
        return propietario!=null;
    }
    
    void edificarCasa(){
        numCasas += 1;
    }
    
    int pagarAlquiler(){
        int costeAlquiler = calcularImporteAlquiler();
        propietario.modificarSaldo(costeAlquiler);

        return costeAlquiler;
    }

    int calcularImporteAlquiler(){
        return (int) (alquilerBase + (numCasas*0.5 + numHoteles*2));
    }
    
    int hipotecar(){
        int costeHipoteca = calcularCosteHipotecar();
        setHipotecada(true);

        return costeHipoteca;
    }
    
    int calcularCosteHipotecar(){
        return (int) (hipotecaBase + numCasas*0.5 + hipotecaBase + numHoteles*hipotecaBase);
    }

    int calcularPrecioVenta(){
        return (int) (precioCompra + ( numCasas + numHoteles ) * precioEdificar * factorRevalorizacion);
    }
    
    void edificarHotel(){
        numHoteles += 1;
        numCasas -= 4;
    }
    
    int calcularCosteCancelar(){
        int costeHipotecar = calcularCosteHipotecar();
        return (int) (costeHipotecar + costeHipotecar*0.1);
    }
    
    void cancelarHipoteca(){
        hipotecada = false;
    }

    @Override
    public String toString() {
        return "\n\tTituloPropiedad" + "\n\t\tnombre=" + nombre + "\n\t\thipotecada=" + hipotecada + "\n\t\tprecioCompra=" + precioCompra + "\n\t\talquilerBase=" + alquilerBase + "\n\t\tfactorRevalorizacion=" + factorRevalorizacion + "\n\t\thipotecaBase=" + hipotecaBase + "\n\t\tprecioEdificar=" + precioEdificar + "\n\t\tnumHoteles=" + numHoteles + "\n\t\tnumCasas=" + numCasas;
    }    
}
