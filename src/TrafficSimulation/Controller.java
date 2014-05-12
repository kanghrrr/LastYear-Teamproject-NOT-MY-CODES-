package TrafficSimulation;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Controller extends JPanel {
	private Scrollbar[] diameters = new Scrollbar[5];
	private final JLabel[] unit = new JLabel[5];
	private final JLabel[] unitName = new JLabel[5];
	private final JLabel limit_velocity = new JLabel("50");
	private int mainInflow = 1800;
	private int truckPercentage = 20;
	private int imposedSpeedLimit = 50;
	private int politenessFactor = 0;
	private int simulationSpeed = 100;
	private Font font;

	public Controller() {
		super(null);
		this.scroll();
		this.scrollValue();
	}

	public void scroll() {
		limit_velocity.setBounds(808, 26, 40, 40);
		font = new Font("DialogInput", Font.BOLD, 28);
		limit_velocity.setFont(font);
		this.add(limit_velocity);

		diameters[0] = new Scrollbar(Scrollbar.HORIZONTAL, 1800, 0, 0, 4001);
		diameters[1] = new Scrollbar(Scrollbar.HORIZONTAL, 20, 0, 0, 101);
		diameters[2] = new Scrollbar(Scrollbar.HORIZONTAL, 50, 0, 20, 150);
		diameters[3] = new Scrollbar(Scrollbar.HORIZONTAL, 0, 0, -100, 201);
		diameters[4] = new Scrollbar(Scrollbar.HORIZONTAL, 100, 0, 10, 201);

		unitName[0] = new JLabel("Main Inflow", JLabel.RIGHT);
		unitName[1] = new JLabel("Truck Percentage", JLabel.RIGHT);
		unitName[2] = new JLabel("Imposed Speed Limit", JLabel.RIGHT);
		unitName[3] = new JLabel("Politeness Factor", JLabel.RIGHT);
		unitName[4] = new JLabel("Simulation Speed", JLabel.RIGHT);
		unit[0] = new JLabel(mainInflow + " Vehicle / h");
		unit[1] = new JLabel(truckPercentage + " %");
		unit[2] = new JLabel(imposedSpeedLimit + " km / h");
		unit[3] = new JLabel((double) politenessFactor / 100 + "");
		unit[4] = new JLabel((double) simulationSpeed / 10 + " times");

		for (int i = 0; i < 5; i++) {
			diameters[i].setBounds(500, 250 + 20 * i, 350, 15);
			unitName[i].setBounds(340, 250 + 20 * i, 150, 15);
			unit[i].setBounds(860, 250 + 20 * i, 90, 15);

			this.add(unitName[i]);
			this.add(diameters[i]);
			this.add(unit[i]);
		}
	}

	public void scrollValue() {
		diameters[0].addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent ae) {
				Scrollbar scroll = (Scrollbar) ae.getSource();
				// if (scroll.getValueIsAdjusting()) {}
				unit[0].setText(scroll.getValue() + " Vehicle / h");
				mainInflow = (int) scroll.getValue();
			}
		});

		diameters[1].addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent ae) {
				Scrollbar scroll = (Scrollbar) ae.getSource();
				unit[1].setText(scroll.getValue() + " %");
				truckPercentage = (int) scroll.getValue();
			}
		});

		diameters[2].addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent ae) {
				Scrollbar scroll = (Scrollbar) ae.getSource();
				if (scroll.getValue() >= 140) {
					BackGround bg = new BackGround();
					limit_velocity.setText(scroll.getValue() / 10 * 10 + "");
					unit[2].setText(scroll.getValue() / 10 * 10 + " km / h");
					imposedSpeedLimit = 140;
				} else if (scroll.getValue() >= 100) {
					font = new Font("DialogInput", Font.BOLD, 20);
					limit_velocity.setFont(font);
					limit_velocity.setText(scroll.getValue() / 10 * 10 + "");
					unit[2].setText(scroll.getValue() / 10 * 10 + " km / h");
					imposedSpeedLimit = (int) scroll.getValue();
				} else {
					font = new Font("DialogInput", Font.BOLD, 28);
					limit_velocity.setFont(font);
					limit_velocity.setText(scroll.getValue() / 10 * 10 + "");
					unit[2].setText(scroll.getValue() / 10 * 10 + " km / h");
					imposedSpeedLimit = (int) scroll.getValue();
				}
			}
		});

		diameters[3].addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent ae) {
				Scrollbar scroll = (Scrollbar) ae.getSource();
				unit[3].setText((double) scroll.getValue() / 100 + "");
				politenessFactor = (int) scroll.getValue();
			}
		});

		diameters[4].addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent ae) {
				Scrollbar scroll = (Scrollbar) ae.getSource();
				unit[4].setText((double) scroll.getValue() / 10 + " times");
				simulationSpeed = (int) scroll.getValue();
			}
		});
	}

	public int getMainInflow() {
		return mainInflow;
	}

	public int getTruckPercentage() {
		return truckPercentage;
	}

	public int getImposedSpeedLimit() {
		return imposedSpeedLimit;
	}

	public int getPolitenessFactor() {
		return politenessFactor;
	}

	public int getSimulationSpeed() {
		return simulationSpeed;
	}

}
