#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
require "singleton"

module ModeloQytetet
  class Dado
    include Singleton
    attr_reader :valor
    
    def initialize
       @valor
    end
    
    def tirar
      @valor = rand(1..6)
      @valor
    end
    
    def to_s
      "Dado \n Valor #{@valor}"
    end
    
  end
end
