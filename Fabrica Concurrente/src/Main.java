import java.util.HashMap;

import Extra.LeerArchivo;
import Extra.Matriz;
import Monitor.Colas;
import Monitor.GestorDeMonitor;
import Monitor.Politicas;
import Monitor.RDP;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LeerArchivo oArchivo = new LeerArchivo();
        HashMap<String,int[][]> datos = oArchivo.LeerHTML();
        RDP rdp = new RDP(datos.get("marcado"),datos.get("incidencia"));
        Colas cola1 = new Colas();
        Colas cola2 = new Colas();
        Politicas politicas = new Politicas();
        GestorDeMonitor gdm = new GestorDeMonitor(cola1,politicas,rdp);
        
        
        int[][] incidenciaRDP = rdp.getIncidencia();
        int[][] marcadoRDP = rdp.getMarcado();
        
        
        Matriz incidencia = new Matriz(incidenciaRDP);
        Matriz marcado = new Matriz(marcadoRDP);
        Matriz sensibilizadas = rdp.sensibilizadas();       
        /*
        }*/
       /* incidencia.imprimirMatriz();
        System.out.println("\n");
       /* marcado.imprimirMatriz();
        System.out.println("\n");
        and.imprimirMatriz();
        System.out.println("\n");*/
        /*marcado.transpose().imprimirMatriz();
        System.out.println("\n");*/
     
		
	}

}
