package Extra;

public class Matriz 
{
	int [][]dato;

	public Matriz(int fil,int col)
	{
		dato=new int[fil][col];
	}

	public Matriz(int[][]dato)
	{
		this.dato=dato;
	}

	public Matriz(int size)
	{
		dato=new int[size][size];
	}

	public int[][] getDato() 
	{
		return dato;
	}
	public int getVal(int fil,int col)
	{
		return dato[fil][col];
	}

	public void setMatrix(int[][] dato) 
	{
		this.dato = dato;
	}

	public void setDato(int fil,int col,int dato) 
	{
		this.dato[fil][col]=dato;
	}

	public int getFilCount() 
	{
		return dato.length;
	}

	public int getColCount() 
	{
		return dato[0].length;
	}
	public Matriz getCol(int col) 
	{
		Matriz columna=new Matriz(this.getFilCount(),1);
		for(int i=0;i<this.getFilCount();i++)
		{
			columna.setDato(i,0, this.dato[i][col]);
		}
		return columna;
	}

	public void Clear(){
		for (int i=0;i<this.getFilCount();i++){
			for (int j=0;j<this.getColCount();j++){
				this.setDato(i, j, 0);
			}
		}
	}

	public boolean isPos(){
		for (int i=0;i<this.getFilCount();i++){
			for (int j=0;j<this.getColCount();j++){
				if(this.dato[i][j]<0)return false;
			}
		}
		return true;
	}
	// create and return the transpose of the invoking matrix
	public Matriz transpose() {
		Matriz A = new Matriz(this.getColCount(), this.getFilCount());
		for (int i = 0; i < this.getFilCount(); i++)
			for (int j = 0; j < this.getColCount(); j++)
				A.setDato(j, i,this.getDato()[i][j]);
		return A;
	}

	@Override
	public String toString(){
		String texto="";
		for(int i=0; i<this.getFilCount(); i++){
			for(int j=0; j<this.getColCount(); j++){
				texto+="   "+this.dato[i][j];
			}
			texto+="\n";
		}
		texto+="";
		return texto;
	}

	// return C = A + B
	public Matriz plus(Matriz B) {
		Matriz A = this;
		if (B.getFilCount() != A.getFilCount() || B.getColCount() != A.getColCount()) throw new RuntimeException("Dimensiones incompatibles");
		Matriz C = new Matriz(getFilCount(), getColCount());
		for (int i = 0; i < getFilCount(); i++)
			for (int j = 0; j < getColCount(); j++)
				C.setDato(i,j,A.getDato()[i][j] + B.getDato()[i][j]);
		return C;
	}

	// return C = A - B
	public Matriz minus(Matriz B) {
		Matriz A = this;
		if (B.getFilCount() != A.getFilCount() || B.getColCount() != A.getColCount()) throw new RuntimeException("Dimensiones no compatibles.");
		Matriz C = new Matriz(getFilCount(), getColCount());
		for (int i = 0; i < getFilCount(); i++)
			for (int j = 0; j < getColCount(); j++)
				C.setDato(i,j,A.getDato()[i][j] - B.getDato()[i][j]);
		return C;
	}

	// return C = A * B
	public Matriz mult(Matriz B) {
		Matriz A = this;
		if (A.getColCount() != B.getFilCount()) throw new RuntimeException("Dimensiones no compatibles.");
		Matriz C = new Matriz(A.getFilCount(), B.getColCount());
		for (int i = 0; i < C.getFilCount(); i++)
			for (int j = 0; j < C.getColCount(); j++)
				for (int k = 0; k < A.getColCount(); k++)
					C.setDato(i,j,C.getDato()[i][j]+(A.getDato()[i][k] * B.getDato()[k][j]));
		return C;
	}

	public void setIdentity() {
		for (int i = 0; i < dato.length; i++) {
			dato[i][i]=1;
		}
	}

	public static int TransActive(int[][] disparo){
		int c = 0;
		for (int i = 0; i < disparo.length; i++) 
			for (int j = 0; j < disparo[i].length; j++) 
				if (disparo[i][j] != 0)
					c++;
		return c;
	}

	public Matriz getFila(int i){
		Matriz oFila = new Matriz(1, this.getColCount());
		for (int j = 0; j < this.getColCount(); j++) {
			oFila.setDato(0, j, this.getVal(i, j));
		}
		return oFila;
	}

	public Matriz AND( Matriz B){
		Matriz A = this;
		Matriz oMand = new Matriz(B.getFilCount(), B.getColCount());

		for (int i = 0; i < A.getFilCount(); i++) {
			for (int j = 0; j < A.getColCount(); j++) {
				if(A.getVal(i, j) == 1 && B.getVal(i, j) == 1)
					oMand.setDato(i,j,1);
				else
					oMand.setDato(i,j,0);
			}            
		}
		return oMand;
	}

	public void imprimirMatriz(){
		for(int i = 0; i < this.getFilCount(); i++)
		{
			for(int j = 0; j < this.getColCount(); j++)
			{
				System.out.printf("%5d ", this.dato[i][j]);
			}
			System.out.println();

		}


	}
	public void imprimirMatrizI(){
		int i;
		int j;
		i=0;
		//First Line
		System.out.printf("      ", i);
		for(i=0; i< this.getColCount(); i++){
			System.out.printf("%5d ", i);
		}
		System.out.println();
		System.out.println();
		for(i = 0; i < this.getFilCount(); i++)
		{
			System.out.printf("%5d ", i);
			for(j = 0; j < this.getColCount(); j++)
			{
				System.out.printf("%5d ", this.dato[i][j]);
			}
			System.out.println();

		}


	}
	public Matriz productoPorEscalar(int escalar){

		Matriz nueva = new Matriz(this.getFilCount(),this.getColCount());
		for(int i=0;i<this.getFilCount();i++){
			for(int j=0;j<this.getColCount();j++){
				nueva.setDato(i, j, this.getVal(i, j)*escalar);
			}
		}

		return nueva;
	}

	public boolean esNula(){
		for(int i=0;i<this.getFilCount();i++){
			for(int j=0;j<this.getColCount();j++){
				if(this.getVal(i, j) !=0){
					return false;
				}
			}
		}
		return true;
	}


	public Matriz multAnd(Matriz B){
		Matriz A = this;
		Matriz oMand = new Matriz(B.getFilCount(), B.getColCount());

		for (int i = 0; i < A.getFilCount(); i++) {
			for (int j = 0; j < A.getColCount(); j++) {
				oMand.setDato(i, j, B.getVal(i, j)*A.getVal(i, j));
			}            
		}
		return oMand;
	}


	public int menorNumMayorCero(){
		int menor=1000;
		int devolver = -1;
		Matriz A = this;


		for(int i=0;i<A.getFilCount();i++){
			for(int j=0;j<A.getColCount();j++){
				if(menor > A.getVal(i, j)){

					if(A.getVal(i, j)>0){
						menor = A.getVal(i,j);
						devolver = i;



					}
				}
			}
		}




		return devolver;
	}
	
	public void contar(int fila, int columna){
		dato[fila][columna]++;
		return;
	}
	
}
