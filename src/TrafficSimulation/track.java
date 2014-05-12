package TrafficSimulation;

public class track {
	double R;
	double length;
	final int cx = 350;
	final int cy = 350; // 커브길의 중심점 좌표

	public track(double p) {
			R = p;
		length = 1300 + Math.PI * R;
	}

	public void getCoord(double distance, mycar car) {
		double theta = 0;
		int straight = car.startX - cx;
		int curve = (int) (Math.PI * R);

		if (distance <= straight) {
			theta = 0;
			car.posX = (int) (car.startX - distance);
			car.posY = (int) (cy - R * Math.cos(theta));
		} else if (straight < distance && distance <= straight + curve) {
			theta = (distance - straight) / R;
			car.Direction = theta + Math.PI;
			car.posX = (int) (cx - R * Math.sin(theta));
			car.posY = (int) (cy - R * Math.cos(theta));

		} else if (straight + curve < distance) {
			car.Direction = 2 * Math.PI;
			car.posX = (int) (350 + (distance - (straight + curve)));
			car.posY = (int) (cy + R);
		}

	}

}
