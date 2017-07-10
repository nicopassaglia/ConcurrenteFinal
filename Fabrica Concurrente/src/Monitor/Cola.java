package Monitor;
import java.util.LinkedList;
/*
 * Esta es la clase Cola. La clase cola es la cola en que entran los hilos (Runnables). 
 * 
 * @See Colas.java
 */

import java.util.Queue;
public class Cola {


	private Queue<Runnable> fifo;
	private String tipoCola;

	public Cola(String tipo){
		this.tipoCola = tipo;
		this.fifo =  new LinkedList<>();
	
	}
	
	
	
	public String getTipoCola(){
		return this.tipoCola;
	}
	
	/*
	 * Devuelve el tipo de cola.
	 * Por ejemplo: Cola Comun, Cola de Cortesia, etc.
	 */
	
	
	public Runnable obtenerProceso(){
		Runnable proceso = fifo.poll();
		
		return proceso;
	
	}
	
	/*
	 * Devuelve el primer proceso de la cola.
	 */
	
	public boolean meterEnCola(Thread proceso){
	
		try {
			proceso.wait();
			return fifo.add(proceso);
		} catch (Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
	}
	
	/*
	 * Duerme al proceso y lo pone en la cola.
	 */
	
	public int tamañoCola(){
		return fifo.size();
	}
	
	
	public boolean isEmpty(){
		return fifo.isEmpty();
	}
	
	/*
	 * Devuelve si la cola esta vacia o no.
	 */
}
