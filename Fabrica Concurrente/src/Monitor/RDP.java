package Monitor;
import java.util.ArrayList;

import Extra.LeerInvariantes;
import Extra.Matriz;

public class RDP {

	public int[][] marcado;
	public int[][] incidencia;
	private Matriz incidenciaM;
	private Matriz marcadoM;
	private Tiempo tiempo;
	private Matriz pinvariante;
	private Matriz tinvariante;
	private Matriz res_pinvariante;
	private Matriz cont_tinvariante;

	public RDP(int[][] marcadoInicial, int[][] incidencia,Tiempo tiempo, LeerInvariantes invariantes){
		this.marcado = marcadoInicial;
		this.incidencia = incidencia;

		incidenciaM = new Matriz(this.incidencia);
		this.marcadoM = new Matriz(this.marcado);
		this.tiempo = tiempo;
		this.pinvariante = invariantes.getPinvariante();
		this.tinvariante = invariantes.getTinvariante();
		this.pinvariante.imprimirMatriz();
		this.res_pinvariante = new Matriz(this.pinvariante.getFilCount(), 1);
		this.cont_tinvariante = new Matriz(this.tinvariante.getFilCount(), 1);
	}
	
	public RDP(int[][] marcadoInicial, int[][] incidencia,Tiempo tiempo){
		this.marcado = marcadoInicial;
		this.incidencia = incidencia;

		incidenciaM = new Matriz(this.incidencia);
		this.marcadoM = new Matriz(this.marcado);
		this.tiempo = tiempo;
	}

	public Matriz sensibilizadas(){

		Matriz marcadoT = marcadoM.transpose();

		Matriz sensibilizadas = new Matriz(incidenciaM.getColCount(),1);
		Matriz incidenciaNueva = incidenciaM.productoPorEscalar(-1);

		for(int i=0;i<incidenciaM.getColCount();i++){
			int transicion = 1;
			for(int j=0;j<marcadoT.getFilCount();j++){
				if(incidenciaNueva.getVal(j, i) > 0){
					if(marcadoT.getVal(j, 0)>=incidenciaNueva.getVal(j, i)){
						continue;
					}else{
						transicion = 0;
						break;
					}
				}
			}
			sensibilizadas.setDato(i, 0, transicion);
		}



		return sensibilizadas;
	}

	public int disparar(int transicion){
		boolean estado;
		Matriz sensiViejas;
		Matriz sensiNuevas;
		
		sensiViejas = sensibilizadas();
		if(sensiViejas.getVal(transicion, 0) == 1){
			estado = true;
		}else{
			estado = false;
		}
		int estadoTiempo = tiempo.estaSensibilizado(transicion);
		
		if(estado){
			
			switch(estadoTiempo){
			case 0:
				return 0;


			case 1:

				
				nuevoMarcado(transicion);
				sensiNuevas = sensibilizadas();
				sensiNuevas = sensiNuevas.minus(sensiViejas);
				tiempo.setNuevoTimeStamp(sensiNuevas);
				tiempo.resetEsperando(transicion);
				return 1;


			case 2:
				
				return 2;
				
			default:
				return 100000;
			
			}
		}else{
			return 0;
		}





	}

	public int[][] getMarcado(){
		return this.marcado;
	}

	public int[][] getIncidencia(){
		return this.incidencia;
	}

	private void nuevoMarcado(int transicion){
		Matriz marcadoT = marcadoM.transpose();
		Matriz vectorDisparo = new Matriz(incidenciaM.getColCount(),1);

		for(int i=0;i<incidenciaM.getColCount();i++){
			if(i!=transicion)
				vectorDisparo.setDato(i,0,0 );
			else{
				vectorDisparo.setDato(i,0,1 );
			}
		}

		Matriz temporal = incidenciaM.mult(vectorDisparo);

		this.marcadoM = marcadoT.plus(temporal).transpose();


	}

	public Matriz getMarcadoM(){
		return this.marcadoM;
	}
	public long getTiempo(){
		return tiempo.getTiempo();
	}

}
