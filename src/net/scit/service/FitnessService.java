package net.scit.service;

import java.util.List;

import net.scit.vo.FitnessVO;

public interface FitnessService {
	//���
	public int regist(FitnessVO vo);
	
	//���̵�� ��ȸ
	public FitnessVO findById(String usrid);
	
	//��ü ������ ��ȸ
	public List<FitnessVO> findAll();
	
	//����
	public int delete(String usrid);
	
	//����
	public int update(FitnessVO vo);
	
	public void setFile();
	
	//���� ����(setFile: ����� ��������, getFile: ������ ó����)
	
	
}
