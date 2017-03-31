package openglexample;
import java.util.Vector;

import javax.media.opengl.GL2;

import abstractclasses.My3DFigure;

public class Sphere extends My3DFigure{
	//private Vector<Edges> masEdgeSphere;
	private final double R = 0.5;
	private float dbSphere;
    private float dlSphere;

    public float getDbSphere() {
		return dbSphere;
	}
	public void setDbSphere(float dbSphere) {
		this.dbSphere = dbSphere;
	}
	public float getDlSphere() {
		return dlSphere;
	}
	public void setDlSphere(float dlSphere) {
		this.dlSphere = dlSphere;
	}
    
	public Sphere(){
		dbSphere = 10;
		dlSphere = 10;	
	}
	@Override
	public void calcPoint() {
		// TODO Auto-generated method stub
		masEdge = new Vector<Edges>();
	    for(float b = -90; b < 90; b = b + dbSphere){
	    	for(float l = 0; l < 360; l= l + dlSphere){
	    		float [] A = new float[3];
	    		float [] B = new float[3];
	    		float [] C = new float[3];
	    		float [] D = new float[3];
	    		   
	    		A[0] = (float)(R * Math.cos(b * Math.PI/180)*Math.sin(l * Math.PI/180));
	    		A[1] = (float)(R * Math.cos(b * Math.PI/180)*Math.cos(l * Math.PI/180));
	    		A[2] = (float)(R * Math.sin(b * Math.PI/180));
	    		   
	    		B[0] = (float)(R * Math.cos(b * Math.PI/180 + dbSphere * Math.PI/180)*Math.sin(l * Math.PI/180));
	    		B[1] = (float)(R * Math.cos(b * Math.PI/180 + dbSphere * Math.PI/180)*Math.cos(l * Math.PI/180));
	    		B[2] = (float)(R * Math.sin(b * Math.PI/180 + dbSphere * Math.PI/180));
	    		   
	    		C[0] = (float)(R * Math.cos(b * Math.PI/180 + dbSphere * Math.PI/180)*Math.sin(l * Math.PI/180 + dlSphere * Math.PI/180));
	    		C[1] = (float)(R * Math.cos(b * Math.PI/180 + dbSphere * Math.PI/180)*Math.cos(l * Math.PI/180 + dlSphere * Math.PI/180));
	    		C[2] = (float)(R * Math.sin(b * Math.PI/180 + dbSphere * Math.PI/180));
	    		   
	    		D[0] = (float)(R * Math.cos(b * Math.PI/180)*Math.sin(l * Math.PI/180 + dlSphere * Math.PI/180));
	    		D[1] = (float)(R * Math.cos(b * Math.PI/180)*Math.cos(l * Math.PI/180 + dlSphere * Math.PI/180));
	    		D[2] = (float)(R * Math.sin(b * Math.PI/180));
	    		   
	    		Edges newEdge = new Edges();
	    		newEdge.setPointsEdge(A, B, C,D);
	    		newEdge.calkNormal(A, B, D);
	    		masEdge.add(newEdge);
	    	}
	    }
	}
}
