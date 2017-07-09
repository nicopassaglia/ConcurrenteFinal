package Monitor;

import java.util.concurrent.Semaphore;

import Extra.Matriz;
import Instrumentos.Instrumento;

public class GestorDeMonitor {

	public Colas colas;
	public Semaphore mutex;
	public Politicas politicas;
	public RDP rdp;
	private boolean k;
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

	public void Disparar(int transicion, Thread proceso){
		
		
		
		//pregunta
		//REVISAR EL TEMA VARIABLES
		try {
			mutex.acquire();
			k=true;
			while(k){
				sensiViejas = rdp.sensibilizadas();
				k = rdp.disparar(transicion);
				if(k){
					sensiNuevas=rdp.sensibilizadas();
					quienes = colas.quienesEstan();
					and = sensiNuevas.AND(quienes);
					cual = politicas.cual(and);

					if(cual != -1){
						colas.release(cual);
					
						//salir del monitor 
						return;
					}
					else{
						k=false;
					}
				}
				else{
					//para mi esto tiene que ser sincronizado??
					//pregunta
					mutex.release();
					colas.acquire(transicion, proceso);
					
					//colas.acquire debe ser sincronizado

				}
				
			}
			mutex.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}
