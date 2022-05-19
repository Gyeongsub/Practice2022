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
			case "0" : System.out.println("**���α׷��� �����մϴ�.");
			service.setFile(); return;
			}
			scanner.nextLine();
		}
	}
	
	public void menu() {
		System.out.println("==== [���� ���α׷�] ====");
		System.out.println("      1. �� ��");		//input
		System.out.println("      2. ��ü ���");	//output
		System.out.println("      3. �� ȸ");		//slectone
		System.out.println("      4. �� ��");		//delete
		System.out.println("      5. �� ��");		//update
		System.out.println("      0. �� ��");
		System.out.println("----------------------");
		System.out.print("      ����>");
	}
	/**
	 * ���̵� �ߺ�Ȯ�� üũ
	 * true : �ߺ��� ���̵� ����, false : ��밡���� id 
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
	 * ������ Ű����� �Է��ؼ� �����ϴ� �޼ҵ�
	 */
	public void input() {
		// �Է��ϱ� ���� �ӽú��� �ʿ� : ��������
		String usrid,usrname;
		double height,weight;
		System.out.print(" > ���̵� : ");
		usrid = scanner.next();
		if(idCheck(usrid)) {   // usrid�� �Է¹��� �� �ߺ��� üũ�Ͽ� true�� ���ϵǸ� �ǵ��ư���
			System.out.println("�ߺ��� ���̵� �����մϴ�.");	
			return;
		}
		System.out.print(" > �̸� : ");
		usrname = scanner.next();
		System.out.print(" > Ű(cm) : ");
		height = scanner.nextDouble();
		System.out.print(" > ������(Kg) : ");
		weight = scanner.nextDouble();
		
		service.regist(new FitnessVO(usrid, usrname, height, weight));
		System.out.println("**ȸ�������� �Ϸ�Ǿ����ϴ�.");
	}
	
	//ȸ���������
	public void output() {
		List<FitnessVO> voList = service.findAll();
		if(voList.isEmpty()) {
			System.out.println("** ������ ȸ���� �����ϴ�.");
			return;
		}
		Iterator<FitnessVO> iter = voList.iterator();
		
		while(iter.hasNext())
			System.out.println(iter.next());
	}
		
	public void slectone() {
		String usrid;
		System.out.print("> ��ȸ�� ȸ���� ���̵� : ");
		usrid = scanner.next();
		FitnessVO vo = service.findById(usrid);
		if(vo == null) {
			System.out.println("****�ش� ���̵�� ���Ե� ȸ���� �����ϴ�");
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
			System.out.println("** ������ ȸ���� �����ϴ�.");
			return;
		}	
		System.out.println("> Ż���� ȸ���� ���̵� : ");
		usrid = scanner.next();
		for(int i = 0; i < voList.size(); ++i) {
			if(voList.get(i).getUsrid().equals(usrid)) {
				no = i;
				break;
			}
		}
		if(no == -1) {
			System.out.println("**���̵� �ٽ� Ȯ�����ּ���");
			return;
		}
		System.out.println("** ������ Ż���Ͻðڽ��ϱ�? (y/n) ");
		answer = scanner.next();
		// if(answer == 'y') why�� answer�� �����ڷ����� �ƴϱ⶧���� ==���� ���Ҽ�����. (x)
		if(answer.equals("y")) {
			service.delete(usrid);
			System.out.println("**ȸ��Ż�� �Ϸ��Ͽ����ϴ�.");
			return;
		}
		else { 
			System.out.println("**Ż���۾��� ��ҵǾ����ϴ�.");
		}
	}
	public void update() {
		List<FitnessVO> voList = service.findAll();
		String usrid,choice;
		double height,weight;
		if(voList.isEmpty()) {
			System.out.println("** ������ ȸ���� �����ϴ�.");
			return;
		}
		System.out.println("**���̵� �Է����ּ��� : ");
		usrid = scanner.next();
		for(int i = 0; i < voList.size(); ++i) {
		if(!voList.get(i).getUsrid().equals(usrid))	{
			System.out.println("**���̵� �ٽ� Ȯ�����ּ���");
		}
		if(voList.get(i).getUsrid().equals(usrid)) {
				System.out.println("=== [���� ���α׷�] ====");
				System.out.println("    1. Ű(cm)        ");
				System.out.println("    2. ������(kg)     ");
				System.out.println("    3. ���           ");
				System.out.println("---------------------");
				System.out.print("      ����>");
				choice = scanner.next();
				
				switch(choice) {
				case "1" : System.out.print("�ٲٽ� Ű�� �Է����ּ��� : ");  
	   						height = scanner.nextDouble(); 
	   						voList.get(i).setHeight(height);
	   						service.update(voList.get(i));
	   						System.out.println("������ �Ϸ�Ǿ����ϴ�");
	   						break;
				case "2" : System.out.print("�ٲٽ� �����Ը� �Է����ּ��� : ");  
							weight = scanner.nextDouble(); 
							voList.get(i).setWeight(weight);
							service.update(voList.get(i));
							System.out.println("**������ �Ϸ�Ǿ����ϴ�");
							break;
				case "3" : System.out.println("**������ ����մϴ�.");  
							break;			
				default : System.out.println("**error)) �ٽ� �������ּ���.");
				
			}
		}
		}
	
	}
}
