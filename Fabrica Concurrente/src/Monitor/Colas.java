package Monitor;
import java.util.ArrayList;
import java.util.Vector;

import Extra.Matriz;

public class Colas {

	private Cola[] arregloColas;
	private Matriz arregloEstan;
	
	public Colas(int transiciones){
		arregloColas = new Cola[transiciones];
		this.arregloEstan = new Matriz(transiciones,1);
		for(int i=0; i<transiciones; i++){
			arregloColas[i]=new Cola("Cortesia");
			arregloEstan.setDato(i, 0, 0);
		}
	}
	
	public synchronized void acquire(int transicion, Thread proceso){
		arregloColas[transicion].meterEnCola(proceso);
		arregloEstan.setDato(transicion, 0, 1);
		//arregloEstan[transicion]=true;
	}
	public void release(int transicion){
		
		arregloColas[transicion].obtenerProceso();
		if(arregloColas[transicion].isEmpty())
			arregloEstan.setDato(transicion, 0, 0);
	}
	
	public Matriz quienesEstan(){
		
		return arregloEstan;
	}
	
}