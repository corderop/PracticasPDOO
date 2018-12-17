package vistatextualqytetet;

import java.util.ArrayList;
import java.util.Scanner;
import controladorqytetet.*;

public class VistaTextualQytetet {
    private ControladorQytetet controlador = ControladorQytetet.getInstance();
    private static final Scanner in = new Scanner(System.in);
    
    public int obtenerOpcionMenu(ArrayList<String> menuOperaciones){
        for(String i : menuOperaciones){
            System.out.println(i +"-> "+OpcionMenu.values()[Integer.parseInt(i)]+" ");
        }
        
        return Integer.parseInt(leerValorCorrecto(menuOperaciones));
    }
    
    public ArrayList<String> obtenerNombreJugadores(){
        ArrayList<String> salida = new ArrayList<String>();
        
        System.out.print("Introduce número de jugadores: ");
        int tam = in.nextInt();
        String aux;
        
        if( tam>=2 && tam<=4){
            for( int i=0 ; i<tam ; i++){
                System.out.print("Jugador "+ i +": ");
                aux = in.next();
                salida.add(aux);
            }
        }
        else{
            System.out.println("Tiene que haber entre 2 y 4 jugadores");
            System.exit(0);
        }
        
        return salida;
    }
    
    public int elegirCasilla(int opcionMenu){
        ArrayList<Integer> casillasValidas = controlador.obtenerCasillasValidas(opcionMenu);
        ArrayList<String> convertidoAString = new ArrayList<>();
        int valorLeido;
        
        if( casillasValidas.isEmpty() ){
            System.out.println("-------------------"+
                               "\nNo hay ninguna casilla con la que realizar esta acción "+
                               "\n-------------------");
            return -1;
        }
        else{
            System.out.println("-------------------"+
                               "\nEscoja la posición de la casilla sobre la que quiera operar"+
                               "\nde las mostradas abajo: ");
            for(int i : casillasValidas){
                convertidoAString.add(Integer.toString(i));
                System.out.print(i+" ");
            }
            System.out.println("\n-------------------");
        }
        
        valorLeido = Integer.parseInt(leerValorCorrecto(convertidoAString));
        
        return valorLeido;
    }
  
    public String leerValorCorrecto(ArrayList<String> valoresCorrectos){
        String entrada;
        
        do{
            System.out.print("Introduce un valor válido: ");
            entrada = in.next();
        }while(valoresCorrectos.indexOf(entrada) == -1);
        
        return entrada;
    }
    
    public int elegirOperacion(){
        ArrayList<Integer> opValidas = controlador.obtenerOperacionesJuegoValidas();
        ArrayList<String> convertidas = new ArrayList<>();
        
        for(int i : opValidas)
            convertidas.add(Integer.toString(i));
        
        return obtenerOpcionMenu(convertidas);
    }
    
    public static void main(String[] args) {
        
        VistaTextualQytetet ui = new VistaTextualQytetet();
        ui.controlador.setNombreJugadores(ui.obtenerNombreJugadores());
        int operacionElegida, casillaElegida = 0;
        boolean necesitaElegirCasilla;

        do {
            operacionElegida = ui.elegirOperacion();
            necesitaElegirCasilla = ui.controlador.necesitaElegirCasilla(operacionElegida);

            if (necesitaElegirCasilla)
                casillaElegida = ui.elegirCasilla(operacionElegida);

            if (!necesitaElegirCasilla || casillaElegida >= 0)
                System.out.println(ui.controlador.realizarOperacion(operacionElegida, casillaElegida));
        } while (true);
    }

}
