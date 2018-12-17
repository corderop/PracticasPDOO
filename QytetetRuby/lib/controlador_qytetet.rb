#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
require "singleton"
require_relative "qytetet"
require_relative "estado_juego"
require_relative "metodo_salir_carcel"

module ControladorQytetet
    class ControladorQytetet
      include ModeloQytetet
      include Singleton
      
      attr_reader   :modelo
      attr_accessor :nombreJugadores
      
      def initialize
        @modelo = ModeloQytetet::Qytetet.instance
        @nombreJugadores = Array.new
      end
      
      def obtenerOperacionesJuegoValidas
        salida = Array.new
        
        if@modelo.jugadores.empty?
          salida << OpcionMenu.index(:INICIARJUEGO)
        elsif @modelo.estadoJuego == ModeloQytetet::EstadoJuego::JA_PREPARADO
          salida << OpcionMenu.index(:JUGAR)
          salida << OpcionMenu.index(:OBTENERRANKING)
          salida << OpcionMenu.index(:MOSTRARJUGADORACTUAL)
          salida << OpcionMenu.index(:MOSTRARJUGADORES)
          salida << OpcionMenu.index(:MOSTRARTABLERO)
          salida << OpcionMenu.index(:TERMINARJUEGO)
        elsif @modelo.estadoJuego == ModeloQytetet::EstadoJuego::JA_PUEDEGESTIONAR
          salida << OpcionMenu.index(:VENDERPROPIEDAD)
          salida << OpcionMenu.index(:HIPOTECARPROPIEDAD)
          salida << OpcionMenu.index(:CANCELARHIPOTECA)
          salida << OpcionMenu.index(:EDIFICARCASA)
          salida << OpcionMenu.index(:EDIFICARHOTEL)
          salida << OpcionMenu.index(:PASARTURNO)
          salida << OpcionMenu.index(:OBTENERRANKING)
          salida << OpcionMenu.index(:MOSTRARJUGADORACTUAL)
          salida << OpcionMenu.index(:MOSTRARJUGADORES)
          salida << OpcionMenu.index(:MOSTRARTABLERO)
          salida << OpcionMenu.index(:TERMINARJUEGO)
        elsif @modelo.estadoJuego == ModeloQytetet::EstadoJuego::JA_ENCARCELADO
          salida << OpcionMenu.index(:PASARTURNO)
          salida << OpcionMenu.index(:OBTENERRANKING)
          salida << OpcionMenu.index(:MOSTRARJUGADORACTUAL)
          salida << OpcionMenu.index(:MOSTRARJUGADORES)
          salida << OpcionMenu.index(:MOSTRARTABLERO)
          salida << OpcionMenu.index(:TERMINARJUEGO)
        elsif @modelo.estadoJuego == ModeloQytetet::EstadoJuego::JA_CONSORPRESA
          salida << OpcionMenu.index(:APLICARSORPRESA)
          salida << OpcionMenu.index(:OBTENERRANKING)
          salida << OpcionMenu.index(:MOSTRARJUGADORACTUAL)
          salida << OpcionMenu.index(:MOSTRARJUGADORES)
          salida << OpcionMenu.index(:MOSTRARTABLERO)
          salida << OpcionMenu.index(:TERMINARJUEGO)
        elsif @modelo.estadoJuego == ModeloQytetet::EstadoJuego::JA_PUEDECOMPRAROGESTIONAR
          salida << OpcionMenu.index(:VENDERPROPIEDAD)
          salida << OpcionMenu.index(:HIPOTECARPROPIEDAD)
          salida << OpcionMenu.index(:CANCELARHIPOTECA)
          salida << OpcionMenu.index(:EDIFICARCASA)
          salida << OpcionMenu.index(:EDIFICARHOTEL)
          salida << OpcionMenu.index(:COMPRARTITULOPROPIEDAD)
          salida << OpcionMenu.index(:PASARTURNO)
          salida << OpcionMenu.index(:OBTENERRANKING)
          salida << OpcionMenu.index(:MOSTRARJUGADORACTUAL)
          salida << OpcionMenu.index(:MOSTRARJUGADORES)
          salida << OpcionMenu.index(:MOSTRARTABLERO)
          salida << OpcionMenu.index(:TERMINARJUEGO)
        elsif @modelo.estadoJuego == ModeloQytetet::EstadoJuego::JA_ENCARCELADOCONOPCIONDELIBERTAD
          salida << OpcionMenu.index(:INTENTARSALIRCARCELPAGANDOLIBERTAD)
          salida << OpcionMenu.index(:INTENTARSALIRCARCELTIRANDODADO)
          salida << OpcionMenu.index(:OBTENERRANKING)
          salida << OpcionMenu.index(:MOSTRARJUGADORACTUAL)
          salida << OpcionMenu.index(:MOSTRARJUGADORES)
          salida << OpcionMenu.index(:MOSTRARTABLERO)
          salida << OpcionMenu.index(:TERMINARJUEGO)
        elsif @modelo.estadoJuego == ModeloQytetet::EstadoJuego::ALGUNJUGADORENBANCARROTA
          salida << OpcionMenu.index(:OBTENERRANKING)
        end
        
        salida
      end
    
      def necesitaElegirCasilla(opcionMenu)
        salida = false
        
        if( OpcionMenu[opcionMenu] == :HIPOTECARPROPIEDAD || OpcionMenu[opcionMenu] == :CANCELARHIPOTECA || OpcionMenu[opcionMenu] == :EDIFICARCASA || OpcionMenu[opcionMenu] == :EDIFICARHOTEL || OpcionMenu[opcionMenu] == :VENDERPROPIEDAD)
          salida = true
        end  
          
        salida
      end
      
      def obtenerCasillasValidas(opcionMenu)
        salida = Array.new
        
        if OpcionMenu[opcionMenu] == :HIPOTECARPROPIEDAD
          salida = @modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false)
        elsif OpcionMenu[opcionMenu] == :CANCELARHIPOTECA
          salida = @modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(true)
        elsif OpcionMenu[opcionMenu] == :EDIFICARCASA
          salida = @modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false)
        elsif OpcionMenu[opcionMenu] == :EDIFICARHOTEL
          salida = @modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false)
        elsif OpcionMenu[opcionMenu] == :VENDERPROPIEDAD
          salida = @modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false)
        end
          
        salida
      end
      def realizarOperacion(opcionElegida, casillaElegida)
        salida = "\0"
        
        if OpcionMenu[opcionElegida] == :INICIARJUEGO
          @modelo.inicializarJuego(@nombreJugadores)
          salida = "-------------------\nSe ha iniciado el juego\nEmpieza el jugador: #{@modelo.jugadorActual}\n-------------------"
        elsif OpcionMenu[opcionElegida] == :JUGAR
          @modelo.jugar
          salida = "-------------------\nHas tirado el dado y has sacado #{@modelo.getValorDado}\nHas caído en la casilla: #{@modelo.obtenerCasillaJugadorActual}\n-------------------"
        elsif OpcionMenu[opcionElegida] == :APLICARSORPRESA
          salida = "-------------------\nHas aplicado la sorpresa: #{@modelo.cartaActual}\n-------------------"
          @modelo.aplicarSorpresa
        elsif OpcionMenu[opcionElegida] == :INTENTARSALIRCARCELPAGANDOLIBERTAD
          if @modelo.intentarSalirCarcel(ModeloQytetet::MetodoSalirCarcel::PAGANDOLIBERTAD)
            salida = "-------------------\nHas conseguido salir de la carcel :)\n-------------------"
          else
            salida = "-------------------\nNo has conseguido salir de la carcel por falta de saldo :(\n-------------------"
          end
        elsif OpcionMenu[opcionElegida] == :INTENTARSALIRCARCELTIRANDODADO
          if @modelo.intentarSalirCarcel(ModeloQytetet::MetodoSalirCarcel::TIRANDODADO)
              salida = "-------------------\nHas conseguido salir de la carcel :)\n-------------------"
          else
              salida = "-------------------\nNo has obtenido el valor necesario y no has conseguido salir de la carcel :(\n-------------------"
          end
        elsif OpcionMenu[opcionElegida] == :COMPRARTITULOPROPIEDAD
          if @modelo.comprarTituloPropiedad
              salida = "-------------------\nHas comprado la propiedad :)\n-------------------"
          else
              salida = "-------------------\nNo has podido comprar la propiedad por falta de saldo. :(\n-------------------"
          end
        elsif OpcionMenu[opcionElegida] == :HIPOTECARPROPIEDAD
          @modelo.hipotecarPropiedad(casillaElegida)
          salida = "-------------------\nPropiedad hipotecada\n-------------------"
        elsif OpcionMenu[opcionElegida] == :CANCELARHIPOTECA
          if @modelo.cancelarHipoteca(casillaElegida)
            salida = "-------------------\nHipoteca cancelada correctamente\n-------------------"
          else
            salida = "-------------------\nError. No tienes saldo para cancelarla, no eres propietario\n o no esta hipotecada la propiedad\n-------------------"
          end
        elsif OpcionMenu[opcionElegida] == :EDIFICARCASA
          if @modelo.edificarCasa(casillaElegida)
            salida = "-------------------\nCasa edificada correctamente\n-------------------"
          else
            salida = "-------------------\nNo se ha podido edificar la casa\n-------------------"
          end
        elsif OpcionMenu[opcionElegida] == :EDIFICARHOTEL
          if @modelo.edificarHotel(casillaElegida)
            salida = "-------------------\nHotel edificado correctamente\n-------------------"
          else
            salida = "-------------------\nNo se ha podido edificar el hotel\n-------------------"
          end
        elsif OpcionMenu[opcionElegida] == :VENDERPROPIEDAD
          @modelo.venderPropiedad(casillaElegida)
          salida = "-------------------\nPropiedad vendida\n-------------------"
        elsif OpcionMenu[opcionElegida] == :PASARTURNO
          @modelo.siguienteJugador
          salida = "-------------------\nTurno de: #{@modelo.jugadorActual}\n-------------------"
        elsif OpcionMenu[opcionElegida] == :OBTENERRANKING
          @modelo.obtenerRanking
          #salida = "-------------------\n#{@modelo.jugadores}\n-------------------"
        elsif OpcionMenu[opcionElegida] == :MOSTRARJUGADORACTUAL
          salida = "-------------------\n#{@modelo.jugadorActual}\n-------------------"
        elsif OpcionMenu[opcionElegida] == :MOSTRARJUGADORES
          salida = "-------------------\n#{@modelo.jugadores}\n-------------------"
        elsif OpcionMenu[opcionElegida] == :MOSTRARTABLERO
          salida = "-------------------\n#{@modelo.tablero}\n-------------------"
        elsif OpcionMenu[opcionElegida] == :TERMINARJUEGO
          @modelo.obtenerRanking
          salida = "-------------------\nJuego finalizado\n-------------------"
          exit(0)
        end
        
        salida
      end
    end
end
