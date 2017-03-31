package openglexample;

class MyModelView{
	private float [] matrixView = {1,0,0,0,
								   0,1,0,0,
								   0,0,1,0,
								   0,0,0,1};
	public void initMatrix(){
		for(int i = 0; i < 16; i++){
			matrixView[i] = 0;							
		}
		matrixView[0] = 1;
		matrixView[5] = 1;
		matrixView[10] = -1;
		matrixView[15] = 1;
	}
	public float[] getMatrix(){
		return matrixView;
	}
	public void setMatrixView(float[][]C){
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4;j++){
				matrixView[i*4+j] = C[i][j];
			}
		}
	}
	
	public float[][] multMatrix(float[][] A,float[][] B){
		float [][] C = new float[4][4];
		for(int i = 0; i < 4;i++){
			for(int j = 0; j < 4;j++){
				for(int k = 0; k < 4; k++){
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		return C;
	}
	public void initAandC(float[][] A,float[][]C){
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				C[i][j] = 0;
				A[i][j] = matrixView[i*4 + j];
			}
		}
		
	}
	public void turnOX(float fiX){
		float [][] matrTurnOX = new float[4][4];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4;j++){
				matrTurnOX[i][j] = 0;			
			}
		}
		matrTurnOX[0][0] = 1;
		matrTurnOX[1][1] = (float)Math.cos(fiX);
		matrTurnOX[1][2] = (-1)*(float)Math.sin(fiX);
		matrTurnOX[2][1] = (float)Math.sin(fiX);
		matrTurnOX[2][2] = (float)Math.cos(fiX);
		matrTurnOX[3][3] = 1;
		float [][] A = new float[4][4];
		float [][] C = new float[4][4]; 
		initAandC(A,C);
		C = multMatrix(matrTurnOX,A);
		setMatrixView(C);
	}
	
	public void turnOY(float fiY){
		float [][] matrTurnOY = new float[4][4];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4;j++){
				matrTurnOY[i][j] = 0;			
			}
		}
		matrTurnOY[0][0] = (float)Math.cos(fiY);
		matrTurnOY[0][2] = (float)Math.sin(fiY);
		matrTurnOY[1][1] = 1;
		matrTurnOY[2][0] = (-1)*(float)Math.sin(fiY);
		matrTurnOY[2][2] = (float)Math.cos(fiY);
		matrTurnOY[3][3] = 1;
		
		float [][] A = new float[4][4];
		float [][] C = new float[4][4]; 
		initAandC(A,C);
		C = multMatrix(matrTurnOY,A);
		setMatrixView(C);
	}
}
