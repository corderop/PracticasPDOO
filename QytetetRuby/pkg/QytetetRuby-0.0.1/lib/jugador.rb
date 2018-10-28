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
=begin
  MÉTODOS SIN IMPLEMENTAR

  def cancelarHipoteca(titulo)
  def comprarTituloPropiedad()
  def cuantasCasasHotelesTengo()
  def deboPagarAlquiler()
  def devolverCartaLibertad()
  def edificarCasa(titulo)
  def edificarHotel(titulo)
  def eliminarDeMisPropiedades(titulo)
  def esDeMiPropiedad(titulo)
  def estoyEnCalleLibre()
  def hipotecarPropiedad(titulo)
  def irACarcel(casilla)
  def modificarSaldo(cantidad)
  def obtenerCapital()
  def obtenerPropiedades(hipotecada)
  def pagarAlquiler()
  def pagarImpuesto()
  def pagarLibertad(cantidad)
  def tengoCartaLibertad()
  def tengoSaldo(cantidad)
  def venderPropiedad(casilla)

  private :eliminarDeMisPropiedades, :esDeMiPropiedad, :tengoSaldo
=end
    
    def initialize(nmbr, cActual, prpdds)
      @nombre = nmbr
      @casillaActual = cActual
      @propiedades = prpdds
      @encarcelado = false
      @saldo = 7500
      @cartaLibertad = nil
    end
    
    def to_s
      "Jugador: \n Nombre #{@nombre} \n Casilla actual #{@casillaActual} \n Propiedades #{@propiedades} \n Encarcelado #{@encarcelado} \n Saldo #{@saldo} \n Carta de libertad #{@cartaLibertad}"
    end
  end
end
