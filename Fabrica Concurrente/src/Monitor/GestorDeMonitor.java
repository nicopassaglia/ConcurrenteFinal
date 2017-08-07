package Monitor;

import java.util.concurrent.Semaphore;

import Extra.Matriz;
import Extra.actorNuevo;
import Instrumentos.Instrumento;

public class GestorDeMonitor {

	public Colas colas;
	public Semaphore mutex;
	public Politicas politicas;
	public RDP rdp;
	private int k;
	private int numero_disparos_realizados;
	private int numero_disparos_objetivo;
	private Matriz sensiViejas;
	private Matriz sensiNuevas;
	private Matriz quienes;
	private Matriz and;
	private int cual;
	private boolean first_time;

	public GestorDeMonitor(Colas colas, Politicas politicas, RDP rdp,Semaphore mutex){

	//	this.mutex = new Semaphore(1,true);
		this.mutex = mutex;
		this.colas = colas;
		this.politicas = politicas;
		this.rdp = rdp;
		this.numero_disparos_objetivo = 0;
		this.numero_disparos_realizados = 0;
		this.first_time = true;
	}
	
	public GestorDeMonitor(Colas colas, Politicas politicas, RDP rdp,Semaphore mutex, int numero_disparos){

	//	this.mutex = new Semaphore(1,true);
		this.mutex = mutex;
		this.colas = colas;
		this.politicas = politicas;
		this.rdp = rdp;
		this.numero_disparos_objetivo = numero_disparos;
		this.numero_disparos_realizados = 0;
		this.first_time = true;
	}

	public boolean Disparar(int transicion, actorNuevo actor){
		Thread proceso = actor.getThread();
		
		
		//pregunta
		//REVISAR EL TEMA VARIABLES
		try {
			mutex.acquire();
			System.out.println("Soy "+actor.getID()+" quiero ejecutar: "+transicion);
			k=1;
			while(k == 1){
				
				sensiViejas = rdp.sensibilizadas();
				k = rdp.disparar(transicion);
				
				if(k==1){
					if(this.numero_disparos_objetivo != 0 && this.numero_disparos_objetivo == this.numero_disparos_realizados){
						stopExecution();
					}
					this.numero_disparos_realizados++;
					
					System.out.println("se ejecuto transicion :" +transicion +" por el hilo:"+actor.getID()+" en el instante:"+System.currentTimeMillis());				
					sensiNuevas=rdp.sensibilizadas();
					//sensiNuevas.imprimirMatriz();
					//colas.quienesEstan().imprimirMatriz();
					quienes = colas.quienesEstan();
					and = sensiNuevas.AND(quienes);
					cual = politicas.cualFifo(and);

					if(cual != -1){
						colas.release(cual);
					
						//salir del monitor 
						return true;
					}
					else{
						k=0;
					}
				}
				else if(k==0){
					//para mi esto tiene que ser sincronizado??
					//pregunta
					System.out.println("no pude, estoy en cola, soy: " + actor.getID());
					politicas.insertFifo(transicion);
					mutex.release();
					colas.acquire(transicion, proceso);
					
					//colas.acquire debe ser sincronizado

				}else{
					//dormido
					long tiempoDormir = rdp.getTiempo();
					System.out.println("ME DEBERIA DORMIR SOY "+ actor.getID() + " por " + tiempoDormir);
					mutex.release();
					
					sleep_thread(tiempoDormir, actor);
					
					
					
					
					return false;
				}
				
			}
			mutex.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	private void sleep_thread(long tiempoDormir, actorNuevo actor){
		try{
			synchronized(this){
				
				wait(tiempoDormir);
				System.out.println("ME DESPERTE :"+ actor.getID());
			}
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void releaseMutex(){
		this.mutex.release();
	}

	public void stopExecution(){
		if(first_time){
			first_time = false;
			System.out.println("####Cantidad de T-Invariantes####");
			this.rdp.getContadorTinvariante().imprimirMatriz();
		}
		releaseMutex();
		
		try {
			synchronized(this){
				wait();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
