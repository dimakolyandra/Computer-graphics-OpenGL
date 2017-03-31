package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import abstractclasses.My3DFigure;
import openglexample.Renderer;
import openglexample.Sphere;
import openglexample.Tor;

public class ReduceApproximation implements ActionListener{
	private Renderer ren;
	public ReduceApproximation(Renderer r){
		ren = r;
	}
	public void actionPerformed(ActionEvent ev){
		My3DFigure fig = ren.getFigure();
		if(fig.getClass()==openglexample.Tor.class){
			Tor tor = (Tor) fig;
			float dbTor = tor.getDbTor();
			float dlTor = tor.getDlTor();
			if(dbTor < 1 && dlTor < 1){
				dbTor += 0.1f;
				dlTor += 0.1f;
			}
			tor.setDbTor(dbTor);
			tor.setDlTor(dlTor);
			tor.calcPoint();
		}
		else if(fig.getClass()==openglexample.Sphere.class){
			Sphere sph = (Sphere) fig; 
			float dbSphere = sph.getDbSphere();
			float dlSphere = sph.getDlSphere();
			if(dlSphere < 120 && dbSphere < 90){
				dbSphere += 5;
				dlSphere += 5;
			}
			sph.setDlSphere(dlSphere);
			sph.setDbSphere(dbSphere);
			sph.calcPoint();
		}
	}
}
