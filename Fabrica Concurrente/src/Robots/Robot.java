package Robots;

import Instrumentos.Instrumento;

public class Robot extends Instrumento {
	
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
	public void Disparar() {
		// TODO Auto-generated method stub
		
	}
}
