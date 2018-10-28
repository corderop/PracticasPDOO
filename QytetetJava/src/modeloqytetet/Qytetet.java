/*
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;
import java.util.ArrayList;

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
    private Jugador jugadorActual;
    private ArrayList<Jugador> jugadores = new ArrayList<>(0); 
    

  /**
   * MÉTODOS SIN IMPLEMENTAR
   * 
   * void actuarSiEnCasillaEdificable();
   * void actuarSiEnCasillaNoEdificable();
   * public void aplicarSorpresa();
   * public boolean cancelarHipoteca(int numeroCasilla);
   * public boolean comprarTituloPropiedad();
   * public boolean edificarCasa(int numeroCasilla);
   * public boolean edificarHotel(int numeroCasilla);
   * private void encarcelarJugador();
   * public int getValorDado();
   * public void hipotecarPropiedad(int numeroCasilla);
   * private void inicializarCartasSorpresa();
   * public boolean intentarSalirCarcel( MetodoSalirCarcel metodo );
   * public void jugar();
   * void mover(int numCasillaDestino);
   * public Casilla obtenerCasillaJugadorActual();
   * public ArrayList<Casilla> obtenerCasillasTablero();
   * public ArrayList<int> obtenerPropiedadesJugador();
   * public ArrayList<int> obtenerPorpiedadesJugadorSegunEstadoHipoteca(boolean estadoHipoteca);
   * public void obtenerRanking();
   * public int obtenerSaldoJugadorActual();
   * private void salidaJugadores();
   * public void siguienteJugador();
   * int tirarDado();
   * public boolean venderPropiedad(int numeroCasilla);
   */
    
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
    
    private void setCartaActual( Sorpresa cartaActual ){
        this.cartaActual = cartaActual;
    }
    // get y set -----------------------------
    
    // Inicializadores------------------------
    
    public void inicializarJuego(ArrayList<String> nombres){
        inicializarTablero();
        inicializarCartasSorpresa();
        inicializarJugadores(nombres);
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
        mazo.add(new Sorpresa ("Le debes dinero a hacienda", 100, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("¡Ve a la cárcel!", tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Tienes un congreso, ve a la casilla 8. Si pasas por la salida cobra el dinero correspondiente", 8, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Ve a la salida", 1, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Cobra por el mantenimiento de cada casa o hotel que tengas en propiedad", 100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Paga los impuesto por cada casa o hotel que tengas en propiedad", 100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("¡Se un poco más generoso!", 150, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("¡Cobrales a tus contrincantes!", 150, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("Carta de libertad. Puedes usar esta carta para salir de la cárcel", 0, TipoSorpresa.SALIRCARCEL));
    }

    @Override
    public String toString() {
        return "Qytetet{" + "cartaActual=" + cartaActual + ", mazo=" + mazo + ", tablero=" + tablero + ", dado=" + dado + ", jugadorActual=" + jugadorActual + ", jugadores=" + jugadores + '}';
    }
}
