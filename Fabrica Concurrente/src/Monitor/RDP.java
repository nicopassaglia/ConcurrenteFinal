package Monitor;
import java.util.ArrayList;

import Extra.Matriz;

/*
 * Esta clase contiene todas las matrices que representan a la Red de Petri.
 */

public class RDP {
	
	public int[][] marcado;
	public int[][] incidencia;
	private Matriz incidenciaM;
	private Matriz marcadoM;
	
	public RDP(int[][] marcadoInicial, int[][] incidencia){
		this.marcado = marcadoInicial;
		this.incidencia = incidencia;
		
		incidenciaM = new Matriz(this.incidencia);
		this.marcadoM = new Matriz(this.marcado);
	}
	
	public Matriz sensibilizadas(){
		
		Matriz marcadoT = marcadoM.transpose();
	
		Matriz sensibilizadas = new Matriz(incidenciaM.getColCount(),1);
		Matriz incidenciaNueva = incidenciaM.productoPorEscalar(-1);
		
		for(int i=0;i<incidenciaM.getColCount();i++){
			int transicion = 1;
			for(int j=0;j<marcadoT.getFilCount();j++){
				
					if(marcadoT.getVal(j, 0)>=incidenciaNueva.getVal(j, i)){
						continue;
					}else{
						transicion = 0;
						break;
					}
			}
			sensibilizadas.setDato(i, 0, transicion);
		}
		
		
		return sensibilizadas;
	}
	
	/*
	 * Devuelve la matriz que tiene las transiciones sensibilizadas.
	 */
	
	public boolean disparar(int transicion){
		
		Matriz sensi;
		sensi = sensibilizadas();
		if(sensi.getVal(transicion, 0) == 1){
			nuevoMarcado(transicion);
			return true;
		}
		else
			return false;
		
		
	}
	
	/*
	 * Si la transicion x esta habilitada, dispara x transicion y obtiene el nuevo marcado del sistema. Y devuelve true.
	 * Sino no hace nada, y devuelve falso
	 */
	
	public int[][] getMarcado(){
		return this.marcado;
	}
	
	/*
	 * Devuelve el marcado actual.
	 */
	
	public int[][] getIncidencia(){
		return this.incidencia;
	}
	
	/*
	 * Devuelve la matriz de incidencia.
	 */
	
	public void nuevoMarcado(int transicion){
		Matriz marcadoT = marcadoM.transpose();
		Matriz vectorDisparo = new Matriz(incidenciaM.getColCount(),1);
		
		for(int i=0;i<20;i++){
			if(i!=transicion)
				vectorDisparo.setDato(i,0,0 );
			else{
				vectorDisparo.setDato(i,0,1 );
			}
		}
		
		Matriz temporal = incidenciaM.mult(vectorDisparo);
	
		this.marcadoM = marcadoT.plus(temporal).transpose();
		
		
	}
	
	/*
	 * Dispara x transicion y calcula el nuevo marcado.
	 */
	
	public Matriz getMarcadoM(){
		return this.marcadoM;
	}
	
	/*
	 * Devuelve el marcado.
	 */
	
	
}
