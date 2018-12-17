#encoding: utf-8
# Francisco Beltrán Sánchez
# Pablo Cordero Romero
module ModeloQytetet
    class Calle < Casilla
        attr_reader :titulo
        attr_reader :tipo

        def initialize(numCas, coste, ttl)
            super(numCas, coste)
            @titulo = ttl
            @tipo = TipoCasilla::CALLE
        end

#        def self.copia(casilla,ttl)
#            super(casilla)
#            @titulo = ttl
#            @tipo = TipoCasilla::CALLE
#            
#            self
#        end

        def soyEdificable
            salida = true
            salida
        end

        def tengoPropietario
            salida = @titulo.tengoPropietario();
            salida
        end

        def setTitulo(ttl)
            @titulo = ttl
            super(ttl)
        end

        def propietarioEncarcelado
            @titulo.propietarioEncarcelado
        end

        def asignarPropietario(jugador)
            @titulo.propietario = jugador
            @titulo
        end

        def pagarAlquiler
            @titulo.pagarAlquiler
        end

        def to_s
          "#{super}\n\tTipo: #{@tipo} \n\tTitulo: #{@titulo} \n"
        end

        private :setTitulo
    end
end