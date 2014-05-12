package TrafficSimulation;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class Animation {
	DrawPanel drawPanel;
	// int[] outLine_x1 = new int[4];
	// int[] outLine_y1 = new int[4];
	private int traffic;
	boolean remove = false;
	boolean checkButton1 = false;
	boolean checkButton2 = false;

	Obstacle obstacle;
	Controller controller;
	ArrayList<mycar> car = new ArrayList<mycar>();

	public Animation() {
		drawPanel = new DrawPanel();
	}

	public void setController(Controller c) {
		controller = c;
	}

	public void setObstacle(Obstacle o) {
		obstacle = o;
	}

	public void setInitial() {
		this.remove = false;
		this.car = new ArrayList<mycar>();
		this.traffic = 0;
		obstacle.setInitial();
	}

	public void animate() {
		double t = 0;
		int time = 0;

		while (!checkButton1 && !checkButton2) {
			if (traffic >= 210) {
				if (obstacle.trafficSign) {
					obstacle.repaint();
					obstacle.trafficSign = false;
				} else {
					obstacle.repaint();
					obstacle.trafficSign = true;
				}
				traffic = 0;
			} // traffic light-> set initial is 7 sec

			if (t >= 1) {
				int carNum = 0;
				if (Math.random() * 100 <= controller.getTruckPercentage()) {
					for (int i = 0; i < car.size(); i++)
						if (car.get(i).distance > 50)
							carNum++;
					if (car.size() == carNum) {
						if (Math.random() <= 0.6)
							car.add(new truck(new track(212.5), 5));
						else
							car.add(new truck(new track(237.5), 5));
						car.get(carNum).checkTruck = true;
						t = 0;
					}
				} else {
					for (int i = 0; i < car.size(); i++)
						if (car.get(i).distance > 40)
							carNum++;
					if (car.size() == carNum) {
						if (Math.random() <= 0.6)
							car.add(new sedan(new track(212.5), 10));
						else
							car.add(new sedan(new track(237.5), 10));
						t = 0;
					}
				}
			} // create car

			for (int m = 0; m < car.size(); m++) {
				car.get(m).move(1);
				car.get(m).track.getCoord(car.get(m).distance, car.get(m));
				if (car.get(m).isRemoved())
					car.remove(car.get(m));
				car.get(m).outLining();
			} // moving animation and remove car

			for (int m = 0; m < car.size(); m++) {
				if (obstacle.selectLaneClosing) {
				/*	if (car.get(m).collision.laneCheck(car))
						car.get(m).checkCollision = true;
					if (car.get(m).checkCollision) */
						car.get(m).collision.collisionLC(controller
								.getImposedSpeedLimit() / 5.0);
				}

				if (obstacle.selectTrafficLight && !obstacle.trafficSign)
					car.get(m).collision.collisionTL(controller
							.getImposedSpeedLimit() / 5.0);
				else if (obstacle.trafficSign)
					car.get(m).accelerate(car.get(m).velocity,
							controller.getImposedSpeedLimit() / 5.0);
			} // collision car by obstacle

			for (int m = 0; m < car.size(); m++) {
				for (int n = 0; n < car.size(); n++) {
					int distanceCbyC = car.get(m).distance
							- car.get(n).distance;
					if (m < n) {
						if (distanceCbyC < 40 + car.get(n).velocity * 5
								&& distanceCbyC > 0 && car.get(n).distance > 14)
							car.get(n).collision.collisionCbyC(car.get(m),
									controller.getImposedSpeedLimit() / 5.0);
					/*	else if (distanceCbyC > -(40 + car.get(m).velocity * 5)
								&& distanceCbyC < 0 && car.get(m).distance > 14)
						car.get(m).collision.collisionCbyC(car.get(n),
									controller.getImposedSpeedLimit() / 5.0); */
					}
				}
			} // collision car by car. next to enstance, remove false car

			drawPanel.repaint();

			try {
				Thread.sleep((3000 / controller.getSimulationSpeed())); // simulation
																		// speed
			} catch (Exception ex) {
			}
			t += controller.getMainInflow() / 3600.0 / 5; // traffic
															// congestion
			traffic++; // count time of traffic light
		} // end while
	} // end animate()

	class DrawPanel extends JPanel {
		int outLine_x[] = new int[4];
		int outLine_y[] = new int[4];

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.green);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			g.setColor(Color.GRAY);
			g.fillRect(350, 100, 700, 500);
			g.setColor(Color.GRAY);
			g.fillOval(100, 100, 500, 500);

			g.setColor(Color.GREEN);
			g.fillRect(350, 150, 700, 400);
			g.setColor(Color.GREEN);
			g.fillOval(150, 150, 400, 400);

			g.setColor(Color.WHITE);

			double alpha = Math.acos(Math.cos(Math.PI / 18.0));
			double[] revise = { -alpha, -Math.PI + alpha, -Math.PI - alpha,
					alpha };
			carLine line = new carLine();
			for (int i1 = 0; i1 < 4; i1++) {
				outLine_x[i1] = line.X
						+ (int) (20 * Math.cos((line.Direction) + revise[i1]));
				outLine_y[i1] = line.Y
						+ (int) (-20 * Math.sin((line.Direction) + revise[i1]));
			}
			g.setColor(Color.white);
			g.fillPolygon(outLine_x, outLine_y, 4);
			for (int i = 0; i < 41; i++) {
				line.translate(line.X, line.Y, line.Direction);
				for (int i1 = 0; i1 < 4; i1++) {
					outLine_x[i1] = line.X
							+ (int) (20 * Math.cos((line.Direction)
									+ revise[i1]));
					outLine_y[i1] = line.Y
							+ (int) (-20 * Math.sin((line.Direction)
									+ revise[i1]));
				}
				g.setColor(Color.white);
				g.fillPolygon(outLine_x, outLine_y, 4);
			}
			g.setColor(Color.RED); // Ç¥ÁöÆÇ
			g.fillOval(800, 20, 50, 50);
			g.setColor(Color.WHITE);
			g.fillOval(805, 25, 40, 40);
			g.setColor(Color.BLACK);
			g.fillRect(823, 70, 5, 30);

			for (int m = 0; m < car.size(); m++) {
				if (car.get(m).checkTruck)
					g.setColor(Color.LIGHT_GRAY);
				else {
					g.setColor(new Color(Color.HSBtoRGB(
							(float) ((car.get(m).velocity - 4) < 0 ? 0 : (car
									.get(m).velocity - 4) / 40), (float) 1.0,
							(float) 1.0)));
				}
				g.fillPolygon(car.get(m).outLineX, car.get(m).outLineY, 4);
				g.setColor(Color.BLACK);
				g.drawLine(car.get(m).outLineX[0], car.get(m).outLineY[0],
						car.get(m).outLineX[1], car.get(m).outLineY[1]);
				g.drawLine(car.get(m).outLineX[1], car.get(m).outLineY[1],
						car.get(m).outLineX[2], car.get(m).outLineY[2]);
				g.drawLine(car.get(m).outLineX[2], car.get(m).outLineY[2],
						car.get(m).outLineX[3], car.get(m).outLineY[3]);
				g.drawLine(car.get(m).outLineX[3], car.get(m).outLineY[3],
						car.get(m).outLineX[0], car.get(m).outLineY[0]);
			}
		}
	} // end drawPanel
} // end class
