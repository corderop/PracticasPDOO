/**
 * Practica de:
 * 
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;
import java.util.ArrayList;

public class Jugador implements Comparable{
    private boolean encarcelado = false;
    private String nombre;
    private int saldo = 7500;
    private Sorpresa cartaLibertad = null;
    private Casilla casillaActual;
    private ArrayList<TituloPropiedad> propiedades;
    
    Jugador(String nombre, Casilla casillaActual, ArrayList<TituloPropiedad> propiedades, boolean encarc, int sald, Sorpresa cartaLib){
        this.nombre = nombre;
        this.casillaActual = casillaActual;
        this.propiedades = propiedades;
        this.encarcelado = encarc;
        this.saldo = sald;
        this.cartaLibertad = cartaLib;
    }

    protected Jugador( Jugador copia ){
        this.encarcelado = copia.encarcelado;
        this.nombre = copia.nombre;
        this.saldo = copia.saldo;
        this.cartaLibertad = copia.cartaLibertad;
        this.casillaActual = copia.casillaActual;
        this.propiedades = copia.propiedades;
    }

    Jugador(String nombre){
        this.nombre = nombre;
        this.casillaActual = null;
        this.propiedades = new ArrayList<>();
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
    
    int cuantasCasasHotelesTengo(){
        int suma = 0;
        
        for(int i=0; i<propiedades.size() ; i++)
            suma += propiedades.get(i).getNumCasas() + propiedades.get(i).getNumHoteles();
        
        return suma;
    }
    
    Sorpresa devolverCartaLibertad(){
        Sorpresa aux = cartaLibertad;
        cartaLibertad = null;
        
        return aux;
    }
    
    int obtenerCapital(){
        int suma = 0;
        
        suma += saldo;
        for( TituloPropiedad i : propiedades)
            suma += i.getPrecioCompra() + (i.getNumCasas()+i.getNumHoteles())*i.getPrecioEdificar();
        
        return suma;
    }
    
    protected void pagarImpuesto(){
        saldo -= casillaActual.getCoste();
    }
    
    boolean tengoCartaLibertad(){
        return cartaLibertad != null;
    }
    
    private boolean esDeMiPropiedad(TituloPropiedad titulo){
        boolean salida = false;
        
        for(TituloPropiedad i : propiedades)
            if( i == titulo)
                salida = true;
        
        return salida;
    }
    
    int modificarSaldo(int cantidad){
        saldo += cantidad;
        
        return saldo;
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedades(boolean estadoHipoteca){
        ArrayList<TituloPropiedad> salida = new ArrayList<>();
        
        for(TituloPropiedad i : propiedades )
            if(estadoHipoteca == i.getHipotecada())
                salida.add(i);
                
        return salida;
    }
    
    protected boolean tengoSaldo(int cantidad){
        return saldo > cantidad;
    }
    
    boolean comprarTituloPropiedad(){
        boolean comprado = false;

        int costeCompra = casillaActual.getCoste();
        if(costeCompra<saldo){
            TituloPropiedad titulo = ((Calle)casillaActual).asignarPropietario(this);
            propiedades.add(titulo);
            modificarSaldo(-costeCompra);
            comprado = true;
        }

        return comprado;
    }
    
    boolean edificarCasa(TituloPropiedad titulo){
        boolean edificada = false;

        if(this.puedoEdificarCasa(titulo)){
            int costeEdificarCasa = titulo.getPrecioEdificar();
            boolean tengoSaldo = tengoSaldo(costeEdificarCasa);
            if(tengoSaldo){
                titulo.edificarCasa();
                modificarSaldo(-costeEdificarCasa);
                edificada = true;
            }
        }

        return edificada;
    }
    
    void irACarcel(Casilla casilla){
        setCasillaActual(casilla);
        setEncarcelado(true);
    }
    
    void pagarLibertad(int cantidad){
        boolean tengoSaldo = tengoSaldo(cantidad);

        if(tengoSaldo){
            setEncarcelado(false);
            modificarSaldo(-cantidad);
        }
    }
    
    void pagarAlquiler(){
        int costeAlquiler = ((Calle)casillaActual).pagarAlquiler();
        modificarSaldo(-costeAlquiler);
    }
    
    boolean deboPagarAlquiler(){
        TituloPropiedad titulo = casillaActual.getTitulo();
        boolean esDeMiPropiedad = esDeMiPropiedad(titulo);
        boolean deboPagar = false;
        
        if(!esDeMiPropiedad){
            boolean tienePropietario = titulo.tengoPropietario();

            if(tienePropietario){
                boolean encarcelado = titulo.propietarioEncarcelado();
                if(!encarcelado){
                    boolean estaHipotecada = titulo.getHipotecada();
                    if(!estaHipotecada)
                        deboPagar = true;
                }
            }
        }

        return deboPagar;
    }
    
    void hipotecarPropiedad(TituloPropiedad titulo){
        int costeHipoteca = titulo.hipotecar();
        modificarSaldo(costeHipoteca);
    }
    
    void venderPropiedad(Casilla casilla){
        TituloPropiedad titulo = casilla.getTitulo();
        eliminarDeMisPropiedades(titulo);
        int precioVenta = titulo.calcularPrecioVenta();
        modificarSaldo(precioVenta);
    }
    
    private void eliminarDeMisPropiedades(TituloPropiedad titulo){
        propiedades.remove(titulo);
        titulo.setPropietario(null);
    }
    
    boolean edificarHotel( TituloPropiedad titulo){
        boolean edificado = false;

        if(this.puedoEdificarHotel(titulo)){
            int costeEdificarHotel = titulo.getPrecioEdificar();
            boolean tengoSaldo = tengoSaldo(costeEdificarHotel);
            if(tengoSaldo){
                titulo.edificarHotel();
                modificarSaldo(-costeEdificarHotel);
                edificado = true;
            }
        }

        return edificado;
    }
    
    boolean cancelarHipoteca(TituloPropiedad titulo){
        boolean cancelada = false;
        int costeCancelar = titulo.calcularCosteCancelar();

        if(costeCancelar < saldo){
            modificarSaldo(-costeCancelar);
            titulo.cancelarHipoteca();
            cancelada = true;
        }

        return cancelada;
    }

    protected Especulador convertirme(int fianza){
        Especulador salida = new Especulador(this, fianza);
        return salida;
    }

    protected boolean deboIrACarcel(){
        return !tengoCartaLibertad();
    }

    protected boolean puedoEdificarCasa(TituloPropiedad titulo){
        int numCasas = titulo.getNumCasas();

        return numCasas<4;
    }

    protected boolean puedoEdificarHotel(TituloPropiedad titulo){
        int numHoteles = titulo.getNumHoteles();
        int numCasas = titulo.getNumCasas();

        return (numHoteles<4 && numCasas==4);
    }

    // Modificar
    @Override
    public String toString() {
        return "\n-----\nJugador" + "\n\tencarcelado=" + encarcelado + "\n\tnombre=" + nombre + "\n\tsaldo=" + saldo + "\n\tcartaLibertad=" + cartaLibertad + "\n\tcasillaActual=" + casillaActual + "\n-----\n\tpropiedades=" + propiedades;
    }
    
    @Override
    public int compareTo(Object otroJugador) {
        int otroCapital = ((Jugador) otroJugador).obtenerCapital();
        return otroCapital - obtenerCapital();
    }
}
