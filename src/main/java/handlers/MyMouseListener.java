package handlers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseMotionListener{
	
	private double rotate_x;
	private double rotate_y;
	private float fiX;
	private float fiY;
	private double rotate_x_prev;
	private double rotate_y_prev;
	
	public float getFiX() {
		return fiX;
	}
	public void setFiX(float fiX) {
		this.fiX = fiX;
	}
	public float getFiY() {
		return fiY;
	}
	public void setFiY(float fiY) {
		this.fiY = fiY;
	}
	public MyMouseListener(){
		rotate_x = 0;
		rotate_y = 0;
		fiX = 0;
		fiY = 0;
		rotate_x_prev = 0;
		rotate_y_prev = 0;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Point a = e.getLocationOnScreen();
		rotate_x = a.getX();
		rotate_y = a.getY();
		if( rotate_x > rotate_x_prev){
			fiY += 0.05;
			rotate_x_prev = rotate_x;
		}
		else if(rotate_x < rotate_x_prev){
			fiY -= 0.05;
			rotate_x_prev = rotate_x;
		}
		else if(rotate_y < rotate_y_prev){
			fiX -= 0.05;
			rotate_y_prev = rotate_y;
		}
		else if(rotate_y > rotate_y_prev){
			fiX += 0.05;
			rotate_y_prev = rotate_y;
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		rotate_x_prev = e.getX();
    	rotate_y_prev = e.getY();
	}

}
