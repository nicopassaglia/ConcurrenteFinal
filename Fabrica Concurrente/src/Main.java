import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Extra.LeerArchivo;
import Extra.Matriz;
import Maquinas.Maquina_1;
import Maquinas.Maquina_2;
import Maquinas.Maquina_3;
import Maquinas.Maquina_4;
import Monitor.Colas;
import Monitor.GestorDeMonitor;
import Monitor.Politicas;
import Monitor.RDP;
import Robots.Robot_1;
import Robots.Robot_2;
import Robots.Robot_3;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LeerArchivo oArchivo = new LeerArchivo();
        HashMap<String,int[][]> datos = oArchivo.LeerHTML();
        RDP rdp = new RDP(datos.get("marcado"),datos.get("incidencia"));
        
        Politicas politicas = new Politicas();
        
        
        
        int[][] incidenciaRDP = rdp.getIncidencia();
        int[][] marcadoRDP = rdp.getMarcado();
        
        
        Matriz incidencia = new Matriz(incidenciaRDP);
        Matriz marcado = new Matriz(marcadoRDP);
       // Matriz transiciones = oArchivo.leerTxtFile();
        

        
      List<Matriz> listaTransiciones = new ArrayList<>();
      
      listaTransiciones = oArchivo.leerTxtFile();
      
      for(int i = 0;i<listaTransiciones.size();i++){
    	  listaTransiciones.get(i).imprimirMatriz();
    	  System.out.println("----------------");
      }
        
       /* Matriz sensibilizadas = rdp.sensibilizadas();     
        
        Colas colas = new Colas(incidencia.getColCount());
        
        GestorDeMonitor gdm = new GestorDeMonitor(colas,politicas,rdp);
        
        Matriz quienesEstan = colas.quienesEstan();
        //HILOS
        Maquina_1 m1 = new Maquina_1(gdm);
        Thread hilo_maq_1 = new Thread(m1);
        
        Maquina_2 m2 = new Maquina_2(gdm);
        Thread hilo_maq_2 = new Thread(m2);
        
        Maquina_3 m3 = new Maquina_3(gdm);
        Thread hilo_maq_3 = new Thread(m3);
        
        Maquina_4 m4 = new Maquina_4(gdm);
        Thread hilo_maq_4 = new Thread(m4);
        
        Robot_1 r1 = new Robot_1();
        Thread hilo_rob_1 = new Thread(r1);
        
        Robot_2 r2= new Robot_2();
        Thread hilo_rob_2 = new Thread(r2);
        
        Robot_3 r3 = new Robot_3();
        Thread hilo_rob_3 = new Thread(r3);
        
        /*colas.acquire(5, m3);
        colas.acquire(13, m1);*/
        
        //ARRANCAN TODOS LOS HILOS
        
        
        
        /*hilo_maq_1.start();
		hilo_maq_3.start();
		hilo_maq_4.start();*/
        
		
		//sensibilizadas.transpose().imprimirMatriz();
       
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
