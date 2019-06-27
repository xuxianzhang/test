package com.xxz.test;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.awt.Component;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Merit {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Merit window = new Merit();
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
	public Merit() {
		initialize();
	}
	
	private final int ticket = 4;
	private int select = 0;
	private int votedPer = 0;
	private int votes = 0;
	private ChartPanel charPanel = null;
	private List<JTextField> voteField;
	private JTextField voted;
	private JTextField allVotes;
	private List<Candidate> candidate;

	enum Sex{
		MALE,FAMALE;
	}
	class Candidate {
		private String name;
		private Sex sex;
		private int voter;

		public String getInfo() {
			return "姓名：" + name + "     性别：" + sex;
		}

		public Candidate(String name, Sex sex) {
			this.name = name;
			this.sex = sex;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Sex getSex() {
			return sex;
		}

		public void setSex(Sex sex) {
			this.sex = sex;
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

	private ImageIcon getIcon(Sex sex) {
		ImageIcon icon = null;
		switch(sex){
		case FAMALE:
			icon = new ImageIcon("F:\\icon\\famale.png");
			break;
		case MALE:
			icon = new ImageIcon("F:\\icon\\male.png");
			break;
		default:
			break;
		}
		icon.setImage(icon.getImage().getScaledInstance(36, 21, Image.SCALE_DEFAULT));
		return icon;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("三好学生推优系统");
		frame.setBounds(100, 100, 734, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		candidate = new ArrayList<Candidate>();
		Candidate candidateOne = new Candidate("李雷", Sex.MALE);
		Candidate candidateTwo = new Candidate("韩梅", Sex.FAMALE);
		Candidate candidateThree = new Candidate("王颖", Sex.FAMALE);
		Candidate candidateFour = new Candidate("王江", Sex.MALE);
		Candidate candidateFive = new Candidate("陈磊", Sex.MALE);
		Candidate candidateSix = new Candidate("杜克", Sex.MALE);
		Candidate candidateSeven = new Candidate("武萌", Sex.FAMALE);
		Candidate candidateEight = new Candidate("孙斌", Sex.MALE);
		candidate.add(candidateOne);
		candidate.add(candidateTwo);
		candidate.add(candidateThree);
		candidate.add(candidateFour);
		candidate.add(candidateFive);
		candidate.add(candidateSix);
		candidate.add(candidateSeven);
		candidate.add(candidateEight);

		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("候选人信息");
		lblNewLabel.setBounds(45, 65, 124, 15);
		frame.getContentPane().add(lblNewLabel);

		JLabel vectorLabel = new JLabel("得票");
		vectorLabel.setBounds(312, 65, 36, 15);
		frame.getContentPane().add(vectorLabel);

		voteField = new ArrayList<JTextField>();
		
		//初始化图标
		
		// 增加图标
		JFreeChart chart = getFreeChart(candidate);
		charPanel = new ChartPanel(chart, true);
		charPanel.setBounds(396, 99, 301, 239);
		frame.getContentPane().add(charPanel);

		List<JCheckBox> candidateInfo = new ArrayList<JCheckBox>();
		candidate.forEach(item -> {
			JTextField vote = new JTextField();
			vote.setColumns(10);
			vote.setEditable(false);
			vote.setText("0");
			vote.setHorizontalAlignment(SwingConstants.CENTER);
			vote.setBounds(312, 86 + candidateInfo.size() * 25, 36, 21);
			voteField.add(vote);

			frame.getContentPane().add(vote);
			JCheckBox checkBox = new JCheckBox(item.getInfo());
			checkBox.setBounds(86, 86 + candidateInfo.size() * 25, 229, 23);
			checkBox.setSelected(false);
			checkBox.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					JCheckBox checkBox = (JCheckBox) e.getSource();
					//System.out.println(checkBox.isSelected());
					if (checkBox.isSelected()) {
						++select;
						if (select > ticket) {
							checkBox.setSelected(false);
						}
					} else {
						--select;
					}
				}

			});
			JLabel iconLabel = new JLabel();
			iconLabel.setBounds(45, 86 + candidateInfo.size() * 25, 36, 21);
			iconLabel.setIcon(getIcon(item.getSex()));
			frame.getContentPane().add(iconLabel);
			
			candidateInfo.add(checkBox);
			frame.getContentPane().add(checkBox);
			
		});

		// 投票按钮
		JButton btnNewButton = new JButton("投票");
		btnNewButton.setBounds(86, 350, 114, 46);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				votedPer++;
				candidateInfo.forEach(item -> {
					if (item.isSelected()) {
						candidate.get(candidateInfo.indexOf(item)).addVoter();
						item.setSelected(false);
					}
				});
				repaintChart(candidate);
				setVoteInfo(candidate);
				voted.setText(String.valueOf(votedPer));
				select = 0;
			}
		});
		frame.getContentPane().add(btnNewButton);

		voted = new JTextField();
		voted.setHorizontalAlignment(SwingConstants.CENTER);
		voted.setBounds(150, 292, 48, 46);
		frame.getContentPane().add(voted);
		voted.setText("0");
		voted.setColumns(10);

		JLabel label = new JLabel("已投人数：");
		label.setBounds(86, 308, 72, 15);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("总票数：");
		label_1.setBounds(258, 308, 72, 15);
		frame.getContentPane().add(label_1);

		allVotes = new JTextField();
		allVotes.setText("0");
		allVotes.setHorizontalAlignment(SwingConstants.CENTER);
		allVotes.setColumns(10);
		allVotes.setBounds(322, 292, 48, 46);
		frame.getContentPane().add(allVotes);

		// 导出按钮
		JButton button = new JButton("导出结果");
		button.setBounds(256, 350, 114, 46);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showFileSaveDialog(frame, getRes());
			}
		});
		frame.getContentPane().add(button);
		// 导出投票数据
	}
	
	private String getRes() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("投票人数：");
		buffer.append(votedPer);
		buffer.append("\r\n");
		buffer.append("总票数：");
		buffer.append(votes);
		buffer.append("\r\n");
		candidate.forEach(item->{
			buffer.append("竞选人：");
			buffer.append(item.getName());
			buffer.append(".    ");
			
			buffer.append("性别：");
			buffer.append(item.getSex());
			buffer.append(".    ");
			
			buffer.append("得票：");
			buffer.append(item.getVoter());
			buffer.append(".");
			buffer.append("\r\n");
		});
		return buffer.toString();
	}
	
	private static void showFileSaveDialog(Component parent, String res) {
		// 创建一个默认的文件选取器
		JFileChooser fileChooser = new JFileChooser();

		// 设置打开文件选择框后默认输入的文件名
		fileChooser.setSelectedFile(new File("投票结果.txt"));
		FileOutputStream fos = null;
		try {
			// 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
			int result = fileChooser.showSaveDialog(parent);

			if (result == JFileChooser.APPROVE_OPTION) {
				// 如果点击了"保存", 则获取选择的保存路径
				File file = fileChooser.getSelectedFile();
				fos = new FileOutputStream(file);
				byte[] out = res.getBytes();
				fos.write(out);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void setVoteInfo(List<Candidate> candidate) {
		// 修改得票信息
		votes = 0;
		candidate.forEach(item -> {
			int index = candidate.indexOf(item);
			votes += item.getVoter();
			voteField.get(index).setText(String.valueOf(item.getVoter()));
		});
		allVotes.setText(String.valueOf(votes));
	}

	private void repaintChart(List<Candidate> candidate) {
		// 统计信息,柱形图
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		candidate.forEach(item -> {
			dataset.addValue(item.getVoter(), item.getSex(), item.getName());

		});

		JFreeChart chart = getFreeChart(candidate);
		charPanel.setChart(chart);
		charPanel.repaint();
	}

	private JFreeChart getFreeChart(List<Candidate> candidate) {

		// 统计信息,柱形图
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		candidate.forEach(item -> {
			dataset.addValue(item.getVoter(), item.getSex(), item.getName());

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
		// rangeAxis.setRange(0, 30);
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 11));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 11));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 14));// 设置标题字体
		return chart;
	}
}
