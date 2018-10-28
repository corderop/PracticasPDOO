#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
module ModeloQytetet
  
  class Sorpresa
    #Consultores de forma implícita
    attr_reader :texto
    attr_reader :valor
    attr_reader :tipo

    def initialize(tx, vl, tp)
      @texto = tx
      @valor = vl
      @tipo = tp
    end

    def to_s
     "Texto: #{@texto} \n Valor: #{@valor} \n Tipo: #{@tipo}"
    end

=begin
    Así serían los consultores de forma explicita:

    def texto
      @texto
    end

    def valor
      @valor
    end

    def tipo
     @tipo
    end
=end

  end
end
