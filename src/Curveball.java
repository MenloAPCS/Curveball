import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;



public class Curveball extends JFrame implements MouseListener, MouseMotionListener
{
	public static final int WINDOW_SIZE = 512;
	public static final int HEADER_Y = 20;
	
	public static void main(String[] args)
	{
		Curveball gp = new Curveball();
		
		gp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gp.setTitle("Curveball");
		gp.setSize(WINDOW_SIZE, WINDOW_SIZE + HEADER_Y);
		gp.setVisible(true);
		gp.setResizable(false);
		gp.repaint();
		
		//Add listeners
		gp.addMouseListener(gp);
		gp.addMouseMotionListener(gp);
		
		gp.start();
	}
	
	public void start()
	{
		System.out.println();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
}