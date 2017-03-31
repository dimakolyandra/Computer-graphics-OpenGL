package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import openglexample.Renderer;
import openglexample.Sphere;
import openglexample.Tor;

public class DrawTor implements ActionListener{
	private Renderer ren;
	ApplicationContext context;
	public DrawTor(Renderer ren2,ApplicationContext context){
		this.context = context;
		ren = ren2;
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Tor tor = (Tor)context.getBean("tor");
		tor.calcPoint();
		ren.setFigure(tor);
	}
}
