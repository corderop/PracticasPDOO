#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
module ModeloQytetet
  class Tablero
    attr_reader :casillas
    attr_reader :carcel
    
    def initialize
      inicializar
    end
    
    def inicializar
      @casillas = Array.new
      
      @casillas<< OtraCasilla.new(0, 0, TipoCasilla::SALIDA)
      @casillas<< Calle.new(1, 500, TituloPropiedad.new("Av. de Andalucía", 500, 50, 20, 150, 250))
      @casillas<< Calle.new(2, 550, TituloPropiedad.new("Calle Camarones", 550, 55, 15, 220, 300))
      @casillas<< OtraCasilla.new(3, 0, TipoCasilla::SORPRESA)
      @casillas<< Calle.new(4, 600, TituloPropiedad.new("Av. de José Fariña", 600, 60, 10, 290, 350))
      @casillas<< Calle.new(5, 650, TituloPropiedad.new("Calle Concepción", 650, 65, 5, 360, 400))
      @casillas<< OtraCasilla.new(6, 0, TipoCasilla::JUEZ)
      @casillas<< Calle.new(7, 700, TituloPropiedad.new("Paseo de la Independencia", 700, 70, 5, 430, 450))
      @casillas<< Calle.new(8, 750, TituloPropiedad.new("Av de Diego Morón", 750, 75, 0, 500, 500))
      @casillas<< OtraCasilla.new(9, 0, TipoCasilla::SORPRESA)
      @casillas<< Calle.new(10, 800, TituloPropiedad.new("Calle Huerto Don Moisés", 800, 80, 0, 570, 550))
      @casillas<< Calle.new(11, 850, TituloPropiedad.new("Av del Molino de la Vega", 850, 85, -5, 640, 600))
      @casillas<< OtraCasilla.new(12, 0, TipoCasilla::CARCEL)
      @carcel = casillas[12];
      @casillas<< Calle.new(13, 900, TituloPropiedad.new("Calle Niña", 900, 90, -5, 710, 650))
      @casillas<< OtraCasilla.new(14, 500, TipoCasilla::IMPUESTO)
      @casillas<< OtraCasilla.new(15, 0, TipoCasilla::SORPRESA)
      @casillas<< Calle.new(16, 950, TituloPropiedad.new("Av de Italia", 950, 95, -10, 780, 700))
      @casillas<< Calle.new(17, 1000, TituloPropiedad.new("Calle Tesoro de la Aliseda", 1000, 100, -15, 850, 750))
      @casillas<< OtraCasilla.new(18, 0, TipoCasilla::PARKING)
      @casillas<< Calle.new(19, 1000, TituloPropiedad.new("Av de Cristóbal Colón", 1000, 100, -20, 1000, 750))
    end
    
    def esCasillaCarcel(numeroCasilla)
      salida = numeroCasilla==@carcel.numeroCasilla
      
      salida
    end
    
    def obtenerCasillaNumero(numeroCasilla)
      fail if (numeroCasilla<0 && numeroCasilla>=@casillas.size)
      @casillas[numeroCasilla]
    end
    
    def obtenerCasillaFinal(casilla, desplazamiento)
      retorno = nil
      
      for i in 0..@casillas.size do
        if(retorno == nil)
          if @casillas[i]==casilla
            retorno = @casillas[(i+desplazamiento)% @casillas.size]
          end
        end
      end
      fail if retorno==nil
      retorno
    end
    
    def to_s
      "\n-----\nTablero \n\tcasillas= #{@casillas} \n-----\n\tCarcel= #{@carcel}"
    end
    
    private :inicializar
  end
end
