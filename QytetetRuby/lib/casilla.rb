#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
module ModeloQytetet
  class Casilla
    attr_reader :numeroCasilla
    attr_reader :precioCompra
    attr_reader :tipo
    attr_reader :titulo
    
    def initialize(numCas, tp, ttl)
      @numeroCasilla = numCas
      @tipo = tp
      @precioCompra = 0
      if !ttl.nil?
        setTitulo(ttl);
      end
    end
    
    def self.new2(numCas, tp)
      self.new(numCas, tp, nil)
    end
    
    # Metodo privatizado más abajo
    def setTitulo(ttl)
      @titulo = ttl
      @precioCompra = ttl.precioCompra
    end
    
    def soyEdificable  
      @tipo==TipoCasilla::CALLE
    end
    
    def tengoPropietario
      @titulo.tengoPropietario
    end
    
    def propietarioEncarcelado
      @titulo.propietarioEncarcelado
    end
    
    def asignarPropietario(jugador)
      @titulo.propietario = jugador;
      @titulo
    end
    
    def pagarAlquiler
      @titulo.pagarAlquiler
    end
    
    def to_s
      if(@tipo==TipoCasilla::CALLE)
        "Casilla: \n Número #{@numeroCasilla} \n Coste: #{@precioCompra} \n Tipo: #{@tipo} \n Titulo: #{@titulo} \n"
      else
        "Casilla: \n Número #{@numeroCasilla} \n Tipo: #{@tipo} \n"
      end
    end
    
    private :setTitulo
  end
end
