import java.util.HashMap;

import Extra.LeerArchivo;

import Monitor.RDP;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LeerArchivo oArchivo = new LeerArchivo();
        HashMap<String,int[][]> datos = oArchivo.LeerHTML();
        RDP rdp = new RDP(datos.get("marcado"),datos.get("incidencia"));
        
        int[][] incidenciaRDP = rdp.getIncidencia();
        for(int i = 0; i < 28; i++)
        {
           for(int j = 0; j < 20; j++)
           {
              System.out.printf("%5d ", incidenciaRDP[i][j]);
           }
           System.out.println();
        }
		
	}

}
