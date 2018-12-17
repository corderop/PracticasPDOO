/**
 * Practica de:
 * 
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;
import java.util.ArrayList;

public class Tablero {
    private ArrayList<Casilla> casillas;
    private Casilla carcel;

    Tablero() {
        inicializar();
    }

    ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    Casilla getCarcel() {
        return carcel;
    }
    
    private void inicializar(){
        casillas = new ArrayList<>();
        
        casillas.add(new OtraCasilla(0, 0, TipoCasilla.SALIDA));
        casillas.add(new Calle(1, 500, new TituloPropiedad("Av. de Andalucía", 500, 50, 20, 150, 250)));
        casillas.add(new Calle(2, 550, new TituloPropiedad("Calle Camarones", 550, 55, 15, 220, 300)));
        casillas.add(new OtraCasilla(3, 0, TipoCasilla.SORPRESA));
        casillas.add(new Calle(4, 600, new TituloPropiedad("Av. de José Fariña", 600, 60, 10, 290, 350)));
        casillas.add(new Calle(5, 650, new TituloPropiedad("Calle Concepción", 650, 65, 5, 360, 400)));
        casillas.add(new OtraCasilla(6, 0, TipoCasilla.JUEZ));
        casillas.add(new Calle(7, 700, new TituloPropiedad("Paseo de la Independencia", 700, 70, 5, 430, 450)));
        casillas.add(new Calle(8, 750, new TituloPropiedad("Av de Diego Morón", 750, 75, 0, 500, 500)));
        casillas.add(new OtraCasilla(9, 0, TipoCasilla.SORPRESA));
        casillas.add(new Calle(10, 800, new TituloPropiedad("Calle Huerto Don Moisés", 800, 80, 0, 570, 550)));
        casillas.add(new Calle(11, 850, new TituloPropiedad("Av del Molino de la Vega", 850, 85, -5, 640, 600)));
        casillas.add(new OtraCasilla(12, 0, TipoCasilla.CARCEL));
        carcel = casillas.get(12);
        casillas.add(new Calle(13, 900, new TituloPropiedad("Calle Niña", 900, 90, -5, 710, 650)));
        casillas.add(new OtraCasilla(14, 500, TipoCasilla.IMPUESTO));
        casillas.add(new OtraCasilla(15, 0, TipoCasilla.SORPRESA));
        casillas.add(new Calle(16, 950, new TituloPropiedad("Av de Italia", 950, 95, -10, 780, 700)));
        casillas.add(new Calle(17, 1000, new TituloPropiedad("Calle Tesoro de la Aliseda", 1000, 100, -15, 850, 750)));
        casillas.add(new OtraCasilla(18, 0, TipoCasilla.PARKING));
        casillas.add(new Calle(19, 1000, new TituloPropiedad("Av de Cristóbal Colón", 1000, 100, -20, 1000, 750)));
    }
    
    boolean esCasillaCarcel(int numeroCasilla){
        return (numeroCasilla == carcel.getNumeroCasilla());
    }
    
    Casilla obtenerCasillaNumero( int numeroCasilla ){
        assert (numeroCasilla > 0 && numeroCasilla < casillas.size());
        return casillas.get(numeroCasilla);
    }
    
    Casilla obtenerCasillaFinal(Casilla casilla, int desplazamiento){
        Casilla retorno = null;
        
        for(int i=0 ; i<=casillas.size() && retorno==null ; i++){
            if(casillas.get(i) == casilla)
                retorno = casillas.get((i+desplazamiento)%casillas.size());  
        }
        
        assert retorno != null;
        return retorno;
    }

    @Override
    public String toString() {
        return "\n-----\nTablero" + "\n\tcasillas=" + casillas + "\n-----\n\tCarcel=" + carcel;
    }

    
}
