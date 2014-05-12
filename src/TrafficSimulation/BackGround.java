package TrafficSimulation;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BackGround extends JPanel {
	int outLine_x1[] = new int[4];
	int outLine_y1[] = new int[4];
	
	public BackGround(){
		this.setBounds(0, 0, 1000, 700);
		this.setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.fillRect(350, 100, 700, 500);
		g.setColor(Color.GRAY);
		g.fillOval(100, 100, 500, 500);

		g.setColor(Color.GREEN);
		g.fillRect(350, 150, 700, 400);
		g.setColor(Color.GREEN);
		g.fillOval(150, 150, 400, 400);		// 배경

		g.setColor(Color.WHITE);
		double alpha = Math.acos(Math.cos(Math.PI / 18.0));
		double[] revise = { Math.PI - alpha, alpha, -alpha, Math.PI + alpha };
		carLine line = new carLine();
		g.setColor(Color.white);
		g.fillPolygon(outLine_x1, outLine_y1, 4);
		for (int i = 0; i < 41; i++) {
			line.translate(line.X, line.Y, line.Direction);
			for (int i1 = 0; i1 < 4; i1++) {
				outLine_x1[i1] = line.X
						+ (int) (20 * Math.cos((line.Direction) + revise[i1]));
				outLine_y1[i1] = line.Y
						+ (int) (-20 * Math.sin((line.Direction) + revise[i1]));
			}
			g.setColor(Color.white);
			g.fillPolygon(outLine_x1, outLine_y1, 4);
		}			//	하얀색 차선

		g.setColor(Color.RED);		// 표지판
		g.fillOval(800, 20, 50, 50);
		g.setColor(Color.WHITE);
		g.fillOval(805, 25, 40, 40);
		g.setColor(Color.BLACK);
		g.fillRect(823, 70, 5, 30);
	}
}
