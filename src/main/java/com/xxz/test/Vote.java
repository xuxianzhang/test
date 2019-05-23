package com.xxz.test;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Vote {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vote window = new Vote();
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
	public Vote() {
		initialize();
	}

	private final int ticket = 3;
	private int select = 0;
	private ChartPanel charPanel = null;

	class Candidate {
		private String name;
		private String position;
		private int voter;

		public String getInfo() {
			return "姓名：" + name + "     职务：" + position;
		}

		public Candidate(String name, String position) {
			this.name = name;
			this.position = position;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public int getVoter() {
			return voter;
		}

		public void setVoter(int voter) {
			this.voter = voter;
		}

		public void addVoter() {
			this.voter++;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 734, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		List<Candidate> candidate = new ArrayList<Candidate>();
		Candidate candidateOne = new Candidate("张三", "班长");
		Candidate candidateTwo = new Candidate("李四", "副班长");
		Candidate candidateThree = new Candidate("王五", "团委");
		Candidate candidateFour = new Candidate("马六", "体委");
		Candidate candidateFive = new Candidate("陈七", "文委");
		Candidate candidateSix = new Candidate("王八", "学委");
		candidate.add(candidateOne);
		candidate.add(candidateTwo);
		candidate.add(candidateThree);
		candidate.add(candidateFour);
		candidate.add(candidateFive);
		candidate.add(candidateSix);

		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("候选人信息");
		lblNewLabel.setBounds(86, 65, 229, 15);
		frame.getContentPane().add(lblNewLabel);
		//增加图标
		charPanel = getCharPanel(candidate);
		frame.getContentPane().add(charPanel);
		
		List<JCheckBox> candidateInfo = new ArrayList<JCheckBox>();
		candidate.forEach(item -> {
			JCheckBox checkBox = new JCheckBox(item.getInfo());
			checkBox.setBounds(86, 86 + candidateInfo.size() * 25, 229, 23);
			checkBox.setSelected(true);
			checkBox.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					JCheckBox checkBox = (JCheckBox) e.getSource();
					// TODO Auto-generated method stub
					if(checkBox.isSelected()) {
						System.out.println("11");
					}else {
						System.out.println("22");
					}
				}
			});
//			checkBox.addMouseListener(new MouseAdapter() {
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					JCheckBox checkBox = (JCheckBox) e.getSource();
//					if(select>=ticket && !checkBox.isSelected()) {
//						return;
//					}else if(checkBox.isSelected()){
//						checkBox.setSelected(false);
//						--select;
//					}else if(select<ticket){
//						checkBox.setSelected(true);
//						++select;
//					}
//				}
//			});

			candidateInfo.add(checkBox);
			frame.getContentPane().add(checkBox);
		});

		// 投票按钮
		JButton btnNewButton = new JButton("投票");
		btnNewButton.setBounds(86, 292, 114, 46);


		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				candidateInfo.forEach(item -> {
					if (item.isSelected()) {
						candidate.get(candidateInfo.indexOf(item)).addVoter();
						item.setSelected(false);
					}
				});
				charPanel = getCharPanel(candidate);
				charPanel.repaint();
				select = 0;
			}
		});
		frame.getContentPane().add(btnNewButton);

		// 导出投票数据
	}

	private static ChartPanel getCharPanel(List<Candidate> candidate) {
		// 统计信息,柱形图
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		candidate.forEach(item->{
			dataset.addValue(item.getVoter(), item.getPosition(), item.getName());
		});

		JFreeChart chart = ChartFactory.createBarChart("计票信息", "候选人", "得票数", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
		);
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 10)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 8)); // 垂直标题
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setRange(0, 100);                                                                                                                                                                                                                                                                                                                                                                                                                
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 11));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 11));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 14));// 设置标题字体
		ChartPanel charPanel = new ChartPanel(chart, true);
		charPanel.setBounds(396, 99, 301, 239);
		return charPanel;
	}
}
