package Monitor;

import Extra.Matriz;

public class PoliticasLlamar {
	
	
	private Matriz registros;
	
	public PoliticasLlamar(int cantidadTransiciones){
		registros = new Matriz(cantidadTransiciones,1);
		
		for(int i=0;i<cantidadTransiciones;i++){
			registros.setDato(i,0 , 1);
		}
	}
	
	public int quienLlamo(Matriz sensiViejas, Matriz sensiNuevas){
		int llamo=0;
		Matriz resta;
		
		resta = sensiNuevas.minus(sensiViejas);
		
		
		// crear metodo que sea un and y una multiplicacion 
		//resta.mult(registros);
		
		resta = resta.multAnd(registros);
		
		//dsp se llama un metodo que encuentre el menor numero mayor a 0 y llamarlo
		llamo =resta.menorNumMayorCero();
		
		registros.setDato(llamo, 0, registros.getVal(llamo, 0)+1);
		//antes de return sumo 1 a la transicion que devuelvo en la matriz registro
		// return al numero de transicion.
		
		
		
		return llamo;
		
	}
	
	
}
	

