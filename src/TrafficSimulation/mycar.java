package TrafficSimulation;

import java.awt.Color;
import java.util.ArrayList;

public class mycar {
	boolean checkTruck;
	//boolean checkCollision;
	int posX, posY; // 자동차 위치 x, y 좌표
	int startX, startY;
	int distance; // 출발지점에서 자동차가 이동한 거리;
	double accel;
	double velocity; // 자동차 속력
	double Direction; // 자동차 방향
	track track; // 자동차가 달리는 도로

	int[] outLineX = new int[4];
	int[] outLineY = new int[4];
	double[] revise = new double[4];

	int politeTime;
	int timeToChange;
	CarCollision collision = new CarCollision(this);

	public mycar(track r, double v, double alpha) {
		track = r;
		startX = posX = 1000;
		startY = posY = (int) (track.cy - track.R);
		Direction = Math.PI;
		velocity = v;
		revise[0]= -alpha;
		revise[1]= -Math.PI + alpha;
		revise[2]= -Math.PI - alpha;
		revise[3]= alpha;
	}

	public void setTrack(track t) {
		track = t;
	}

	public void move(double t) { // 현재 위치하고 있던 곳에서 t시간 만큼 움직인 후의 위치 계싼
		double s = velocity * t;
		distance += (int) s;
	}

	public void accelerate(double v, double limitV) {
		if (v >= limitV)
			velocity = limitV;
		else {
			if (v != 0 && this.distance < 100)
				accel = v / 5;
			else if (v != 0)
				accel = v / 10;
			else
				accel = 2;
			velocity += accel;
		}
	}

	public void deaccelerate(double v, double limitV) {
		if (v <= 0)
			velocity = 0;
		else {
			if (v >= limitV)
				accel = v / 3;
			else if (v >= limitV/2)
				accel = v / 6;
			else if (v >= limitV/4)
				accel = v / 10;
			else
				velocity = accel;
			velocity -= accel;
		}
	}
	
	public boolean isRemoved() {
		if (posX > 1000 || distance > (int) track.length)
			return true;
		else
			return false;
	}

	public void outLining() {
		for (int i = 0; i < 4; i++) {
			outLineX[i] = posX + (int) (20 * Math.cos(Direction + revise[i]));
			outLineY[i] = posY + (int) (-20 * Math.sin(Direction + revise[i]));
		}
	} // 차 좌표 계산

	public void polite() {

	}
}
