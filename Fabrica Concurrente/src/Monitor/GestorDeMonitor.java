package Monitor;

import java.util.concurrent.Semaphore;

public class GestorDeMonitor {
	
	public Colas colas;
	public Semaphore mutex;
	public Politicas politicas;
	public RDP rdp;
	
	
	
	public GestorDeMonitor(Colas colas, Politicas politicas, RDP rdp){
		
		this.mutex = new Semaphore(1,true);
		this.colas = colas;
		this.politicas = politicas;
		this.rdp = rdp;
	}	
	
	
	
}
