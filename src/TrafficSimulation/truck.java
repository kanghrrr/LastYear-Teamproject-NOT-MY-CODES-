package TrafficSimulation;

public class truck extends mycar {
	
	public truck(track r, double v){
		super(r, v,Math.acos(0.9));
	}

	public void accelerate(double v, double limitV) {
		limitV /= 1.5;
		if (v >= limitV)
			velocity = limitV;
		else {
			if (v != 0 && this.distance < 100)
				accel = v / 10;
			else if (v != 0)
				accel = v / 15;
			else
				accel = 2;
			velocity += accel;
		}
	}
}
