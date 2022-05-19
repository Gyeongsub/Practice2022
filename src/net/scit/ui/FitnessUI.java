package net.scit.ui;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import net.scit.service.FitnessService;
import net.scit.service.FitnessServiceImpl;
import net.scit.vo.FitnessVO;

public class FitnessUI {
	
	FitnessService service = new FitnessServiceImpl();
	Scanner scanner = new Scanner(System.in);
	
	public FitnessUI() {
		String choice;
		while(true) {
			menu();
			choice = scanner.next();
			switch(choice) {
			case "1" : input(); break;
			case "2" : output(); break;
			case "3" : slectone(); break;
			case "4" : delete(); break;
			case "5" : update(); break;
			case "0" : System.out.println("**프로그램을 종료합니다.");
			service.setFile(); return;
			}
			scanner.nextLine();
		}
	}
	
	public void menu() {
		System.out.println("==== [관리 프로그램] ====");
		System.out.println("      1. 입 력");		//input
		System.out.println("      2. 전체 출력");	//output
		System.out.println("      3. 조 회");		//slectone
		System.out.println("      4. 삭 제");		//delete
		System.out.println("      5. 수 정");		//update
		System.out.println("      0. 종 료");
		System.out.println("----------------------");
		System.out.print("      선택>");
	}
	/**
	 * 아이디 중복확인 체크
	 * true : 중복된 아이디가 있음, false : 사용가능한 id 
	 */
	private boolean idCheck(String usrid) {
		List<FitnessVO> voList = service.findAll();
		for(int i = 0; i < voList.size(); ++i) {
			if(voList.get(i).getUsrid().equals(usrid)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 정보를 키보드로 입력해서 관리하는 메소드
	 */
	public void input() {
		// 입력하기 위한 임시변수 필요 : 지역변수
		String usrid,usrname;
		double height,weight;
		System.out.print(" > 아이디 : ");
		usrid = scanner.next();
		if(idCheck(usrid)) {   // usrid를 입력받은 후 중복을 체크하여 true가 리턴되면 되돌아간다
			System.out.println("중복된 아이디가 존재합니다.");	
			return;
		}
		System.out.print(" > 이름 : ");
		usrname = scanner.next();
		System.out.print(" > 키(cm) : ");
		height = scanner.nextDouble();
		System.out.print(" > 몸무게(Kg) : ");
		weight = scanner.nextDouble();
		
		service.regist(new FitnessVO(usrid, usrname, height, weight));
		System.out.println("**회원가입이 완료되었습니다.");
	}
	
	//회원정보출력
	public void output() {
		List<FitnessVO> voList = service.findAll();
		if(voList.isEmpty()) {
			System.out.println("** 가입한 회원이 없습니다.");
			return;
		}
		Iterator<FitnessVO> iter = voList.iterator();
		
		while(iter.hasNext())
			System.out.println(iter.next());
	}
		
	public void slectone() {
		String usrid;
		System.out.print("> 조회할 회원의 아이디 : ");
		usrid = scanner.next();
		FitnessVO vo = service.findById(usrid);
		if(vo == null) {
			System.out.println("****해당 아이디로 가입된 회원이 없습니다");
		}
		else {
			System.out.println(vo);
		}
		return;
	}
	public void delete() {
		List<FitnessVO> voList = service.findAll();
		int no = -1;
		String answer,usrid;
		if(voList.isEmpty()) {
			System.out.println("** 가입한 회원이 없습니다.");
			return;
		}	
		System.out.println("> 탈퇴할 회원의 아이디 : ");
		usrid = scanner.next();
		for(int i = 0; i < voList.size(); ++i) {
			if(voList.get(i).getUsrid().equals(usrid)) {
				no = i;
				break;
			}
		}
		if(no == -1) {
			System.out.println("**아이디를 다시 확인해주세요");
			return;
		}
		System.out.println("** 정말로 탈퇴하시겠습니까? (y/n) ");
		answer = scanner.next();
		// if(answer == 'y') why나 answer는 기초자료형이 아니기때문에 ==으로 비교할수없다. (x)
		if(answer.equals("y")) {
			service.delete(usrid);
			System.out.println("**회원탈퇴를 완료하였습니다.");
			return;
		}
		else { 
			System.out.println("**탈퇴작업이 취소되었습니다.");
		}
	}
	public void update() {
		List<FitnessVO> voList = service.findAll();
		String usrid,choice;
		double height,weight;
		if(voList.isEmpty()) {
			System.out.println("** 가입한 회원이 없습니다.");
			return;
		}
		System.out.println("**아이디를 입력해주세요 : ");
		usrid = scanner.next();
		for(int i = 0; i < voList.size(); ++i) {
		if(!voList.get(i).getUsrid().equals(usrid))	{
			System.out.println("**아이디를 다시 확인해주세요");
		}
		if(voList.get(i).getUsrid().equals(usrid)) {
				System.out.println("=== [수정 프로그램] ====");
				System.out.println("    1. 키(cm)        ");
				System.out.println("    2. 몸무게(kg)     ");
				System.out.println("    3. 취소           ");
				System.out.println("---------------------");
				System.out.print("      선택>");
				choice = scanner.next();
				
				switch(choice) {
				case "1" : System.out.print("바꾸실 키를 입력해주세요 : ");  
	   						height = scanner.nextDouble(); 
	   						voList.get(i).setHeight(height);
	   						service.update(voList.get(i));
	   						System.out.println("수정이 완료되었습니다");
	   						break;
				case "2" : System.out.print("바꾸실 몸무게를 입력해주세요 : ");  
							weight = scanner.nextDouble(); 
							voList.get(i).setWeight(weight);
							service.update(voList.get(i));
							System.out.println("**수정이 완료되었습니다");
							break;
				case "3" : System.out.println("**수정을 취소합니다.");  
							break;			
				default : System.out.println("**error)) 다시 선택해주세요.");
				
			}
		}
		}
	
	}
}
