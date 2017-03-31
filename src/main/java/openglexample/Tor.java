package openglexample;
import java.util.Vector;

import javax.media.opengl.GL2;

import abstractclasses.My3DFigure;

public class Tor extends My3DFigure{
	
	//private Vector<Edges> masEdgeTor;
	private final float R =0.5f;
    private final float r = 0.1f;
    private float dbTor;
    private float dlTor;
    
    public Tor(){
    	dbTor = 0.3f;
    	dlTor = 0.3f;
    }
    
	public float getDbTor() {
		return dbTor;
	}
	
	public void setDbTor(float dbTor) {
		this.dbTor = dbTor;
	}
	
	public float getDlTor() {
		return dlTor;
	}
	
	public void setDlTor(float dlTor) {
		this.dlTor = dlTor;
	}
	
    @Override
	public void calcPoint() {
    	masEdge = new Vector<Edges>();
    	for(float i = 0; i < 2*Math.PI;i+=dbTor){
			for(float j = 0; j < 2*Math.PI;j+=dlTor){
				 float [] A = new float[3];
	    		 float [] B = new float[3];
	    		 float [] C = new float[3];
	    		 float [] D = new float[3];
				 
	    		 A[0] = (float) ((R + r*Math.cos(i))*Math.cos(j));
				 A[1] = (float)((R + r*Math.cos(i))*Math.sin(j));
				 A[2] = (float)( r*Math.sin(i));				 
				 
				 B[0] = (float) ((R + r*Math.cos(i + dbTor))*Math.cos(j));
				 B[1] = (float)((R + r*Math.cos(i + dbTor))*Math.sin(j));
				 B[2] = (float)( r*Math.sin(i + dbTor));				 
				 
				 C[0] = (float) ((R + r*Math.cos(i+ dbTor))*Math.cos(j + dlTor));
				 C[1] = (float)((R + r*Math.cos(i+ dbTor))*Math.sin(j+ dlTor));
				 C[2] = (float)( r*Math.sin(i+ dbTor));				 
				 
				 D[0] = (float) ((R + r*Math.cos(i))*Math.cos(j+ dlTor));
				 D[1] = (float)((R + r*Math.cos(i ))*Math.sin(j+ dlTor));
				 D[2] = (float)( r*Math.sin(i));
				 
				 float [] middlePoint = new float[3]; 
				 middlePoint[0] = (A[0] + C[0]) / 2;
	    		 middlePoint[1] = (A[1] + C[1]) / 2;
	    		 middlePoint[2] = A[2];	    		 
				 Edges newEdge = new Edges();
				 newEdge.setPointsEdge(A, B, C, D);
				 newEdge.calkNormal(D,middlePoint,B);
				 masEdge.add(newEdge);
			}
		}
	}
}
