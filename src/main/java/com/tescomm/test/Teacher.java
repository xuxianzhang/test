package com.tescomm.test;

public class Teacher {

	private String name;
	private String id;
	private double basicSalary;
	private double postSalary;
	private double meritSalary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public double getPostSalary() {
		return postSalary;
	}

	public void setPostSalary(double postSalary) {
		this.postSalary = postSalary;
	}

	public double getMeritSalary() {
		return meritSalary;
	}

	public void setMeritSalary(double meritSalary) {
		this.meritSalary = meritSalary;
	}

	@Override
	public String toString() {
		return "Teacher [姓名：" + name + ", 工号：" + id + ", 基本工资：" + basicSalary + ", 岗位工资：" + postSalary + ", 绩效工资："
				+ meritSalary + "]";
	}

	/*
	 * 计算总工资
	 */
	public double getTotalSalary() {
		return this.basicSalary + this.meritSalary + this.postSalary;
	}

	private double getIncomeTax(double high, double low) {
		return high > 0.0 ? (high * 0.15 + low * 0.1) : ((low>0.0)?(low * 0.1):(0));
	}

	/*
	 * 计算税后工资 3000以内不收税，3000-5000之间的部分扣10%，大于5000的部分扣15%
	 */
	public double getTakeHomeSalary() {
		double totalSalary = this.getTotalSalary();
		double high = totalSalary - 5000.0;
		double low = high > 0 ? (totalSalary - 3000.0 - high) : (totalSalary - 3000.0);
		double incomeTax = this.getIncomeTax(high, low);
		return totalSalary - incomeTax;
	}

	public static void main(String[] args) {
		Teacher teacher1 = new Teacher();
		Teacher teacher2 = new Teacher();
		Teacher teacher3 = new Teacher();
		teacher1.setId("100123");
		teacher1.setName("张三");
		teacher1.setBasicSalary(2888.0);
		teacher1.setPostSalary(2000.0);
		teacher1.setMeritSalary(1000.0);
		System.out.println(teacher1.toString());
		System.out.println("总工资：" + teacher1.getTotalSalary());
		System.out.println("税后工资：" + teacher1.getTakeHomeSalary());
		
		teacher2.setId("100124");
		teacher2.setName("李四");
		teacher2.setBasicSalary(2400.0);
		teacher2.setPostSalary(2000.0);
		teacher2.setMeritSalary(300.0);
		System.out.println(teacher2.toString());
		System.out.println("总工资：" + teacher2.getTotalSalary());
		System.out.println("税后工资：" + teacher2.getTakeHomeSalary());
		
		teacher3.setId("100125");
		teacher3.setName("王五");
		teacher3.setBasicSalary(2888.0);
		System.out.println(teacher3.toString());
		System.out.println("总工资：" + teacher3.getTotalSalary());
		System.out.println("税后工资：" + teacher3.getTakeHomeSalary());
	}
}
