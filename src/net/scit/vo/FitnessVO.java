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

	// 기본생성자
	public FitnessVO() {}

	// 오버로딩 생성자
	public FitnessVO(String usrid, String usrname, 
			         double height, double weight) {
		this.usrid = usrid;
		this.usrname = usrname;
		this.height = height;
		this.weight = weight;
		
		calcBmi();
	}
	private void calcBmi() {
		double tmp;   // m로 환산한 임시변수
		tmp = this.height * 0.01;
		
		this.bmi = this.weight / (tmp * tmp);
		
		setResult(); //BMI계산된 직후 바로 결과를 산출함
	}
	
	public double getBmi() {
		return this.bmi;
	}
	// bmi에 대한 결과값으로 외부에서 이 데이터 하나만 접근할 필요가 없다!
	private void setResult() {
		if(bmi >= 35) {
			result = "고도비만";
		}
		else if(bmi >= 30) {
			result = "중도비만";
		}
		else if(bmi >= 25) {
			result = "경도비만";
		}
		else if(bmi >= 23) {
			result = "과체중";
		}
		else if(bmi > 18.5) {
			result = "정상";
		}
		else {
			result = "저체중";
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
	// setter는 값을 수정
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
	// 멤버의 정보를 화면에 출력하는 메소드
	public void output() {
		System.out.println(usrid + ":" + usrname +"님, "+height+"cm, "
				+ weight +"Kg, BMI=" + bmi+ ", 결과="+result);
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
	return	buf.append(usrid).append(":")
		   .append(usrname).append("님, ")
		   .append(height).append("cm, ")
		   .append(weight).append("kg, BMI= ")
		   .append(result).append(" , 결과= ")
		   .append(result).toString();
		   
		
	}
}





