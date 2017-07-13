package Testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import Extra.LeerArchivo;
import Extra.Matriz;
import Extra.actorNuevo;
import Monitor.Colas;
import Monitor.GestorDeMonitor;
import Monitor.Politicas;
import Monitor.RDP;

public class systemTest {
	
	@Test
	public void pruebaRedSimple(){
	LeerArchivo oArchivo = new LeerArchivo();
	HashMap<String,int[][]> datos = oArchivo.LeerHTML();
	RDP rdp = new RDP(datos.get("marcado"),datos.get("incidencia"));

	Politicas politicas = new Politicas();



	int[][] incidenciaRDP = rdp.getIncidencia();
	int[][] marcadoRDP = rdp.getMarcado();

	
	Matriz incidencia = new Matriz(incidenciaRDP);
	
	Matriz marcado = new Matriz(marcadoRDP);
	// Matriz transiciones = oArchivo.leerTxtFile();
	Colas colas = new Colas(incidencia.getColCount());
//	incidencia.imprimirMatriz();
	GestorDeMonitor gdm = new GestorDeMonitor(colas,politicas,rdp);

	List<Matriz> listaTransiciones = new ArrayList<>();
	
	List<actorNuevo> actores = new ArrayList<>();

	listaTransiciones = oArchivo.leerTxtFile("hilosPrueba");

	for(int i = 0;i<listaTransiciones.size();i++){
		/*listaTransiciones.get(i).imprimirMatriz();
		System.out.println("----------------");*/
		actorNuevo temporal = new actorNuevo(listaTransiciones.get(i),gdm,i);
		actores.add(i,temporal);
		}
	
	}
}