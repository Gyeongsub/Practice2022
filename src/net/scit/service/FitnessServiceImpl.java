package net.scit.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.scit.vo.FitnessVO;


public class FitnessServiceImpl implements FitnessService {
	List<FitnessVO> list = new ArrayList<>();
	
	
	public FitnessServiceImpl() {
		
		getFile();
	}
	
	
	//파일로부터 데이터를 읽어옴
	@SuppressWarnings("unchecked")
	private void getFile() {
		File file = null;
		ObjectInputStream ois = null;
		
			
		try {
		file = new File("fitness.dat");
		
		if(!file.exists()) return;
		ois = new ObjectInputStream(new FileInputStream(file));
		
		 list = (List<FitnessVO>)ois.readObject();
		
	}catch (IOException | ClassNotFoundException e) {
		e.printStackTrace();
	}
		
	}
	@Override
	public void setFile() {
		File file = null;
		ObjectOutputStream oos = null;
		
		try {
			file = new File("fitness.dat");
			oos = new ObjectOutputStream(new FileOutputStream(file));
			
			oos.writeObject(list);
			
			oos.close();
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int regist(FitnessVO vo) {
		list.add(vo);
		return 1;
	}

	@Override
	public FitnessVO findById(String usrid) {
		FitnessVO vo = null;
		for(FitnessVO temp : list) {
			if(temp.getUsrid().equals(usrid)) {
				vo = temp; break;
			}
		}

		return vo;
	}

	@Override
	public List<FitnessVO> findAll() {

		return list;
	}

	@Override
	public int delete(String usrid) {
		int no = searchPositionOfFitness(usrid);
		return 0;
	}

	@Override
	public int update(FitnessVO vo) {
		int no = searchPositionOfFitness(vo.getUsrid());
		if(no == -1) {
			return 0;
		}
		list.set(no, vo);
		return 1;
	}
	
	private int searchPositionOfFitness(String usrid) {
		int no = -1;
		for(int i=0; i<list.size(); ++i) {
			if(list.get(i).getUsrid().equals(usrid))
				return i;
		}
			
		return -1;
	}
}
