package com.tescomm.test;

public class Student {
	private String name;
	private String no;
	private int mathRecord;
	private int engRecord;
	private int chineseRecord;

	public Student() {
	}

	public Student(String name, String no) {
		this.name = name;
		this.no = no;
	}

//输出信息
	public String toString() {
		return "student [name=" + name + ", no=" + no + ", mathRecord=" + mathRecord + ", engRecord=" + engRecord
				+ ", chineseRecord=" + chineseRecord + "]";
	}

//设置姓名
	public void setName(String name) {
		this.name = name;
	}

//设置学号
	public void setNo(String no) {
		this.no = no;
	}

//设置语文成绩
	public void setChineseRecord(int record) {
		this.chineseRecord = record;
	}

//设置数学成绩
	public void setMathRecord(int record) {
		this.mathRecord = record;
	}

//设置英语成绩
	public void setEngRecord(int record) {
		this.engRecord = record;
	}

//计算总成绩
	public int getTotalRecord() {
		return (this.mathRecord + this.engRecord + this.chineseRecord);
	}

//计算平均成绩
	public float getAverageRecord() {
		return this.getTotalRecord() / 3.0f;
	}

	public static void main(String[] args) {
		Student st = new Student();
		st.setName("张三");
		st.setNo("201112120321");
		st.setChineseRecord(95);
		st.setMathRecord(90);
		st.setEngRecord(97);
		System.out.println(st.toString());
		System.out.println("总成绩：" + st.getTotalRecord());
		System.out.println("平均成绩：" + st.getAverageRecord());
	}
}
