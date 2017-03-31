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

public class Renderer implements GLEventListener{
	private MyModelView Matrix;
	private My3DFigure figure;
	private MyMouseListener mouseListener;
	private final float [] camera = {0.0f,0.0f,10000.0f};
	private final float [] light = {0.0f,0.0f,1000.0f};
	
	public My3DFigure getFigure() {
		return figure;
	}

	public void setFigure(My3DFigure figure) {
		this.figure = figure;
	}

	public MyMouseListener getMouseListener(){
		return mouseListener;
	}
	
	public Renderer(MyModelView matr,MyMouseListener mouse){
		Matrix = matr;
		mouseListener = mouse;
	}
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
	public void init(GLAutoDrawable drawable) {
		figure.calcPoint();
	}
	
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