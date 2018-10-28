#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
require_relative "tipo_sorpresa"
require_relative "dado"
require_relative "jugador"
require_relative "metodo_salir_carcel"
require_relative "tipo_casilla"
require_relative "sorpresa"
require_relative "qytetet"
require_relative "tablero"
require_relative "casilla"
require_relative "titulo_propiedad"
  
module ModeloQytetet
  
  class PruebaQytetet
    
    attr_reader :juego 

    @@juego = Qytetet.instance
    
    def self.getNombresJugadores
      salida = Array.new
      puts "Introduce el número de jugadores: "
      tam = gets.chomp.to_i
      
      if tam>=2 && tam<=4
        tam.times do |i|
          puts "Introduce nombre jugador #{@i}: "
          cadena = gets
          salida << cadena
        end
      else
        puts "Tiene que haber entre 2 y 4 jugadores"
      end
      
      return salida
    end
      
    def self.main
      @@juego.inicializarJuego(getNombresJugadores)
      
      for i in 0..@@juego.jugadores.size
        puts @@juego.jugadores[i].to_s
      end
      
      puts @@juego.to_s
    end
  end
  
  PruebaQytetet.main
end