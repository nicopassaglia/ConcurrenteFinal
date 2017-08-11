package Monitor;

import Extra.LeerInvariantes;
import Extra.Matriz;


public class Invariantes {
	
	private Matriz marcadoM;
	private Matriz pinvariante;
	private Matriz tinvariante;
	private Matriz res_pinvariante;
	private Matriz cont_tinvariante;
	private Matriz cont_transiciones;
	
	public Invariantes(LeerInvariantes invariantes){
		
		this.pinvariante = invariantes.getPinvariante();
		this.tinvariante = invariantes.getTinvariante();
		this.res_pinvariante = new Matriz(this.pinvariante.getFilCount(), 1);
		this.cont_tinvariante = new Matriz(this.tinvariante.getFilCount(), 1);
		this.cont_transiciones = new Matriz(1, this.tinvariante.getColCount());
		this.cont_tinvariante.Clear();
		this.cont_transiciones.Clear();	
	}
	
	
	public void obtenerResultadosPinvariante(){
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
	
	public void contarTinvariante(int transicion){
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
	
	public void updateMarcado(Matriz marcado){
		this.marcadoM = marcado;
	}
	
}

