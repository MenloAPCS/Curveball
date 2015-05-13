import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

import javax.swing.JFrame;


//PLEASE DISREGARD THE HORRIBLE CODE I MADE FOR THE DEMO!!!
public class Curveball extends JFrame implements
	KeyListener, MouseListener, MouseMotionListener
{
	public static final int WINDOW_SIZE = 512;
	public static final int HEADER_Y = 20;
	
	private Camera camera = new Camera(
			new Position3D(-60, 0, 10), new Orientation(0, Math.PI/4, 0)
	);
	
	private HashSet<Character> activeEvents = new HashSet<Character>();
	
	public static void main(String[] args)
	{
		Curveball gp = new Curveball();
		
		gp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gp.setTitle("Curveball");
		gp.setSize(WINDOW_SIZE, WINDOW_SIZE + HEADER_Y);
		gp.setVisible(true);
		gp.setResizable(false);
		gp.setBackground(Color.BLACK);
		gp.repaint();
		
		//Add listeners
		gp.addKeyListener(gp);
		gp.addMouseListener(gp);
		gp.addMouseMotionListener(gp);
		gp.start();
		gp.repaint();
	}
	
	public void start()
	{
		while(true)
		{
			//System.out.println(activeEvents);
			if(activeEvents.size() > 0)
			{
				for(Character c: activeEvents)
				{
					charPressed(c);
				}
				repaint();
			}
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, HEADER_Y, WINDOW_SIZE, WINDOW_SIZE);
		g.setColor(Color.GREEN);
		
		Sphere test = new Sphere(10, new Position3D(0, 0, 70));
		test.render(g, camera);
		
		Sphere s1 = new Sphere(5, new Position3D(-20, 20, 50), Color.RED);
		Sphere s2 = new Sphere(5, new Position3D(20, 20, 50), Color.ORANGE);
		Sphere s3 = new Sphere(5, new Position3D(20, -20, 50), Color.YELLOW);
		Sphere s4 = new Sphere(5, new Position3D(-20, -20, 50), Color.GREEN);
		
		Sphere s5 = new Sphere(5, new Position3D(-20, 20, 90), Color.CYAN);
		Sphere s6 = new Sphere(5, new Position3D(20, 20, 90), Color.BLUE);
		Sphere s7 = new Sphere(5, new Position3D(20, -20, 90), Color.RED);
		Sphere s8 = new Sphere(5, new Position3D(-20, -20, 90), Color.GRAY);
		
		Position3D p1 = new Position3D(-20, 20, 50);
		Position3D p2 = new Position3D(20, 20, 50);
		Position3D p3 = new Position3D(20, -20, 50);
		Position3D p4 = new Position3D(-20, -20, 50);
		
		Position3D p5 = new Position3D(-20, 20, 90);
		Position3D p6 = new Position3D(20, 20, 90);
		Position3D p7 = new Position3D(20, -20, 90);
		Position3D p8 = new Position3D(-20, -20, 90);
		
		Rectangle rect = new Rectangle(p1, p2, p3, p4);
		Rectangle rect2 = new Rectangle(p5, p6, p7, p8);
		Rectangle rect3 = new Rectangle(p1, p2, p6, p5);
		Rectangle rect4 = new Rectangle(p2, p3, p7, p6);
		Rectangle rect5 = new Rectangle(p3, p4, p8, p7);
		Rectangle rect6 = new Rectangle(p4, p1, p5, p8);
		
		rect.render(g, camera);
		rect2.render(g, camera);
		rect3.render(g, camera);
		rect4.render(g, camera);
		rect5.render(g, camera);
		rect6.render(g, camera);
		
		s1.render(g, camera);
		s2.render(g, camera);
		s3.render(g, camera);
		s4.render(g, camera);
		
		s5.render(g, camera);
		s6.render(g, camera);
		s7.render(g, camera);
		s8.render(g, camera);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) 
	{
	
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e)
	{
		this.repaint(e.getKeyChar());
		activeEvents.add(e.getKeyChar());
	}
	
	private void charPressed(char c)
	{
		if(c == 'w')
		{
			camera.move(0, 0, 1);
		}
		else if(c == 'a')
		{
			camera.move(-1, 0, 0);
		}
		else if(c == 's')
		{
			camera.move(0, 0, -1);
		}
		else if(c == 'd')
		{
			camera.move(1, 0, 0);
		}
		else if(c == 'q')
		{
			camera.move(0, 1, 0);
		}
		else if(c == 'z')
		{
			camera.move(0, -1, 0);
		}
		else if(c == 'i')
		{
			camera.turn(.01, 0, 0);
		}
		else if(c == 'j')
		{
			camera.turn(0, -.01, 0);
		}
		else if(c == 'k')
		{
			camera.turn(-.01, 0, 0);
		}
		else if(c == 'l')
		{
			camera.turn(0, .01, 0);
		}
	}

	public void keyReleased(KeyEvent e) {
		activeEvents.remove(e.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}