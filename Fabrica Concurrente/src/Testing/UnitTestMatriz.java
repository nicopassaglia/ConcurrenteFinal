package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import Extra.Matriz;

public class UnitTestMatriz {

	
	
	@Test
	public void SumaMatrices(){
		Matriz A = new Matriz(1,1);
		Matriz B = new Matriz(1,1);
		
		A.setDato(0, 0, 1);
		B.setDato(0, 0, 2);
		
		Matriz C = new Matriz(1,1);
		C.setDato(0, 0, 3);
		
		assertArrayEquals(C.getDato(),A.plus(B).getDato());
	}
	
	@Test
	public void RestaMatrices(){
		Matriz A = new Matriz(1,1);
		Matriz B = new Matriz(1,1);
		
		A.setDato(0, 0, 5);
		B.setDato(0, 0, 2);
		
		Matriz C = new Matriz(1,1);
		C.setDato(0, 0, 3);
		
		assertArrayEquals(C.getDato(),A.minus(B).getDato());
	}
	@Test
	public void multAndMatrices(){
		Matriz A = new Matriz(1,1);
		Matriz B = new Matriz(1,1);
		
		A.setDato(0, 0, 10);
		B.setDato(0, 0, 2);
		
		Matriz C = new Matriz(1,1);
		C.setDato(0, 0, 20);
		
		assertArrayEquals(C.getDato(),A.multAnd(B).getDato());
	}
	
	
	@Test
	public void MenorNumMayorCero(){
		
		
		Matriz A = new Matriz(3,1);
		A.setDato(0, 0, 5);
		A.setDato(1, 0, -2);
		A.setDato(2, 0, 10);
		
		
		assertEquals(0,A.menorNumMayorCero());
	}
	
	@Test
	public void transpose(){
		
		Matriz A = new Matriz(3,1);
		
		A.setDato(0, 0, 5);
		A.setDato(1, 0, -2);
		A.setDato(2, 0, 10);
		
		Matriz B = new Matriz(1,3);
		B.setDato(0, 0, 5);
		B.setDato(0, 1, -2);
		B.setDato(0, 2, 10);
		assertArrayEquals(B.getDato(),A.transpose().getDato());
	}
	
	@Test
	public void clear(){
		Matriz A = new Matriz(3,1);
		
		A.setDato(0, 0, 5);
		A.setDato(1, 0, -2);
		A.setDato(2, 0, 10);
		
		Matriz B = new Matriz(3,1);
		B.setDato(0, 0, 0);
		B.setDato(1, 0, 0);
		B.setDato(2, 0, 0);
		
		A.Clear();
		
		assertArrayEquals(B.getDato(),A.getDato());
	}
	
	@Test
	public void setIdentity(){
		Matriz A = new Matriz(3,3);
		A.setIdentity();
		
		Matriz B = new Matriz(3,3);
		B.setDato(0, 0, 1);
		B.setDato(1, 0, 0);
		B.setDato(2, 0, 0);
		
		B.setDato(1,0, 0);
		B.setDato(1,1, 1);
		B.setDato(1,2, 0);
		
		B.setDato(2,0, 0);
		B.setDato(2,1, 0);
		B.setDato(2,2, 1);		
		
		assertArrayEquals(B.getDato(),A.getDato());
		
	}
	
	@Test
	public void productoPorEscalar(){
		Matriz A = new Matriz(3,1);
		A.setDato(0, 0, 5);
		A.setDato(1, 0, -2);
		A.setDato(2, 0, 10);		
		
		A = A.productoPorEscalar(3);
		
		Matriz B = new Matriz(3,1);
		
		B.setDato(0, 0, 15);
		B.setDato(1, 0, -6);
		B.setDato(2, 0, 30);
		
		assertArrayEquals(B.getDato(),A.getDato());
	}
	
	@Test
	public void esNula(){
		Matriz A = new Matriz(3,1);
		A.setDato(0, 0, 5);
		A.setDato(1, 0, -2);
		A.setDato(2, 0, 10);		
		
		A = A.productoPorEscalar(0);
		
		assertTrue(A.esNula());
	}
	
	public void esNulaNegativo(){
		Matriz A = new Matriz(3,1);
		A.setDato(0, 0, 5);
		A.setDato(1, 0, -2);
		A.setDato(2, 0, 10);		
		
	
		
		assertFalse(A.esNula());
	}
	
}
