#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
require_relative "controlador_qytetet"
require_relative "opcion_menu"

module VistaTextualQytetet
  class VistaTextualQytetet
    include ControladorQytetet
    
    attr_reader :controlador
    
    def initialize
      @controlador = ControladorQytetet.instance
    end
    
    def obtenerOpcionMenu(menuOperaciones)
      for i in menuOperaciones
        puts "#{i}-> #{OpcionMenu[i.to_i]}"
      end
      
      salida = leerValorCorrecto(menuOperaciones).to_i
      
      salida
    end
    
    def obtenerNombreJugadores
      salida = Array.new
      puts "Introduce el número de jugadores: "
      tam = gets.chomp.to_i
      if tam>=2 && tam<=4
        for i in 1..tam
          puts "Introduce nombre jugador #{@i}: "
          cadena = gets.chomp
          salida << cadena
        end
      else
        puts "Tiene que haber entre 2 y 4 jugadores"
        exit(0)
      end
      
      return salida
    end
    
    def elegirCasilla(opcionMenu)
      casillasValidas = @controlador.obtenerCasillasValidas(opcionMenu)
      convertidoAString = Array.new
      
      if casillasValidas.empty?
        puts "-------------------\nNo hay ninguna casilla con la que realizar esta acción\n-------------------"
        valorLeido = -1
      else
        puts "-------------------\nEscoja la posición de la casilla sobre la que quiera operar\nde las mostradas abajo: "
        
        for i in casillasValidas
          convertidoAString << i.to_s
          puts "#{i} "
        end
        
        puts "\n-------------------"
      end
      
      if valorLeido != -1
        valorLeido = leerValorCorrecto(convertidoAString).to_i
      end
      
      valorLeido
    end
    
    def leerValorCorrecto(valoresCorrectos)
      entrada = "\0"
      
      while !valoresCorrectos.include?(entrada) do 
        puts "Introduce un valor válido: "
        entrada = gets.chomp
      end
      
      entrada
    end
    
    def elegirOperacion
      opValidas = @controlador.obtenerOperacionesJuegoValidas
      convertidas = Array.new
      
      for i in opValidas
        convertidas << i.to_s
      end
      
      salida = obtenerOpcionMenu(convertidas)
      salida
    end
    
    def self.main
      ui = VistaTextualQytetet.new
      ui.controlador.nombreJugadores = ui.obtenerNombreJugadores
      casillaElegida = 0
      
      while true do 
        operacionElegida = ui.elegirOperacion
        necesitaElegirCasilla = ui.controlador.necesitaElegirCasilla(operacionElegida)
        
        if necesitaElegirCasilla
          casillaElegida = ui.elegirCasilla(operacionElegida)
        end
          
        if(!necesitaElegirCasilla || casillaElegida >= 0)
          puts ui.controlador.realizarOperacion(operacionElegida, casillaElegida)
        end
      end 
    end
        
  end
  
  VistaTextualQytetet.main
end
