package Maquinas;

import Extra.Matriz;
import Monitor.GestorDeMonitor;

public class Maquina_1 extends Maquina{
	private Matriz secuencia;
	public Maquina_1(GestorDeMonitor gdm){
		super(gdm);
		definirTransiciones();
		secuencia = new Matriz(1,20).productoPorEscalar(0);
		
		
		}
	
	public void definirTransiciones(){
		for(int i=0;i<20;i++){
			if(i!=14){
				transiciones.setDato(0, i, 0);
			}else{
				transiciones.setDato(0, i, 1);
			}
		}
	}
	
	public void definirSecuencia(){
		
	}

	
}
