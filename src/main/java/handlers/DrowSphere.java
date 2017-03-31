package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import openglexample.Renderer;
import openglexample.Sphere;

/**
 * Handler for the button click sphere
 * @author dimakolyandra
 */

public class DrowSphere implements ActionListener{
	private Renderer ren;
	ApplicationContext context;
	public DrowSphere(Renderer r,ApplicationContext context){
		this.context = context;
		ren = r;
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Sphere sp = (Sphere)context.getBean("sphera");
		sp.calcPoint();
		ren.setFigure(sp);
	}
}
