package net.scit.vo;

import java.io.Serializable;

public class FitnessVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String usrid;
	private String usrname;
	private double height;
	private double weight;
	private double bmi;
	private String result;

	// �⺻������
	public FitnessVO() {}

	// �����ε� ������
	public FitnessVO(String usrid, String usrname, 
			         double height, double weight) {
		this.usrid = usrid;
		this.usrname = usrname;
		this.height = height;
		this.weight = weight;
		
		calcBmi();
	}
	private void calcBmi() {
		double tmp;   // m�� ȯ���� �ӽú���
		tmp = this.height * 0.01;
		
		this.bmi = this.weight / (tmp * tmp);
		
		setResult(); //BMI���� ���� �ٷ� ����� ������
	}
	
	public double getBmi() {
		return this.bmi;
	}
	// bmi�� ���� ��������� �ܺο��� �� ������ �ϳ��� ������ �ʿ䰡 ����!
	private void setResult() {
		if(bmi >= 35) {
			result = "����";
		}
		else if(bmi >= 30) {
			result = "�ߵ���";
		}
		else if(bmi >= 25) {
			result = "�浵��";
		}
		else if(bmi >= 23) {
			result = "��ü��";
		}
		else if(bmi > 18.5) {
			result = "����";
		}
		else {
			result = "��ü��";
		}
	}
	
	public String getUsrid() {
		return usrid;
	}
	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}
	public String getUsrname() {
		return usrname;
	}
	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}
	public double getHeight() {
		return height;
	}
	// setter�� ���� ����
	public void setHeight(double height) {
		this.height = height;
		calcBmi();
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
		calcBmi();
	}
	// ����� ������ ȭ�鿡 ����ϴ� �޼ҵ�
	public void output() {
		System.out.println(usrid + ":" + usrname +"��, "+height+"cm, "
				+ weight +"Kg, BMI=" + bmi+ ", ���="+result);
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
	return	buf.append(usrid).append(":")
		   .append(usrname).append("��, ")
		   .append(height).append("cm, ")
		   .append(weight).append("kg, BMI= ")
		   .append(result).append(" , ���= ")
		   .append(result).toString();
		   
		
	}
}





