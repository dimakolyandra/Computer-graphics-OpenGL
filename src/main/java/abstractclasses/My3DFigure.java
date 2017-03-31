package abstractclasses;

import java.util.Vector;

import javax.media.opengl.GL2;

import openglexample.Edges;
/** 
 * Abstract class which is parent for classes Sphere and Tor
 *  
 * @author dimakolyandra
 *
 */
public abstract class My3DFigure {
	/** Array of edges that approximates figure*/
	protected Vector<Edges> masEdge; 
	
	/** Abstract method, which calculate points of polygons for approximation of the figure */	
	public abstract void calcPoint(); 
	
	/** Method that draws figure
	 * @param gl openGL Context
	 * @param light Coordinates of light source
	 * @param camera Coordinates of camera 
	 */
	public void drawFigure(GL2 gl,float[] light,float []  camera) {
		 for(int i = 0; i < masEdge.size();i++){
		       float [] A = new float[3];
	  		   float [] B = new float[3];
	  		   float [] C = new float[3];
	  		   float [] D = new float[3];
	  		   A = masEdge.get(i).getFirstPoint();
	  		   B = masEdge.get(i).getSecondPoint();
	  		   C = masEdge.get(i).getThirdPoint();
	  		   D = masEdge.get(i).getFourPoint();
	  		   float tmp = masEdge.get(i).robertsAlgo(camera);
	  		   if(masEdge.get(i).isVisible){
	  			   float [] color = new float[3];
		  		   color = masEdge.get(i).Font(light, camera);
	  			   gl.glColor3f(color[0],color[1],color[2]);
	  			   gl.glBegin(GL2.GL_POLYGON);
				   gl.glVertex3f(A[0],A[1],A[2]);
				   gl.glVertex3f(B[0],B[1],B[2]); 
				   gl.glVertex3f(C[0],C[1],C[2]);
				   gl.glVertex3f(D[0],D[1],D[2]);			  
				   gl.glEnd();
	  		  }
		 }
	}

}
