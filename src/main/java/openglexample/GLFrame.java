package openglexample;
import javax.media.opengl.awt.GLCanvas;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.jogamp.opengl.util.Animator;

import handlers.DrawThor;
import handlers.DrowSphere;
import handlers.IncreaseApproximation;
import handlers.ReduceApproximation;

/**
 * Main class, which initialize the frame
 * @author dimakolyandra
 */
public class GLFrame extends JFrame {
    
	/** Panel for rendering */
    private GLCanvas canvas;
    
    /** Animator which realizes infinite cycle */
    private Animator anim;
    
    /** Realizes rendering*/ 
    private Renderer ren;
    
    /** Spring context of application*/
    private static ApplicationContext context;
    
    /**
     * @param glcanvas panel for rendering
     * @param animator object for infinite cycle
     * @param renderer control the drawing
     */
    public GLFrame(GLCanvas glcanvas,Animator animator,Renderer renderer) {
        this.canvas = glcanvas;
        this.anim = animator;
        this.ren = renderer;
        canvas.setSize(700, 600);
        setTitle("Approximation and lighting");
        setResizable(false);
        setLayout(new BorderLayout());
        
        JPanel my = new JPanel();
        JButton incrApr = new JButton("Increase approximation");
        JButton redApr = new JButton("Reduce approximation");
        JButton tor = new JButton("Tor");
        JButton Sphere = new JButton("Sphere");
        
        my.add(incrApr);
        my.add(redApr); 
        my.add(tor); 
        my.add(Sphere);
        
        add(canvas, BorderLayout.CENTER);
        add(my,BorderLayout.SOUTH);
        setSize(getPreferredSize());
        incrApr.addActionListener(new  IncreaseApproximation(ren));
        redApr.addActionListener(new ReduceApproximation(ren));
        tor.addActionListener(new DrawThor(ren,context));
        Sphere.addActionListener(new DrowSphere(ren,context));
        canvas.addGLEventListener(ren);
        canvas.addMouseMotionListener(ren.getMouseListener());
        anim.setRunAsFastAsPossible(true);
        anim.start();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                anim.stop();
                dispose();
            }
        });
    }
    
    /** Method main 
     * @param args array with arguments 
     */
    public static void main(String[] args) {
    	context = new ClassPathXmlApplicationContext("context.xml");
    	GLFrame frame = (GLFrame)context.getBean("glFrame");
    }
}