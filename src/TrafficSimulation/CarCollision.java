package TrafficSimulation;

import java.util.ArrayList;

public class CarCollision {
	mycar car;

	public CarCollision(mycar c) {
		this.car = c;
	}

/*	public boolean laneCheck(ArrayList<mycar> car) {
		double nearest = 3000;

		for (int n = 0; n < car.size(); n++) {
			if (this.car == car.get(n))
				continue;
			if (car.get(n).track.R >= 237.5) {
				double gap = Math.abs(this.car.distance - car.get(n).distance);
				if (gap < nearest)
					nearest = gap;
			}
		}
		if (nearest > 40)
			return true;
		else
			return false;
	} */

	public void collisionLC(double limitV) {
		if (car.distance > 450 && car.track.R < 237.5
				&& car.Direction == Math.PI) {
			car.setTrack(new track(car.track.R + 1.5 * limitV / 10));
			car.track.getCoord(car.distance, car);
			car.outLining();
			car.velocity = limitV / 10;
		} else
			car.accelerate(car.velocity, limitV);
		/* if (car.track.R >= 237.5)
			car.checkCollision = false; */
	}

	public void collisionTL(double limitV) {
		if ((car.Direction == 2 * Math.PI) && car.outLineX[0] > 430
				&& car.outLineX[1] < 430) {
			car.track.getCoord(car.distance, car);
			car.outLining();
			if (710 + car.track.R * Math.PI - car.distance > 0)
				car.deaccelerate(car.velocity, limitV);
			else
				car.velocity = 0;
		} else
			car.accelerate(car.velocity, limitV);
	}

	public void collisionCbyC(mycar frontcar, double limitV) {
		if (!(frontcar.track.R == 237.5 && car.track.R == 212.5)
				&& !(frontcar.track.R == 212.5 && car.track.R == 237.5)) {
			if (car.distance > 0 && car.velocity != 0) {
				car.track.getCoord(car.distance, car);
				car.outLining();
				if (frontcar.distance - car.distance > 40)
					car.deaccelerate(car.velocity, limitV);
				else
					car.velocity = 0;
			} else {
				car.accelerate(car.velocity, limitV);
			}
		} // outer if
	} // end collisionCbyC method
}
