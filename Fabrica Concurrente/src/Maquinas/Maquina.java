package Maquinas;

import Extra.Matriz;
import Instrumentos.Instrumento;
import Monitor.GestorDeMonitor;

public class Maquina extends Instrumento implements Runnable{
	public static GestorDeMonitor gdm;
	protected Matriz transiciones;
	private Matriz secuencia;
	public Maquina(GestorDeMonitor gdm){
		super();
		transiciones = new Matriz(1,20);
		//secuencia = new Matriz(1,20);
		this.gdm = gdm;

	}

	@Override
	public void DispararElemento() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(3000);
			System.out.println("Me dispare " + this.getClass().getName());

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("ME QUIERO DISPARAR");
		Thread proceso = new Thread(this);
		for(int i=0;i<secuencia.getColCount();i++){

			gdm.Disparar(secuencia.getVal(0, i), proceso);
		}

		/*if disparar
		 * intento disparar mi secuencia
			 else
			 me duermo------->imposible
		 */

	}

	@Override
	public void dormir(boolean dormir) {
		// TODO Auto-generated method stub
		while(dormir){
			try {

				Thread.sleep(Long.MAX_VALUE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.DispararElemento();

	}

	public Matriz getTransiciones(){
		return this.transiciones;
	}








}
