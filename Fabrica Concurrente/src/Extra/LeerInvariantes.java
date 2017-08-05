package Extra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerInvariantes {

	private Matriz p_invariante;
	private Matriz t_invariante;
	
	public LeerInvariantes (){
		p_invariante = null;
		t_invariante = null;
	}


	public void obtenerInvariantes(String nombreArchivo){
		int max=30;
		int estado; //estado == 0 estoy en transiciones
					//estado == 1 estoy en plazas
		FileReader input;
		List<Integer> transiciones = new ArrayList<>();
		List<Integer> plazas = new ArrayList<>();
		List<Integer> vector_transiciones = new ArrayList<>();
		List<Integer> vector_plazas = new ArrayList<>();
		int cant_transiciones = 0;
		int cant_plazas = 0;
		int cant_filas_t;
		int cant_filas_p;


		
		//Guardo los datos del .txt en los vectores.
		estado = 2;
		try{
			input = new FileReader("src/Extra/"+nombreArchivo+".txt");
			BufferedReader bufRead = new BufferedReader(input);
			String myLine = null;



			while((myLine = bufRead.readLine()) != null){
				//System.out.println(myLine);
				if(myLine.contains("T")){
					myLine = myLine.substring(1);
					transiciones.add(Integer.parseInt(myLine));
					estado = 0;
					cant_transiciones ++;
				}
				else if(myLine.contains("P")){
					myLine = myLine.substring(1);
					plazas.add(Integer.parseInt(myLine));
					estado = 1;
					cant_plazas ++ ;
				}
				else if(estado == 0){
					vector_transiciones.add(Integer.parseInt(myLine));
				}
				else if(estado == 1){
					vector_plazas.add(Integer.parseInt(myLine));
				}
					
			}
			
			bufRead.close();


		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Creo las matrices.
		cant_filas_t = vector_transiciones.size()/cant_transiciones;
		cant_filas_p = vector_plazas.size()/cant_plazas;
		
		this.p_invariante = new Matriz(cant_filas_p, cant_plazas);
		this.t_invariante = new Matriz(cant_filas_t, cant_transiciones);
		/*
		System.out.println(cant_filas_t);
		System.out.println(cant_transiciones);
		System.out.println(cant_filas_p);
		System.out.println(cant_plazas);
		*/
		//Pongo los datos ordenados en las matrices
		int i;	//columna
		int j;	//fila
		int index;
		for(i=0; i<cant_transiciones; i++){
			index = transiciones.get(i);
			//System.out.println(index);
			for(j=0; j<cant_filas_t; j++){
				this.t_invariante.setDato(j, index, vector_transiciones.get(i+j*cant_transiciones));
			}
		}
		
		for(i=0; i<cant_plazas; i++){
			index = plazas.get(i);
			//System.out.println(index);
			for(j=0; j<cant_filas_p; j++){
				this.p_invariante.setDato(j, index, vector_plazas.get(i+j*cant_plazas));
			}
		}
		
		//this.t_invariante.imprimirMatriz();
		//this.p_invariante.imprimirMatrizI();
		//System.out.println(this.p_invariante.getFilCount());
		
		return;
	}

	public Matriz getPinvariante(){
		return this.p_invariante;
	}
	
	public Matriz getTinvariante(){
		return this.t_invariante;
	}

}