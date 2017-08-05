package Testing;

import static org.junit.Assert.*;

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

public class testPinvariante {
	/*private static LeerArchivo oArchivo  = new LeerArchivo();;
	private static HashMap<String,int[][]> datos = oArchivo.LeerHTML();*/


	
	@Test
	public void testPinvariante(){

		LeerInvariantes oInvariantes = new LeerInvariantes();
		oInvariantes.obtenerInvariantes("invariantes.txt");
		LeerArchivo oArchivo = new LeerArchivo();
		HashMap<String,int[][]> datos = oArchivo.LeerHTML();
		int tamano = datos.get("incidencia")[0].length;
		Semaphore mutex = new Semaphore(1,true);
		Tiempo tiempo = new Tiempo(tamano,mutex,"tiempo");
		RDP rdp = new RDP(datos.get("marcado"),datos.get("incidencia"),tiempo, oInvariantes);
		int[][] incidenciaRDP = rdp.getIncidencia();
		int[][] marcadoRDP = rdp.getMarcado();
		Matriz incidencia = new Matriz(incidenciaRDP);
		Matriz marcado = new Matriz(marcadoRDP);


		Matriz marcadoTemporal = new Matriz(1,marcado.getColCount());
		
		for(int i=0;i<marcado.getColCount();i++){
			marcadoTemporal.setDato(0, i, 1);
			
		}

		rdp.setMarcado(marcadoTemporal);
		
		assertFalse(rdp.comprobarPinvariante());

	}
	
	@Test
	public void testPinvariante2(){

		LeerInvariantes oInvariantes = new LeerInvariantes();
		oInvariantes.obtenerInvariantes("invariantes.txt");
		LeerArchivo oArchivo = new LeerArchivo();
		HashMap<String,int[][]> datos = oArchivo.LeerHTML();
		int tamano = datos.get("incidencia")[0].length;
		Semaphore mutex = new Semaphore(1,true);
		Tiempo tiempo = new Tiempo(tamano,mutex,"tiempo");
		RDP rdp = new RDP(datos.get("marcado"),datos.get("incidencia"),tiempo, oInvariantes);
		int[][] incidenciaRDP = rdp.getIncidencia();
		int[][] marcadoRDP = rdp.getMarcado();
		Matriz incidencia = new Matriz(incidenciaRDP);
		Matriz marcado = new Matriz(marcadoRDP);


		Matriz marcadoTemporal = new Matriz(1,marcado.getColCount());
		
		marcado.setDato(0, 0, 0);
		marcado.setDato(0, 10, 24);
		marcado.setDato(0, 16, 1);
		marcado.setDato(0, 17, 1);
		marcado.setDato(0, 24, 0);
		marcado.setDato(0, 26, 0);
		
		
		rdp.setMarcado(marcado);
		
		assertTrue(rdp.comprobarPinvariante());

	}

	@Test
	public void testPinvariante3(){

		LeerInvariantes oInvariantes = new LeerInvariantes();
		oInvariantes.obtenerInvariantes("invariantes.txt");
		LeerArchivo oArchivo = new LeerArchivo();
		HashMap<String,int[][]> datos = oArchivo.LeerHTML();
		int tamano = datos.get("incidencia")[0].length;
		Semaphore mutex = new Semaphore(1,true);
		Tiempo tiempo = new Tiempo(tamano,mutex,"tiempo");
		RDP rdp = new RDP(datos.get("marcado"),datos.get("incidencia"),tiempo, oInvariantes);
		int[][] incidenciaRDP = rdp.getIncidencia();
		int[][] marcadoRDP = rdp.getMarcado();
		Matriz incidencia = new Matriz(incidenciaRDP);
		Matriz marcado = new Matriz(marcadoRDP);


		Matriz marcadoTemporal = new Matriz(1,marcado.getColCount());
		

		marcado.setDato(0, 17, 1);
	
		rdp.setMarcado(marcado);
		
		assertFalse(rdp.comprobarPinvariante());

	}
	@Test
	public void testPinvariante4(){

		LeerInvariantes oInvariantes = new LeerInvariantes();
		oInvariantes.obtenerInvariantes("invariantes.txt");
		LeerArchivo oArchivo = new LeerArchivo();
		HashMap<String,int[][]> datos = oArchivo.LeerHTML();
		int tamano = datos.get("incidencia")[0].length;
		Semaphore mutex = new Semaphore(1,true);
		Tiempo tiempo = new Tiempo(tamano,mutex,"tiempo");
		RDP rdp = new RDP(datos.get("marcado"),datos.get("incidencia"),tiempo, oInvariantes);
		int[][] incidenciaRDP = rdp.getIncidencia();
		int[][] marcadoRDP = rdp.getMarcado();
		Matriz incidencia = new Matriz(incidenciaRDP);
		Matriz marcado = new Matriz(marcadoRDP);


		Matriz marcadoTemporal = new Matriz(1,marcado.getColCount());
		

		marcado.setDato(0, 0, 0);
		marcado.setDato(0, 10, 25);
		marcado.setDato(0, 13, 24);
		marcado.setDato(0, 14, 1);
		marcado.setDato(0, 17, 1);
		marcado.setDato(0, 21, 25);
		marcado.setDato(0, 24, 0);
		marcado.setDato(0, 23, 0);
		marcado.setDato(0, 26, 0);
		marcado.setDato(0, 27, 0);
		marcado.setDato(0, 3, 0);
		
		marcado.setDato(0, 5, 1);
		marcado.setDato(0, 8, 1);
		
		
		
	
		rdp.setMarcado(marcado);
		
		assertTrue(rdp.comprobarPinvariante());

	}
}
