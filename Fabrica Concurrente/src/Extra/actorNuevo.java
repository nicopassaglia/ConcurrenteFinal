package Extra;

import Monitor.GestorDeMonitor;

public class actorNuevo implements Runnable {
	
	private Matriz secuencia;
	private int estadoActual;
	private GestorDeMonitor gdm;
	private Thread yo;
	
	public actorNuevo(Matriz secuencia, GestorDeMonitor gdm){
		this.secuencia = secuencia;
		this.gdm = gdm;
		yo = new Thread(this);
		yo.start();
	}
	
	public void run(){
		
		while(true){
			
			for(int i = 0; i<secuencia.getFilCount();i++){
				gdm.Disparar(secuencia.getVal(0, i), yo);
				System.out.println("Dispare "+secuencia.getVal(0, i)+" transicion");
			}
		}
		
	}
	
	public void setEstado(int estado){
		this.estadoActual = estado;
	}
	
	public int getEstado(){
		return this.estadoActual;
	}
	
	public void setSecuencia(Matriz secuencia){
		
		this.secuencia = secuencia;
		
	}
	}

