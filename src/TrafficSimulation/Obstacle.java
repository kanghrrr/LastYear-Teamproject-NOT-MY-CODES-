package TrafficSimulation;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class Obstacle extends JPanel {
	boolean trafficSign = false;
	boolean selectTrafficLight = true;
	boolean selectLaneClosing = false;

	public Obstacle() {
		this.setBounds(0, 0, 1000, 700);
		this.setOpaque(false);
	}
	
	public void setInitial(){
		trafficSign = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (selectTrafficLight) {
			g.setColor(Color.BLACK);
			g.fillRect(430, 530, 4, 17);
			g.fillRect(422, 500, 20, 31);

			if (trafficSign) {
				g.setColor(Color.green);
				g.fillOval(424, 515, 15, 15);
				g.setColor(Color.black);
				g.fillOval(424, 500, 15, 15);
			} else {
				g.setColor(Color.red);
				g.fillOval(424, 500, 15, 15);
				g.setColor(Color.black);
				g.fillOval(424, 515, 15, 15);
				g.setColor(Color.BLACK);
				g.fillRect(450, 555, 25, 13);
				g.fillRect(450, 580, 25, 13);
			}
		} else {
			g.setColor(Color.black);
			g.fillRect(400, 134, 120, 10);
		}
	}
}
