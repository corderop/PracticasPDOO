#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
module ModeloQytetet
  class TituloPropiedad
    attr_accessor :hipotecada
    attr_accessor :propietario
    attr_reader :nombre
    attr_reader :precioCompra
    attr_reader :alquilerBase
    attr_reader :factorRevalorizacion
    attr_reader :hipotecaBase
    attr_reader :precioEdificar
    attr_reader :numHoteles
    attr_reader :numCasas
    
    def initialize(nb, pCompra, aBase, fRevalorizacion, hBase, pEdificar)
      @nombre = nb
      @hipotecada = false
      @numHoteles = 0
      @numCasas = 0
      @propietario = nil
      
      if pCompra>=500 && pCompra<=1000
        @precioCompra = pCompra
      end
      
      if aBase>=50 && aBase<=100
        @alquilerBase = aBase
      end
      
      if (fRevalorizacion>=10 && fRevalorizacion<=20 ) || ( fRevalorizacion>=-20 && fRevalorizacion<=-10)
        @factorRevalorizacion = fRevalorizacion
      end
      
      if hBase>=150 && hBase<=1000
        @hipotecaBase = hBase
      end
      if pEdificar>=250 && pEdificar<=750
        @precioEdificar = pEdificar
      end
    end
    
    def propietarioEncarcelado
      @propietario.encarcelado
    end
    
    def tengoPropietario
      @propietario!=nil
    end
    
    def edificarCasa
      @numCasas +=1
    end
    
    def pagarAlquiler
      costeAlquiler = calcularImporteAlquiler
      @propietario.modificarSaldo(costeAlquiler)
      
      costeAlquiler
    end
    
    def calcularImporteAlquiler
      @alquilerBase+(@numCasas*0.5+@numHoteles*2)
    end
    
    def hipotecar
      costeHipoteca = calcularCosteHipotecar
      @hipotecada = true
      
      costeHipoteca
    end
    
    def calcularCosteHipotecar
      @hipotecaBase + @numCasas*0.5 + @hipotecaBase + @numHoteles*@hipotecaBase
    end
    
    def calcularPrecioVenta
      @precioCompra + ( @numCasas + @numHoteles ) * @precioEdificar * @factorRevalorizacion
    end
    
    def edificarHotel
      @numHoteles += 1
      @numCasas -= 4
    end
    
    def calcularCosteCancelar
      costeHipotecar = calcularCosteHipotecar
      costeHipotecar+costeHipotecar*0.1
    end
    
    def cancelarHipoteca
      @hipotecada = false
    end
    
    def to_s
      "Nombre: #{@nombre} \n Hipotecada: #{@hipotecada} \n Precio compra: #{@precioCompra} \n Alquiler base: #{@alquilerBase} \n Factor revalorizacion: #{@factorRevalorizacion} \n Hipoteca base: #{@hipotecaBase} \n Precio edificar: #{@precioEdificar} \n Numero hoteles: #{@numHoteles} \n Numero casas: #{@numCasas}\n"
    end
    
  end
end
