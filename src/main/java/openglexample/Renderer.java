package openglexample;
import javax.media.opengl.GL2;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import abstractclasses.My3DFigure;

import java.awt.*;
import java.awt.event.*;
import java.nio.FloatBuffer;
import handlers.MyMouseListener;
/**
 * Class managing the rendering
 * @author dimakolyandra
 *
 */
public class Renderer implements GLEventListener{
	
	/** Matrix modelview  */
	private MyModelView Matrix;
	
	/** Figure for rendering */
	private My3DFigure figure;
	
	/** Handler of mouse events*/
	private MyMouseListener mouseListener;
	
	/** Coordinates of camera*/
	private final float [] camera = {0.0f,0.0f,10000.0f};
	
	/** Coordinates of light source */
	private final float [] light = {0.0f,0.0f,1000.0f};
	
	/** @return return object of figure*/
	public My3DFigure getFigure() {
		return figure;
	}
	
	/** @param figure Figure for setting rendering */
	public void setFigure(My3DFigure figure) {
		this.figure = figure;
	}
	
	/** @return Returns handler of mouses event */
	public MyMouseListener getMouseListener(){
		return mouseListener;
	}
	
	/** @param matr Modelview matrix
	 *  @param mouse Handler of mouse event 
	 */
	public Renderer(MyModelView matr,MyMouseListener mouse){
		Matrix = matr;
		mouseListener = mouse;
	}
	
	/** Turning camera and light source*/
	public void cameraAndLightRotate(){
		float [] matr = Matrix.getMatrix();
		float [] tmp = new float[3];
		float x = 0;
		float y = 0;
		float z = 10;
		camera[0] = (x * matr[0] + y * matr[1] + z * matr[2]);
		camera[1] = x * matr[4] + y * matr[5] + z * matr[6];
		camera[2] = x * matr[8] + y * matr[9] + z * matr[10];
		x = 0;
		y = 0;
		z = 10;
		light[0] = (x * matr[0] + y * matr[1] + z * matr[2]);
		light[1] = x * matr[4] + y * matr[5] + z * matr[6];
		light[2] = x * matr[8] + y * matr[9] + z * matr[10];
	}
	
	/** Initialize rendering and call function which calculate points of figure*/
	public void init(GLAutoDrawable drawable) {
		figure.calcPoint();
	}
	
	/** Renders the image
	 * @param drawable Object for initialization rendering
	 */
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
	    gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	    Matrix.initMatrix();	       
	    Matrix.turnOY(mouseListener.getFiY());
	    Matrix.turnOX(mouseListener.getFiX()); 
	    gl.glLoadMatrixf(Matrix.getMatrix(),0);
	    cameraAndLightRotate();
	    figure.drawFigure(gl, light, camera);
	}
 
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}
    
}