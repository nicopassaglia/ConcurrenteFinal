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
	private Matriz cont_transiciones;

	public RDP(int[][] marcadoInicial, int[][] incidencia,Tiempo tiempo, LeerInvariantes invariantes){
	
		this.marcado = marcadoInicial;
		this.incidencia = incidencia;

		incidenciaM = new Matriz(this.incidencia);
		this.marcadoM = new Matriz(this.marcado);
		this.tiempo = tiempo;
		this.pinvariante = invariantes.getPinvariante();
		this.tinvariante = invariantes.getTinvariante();
		this.res_pinvariante = new Matriz(this.pinvariante.getFilCount(), 1);
		this.cont_tinvariante = new Matriz(this.tinvariante.getFilCount(), 1);
		this.cont_transiciones = new Matriz(1, this.tinvariante.getColCount());
		this.cont_tinvariante.Clear();
		this.cont_transiciones.Clear();
		
		obtenerResultadosPinvariante();
		// Capaz haya que poner este metodo en el constructor.
		
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
				
				if(!comprobarPinvariante()){
					throw new RuntimeException("El P-invariante no se cumplio.");
				}
				
				contarTinvariante(transicion);
				//this.cont_tinvariante.imprimirMatriz();
				
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
	
	public void setMarcado(Matriz marc){
		this.marcadoM = marc;
	}
	
	public long getTiempo(){
		return tiempo.getTiempo();
	}
	
	private void obtenerResultadosPinvariante(){
		int i;
		int j;
		int resultado;
		/* Obtengo los resultados de las ecuaciones del P-Invariante */
		for(i = 0; i < this.pinvariante.getFilCount(); i++){
			resultado = 0;
			for(j =0; j<this.pinvariante.getColCount(); j++){
				resultado += this.pinvariante.getVal(i, j) * this.marcadoM.getVal(0, j);
			}
			this.res_pinvariante.setDato(i, 0, resultado);
		}
		return;
	}
	
	public boolean comprobarPinvariante(){
		/* Obtengo los resultados de las ecuaciones del P-Invariante */
		int resultado;
		int i;
		int j;
		
		for(i = 0; i < this.pinvariante.getFilCount(); i++){
			resultado = 0;
			
			for(j =0; j<this.pinvariante.getColCount(); j++){
				resultado += this.pinvariante.getVal(i, j) * this.marcadoM.getVal(0, j);
			}

			if(resultado != this.res_pinvariante.getVal(i, 0)){
				return false;
			}
		}
		
		return true;
	}
	
	private void contarTinvariante(int transicion){
		int i;
		int j;
		int temporal;
		boolean contar_tinvariante;
		this.cont_transiciones.contar(0, transicion);
		
		
		
		for(i=0; i<this.tinvariante.getFilCount(); i++){
			contar_tinvariante = true;
			
			for(j=0; j<this.tinvariante.getColCount(); j++){
				if(this.tinvariante.getVal(i, j) == 1 && this.cont_transiciones.getVal(0, j) == 0){
					// Si la transicion es parte del invariante, y no ha sucedido esa transicion. Entonces no contar el invariante.
					//Pero si sucede que todas las transiciones que son parte del invariante han ocurrido al menos una vez, entonces contarlo.
					contar_tinvariante = false;
				}
			}
			if(contar_tinvariante){
				//Cuento el invariante y resto las transiciones contadas.
				this.cont_tinvariante.contar(i, 0);
				for(j=0; j<this.tinvariante.getColCount(); j++){
					temporal = this.cont_transiciones.getVal(0, j) - this.tinvariante.getVal(i, j);
					this.cont_transiciones.setDato(0, j, temporal);					
				}
			}
		}
		//this.tinvariante.imprimirMatrizI();
		//this.cont_transiciones.imprimirMatrizI();
		//this.cont_tinvariante.imprimirMatrizI();
		return;		
	}
	
	public Matriz getPinvariante(){
		return this.pinvariante;
	}
	
	public Matriz getContadorTinvariante(){
		return this.cont_tinvariante;
		
	}
	
}
