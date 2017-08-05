package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import Extra.Matriz;
import Monitor.Politicas;

public class UnitTestPoliticas {
	
	
	@Test
	public void testPoliticas(){
		Politicas politicas = new Politicas();
		
		Matriz m = new Matriz(22,1);
		
		
		//TODAS SENSIBILIZADAS
		for(int i =0;i<m.getFilCount();i++){
			m.setDato(i, 0, 1);
		}
		//Inserto valores, el primero es 10 por lo que deberia ser el primero en salir.
		politicas.insertFifo(10);
		politicas.insertFifo(8);
		politicas.insertFifo(22);
		
		assertEquals(politicas.cualFifo(m),10);
		
	}
	
	@Test
	public void testPoliticasNegativo(){
		Politicas politicas = new Politicas();
		
		Matriz m = new Matriz(22,1);
		
		
		//TODAS SENSIBILIZADAS
		for(int i =0;i<m.getFilCount();i++){
			m.setDato(i, 0, 1);
		}
		//Inserto valores, el primero es 10 por lo que deberia ser el primero en salir.
		politicas.insertFifo(10);
		politicas.insertFifo(8);
		politicas.insertFifo(22);
		
		assertNotEquals(politicas.cualFifo(m),8);
		
	}
}
