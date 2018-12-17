#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
module ModeloQytetet
    class OtraCasilla < Casilla
        attr_reader :tipo
        attr_reader :titulo

        def initialize(numCas, coste, tp)
            super(numCas, coste)
            @tipo = tp
            @titulo = nil
        end

#        def self.copia(casilla, tipo)
#            super(casilla)
#            @tipo = tipo
#          
#            return self
#        end

        def soyEdificable
            salida = false
            salida
        end

        def tengoPropietario
            salida = false
            salida
        end 

        def to_s
          "#{super}\n\tTipo: #{@tipo}"
        end
    end
end