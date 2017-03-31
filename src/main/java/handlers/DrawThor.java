package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import openglexample.Renderer;
import openglexample.Sphere;
import openglexample.Thor;

/**
 * Handler for the button click thor
 * @author dimakolyandra
 */
public class DrawThor implements ActionListener{
	private Renderer ren;
	ApplicationContext context;
	public DrawThor(Renderer ren2,ApplicationContext context){
		this.context = context;
		ren = ren2;
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Thor tor = (Thor)context.getBean("tor");
		tor.calcPoint();
		ren.setFigure(tor);
	}
}
