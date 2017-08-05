package Testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.junit.Test;

import Extra.LeerArchivo;
import Extra.LeerInvariantes;
import Extra.Matriz;
import Extra.actorNuevo;
import Monitor.Colas;
import Monitor.GestorDeMonitor;
import Monitor.Politicas;
import Monitor.RDP;
import Monitor.Tiempo;

public class systemTest {

	@Test
	public void pruebaRedSimple(){
		
			// TODO Auto-generated method stub

			LeerInvariantes oInvariantes = new LeerInvariantes();
			oInvariantes.obtenerInvariantes("invariantes-prueba");
			
		

			LeerArchivo oArchivo = new LeerArchivo();
			
			HashMap<String,int[][]> datos = oArchivo.LeerHTML();
			int tamano = datos.get("incidencia")[0].length;
			Semaphore mutex = new Semaphore(1,true);
			Tiempo tiempo = new Tiempo(tamano,mutex,"tiempoPrueba");
			//System.out.println(tamano);
			
			
			
			RDP rdp = new RDP(datos.get("marcado"),datos.get("incidencia"),tiempo, oInvariantes);

			Politicas politicas = new Politicas();



			int[][] incidenciaRDP = rdp.getIncidencia();
			int[][] marcadoRDP = rdp.getMarcado();

			
			Matriz incidencia = new Matriz(incidenciaRDP);
			
			Matriz marcado = new Matriz(marcadoRDP);
			// Matriz transiciones = oArchivo.leerTxtFile();
			Colas colas = new Colas(incidencia.getColCount());
//			incidencia.imprimirMatriz();
			
			GestorDeMonitor gdm = new GestorDeMonitor(colas,politicas,rdp,mutex);
			
			//tina(incidencia);
			List<Matriz> listaTransiciones = new ArrayList<>();
			
			List<actorNuevo> actores = new ArrayList<>();

			listaTransiciones = oArchivo.leerTxtFile("hilosPrueba");

			for(int i = 0;i<listaTransiciones.size();i++){
				/*listaTransiciones.get(i).imprimirMatriz();
				System.out.println("----------------");*/
				actorNuevo temporal = new actorNuevo(listaTransiciones.get(i),gdm,i);
				actores.add(i,temporal);
				
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
	
		public static void tina(Matriz incidencia){
			 String entradas = "";
			 String salidas = "";
			 String output;
			
			 for (int j=0; j<incidencia.getColCount(); j++){
				 for (int i=0; i<incidencia.getFilCount(); i++){
					 if(incidencia.getVal(i, j) == 1){
						 salidas = salidas + " p" + i;
					 }
					 else if(incidencia.getVal(i, j) == -1){
						 entradas = entradas + " p" + i;
					 }
				 }
				 output = "tr t" + j + entradas + " ->" + salidas;
				 System.out.println(output);
				 entradas = "";
				 salidas = "";
			 }
			
		}
}