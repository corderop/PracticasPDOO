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
    
    void pagarImpuesto(){
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
    
    boolean tengoSaldo(int cantidad){
        return saldo > cantidad;
    }
    
    boolean comprarTituloPropiedad(){
        boolean comprado = false;

        int costeCompra = casillaActual.getCoste();
        if(costeCompra<saldo){
            TituloPropiedad titulo = casillaActual.asignarPropietario(this);
            propiedades.add(titulo);
            modificarSaldo(-costeCompra);
            comprado = true;
        }

        return comprado;
    }
    
    boolean edificarCasa(TituloPropiedad titulo){
        int numCasas = titulo.getNumCasas();
        boolean edificada = false;

        if(numCasas<4){
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
        int costeAlquiler = casillaActual.pagarAlquiler();
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
        int numHoteles = titulo.getNumHoteles();
        boolean edificado = false;

        if(numHoteles<4){
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

    // Modificar
    @Override
    public String toString() {
        return "Jugador{" + "encarcelado=" + encarcelado + ", nombre=" + nombre + ", saldo=" + saldo + ", cartaLibertad=" + cartaLibertad + ", casillaActual=" + casillaActual + ", propiedades=" + propiedades + '}';
    }
    
    @Override
    public int compareTo(Object otroJugador) {
        int otroCapital = ((Jugador) otroJugador).obtenerCapital();
        return otroCapital - obtenerCapital();
    }
}
