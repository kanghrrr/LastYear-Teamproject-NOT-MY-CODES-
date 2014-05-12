package TrafficSimulation;

public class carLine {

	double Direction;
	int X, Y;

	public carLine() {
		Direction = Math.PI;
		X = 1000;
		Y = 125;
	}

	public void translate(int p, int q, double direction) {
		double theta = direction - Math.PI / 2.0;
		int x = p;
		int y = q;

		if ((x - 50 >= 350) && (theta < Math.PI * 3.0 / 2.0)) {
			x -= 50;
		} else if (x - 50 < 350 && theta + Math.PI / 15 < Math.PI * 3 / 2.0) {
			y = (int) (125 + (225 - (225 * Math.sin(theta = theta + Math.PI
					/ 15))));
			x = (int) (350 + 225 * Math.cos(theta));

			Direction = Math.PI / 2.0 + theta;
		} else {
			Direction = 2 * Math.PI;
			x += 50;
			y = 575;
			if (x > 1000) {
				theta = Math.PI / 2;
				y = 125;
			}
		}
		X = x;
		Y = y;
	}
}
