package TrafficSimulation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class Screen_form extends JFrame {
	private JRootPane jrp;
	private Container con;
	private Dimension dimen, dimen1;
	private int xpos, ypos;

	private Button bt1 = new Button("Traffic Light");
	private Button bt2 = new Button("Lane Closing");
	Button1Handler handler1 = new Button1Handler();
	Button2Handler handler2 = new Button2Handler();
	Animation k = new Animation();
	Obstacle obs;
	
	public Screen_form() {
		super("Traffic Simulator");
		this.init();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);
		this.setLocation(xpos, ypos);
		this.setVisible(true);
		this.setResizable(false);
		k.animate(); // 애니메이션
		while(k.checkButton1 || k.checkButton2){
			k.checkButton1 = false;
			k.checkButton2 = false;
			k.setInitial();
			k.animate();
		}
	}

	public void init() {
		jrp = this.getRootPane();
		con = this.getContentPane();
		
		bt1.setBounds(0, 0, 150, 40);				//
		bt1.addActionListener(handler1);			// Traffic light button
		this.add(bt1);
		
		bt2.setBounds(150, 0, 150, 40);				//
		bt2.addActionListener(handler2);			// Lane Closing button
		this.add(bt2);
		
		Controller control = new Controller();
		k.setController(control);
		jrp.setGlassPane(control);					// to top-level layer
		control.setOpaque(false);					// invisible panel's background color
		control.setVisible(true);
		
		obs = new Obstacle();
		k.setObstacle(obs);
		con.add(obs);
		con.add(k.drawPanel);
	}

	private class Button1Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			k.checkButton1 = true;
			obs.selectTrafficLight = true;
			obs.selectLaneClosing = false;
			obs.repaint();
		}
	}

	private class Button2Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			k.checkButton2 = true;
			obs.selectTrafficLight = false;
			obs.selectLaneClosing = true;
			obs.repaint();
		}
	}

	public static void main(String ar[]) {
		Screen_form s = new Screen_form();
		
	}
}
