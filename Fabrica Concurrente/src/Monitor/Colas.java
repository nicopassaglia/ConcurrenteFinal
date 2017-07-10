package Monitor;
import java.util.ArrayList;
import java.util.Vector;

import Extra.Matriz;

/*
 * La clase Colas.java es la clase que asocia el elemento "Cola.java" con las transiciones correspondientes.
 * Tambien se encargara de poner hilos en las colas, de liberarlos y de avisar quienes estan en ellas.
 * 
 * @see Cola.java
 */

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
	
	/*
	 * Crea todas las colas comunes correspondientes con cada transicion y las guarda en un arreglo.
	 * Tambien crea un arreglo "arregloEstan" que tendra un "1" si la cola de esa posicion no esta vacia. Y un cero si esta vacia.
	 */
	
	public synchronized void acquire(int transicion, Thread proceso){
		arregloColas[transicion].meterEnCola(proceso);
		arregloEstan.setDato(transicion, 0, 1);
		try {
			proceso.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//arregloEstan[transicion]=true;
	}
	
	/*
	 * Mete un hilo en la cola correspondiente.
	 */
	
	public void release(int transicion){
		
		arregloColas[transicion].obtenerProceso().notify();
		if(arregloColas[transicion].isEmpty())
			arregloEstan.setDato(transicion, 0, 0);
	}
	
	/*
	 * Despierta al hilo y lo saca de la cola.
	 * Modifica el estado del arreglo "arregloEstan"
	 */
	
	public Matriz quienesEstan(){
		
		return arregloEstan;
	}
	
	/*
	 * Devuelve el arreglo que tiene quienes estan en la cola.
	 */
	
}