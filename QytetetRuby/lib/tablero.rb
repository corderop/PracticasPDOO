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
      
      @casillas<< Casilla.new2(0, TipoCasilla::SALIDA)
      @casillas<< Casilla.new(1, TipoCasilla::CALLE, TituloPropiedad.new("Av. de Andalucía", 500, 50, 20, 150, 250))
      @casillas<< Casilla.new(2, TipoCasilla::CALLE, TituloPropiedad.new("Calle Camarones", 550, 55, 15, 220, 300))
      @casillas<< Casilla.new2(3, TipoCasilla::SORPRESA)
      @casillas<< Casilla.new(4, TipoCasilla::CALLE, TituloPropiedad.new("Av. de José Fariña", 600, 60, 10, 290, 350))
      @casillas<< Casilla.new(5, TipoCasilla::CALLE, TituloPropiedad.new("Calle Concepción", 650, 65, 5, 360, 400))
      @casillas<< Casilla.new2(6, TipoCasilla::JUEZ)
      @casillas<< Casilla.new(7, TipoCasilla::CALLE, TituloPropiedad.new("Paseo de la Independencia", 700, 70, 5, 430, 450))
      @casillas<< Casilla.new(8, TipoCasilla::CALLE, TituloPropiedad.new("Av de Diego Morón", 750, 75, 0, 500, 500))
      @casillas<< Casilla.new2(9, TipoCasilla::SORPRESA)
      @casillas<< Casilla.new(10, TipoCasilla::CALLE, TituloPropiedad.new("Calle Huerto Don Moisés", 800, 80, 0, 570, 550))
      @casillas<< Casilla.new(11, TipoCasilla::CALLE, TituloPropiedad.new("Av del Molino de la Vega", 850, 85, -5, 640, 600))
      @casillas<< Casilla.new2(12, TipoCasilla::CARCEL)
      @carcel = casillas[12];
      @casillas<< Casilla.new(13, TipoCasilla::CALLE, TituloPropiedad.new("Calle Niña", 900, 90, -5, 710, 650))
      @casillas<< Casilla.new2(14, TipoCasilla::IMPUESTO)
      @casillas<< Casilla.new2(15, TipoCasilla::SORPRESA)
      @casillas<< Casilla.new(16, TipoCasilla::CALLE, TituloPropiedad.new("Av de Italia", 950, 95, -10, 780, 700))
      @casillas<< Casilla.new(17, TipoCasilla::CALLE, TituloPropiedad.new("Calle Tesoro de la Aliseda", 1000, 100, -15, 850, 750))
      @casillas<< Casilla.new2(18, TipoCasilla::PARKING)
      @casillas<< Casilla.new(19, TipoCasilla::CALLE, TituloPropiedad.new("Av de Cristóbal Colón", 1000, 100, -20, 1000, 750))
    end
    
    def esCasillaCarcel(numeroCasilla)
      numeroCasilla==@carcel.numeroCasilla
    end
    
    def obtenerCasillaNumero(numeroCasilla)
      fail if (numeroCasilla<0 && numeroCasilla>@casillas.size)
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
      "Tablero:\n "
      puts @casillas      
      "#{@carcel}"
    end
    
    private :inicializar
  end
end
