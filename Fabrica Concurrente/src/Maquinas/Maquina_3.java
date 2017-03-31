package Maquinas;

import Extra.Matriz;
import Monitor.GestorDeMonitor;

public class Maquina_3 extends Maquina {
	private Matriz secuencia;
	public Maquina_3(GestorDeMonitor gdm){
		super(gdm);
		definirTransiciones();
		secuencia = new Matriz(2,20).productoPorEscalar(0);
	}
	
	public void definirTransiciones(){
		for(int i=0;i<20;i++){
			if(i!=8 || i!=2){
				transiciones.setDato(0, i, 0);
			}else{
				transiciones.setDato(0, i, 1);
			}
		}
	}
	
	public void definirSecuencia(){
		secuencia.setDato(0, 1, 1);
		secuencia.setDato(0, 2, 1);
		
		secuencia.setDato(1, 8, 1);
		secuencia.setDato(1, 9, 1);
	}

}	
