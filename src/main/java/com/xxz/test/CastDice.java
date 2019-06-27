package com.xxz.test;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;

public class CastDice {

	private JFrame frame;
	private CarPanel car;
	private int moveValue= 0;
	private Direct moveDirect;
	private final int CAR_PANEL_X = 46;
	private final int CAR_PANEL_Y = 33;
	private final int CAR_PANEL_W = 540;
	private final int CAR_PANEL_H = 230;
	private final int CAR_PANEL_CENTER_X = CAR_PANEL_X + CAR_PANEL_W / 2;
	private final int CAR_PANEL_CENTER_Y = CAR_PANEL_Y + CAR_PANEL_H / 2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CastDice window = new CastDice();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CastDice() {
		initialize();
		car.paint(car.getGraphics());
	}

	public enum Direct {
		UP, DOWN, RIGHT, LIFT;
	    public static Direct getDirect(int value) {
	        switch (value) {
	        case 0:
	            return UP;
	        case 1:
	            return DOWN;
	        case 2:
	            return RIGHT;
	        case 3:
	            return LIFT;
	        default:
	            return null;
	        }
	    }
	    public static String getString(Direct value) {
	        switch (value) {
	        case UP:
	            return "向上";
	        case DOWN:
	            return "向下";
	        case RIGHT:
	            return "向右";
	        case LIFT:
	            return "向左";
	        default:
	            return null;
	        }
	    }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	class CarPanel extends JPanel {

		private final int CAR_W = 60;
		private final int CAR_H = 30;
		private final int RAR_TIRE = 7;
		private final int ORIGN_X = CAR_PANEL_CENTER_X - CAR_W;
		private final int ORIGN_Y = CAR_PANEL_CENTER_Y - CAR_H;
		private int x = ORIGN_X;
		private int y = ORIGN_Y;

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawRect(x, y, CAR_W, CAR_H);
			g.fillOval(x + RAR_TIRE, y + CAR_H, RAR_TIRE * 2, RAR_TIRE * 2);
			g.fillOval(x + CAR_W - RAR_TIRE * 3, y + CAR_H, RAR_TIRE * 2, RAR_TIRE * 2);
		}

		private void move(int value, Direct direct) {
			switch (direct) {
			case UP:
				y-=value;
				break;
			case DOWN:
				y+=value;
				break;
			case RIGHT:
				x+=value;
				break;
			case LIFT:
				x-=value;
				break;
			default:
				break;
			}
		}
	}

	private int getMoveValue() {
		
		return (int) Math.round(Math.random()*(5)+1);
	}
	private Direct getMoveDirect() {

		return Direct.getDirect((int) Math.round(Math.random()*(3)));
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("移动小车");
		frame.setBounds(100, 100, 640, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// label
		JLabel info = new JLabel();
		info.setHorizontalAlignment(SwingConstants.CENTER);

		info.setText("请点击“掷骰子”");
		info.setBounds(118, 270, 384, 50);
		frame.getContentPane().add(info);
		
		JButton cast = new JButton();
		cast.setFont(new Font("微软雅黑", Font.BOLD, 16));

		cast.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//投掷骰子
				moveValue = getMoveValue();
				moveDirect = getMoveDirect();
				//显示移动信息
				info.setText(Direct.getString(moveDirect)+"移动"+moveValue+"步.");
			}
		});
		cast.setText("掷骰子");
		cast.setBounds(118, 330, 100, 50);
		frame.getContentPane().add(cast);
		
		JButton move = new JButton();
		move.setFont(new Font("微软雅黑", Font.BOLD, 16));
		move.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//根据移动信息，移动小车
				car.move(moveValue, moveDirect);
				car.paint(car.getGraphics());
			}
		});
		move.setText("移动");
		move.setBounds(402, 330, 100, 50);
		frame.getContentPane().add(move);

		car = new CarPanel();
		car.setBounds(CAR_PANEL_X, CAR_PANEL_Y, CAR_PANEL_W, CAR_PANEL_H);

		frame.getContentPane().add(car);
		frame.setVisible(true);
	}
}
