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
    
=begin
  MÉTODOS SIN IMPLEMENTAR
  
  def calcularCosteCancelar();
  def calcularCosteHipotecar();
  def calcularImporteAlquiler();
  def calcularPrecioVenta();
  def cancelarHipoteca();
  def cobrarAlquiler(coste);
  def edificarCasa();
  def edificarHotel();
  def hipotecar();
  def pagarAlquiler();
  def propietarioEncarcelado();
  def tengoPropietario();
=end
    
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
    
    def to_s
      "Nombre: #{@nombre} \n Hipotecada: #{@hipotecada} \n Precio compra: #{@precioCompra} \n Alquiler base: #{@alquilerBase} \n Factor revalorizacion: #{@factorRevalorizacion} \n Hipoteca base: #{@hipotecaBase} \n Precio edificar: #{@precioEdificar} \n Numero hoteles: #{@numHoteles} \n Numero casas: #{@numCasas} \n Propietario: #{@propietario}"
    end
    
  end
end
