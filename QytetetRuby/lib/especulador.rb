#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
module ModeloQytetet
  class Especulador < Jugador
    attr_accessor :fianza

    def self.copia(jugador, fza)
      @fianza
      salida = super(jugador)
      salida.fianza = fza
      
      salida
    end
    
    def pagarImpuesto
      modificarSaldo(-(@casillaActual.precioCompra)/2)
    end

    def deboIrACarcel
      salida = (super && !pagarFianza)

      salida
    end

    def convertirme(fza)
      self
    end

    def pagarFianza
      salida = @saldo>=@fianza

      if salida
        modificarSaldo(-@fianza)
      end
      
      salida
    end
    
    def puedoEdificarCasa(titulo)
      salida = titulo.numCasas<8
      
      salida
    end

    def puedoEdificarHotel(titulo)
      salida = (titulo.numHoteles<8 && titulo.numCasas>=4)

      salida
    end

    def to_s
      "\n-----\nEspeculador: #{super} \n\tFianza= #{@fianza}"
    end
    
    private :pagarFianza
  end
end
