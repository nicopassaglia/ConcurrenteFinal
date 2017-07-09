package Extra;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Monitor.GestorDeMonitor;

/*
 * Esta clase es obsoleta.
 */

public class Actor extends Thread{

	int ID;		//ID de este hilo(el primero comienza en 0)
	private LinkedList<Integer> fifo;		//Secuencia de transiciones a disparar
	private Matriz comunicador;				//Matriz que relaciona las transiciones con los hilos que las disparan
	private Matriz misTransiciones;			//Transiciones que puede disparar este hilo
	private GestorDeMonitor monitor;		
	private Actor[]	listaHilos;				//Lista de todos los hilos que existen
	Lock lock;

	public Actor(int ID, Matriz comunicador, GestorDeMonitor monitor, Matriz misTransiciones, Actor[] listaHilos){
		this.ID=ID;
		this.comunicador=comunicador;
		this.fifo = new LinkedList<Integer>();	
		this.monitor = monitor;
		this.misTransiciones = misTransiciones;
		this.listaHilos = listaHilos;
		lock = new ReentrantLock();
	}

	@Override
	public void run() {
		Integer transicion;
		Integer transicionQueDisparo;
		while(true){
			lock.lock();
			transicion=fifo.pollFirst();	//Devuelve null si la lista esta vacia
			lock.unlock();
			//y esta seccion, deben tener un lock.

			if(transicion!=null){	//Si no hay transicion para disparar no hago nada.
				transicionQueDisparo=transicion;
				//transicion=monitor.Disparar(transicion.intValue(), (Thread)this);
				disparar(transicionQueDisparo);
				if(transicion!=null){
					if(esMia(transicion)){
						addValue(transicion);	
					}
					else{
						llamarHilo(transicion);
					}

				}
			}
		}

	}

	private void disparar(Integer transicion){
		//Lo que haga el metodo disparar...
		System.out.println("El hilo " + ID + "disparo la transicion " + transicion.intValue());
	}

	private void llamarHilo(Integer transicion){
		//Para comunicar a otro hilo, lo primero que debo hacer es buscar el hilo al que debo hablar.
		Actor 	hilo = null;
		for(int i=0; i<comunicador.getFilCount(); i++){
			if(comunicador.getVal(i, transicion.intValue())==1){
				hilo=listaHilos[i];
			}	
			hilo.addValue(transicion);
		}
	}
	private boolean esMia(Integer transicion){
		int dato = transicion.intValue();
		for(int i=0; i<misTransiciones.getColCount();i++){
			if(dato==misTransiciones.getVal(ID, i)){
				return true;
			}
		}
		return false;

	}
	public void addValue(int transicion){
		lock.lock();
		Integer last = new Integer(transicion);
		fifo.addLast(last);
		lock.unlock();

	}	//este metodo
	public void addValue(Integer transicion){
		lock.lock();
		fifo.addLast(transicion);
		lock.unlock();
	}	//este metodo
}
