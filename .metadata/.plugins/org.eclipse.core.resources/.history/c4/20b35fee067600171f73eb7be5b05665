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
	private Matriz sensiViejas;
	private Matriz sensiNuevas;
	private Matriz quienes;
	private Matriz and;
	private int cual;

	public GestorDeMonitor(Colas colas, Politicas politicas, RDP rdp){

		this.mutex = new Semaphore(1,true);
		this.colas = colas;
		this.politicas = politicas;
		this.rdp = rdp;
	}

	public void Disparar(int transicion, actorNuevo actor){
		Thread proceso = actor.getThread();
		
		
		//pregunta
		//REVISAR EL TEMA VARIABLES
		try {
			mutex.acquire();
			k=1;
			while(k == 1){
				
				sensiViejas = rdp.sensibilizadas();
				k = rdp.disparar(transicion);
				
				if(k==1){
					System.out.println("se ejecuto transicion :" +transicion +" por el hilo:"+actor.getID()+" en el instante:"+System.currentTimeMillis());
					sensiNuevas=rdp.sensibilizadas();
					//sensiNuevas.imprimirMatriz();
					//colas.quienesEstan().imprimirMatriz();
					quienes = colas.quienesEstan();
					and = sensiNuevas.AND(quienes);
					cual = politicas.cual(and);

					if(cual != -1){
						colas.release(cual);
					
						//salir del monitor 
						return;
					}
					else{
						k=0;
					}
				}
				else if(k==0){
					//para mi esto tiene que ser sincronizado??
					//pregunta
					//System.out.println("no pude, estoy en cola ");
					mutex.release();
					colas.acquire(transicion, proceso);
					
					//colas.acquire debe ser sincronizado

				}else{
					//dormido
					return;
				}
				
			}
			mutex.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void releaseMutex(){
		this.mutex.release();
	}




}
