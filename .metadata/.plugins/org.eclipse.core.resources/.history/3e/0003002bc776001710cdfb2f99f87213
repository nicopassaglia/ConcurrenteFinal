package Extra;

import Monitor.GestorDeMonitor;

/*
 * actorNuevo es la clase actor que se va a usar.
 * Esta clase seran los hilos que se encargaran de disparar la secuencia de transiciones que se le cargara a cada hilo.
 * Las secuencias se encuentran en el archivo hilos.txt.
 * A cada hilo se le cargara un id para poder hacer la lectura mas facil. Esto no es una medida necesaria en la aplicacion real.
 */

public class actorNuevo implements Runnable {
	
	private Matriz secuencia;
	private int estadoActual;	//Para que tienen estado??? Para saber si esta dormido el hilo?
	private GestorDeMonitor gdm;
	private Thread yo;
	private int id;
	
	public actorNuevo(Matriz secuencia, GestorDeMonitor gdm, int id){
		this.id = id;
		this.secuencia = secuencia;
		this.gdm = gdm;
		yo = new Thread(this);
		yo.start();
	}
	/*
	 * Constructor
	 * 
	 * Se carga la secuencia y el gestor de monitor. Se comienza la ejecucion del hilo.
	 * @see java.lang.Runnable#run()
	 */
	
	public void run(){
		
		int i;
		while(true){
			
			for(i = 0; i<secuencia.getColCount();i++){
				//System.out.println("Soy "+this.getID()+" quiero ejecutar: "+secuencia.getVal(0, i));
				if(gdm.Disparar(secuencia.getVal(0, i), this)){
					
				}else{
					i--;
				}
				
				//Ejecutar accion. Falta tiempo.
				//System.out.println("Hilo " + getID() + ": "  + "Dispare "  + "la transicion "+ secuencia.getVal(0, i));
			}
		}
		
	}
	/*
	 * El metodo run() del Thread es el perteneciente al hilo. Este disparara la secuencia de transiciones, en un bucle infinito.
	 * Este metodo tambien se encargara de ejecutar la accion correspondiente.
	 * Y de imprimir en consola la ejecucion exitosa.
	 */
	
	public void setEstado(int estado){
		this.estadoActual = estado;
	}
	
	public int getEstado(){
		return this.estadoActual;
	}
	
	public void setSecuencia(Matriz secuencia){
		this.secuencia = secuencia;	
	}
	
	public int getID(){
		return this.id;
	}
	public Thread getThread(){
		return this.yo;
	}
	}

