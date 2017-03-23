package Monitor;
import java.util.ArrayList;

import Extra.Matriz;

public class RDP {
	
	public int[][] marcado;
	public int[][] incidencia;
	
	public RDP(int[][] marcadoInicial, int[][] incidencia){
		this.marcado = marcadoInicial;
		this.incidencia = incidencia;
	}
	
	public Matriz sensibilizadas(){
		
		Matriz marcadoM = new Matriz(this.marcado).transpose();
		Matriz incidenciaM = new Matriz(this.incidencia);
		Matriz sensibilizadas = new Matriz(incidenciaM.getColCount(),1);
		Matriz incidenciaNueva = incidenciaM.productoPorEscalar(-1);
		
		for(int i=0;i<incidenciaM.getColCount();i++){
			int transicion = 1;
			for(int j=0;j<marcadoM.getFilCount();j++){
				
					if(marcadoM.getVal(j, 0)>=incidenciaNueva.getVal(j, i)){
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
	
	public void disparar(){
		
		
	}
	
	public int[][] getMarcado(){
		return this.marcado;
	}
	
	public int[][] getIncidencia(){
		return this.incidencia;
	}
}
