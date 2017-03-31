package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import abstractclasses.My3DFigure;
import openglexample.Thor;
import openglexample.Sphere;
import openglexample.Renderer;

/**
 * Handler for the button click increase approximation
 * @author dimakolyandra
 */
public class IncreaseApproximation implements ActionListener{
	private Renderer ren;
	
	public IncreaseApproximation(Renderer r){
		ren = r;
	}
	
	public void actionPerformed(ActionEvent ev){
		My3DFigure fig = ren.getFigure();
		if(fig.getClass()==openglexample.Thor.class){
			Thor tor = (Thor) fig;
			float dbTor = tor.getDbTor();
			float dlTor = tor.getDlTor();
			if(dbTor > 0.2f && dlTor > 0.2f){
				dbTor -= 0.1f;
				dlTor -= 0.1f;
			}
			tor.setDbTor(dbTor);
			tor.setDlTor(dlTor);
			tor.calcPoint();
		}
		else if(fig.getClass()==openglexample.Sphere.class){
			Sphere sph = (Sphere) fig; 
			float dbSphere = sph.getDbSphere();
			float dlSphere = sph.getDlSphere();
			if(dlSphere > 5 && dbSphere > 5){
				dbSphere -= 5;
				dlSphere -= 5;
			}
			sph.setDlSphere(dlSphere);
			sph.setDbSphere(dbSphere);
			sph.calcPoint();
		}
	}
}