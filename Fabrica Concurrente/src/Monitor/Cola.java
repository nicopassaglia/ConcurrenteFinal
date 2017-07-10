package Monitor;
import java.util.LinkedList;
import java.util.Queue;
public class Cola {


	private Queue<Runnable> fifo;
	private String tipoCola;
	private Object lock = new Object();

	public Cola(String tipo){
		this.tipoCola = tipo;
		this.fifo =  new LinkedList<>();
	
	}
	
	
	public String getTipoCola(){
		return this.tipoCola;
	}
	
	
	public Runnable obtenerProceso(){
		Runnable proceso = fifo.poll();
		
		return proceso;
	
	}
	
	public boolean meterEnCola(Thread proceso){
	
		try {
			synchronized(proceso){
			fifo.add(proceso);
			proceso.wait();
			}
			return true;
		} catch (Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
	
	}
	
	public int tamañoCola(){
		return fifo.size();
	}
	
	public boolean isEmpty(){
		return fifo.isEmpty();
	}
}
