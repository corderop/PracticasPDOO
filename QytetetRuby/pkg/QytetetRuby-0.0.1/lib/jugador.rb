#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
module ModeloQytetet
  class Jugador
    attr_accessor :encarcelado
    attr_accessor :cartaLibertad
    attr_accessor :casillaActual
    attr_reader :nombre
    attr_reader :saldo
    attr_reader :propiedades
    
    def initialize(nmbr, cActual=nil, prpdds=Array.new, encarc=false, sald=7500, cartaLib=nil)
      @nombre = nmbr
      @casillaActual = cActual
      @propiedades = prpdds
      @encarcelado = encarc
      @saldo = sald
      @cartaLibertad = cartaLib
    end
    
    def self.copia(unJugador)
      self.new(unJugador.nombre, unJugador.casillaActual, unJugador.propiedades, unJugador.encarcelado, unJugador.saldo, unJugador.cartaLibertad)
    end

#    def self.nuevo(nombre)
#      self.new(nombre)
#    end

    def cuantasCasasHotelesTengo
      suma = 0
      
      for i in @propiedades
        suma += i.numCasas + i.numHoteles
      end
      
      suma
    end
    
    def devolverCartaLibertad
      aux = @cartaLibertad
      @cartaLibertad = nil
      
      aux
    end
    
    def obtenerCapital
      suma = 0
      
      suma += @saldo
      for i in @propiedades
        suma += i.precioCompra + (i.numCasas+i.numHoteles)*i.precioEdificar
      end
      
      suma
    end
    
    def pagarImpuesto
      @saldo -= casillaActual.precioCompra
    end
    
    def tengoCartaLibertad
      @cartaLibertad!=nil
    end
    
    def esDeMiPropiedad(titulo)
      salida = false
      
      for i in @propiedades
        if i==titulo
          salida = true
        end
      end
      
      salida
    end
    
    def modificarSaldo(cantidad)
      @saldo += cantidad
      
      @saldo
    end
    
    def obtenerPropiedades(estadoHipoteca)
      salida = Array.new
      
      for i in @propiedades
        if estadoHipoteca==i.hipotecada
          salida << i
        end
      end
      
      salida
    end
    
    def tengoSaldo(cantidad)
      @saldo>cantidad
    end
    
    def comprarTituloPropiedad
      comprado = false

      costeCompra = @casillaActual.precioCompra
      if costeCompra<@saldo
        titulo = @casillaActual.asignarPropietario(self)
        @propiedades<<titulo
        modificarSaldo(-costeCompra)
        comprado=true
      end
      
      comprado
    end
    
    def edificarCasa(titulo)
      edificada = false
      
      if puedoEdificarCasa(titulo)
        costeEdificarCasa = titulo.precioEdificar
        tengoSaldo = tengoSaldo(costeEdificarCasa)
        if tengoSaldo
          titulo.edificarCasa
          modificarSaldo(-costeEdificarCasa)
          edificada = true
        end
      end
      
      edificada
    end
    
    def irACarcel(casilla)
      @casillaActual = casilla
      @encarcelado = true
    end
    
    def pagarLibertad(cantidad)
      tengoSaldo = tengoSaldo(cantidad)
      
      if(tengoSaldo)
        @encarcelado=false
        modificarSaldo(-cantidad)
      end
    end
    
    def pagarAlquiler
      costeAlquiler = @casillaActual.pagarAlquiler
      modificarSaldo(-costeAlquiler)
    end
    
    def deboPagarAlquiler
      titulo = @casillaActual.titulo
      esDeMiPropiedad = esDeMiPropiedad(titulo)
      deboPagar = false
      
      if !esDeMiPropiedad
        tienePropietario = titulo.tengoPropietario
        if tienePropietario
          estaHipotecada = titulo.hipotecada
          if !estaHipotecada
            deboPagar = true
          end
        end
      end
      
      deboPagar
    end
    
    def hipotecarPropiedad(titulo)
      costeHipoteca = titulo.hipotecar
      modificarSaldo(costeHipoteca)
    end
    
    def venderPropiedad(casilla)
      titulo = casilla.titulo
      eliminarDeMisPropiedades(titulo)
      precioVenta = titulo.calcularPrecioVenta
      modificarSaldo(precioVenta)
    end
    
    def eliminarDeMisPropiedades(titulo)
      @propiedades.delete(titulo)
      titulo.propietario = nil
    end
    
    def edificarHotel(titulo)
      edificado = false
      
      if puedoEdificarHotel(titulo)
        costeEdificarHotel = titulo.precioEdificar
        tengoSaldo = tengoSaldo(costeEdificarHotel)
        if tengoSaldo
          titulo.edificarHotel
          modificarSaldo(-costeEdificarHotel)
          edificado = true
        end
      end
      
      edificado
    end
    
    def cancelarHipoteca(titulo)
      cancelada = false
      costeCancelar = titulo.calcularCosteCancelar
      
      if(costeCancelar<@saldo)
        modificarSaldo(-costeCancelar)
        titulo.cancelarHipoteca
        cancelada=true
      end
      
      cancelada
    end

    def convertirme(fianza)
      salida = Especulador.copia(self, fianza)

      salida
    end

    def deboIrACarcel
      salida = !tengoCartaLibertad

      salida
    end

    def puedoEdificarCasa(titulo)
      salida = titulo.numCasas<4;

      salida
    end

    def puedoEdificarHotel(titulo)
      salida = (titulo.numHoteles<4 && titulo.numCasas==4)

      salida
    end
    
    def to_s
      "Jugador: Nombre #{@nombre} \n Casilla actual #{@casillaActual} \n Propiedades #{@propiedades} \n Encarcelado #{@encarcelado} \n Saldo #{@saldo} \n Carta de libertad #{@cartaLibertad}"
    end
    
    def <=>(otroJugador)
      otro_capital = otroJugador.obtenerCapital
      mi_capital = obtenerCapital
      
      if (otro_capital>mi_capital)
        return 1 end
      
      if (otro_capital<mi_capital)
        return -1 end
      
      return 0
    end

    private :eliminarDeMisPropiedades, :esDeMiPropiedad
  end
end
