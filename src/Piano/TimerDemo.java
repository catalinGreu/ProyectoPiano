package Piano;

import java.awt.BorderLayout;

import java.awt.event.*;

import java.awt.*;

import javax.swing.Timer;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

public class TimerDemo extends JFrame implements ActionListener {

	JLabel number = new JLabel("0.00");

	JPanel panel1 = new JPanel();

	JButton start = new JButton("Start");

	JButton stop = new JButton("Stop");

	JPanel panel2 = new JPanel();

	Timer timer;

	public TimerDemo() {

		JFrame frame = new JFrame();

		frame.setBounds(300, 50, 200, 150);

		frame.setLayout(new BorderLayout());

		start.setActionCommand("Start");

		start.addActionListener(this);

		stop.setActionCommand("Stop");

		stop.addActionListener(this);

		panel1.add(number);

		panel2.add(start);

		panel2.add(stop);

		frame.add(panel1, BorderLayout.NORTH);

		frame.add(panel2, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);

		ActionListener timerListener = new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				
				tick();

				number.setText(Double.parseDouble(number.getText()) + 1 + "");

			}

			double contador = 0.00;
			
			private void tick() {
				contador += 1;
				System.out.println(contador);
				
			}

		};

		timer = new Timer(20, timerListener);

	}

	public void actionPerformed(ActionEvent e)

	{

		String actionCommand = e.getActionCommand();

		if ("Start".equals(actionCommand))

		{

			timer.start();

		}

		if ("Stop".equals(actionCommand))

		{

			timer.stop();

			number.setVisible(true);

		}

	}

	public static void main(String[] args) {

		new TimerDemo();

	}

}
