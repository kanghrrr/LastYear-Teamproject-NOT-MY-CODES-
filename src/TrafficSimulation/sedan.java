package TrafficSimulation;

public class sedan extends mycar {		// 22 x 16

	public sedan(track r, double v) {
		super(r, v, Math.acos(0.9));
		// TODO Auto-generated constructor stub
	}

	public void outLining() {
		for (int i = 0; i < 4; i++) {
			outLineX[i] = posX + (int) (15 * Math.cos(Direction + revise[i]));
			outLineY[i] = posY + (int) (-15 * Math.sin(Direction + revise[i]));
		}
	}

}
