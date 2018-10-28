#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
require "singleton"

module ModeloQytetet
  class Dado
    include Singleton
    attr_reader :valor
    
=begin
    MÉTODOS SIN INICIALIZAR

    def tirar ... end
=end   
 
    def initialize
       @valor
    end
    
    def to_s
      "Dado \n Valor #{@valor}"
    end
    
  end
end
