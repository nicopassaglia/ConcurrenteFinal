package Robots;

import Extra.Matriz;
import Instrumentos.Instrumento;

public class Robot extends Instrumento implements Runnable {
	private Matriz secuencia;
	public Robot(){
		super();
		
	}
	
	public void extraccion(){
		System.out.println("Se extrajo");
	}
	
	public void deposito(){
		System.out.println("Se depositó");
	}
	

	@Override
	public void DispararElemento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dormir(boolean dormir) {
		// TODO Auto-generated method stub
		
	}
}
