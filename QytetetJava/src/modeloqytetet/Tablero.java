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
        
        casillas.add(new Casilla(0, TipoCasilla.SALIDA));
        casillas.add(new Casilla(1, new TituloPropiedad("Av. de Andalucía", 500, 50, 20, 150, 250)));
        casillas.add(new Casilla(2, new TituloPropiedad("Calle Camarones", 550, 55, 15, 220, 300)));
        casillas.add(new Casilla(3, TipoCasilla.SORPRESA));
        casillas.add(new Casilla(4, new TituloPropiedad("Av. de José Fariña", 600, 60, 10, 290, 350)));
        casillas.add(new Casilla(5, new TituloPropiedad("Calle Concepción", 650, 65, 5, 360, 400)));
        casillas.add(new Casilla(6, TipoCasilla.JUEZ));
        casillas.add(new Casilla(7, new TituloPropiedad("Paseo de la Independencia", 700, 70, 5, 430, 450)));
        casillas.add(new Casilla(8, new TituloPropiedad("Av de Diego Morón", 750, 75, 0, 500, 500)));
        casillas.add(new Casilla(9, TipoCasilla.SORPRESA));
        casillas.add(new Casilla(10, new TituloPropiedad("Calle Huerto Don Moisés", 800, 80, 0, 570, 550)));
        casillas.add(new Casilla(11, new TituloPropiedad("Av del Molino de la Vega", 850, 85, -5, 640, 600)));
        casillas.add(new Casilla(12, TipoCasilla.CARCEL));
        carcel = casillas.get(12);
        casillas.add(new Casilla(13, new TituloPropiedad("Calle Niña", 900, 90, -5, 710, 650)));
        casillas.add(new Casilla(14, TipoCasilla.IMPUESTO));
        casillas.add(new Casilla(15, TipoCasilla.SORPRESA));
        casillas.add(new Casilla(16, new TituloPropiedad("Av de Italia", 950, 95, -10, 780, 700)));
        casillas.add(new Casilla(17, new TituloPropiedad("Calle Tesoro de la Aliseda", 1000, 100, -15, 850, 750)));
        casillas.add(new Casilla(18, TipoCasilla.PARKING));
        casillas.add(new Casilla(19, new TituloPropiedad("Av de Cristóbal Colón", 1000, 100, -20, 1000, 750)));
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
        return "Tablero{" + "casillas=" + casillas + ", carcel=" + carcel + '}';
    }

    
}
