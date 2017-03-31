package Maquinas;

import Extra.Matriz;
import Monitor.GestorDeMonitor;

public class Maquina_4 extends Maquina {
	private Matriz secuencia;
	public Maquina_4(GestorDeMonitor gdm){
		super(gdm);
		definirTransiciones();
		secuencia = new Matriz(2,20).productoPorEscalar(0);
		definirSecuencia();
	}
	
	
	public void definirTransiciones(){
		for(int i=0;i<20;i++){
			if(i!=3 || i!=6){
				transiciones.setDato(0, i, 0);
			}else{
				transiciones.setDato(0, i, 1);
			}
		}
	}
	
	public void definirSecuencia(){
		secuencia.setDato(0, 3, 1);
		secuencia.setDato(0, 4, 1);
		
		secuencia.setDato(1, 6, 1);
		secuencia.setDato(1, 7, 1);
	}
}	