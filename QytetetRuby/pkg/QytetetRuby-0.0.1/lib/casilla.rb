#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
module ModeloQytetet
  class Casilla
    attr_reader :numeroCasilla
    attr_accessor :precioCompra

    def initialize(numCas, coste)
      @numeroCasilla = numCas
      @precioCompra = coste
    end
    
    def self.copia(casilla)
      self.new(casilla.numeroCasilla, casilla.precioCompra)
    end

    # Dejamos este método ya que no podemos modificar precioCompra desde calle
    def setTitulo(ttl)
      @precioCompra = ttl.precioCompra
    end

    def soyEdificable; end
    def tengoPropietario; end

    def to_s
        "\n-----\nCasilla: \n\tnumeroCasilla= #{@numeroCasilla} \n\tprecioCompra= #{@precioCompra}";
    end
    
    private :setTitulo
  end
end
