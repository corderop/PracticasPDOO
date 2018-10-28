#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
require "singleton"

module ModeloQytetet
  
  class Qytetet
    include Singleton
    attr_accessor :cartaActual
    attr_reader :MAX_JUGADORES
    attr_reader :NUM_SORPRESAS
    attr_reader :NUM_CASILLA
    attr_reader :PRECIO_LIBERTAD
    attr_reader :SALDO_SALIDA
    attr_reader :mazo
    attr_reader :dado
    attr_reader :jugadorActual
    attr_reader :jugadores
    attr_reader :tablero
      
    @@MAX_JUGADORES = 4
    @@NUM_SORPRESAS = 10
    @@NUM_CASILLAS = 20
    @@PRECIO_LIBERTAD = 200
    @@SALDO_SALIDA = 1000
=begin
  MÉTODOS SIN IMPLEMENTAR
  
  def actuarSiEnCasillaEdificable()
  def actuarSiEnCasillaNoEdificable()
  def aplicarSorpresa()
  def cancelarHipoteca(numeroCasilla)
  def comprarTituloPropiedad()
  def edificarCasa(numeroCasilla)
  def edificarHotel(numeroCasilla)
  def encarcelarJugador()
  def getValorDado()
  def hipotecarPropiedad(numeroCasilla)
  def inicializarCartasSorpresa()
  def intentarSalirCarcel(metodo)
  def jugar()
  def mover(numCasillaDestino)
  def obtenerCasillaJugadorActual()
  def obtenerCasillasTablero()
  def obtenerPropiedadesJugador()
  def obtenerPorpiedadesJugadorSegunEstadoHipoteca(estadoHipoteca)
  def obtenerRanking()
  def obtenerSaldoJugadorActual()
  def salidaJugadores()
  def siguienteJugador()
  def tirarDado()
  def venderPropiedad(int numeroCasilla)

  private :salidaJugadores
=end
    
    def initialize
      @mazo = Array.new
      @cartaActual
      @dado = Dado.instance
      @jugadorActual
      @jugadores = Array.new
    end
    
    def inicializarJuego(nombres)
      inicializarTablero
      inicializarCartasSorpresa
      inicializarJugadores(nombres)
    end
    
    def inicializarJugadores(nombres)
      for i in 0..nombres.size
        @jugadores << Jugador.new(nombres[i], @tablero.casillas[0], Array.new)
      end
    end
    
    def inicializarTablero
      @tablero = Tablero.new
    end

    def inicializarCartasSorpresa
      @mazo<< Sorpresa.new("¡Has ganado el euromillón!", 100, TipoSorpresa::PAGARCOBRAR)
      @mazo<< Sorpresa.new("Le debes dinero a hacienda", 100, TipoSorpresa::PAGARCOBRAR)
      @mazo<< Sorpresa.new("¡Ve a la cárcel!", @tablero.carcel.numeroCasilla, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Tienes un congreso, ve a la casilla 8. Si pasas por la salida cobra el dinero correspondiente", 8, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Ve a la salida", 1, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Cobra por el mantenimiento de cada casa o hotel que tengas en propiedad", 100, TipoSorpresa::PORCASAHOTEL)
      @mazo<< Sorpresa.new("Paga los impuesto por cada casa o hotel que tengas en propiedad", 100, TipoSorpresa::PORCASAHOTEL)
      @mazo<< Sorpresa.new("¡Se un poco más generoso!", 150, TipoSorpresa::PORJUGADOR)
      @mazo<< Sorpresa.new("¡Cobrales a tus contrincantes!", 150, TipoSorpresa::PORJUGADOR)
      @mazo<< Sorpresa.new("Carta de libertad. Puedes usar esta carta para salir de la cárcel", 0, TipoSorpresa::SALIRCARCEL)
    end
    
    def to_s
    "Qytetet: \n Carta Actual #{@cartaActual} \n Mazo #{@mazo} \n Nombre #{@nombre} \n Tablero #{@tablero} \n Dado #{@dado} \n Jugador actual #{@jugadorActual}"
    end
    
    private :inicializarJugadores, :inicializarTablero, :inicializarCartasSorpresa
  end
end