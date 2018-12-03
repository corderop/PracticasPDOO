/**
 * Practica de:
 * 
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Qytetet {
    
    // Declaración de la instancia de clase ya que la clase es singleton
    private static final Qytetet instance = new Qytetet();
 
    public static final int MAX_JUGADORES = 4;
    static final int NUM_SORPRESAS = 10;
    public static final int NUM_CASILLAS = 20;
    static final int PRECIO_LIBERTAD = 200;
    static final int SALDO_SALIDA = 1000;
    
    private Sorpresa cartaActual;
    private ArrayList<Sorpresa> mazo = new ArrayList<>(NUM_SORPRESAS);
    private Tablero tablero;
    private Dado dado = Dado.getInstance();
    private EstadoJuego estadoJuego;
    private Jugador jugadorActual;
    private ArrayList<Jugador> jugadores = new ArrayList<>(0); 
    
    // Declaración del constructor privado ya que la clase es de tipo
    // singleton, y se declarará fuera de la clase con el método getInstance
    private Qytetet(){}
    
    // get y set ------------------------------
    public static Qytetet getInstance(){
        return instance;
    }
    
    public Sorpresa getCartaActual(){
        return cartaActual;
    }
    
    Dado getDado(){
        return dado;
    }
    
    Jugador getJugadorActual(){
        return jugadorActual;
    }
    
    ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
    
    public Tablero getTablero() {
        return tablero;
    }

    ArrayList<Sorpresa> getMazo(){
        return mazo;
    }
    
    /*private*/ void setCartaActual( Sorpresa cartaActual ){
        this.cartaActual = cartaActual;
    }
    
    public void setEstadoJuego(EstadoJuego estadoJuego){
        this.estadoJuego = estadoJuego;
    }
    
    public Casilla obtenerCasillaJugadorActual(){
        return jugadorActual.getCasillaActual();
    }
    // get y set -----------------------------
    
    // Inicializadores------------------------
    
    public void inicializarJuego(ArrayList<String> nombres){
        inicializarTablero();
        inicializarJugadores(nombres);
        inicializarCartasSorpresa();
        salidaJugadores();
    }
    
    private void inicializarJugadores(ArrayList<String> nombres){
        for( int i=0 ; i<nombres.size() ; i++){
            jugadores.add(new Jugador(nombres.get(i), tablero.getCasillas().get(0), new ArrayList<>(0)));
        }
    }
    
    private void inicializarTablero(){
        this.tablero = new Tablero();
    }
    
    private void inicializarCartasSorpresa(){
        mazo.add(new Sorpresa ("¡Has ganado el euromillón!", 100, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("Le debes dinero a hacienda", -100, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("¡Ve a la cárcel!", tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Tienes un congreso, ve a la casilla 8. Si pasas por la salida cobra el dinero correspondiente", 8, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Ve a la salida", 0, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Cobra por el mantenimiento de cada casa o hotel que tengas en propiedad", 100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Paga los impuesto por cada casa o hotel que tengas en propiedad", -100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("¡Se un poco más generoso!", 150, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("¡Cobrales a tus contrincantes!", -150, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("Carta de libertad. Puedes usar esta carta para salir de la cárcel", 0, TipoSorpresa.SALIRCARCEL));
    }
    
    public void siguienteJugador(){
        int pos = (jugadores.indexOf(jugadorActual) + 1) % jugadores.size();
        jugadorActual = jugadores.get(pos);
        if(jugadorActual.getEncarcelado())
            estadoJuego = EstadoJuego.JA_ENCARCELADOCONOPCIONDELIBERTAD;
        else
            estadoJuego = EstadoJuego.JA_PREPARADO;
    }
    
    private void salidaJugadores(){
        for(int i=0 ; i<jugadores.size() ; i++)
            jugadores.get(i).setCasillaActual(tablero.obtenerCasillaNumero(0));
        
        Random rnd = new Random();
        jugadorActual = jugadores.get( rnd.nextInt(jugadores.size()-1));
        estadoJuego = EstadoJuego.JA_PREPARADO;
    }
    
    public ArrayList<Integer> obtenerPropiedadesJugador(){
        ArrayList<Integer> salida = new ArrayList<>();
        
        for(Casilla i : tablero.getCasillas()){
            if(jugadorActual.getPropiedades().indexOf(i.getTitulo()) != -1)
                salida.add(i.getNumeroCasilla());
        }
        
        return salida;
    }
    
    public ArrayList<Integer> obtenerPropiedadesJugadorSegunEstadoHipoteca(boolean estadoHipoteca){
        ArrayList<Integer> salida = new ArrayList<>();
        
        for(Casilla i : tablero.getCasillas()){
            if(jugadorActual.getPropiedades().indexOf(i.getTitulo()) != -1 && i.getTitulo().getHipotecada() == estadoHipoteca)
                salida.add(i.getNumeroCasilla());
        }
        
        return salida;
    }
    
    public void jugar(){
        int numeroCasillaFinal = tablero.obtenerCasillaFinal(jugadorActual.getCasillaActual(), dado.tirar()).getNumeroCasilla();
        mover(numeroCasillaFinal);
    }
    
    public void obtenerRanking(){
        Collections.sort(jugadores);
    }
    
    public int obtenerSaldoJugadorActual(){
        return jugadorActual.getSaldo();
    }
    
    int tirarDado(){
        return dado.tirar();
    }
    
    public int getValorDado(){
        return dado.getValor();
    }
    
    void actuarSiEnCasillaEdificable(){
        boolean deboPagar = jugadorActual.deboPagarAlquiler();

        if(deboPagar){
            jugadorActual.pagarAlquiler();
            if(jugadorActual.getSaldo()<=0)
                setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
        }

        Casilla casillaActual = obtenerCasillaJugadorActual();
        boolean tengoPropietario = casillaActual.tengoPropietario();

        if(estadoJuego != EstadoJuego.ALGUNJUGADORENBANCARROTA){
            if(tengoPropietario)
                setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
            else
                setEstadoJuego(EstadoJuego.JA_PUEDECOMPRAROGESTIONAR);
        }
    }
    
    void actuarSiEnCasillaNoEdificable(){
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        Casilla casillaActual = jugadorActual.getCasillaActual();

        if(casillaActual.getTipo()==TipoCasilla.IMPUESTO)
            jugadorActual.pagarImpuesto();
        else if(casillaActual.getTipo()==TipoCasilla.JUEZ)
                encarcelarJugador();
        else if(casillaActual.getTipo()==TipoCasilla.SORPRESA){
            cartaActual=mazo.remove(0);
            setEstadoJuego(EstadoJuego.JA_CONSORPRESA);
        }
    }
    
    public void aplicarSorpresa(){
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);

        if(cartaActual.getTipo()==TipoSorpresa.SALIRCARCEL)
            jugadorActual.setCartaLibertad(cartaActual);
        else{
            mazo.add(cartaActual);
            if(cartaActual.getTipo()==TipoSorpresa.PAGARCOBRAR){
                jugadorActual.modificarSaldo(cartaActual.getValor());
                if(jugadorActual.getSaldo()<=0)
                    setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
            }
            else if(cartaActual.getTipo()==TipoSorpresa.IRACASILLA){
                int valor = cartaActual.getValor();
                boolean casillaCarcel = tablero.esCasillaCarcel(valor);
    
                if(casillaCarcel)
                    encarcelarJugador();
                else
                    mover(valor);
            }
            else if(cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL){
                int cantidad = cartaActual.getValor();
                int numeroTotal = jugadorActual.cuantasCasasHotelesTengo();
                jugadorActual.modificarSaldo(cantidad*numeroTotal);
    
                if(jugadorActual.getSaldo()<=0)
                    setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
            }
            else if(cartaActual.getTipo() == TipoSorpresa.PORJUGADOR){
                for(int i=0 ; i<jugadores.size() ; i++){
                    Jugador jugador = jugadores.get(i);
                    if(jugador!=jugadorActual){
                        jugador.modificarSaldo(cartaActual.getValor());

                        if(jugador.getSaldo()<=0)
                            setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);

                        jugadorActual.modificarSaldo(-cartaActual.getValor());

                        if(jugadorActual.getSaldo()<=0)
                            setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                    }
                }
            }
        }
    }
    
    public boolean comprarTituloPropiedad(){
        boolean comprado = jugadorActual.comprarTituloPropiedad();

        if(comprado)
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);

        return comprado;
    }
    
    public boolean edificarCasa(int numeroCasilla){
        boolean edificada = false;

        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        if(casilla.getTipo() == TipoCasilla.CALLE){
            TituloPropiedad titulo = casilla.getTitulo();
            edificada = jugadorActual.edificarCasa(titulo);
            if(edificada)
                setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
            
        }

        return edificada;
    }
    
    private void encarcelarJugador(){
        if(!jugadorActual.tengoCartaLibertad()){
            Casilla casillaCarcel = tablero.getCarcel();
            jugadorActual.irACarcel(casillaCarcel);
            setEstadoJuego(EstadoJuego.JA_ENCARCELADO);
        }
        else{
            Sorpresa carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta);
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
    }
    
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        if(metodo == MetodoSalirCarcel.TIRANDODADO){
            int resultado = tirarDado();
            if(resultado>=5)
                jugadorActual.setEncarcelado(false);
        }
        else if(metodo == MetodoSalirCarcel.PAGANDOLIBERTAD)
            jugadorActual.pagarLibertad(PRECIO_LIBERTAD);

        boolean encarcelado = jugadorActual.getEncarcelado();

        if(encarcelado)
            setEstadoJuego(EstadoJuego.JA_ENCARCELADO);
        else
            setEstadoJuego(EstadoJuego.JA_PREPARADO);
        
        return !encarcelado;
    }

    
    void mover(int numCasillaDestino){
        Casilla casillaInicial = jugadorActual.getCasillaActual();
        Casilla casillaFinal = tablero.obtenerCasillaNumero(numCasillaDestino);
        
        jugadorActual.setCasillaActual(casillaFinal);

        if(numCasillaDestino < casillaInicial.getNumeroCasilla())
            jugadorActual.modificarSaldo(SALDO_SALIDA);
        
        if(casillaFinal.soyEdificable())
            actuarSiEnCasillaEdificable();
        else
            actuarSiEnCasillaNoEdificable();
    }
    
    public void hipotecarPropiedad(int numeroCasilla){
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        TituloPropiedad titulo = casilla.getTitulo();
        jugadorActual.hipotecarPropiedad(titulo);
    }
    
    public void venderPropiedad(int numeroCasilla){
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        jugadorActual.venderPropiedad(casilla);
    }
    
    public boolean cancelarHipoteca(int numeroCasilla){
        boolean cancelada = false;
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        if(casilla.getTipo()==TipoCasilla.CALLE){
            TituloPropiedad titulo = casilla.getTitulo();
            if(titulo.getHipotecada() && titulo.getPropietario()==jugadorActual){
                cancelada = jugadorActual.cancelarHipoteca(titulo);
                setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
            }
        }

        return cancelada;
    }
    
    public boolean edificarHotel(int numeroCasilla){
        boolean edificado = false;

        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        if(casilla.getTipo() == TipoCasilla.CALLE && casilla.getTitulo().getNumCasas() == 4){
            TituloPropiedad titulo = casilla.getTitulo();
            edificado = jugadorActual.edificarHotel(titulo);
            if(edificado)
                setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }

        return edificado;
    }
    
    public ArrayList<Casilla> obtenerCasillasTablero(){
        return tablero.getCasillas();
    }

    @Override
    public String toString() {
        return "Qytetet{" + "cartaActual=" + cartaActual + ", mazo=" + mazo + ", tablero=" + tablero + ", dado=" + dado + ", jugadorActual=" + jugadorActual + ", jugadores=" + jugadores + '}';
    }
}
