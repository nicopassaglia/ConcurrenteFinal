package Monitor;
import java.util.ArrayList;

import Extra.Matriz;

public class Politicas {
	public Politicas(){
		
		
	}
	
	public int cual(Matriz and){
		for(int i=0;i<and.getFilCount();i++){
			
			if(and.getVal(i, 0)==1){
				return i;
				
			}else{
				
			}
		}
		return -1;
	}
}
