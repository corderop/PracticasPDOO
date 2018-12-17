#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
require_relative "tipo_sorpresa"
require_relative "dado"
require_relative "jugador"
require_relative "metodo_salir_carcel"
require_relative "tipo_casilla"
require_relative "sorpresa"
require_relative "tablero"
require_relative "casilla"
require_relative "estado_juego"
require_relative "titulo_propiedad"
require_relative "especulador"
require_relative "otraCasilla"
require_relative "calle"
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
    attr_reader :estadoJuego
      
    @@MAX_JUGADORES = 4
    @@NUM_SORPRESAS = 12
    @@NUM_CASILLAS = 20
    @@PRECIO_LIBERTAD = 200
    @@SALDO_SALIDA = 1000
    
    def initialize
      @mazo = Array.new
      @cartaActual
      @dado = Dado.instance
      @jugadorActual
      @jugadores = Array.new
      @estadoJuego
    end
    
    def inicializarJuego(nombres)
      inicializarTablero
      inicializarCartasSorpresa
      inicializarJugadores(nombres)
      salidaJugadores
    end
    
    def inicializarJugadores(nombres)
      for i in 0..nombres.size-1
        @jugadores << Jugador.new(nombres[i])
        @jugadores[i].casillaActual = @tablero.obtenerCasillaNumero(0)
      end
    end
    
    def inicializarTablero
      @tablero = Tablero.new
    end

    def inicializarCartasSorpresa
      @mazo<< Sorpresa.new("¡Has ganado el euromillón!", 100, TipoSorpresa::PAGARCOBRAR)##
      @mazo<< Sorpresa.new("Le debes dinero a hacienda", -100, TipoSorpresa::PAGARCOBRAR)##
      @mazo<< Sorpresa.new("¡Ve a la cárcel!", @tablero.carcel.numeroCasilla, TipoSorpresa::IRACASILLA)##
      @mazo<< Sorpresa.new("Tienes un congreso, ve a la casilla 8. Si pasas por la salida cobra el dinero correspondiente", 8, TipoSorpresa::IRACASILLA)##
      @mazo<< Sorpresa.new("Ve a la salida", 0, TipoSorpresa::IRACASILLA)##
      @mazo<< Sorpresa.new("Cobra por el mantenimiento de cada casa o hotel que tengas en propiedad", 100, TipoSorpresa::PORCASAHOTEL)##
      @mazo<< Sorpresa.new("Paga los impuesto por cada casa o hotel que tengas en propiedad", -100, TipoSorpresa::PORCASAHOTEL)##
      @mazo<< Sorpresa.new("¡Se un poco más generoso!", 150, TipoSorpresa::PORJUGADOR)##
      @mazo<< Sorpresa.new("¡Cobrales a tus contrincantes!", -150, TipoSorpresa::PORJUGADOR)##
      @mazo<< Sorpresa.new("Carta de libertad. Puedes usar esta carta para salir de la cárcel", 0, TipoSorpresa::SALIRCARCEL)
      @mazo<< Sorpresa.new("¡Es la carta del especulador!", 3000, TipoSorpresa::CONVERTIRME)##
      @mazo<< Sorpresa.new("¡Es la carta del especulador!", 5000, TipoSorpresa::CONVERTIRME)##
      @mazo=@mazo.shuffle
    end
    
    def setEstadoJuego(stdJg)
      @estadoJuego = stdJg
    end
    
    def obtenerCasillaJugadorActual
      @jugadorActual.casillaActual
    end
    
    def siguienteJugador
      pos = ((jugadores.index(@jugadorActual)+1)%jugadores.size)
      @jugadorActual = @jugadores[pos]
      if @jugadorActual.encarcelado
        @estadoJuego = EstadoJuego::JA_ENCARCELADOCONOPCIONDELIBERTAD
      else
        @estadoJuego = EstadoJuego::JA_PREPARADO
      end
    end
    
    def salidaJugadores
      for i in @jugadores
        i.casillaActual = @tablero.obtenerCasillaNumero(0)
      end
      
      @jugadorActual = @jugadores[rand(0..(@jugadores.size - 1))]
      @estadoJuego = EstadoJuego::JA_PREPARADO
    end
    
    def obtenerPropiedadesJugador
      salida = Array.new
      for i in @tablero.casillas
        if @jugadorActual.propiedades.index(i.titulo) != nil
          salida << i.numeroCasilla
        end
      end
      
      salida
    end
    
    def obtenerPropiedadesJugadorSegunEstadoHipoteca(estadoHipoteca)
      salida = Array.new
      
      for i in @tablero.casillas
        if (@jugadorActual.propiedades.index(i.titulo) != nil && i.titulo.hipotecada == estadoHipoteca)
          salida << i.numeroCasilla
        end
      end
      
      salida
    end
    
    def jugar
      numeroCasillaFinal = @tablero.obtenerCasillaFinal(@jugadorActual.casillaActual, dado.tirar).numeroCasilla
      mover(numeroCasillaFinal)
    end
    
    def obtenerRanking
      aux=@jugadores.sort
      puts "-------------------"
      for i in 0...aux.size do
        puts "#{i+1}. #{aux[i].nombre} capital->#{aux[i].obtenerCapital}"
      end
      puts "-------------------"
    end
    
    def obtenerSaldoJugadorActual
      @jugadorActual.saldo
    end
    
    def tirarDado
      @dado.tirar
    end
    
    def getValorDado
      @dado.valor
    end
    
    def actuarSiEnCasillaEdificable
      deboPagar = @jugadorActual.deboPagarAlquiler
      
      if deboPagar
        @jugadorActual.pagarAlquiler
        if @jugadorActual.saldo<=0
          setEstadoJuego(EstadoJuego::ALGUNJUGADORENBANCARROTA)
        end
      end
      
      casillaActual = obtenerCasillaJugadorActual
      tengoPropietario = casillaActual.tengoPropietario
      
      if @estadoJuego != EstadoJuego::ALGUNJUGADORENBANCARROTA
        if tengoPropietario
          setEstadoJuego(EstadoJuego::JA_PUEDEGESTIONAR)
        else
          setEstadoJuego(EstadoJuego::JA_PUEDECOMPRAROGESTIONAR)
        end
      end
    end
    
    def actuarSiEnCasillaNoEdificable
      setEstadoJuego(EstadoJuego::JA_PUEDEGESTIONAR)
      casillaActual = @jugadorActual.casillaActual
      
      if casillaActual.tipo==TipoCasilla::IMPUESTO
        @jugadorActual.pagarImpuesto
      else 
        if casillaActual.tipo == TipoCasilla::JUEZ
          encarcelarJugador
        else
          if casillaActual.tipo == TipoCasilla::SORPRESA
            @cartaActual = @mazo.delete_at(0)
            setEstadoJuego(EstadoJuego::JA_CONSORPRESA)
          end
        end
      end
    end
    
    def aplicarSorpresa
      setEstadoJuego(EstadoJuego::JA_PUEDEGESTIONAR)
      
      if(@cartaActual.tipo==TipoSorpresa::SALIRCARCEL)
        @jugadorActual.cartaLibertad = @cartaActual
      else
        @mazo<<@cartaActual
        if @cartaActual.tipo == TipoSorpresa::PAGARCOBRAR
          @jugadorActual.modificarSaldo(@cartaActual.valor)
          if @jugadorActual.saldo<=0
            setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA)
          end
        elsif @cartaActual.tipo==TipoSorpresa::IRACASILLA
          valor = @cartaActual.valor
          casillaCarcel = @tablero.esCasillaCarcel(valor)
          
          if casillaCarcel
            encarcelarJugador
          else
            mover(valor)
          end
        elsif @cartaActual.tipo == TipoSorpresa::PORCASAHOTEL
          cantidad = @cartaActual.valor
          numeroTotal = @jugadorActual.cuantasCasasHotelesTengo
          @jugadorActual.modificarSaldo(cantidad*numeroTotal)
          
          if @jugadorActual.saldo<=0
            setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA)
          end
        elsif @cartaActual.tipo == TipoSorpresa::PORJUGADOR
          for jugador in @jugadores
            if jugador!=@jugadorActual
              jugador.modificarSaldo(@cartaActual.valor)
              
              if jugador.saldo<=0
                setEstadoJuego(EstadoJuego::ALGUNJUGADORENBANCARROTA)
              end
              
              jugadorActual.modificarSaldo(-@cartaActual.valor)
              
              if @jugadorActual.saldo<=0
                setEstadoJuego(EstadoJuego::ALGUNJUGADORENBANCARROTA)
              end
            end
          end
        elsif @cartaActual.tipo == TipoSorpresa::CONVERTIRME
          i = jugadores.index(@jugadorActual)
          nuevo = @jugadorActual.convertirme(@cartaActual.valor)
          @jugadores[i] = nuevo
          @jugadorActual = nuevo
        end
      end
    end
    
    def comprarTituloPropiedad
      comprado = @jugadorActual.comprarTituloPropiedad
      
      if comprado
        setEstadoJuego(EstadoJuego::JA_PUEDEGESTIONAR)
      end
      
      comprado
    end
    
    def edificarCasa(numeroCasilla)
      edificada = false
      
      casilla = @tablero.obtenerCasillaNumero(numeroCasilla)
      if casilla.tipo == TipoCasilla::CALLE
        titulo = casilla.titulo
        edificada = @jugadorActual.edificarCasa(titulo)
        if edificada
          setEstadoJuego(EstadoJuego::JA_PUEDEGESTIONAR)
        end
      end
      
      edificada
    end
    
    def encarcelarJugador
      if @jugadorActual.deboIrACarcel
        casillaCarcel = @tablero.carcel
        @jugadorActual.irACarcel(casillaCarcel)
        setEstadoJuego(EstadoJuego::JA_ENCARCELADO)
      else
        if @jugadorActual.cartaLibertad != nil
          carta = @jugadorActual.devolverCartaLibertad
          @mazo << carta
        end
        setEstadoJuego(EstadoJuego::JA_PUEDEGESTIONAR)
      end
    end
    
    def intentarSalirCarcel(metodo)
      if metodo == MetodoSalirCarcel::TIRANDODADO
        resultado = tirarDado();
        if resultado >= 5
          @jugadorActual.encarcelado = false
        end
      elsif metodo == MetodoSalirCarcel::PAGANDOLIBERTAD
        @jugadorActual.pagarLibertad(@@PRECIO_LIBERTAD)
      end
      
      encarcelado = @jugadorActual.encarcelado
      
      if encarcelado
        setEstadoJuego(EstadoJuego::JA_ENCARCELADO)
      else
        setEstadoJuego(EstadoJuego::JA_PREPARADO)
      end
      
      return !encarcelado
    end
    
    def mover(numCasillaDestino)
      casillaInicial = @jugadorActual.casillaActual
      casillaFinal = @tablero.obtenerCasillaNumero(numCasillaDestino)
      
      @jugadorActual.casillaActual=casillaFinal
      
      if numCasillaDestino < casillaInicial.numeroCasilla
        @jugadorActual.modificarSaldo(@@SALDO_SALIDA)
      end
      
      if casillaFinal.soyEdificable
        actuarSiEnCasillaEdificable
      else
        actuarSiEnCasillaNoEdificable
      end
    end
    
    def hipotecarPropiedad(numeroCasilla)
      casilla = @tablero.obtenerCasillaNumero(numeroCasilla)
      titulo = casilla.titulo
      
      @jugadorActual.hipotecarPropiedad(titulo)
    end
    
    def venderPropiedad(numeroCasilla)
      casilla = @tablero.obtenerCasillaNumero(numeroCasilla)
      @jugadorActual.venderPropiedad(casilla)
    end
    
    def cancelarHipoteca(numeroCasilla)
      cancelada = false
      casilla = @tablero.obtenerCasillaNumero(numeroCasilla)
      if casilla.tipo==TipoCasilla::CALLE
        titulo = casilla.titulo
        if titulo.hipotecada && titulo.propietario==@jugadorActual
          cancelada = @jugadorActual.cancelarHipoteca(titulo)
          setEstadoJuego(EstadoJuego::JA_PUEDEGESTIONAR)
        end  
      end
      
      cancelada
    end
    
    def edificarHotel(numeroCasilla)
      edificado = false
      
      casilla = @tablero.obtenerCasillaNumero(numeroCasilla)
      if (casilla.tipo == TipoCasilla::CALLE && casilla.titulo.numCasas >= 4)
        titulo = casilla.titulo
        edificado = jugadorActual.edificarHotel(titulo)
        if edificado
          setEstadoJuego(EstadoJuego::JA_PUEDEGESTIONAR)
        end
      end
      
      edificado
    end
    
    def obtenerCasillaTablero
      @tablero.casillas
    end
    
    def to_s
      "\n-----\nQytetet \n\tcartaActual= #{@cartaActual} \n-----\n\tmazo= #{@mazo} \n-----\n\ttablero= #{@tablero} \n-----\n\tdado= #{@dado} \n-----\n\tjugadorActual= #{@jugadorActual} \n------\n\tjugadores= #{@jugadores}"
    end
    
    private :encarcelarJugador, :salidaJugadores, :inicializarJugadores, :inicializarTablero, :inicializarCartasSorpresa
  end
end