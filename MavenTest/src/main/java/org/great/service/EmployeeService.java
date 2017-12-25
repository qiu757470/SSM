package org.great.service;

import java.util.List;

import org.great.bean.Tbemp;
import org.great.bean.TbempExample;
import org.great.bean.TbempExample.Criteria;
import org.great.dao.TbempMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EmployeeService {
	@Autowired
	TbempMapper tbempMapper;
	/**
	 * ��ѯ����Ա��
	 * @return
	 */
	public List<Tbemp> getAll() {
		
		return tbempMapper.selectByExampleWithDept(null);
	}
	/**
	 * Ա������
	 * @param tbemp
	 */
	public void saveEmp(Tbemp tbemp) {
		// TODO Auto-generated method stub
		tbempMapper.insertSelective(tbemp);
	}
	/**
	 * ����Ա���û����Ƿ����
	 * @param empName
	 * @return true ����ǰ�û������ã�false��������
	 */
	public boolean checkUser(String empName) {
		// TODO Auto-generated method stub
		TbempExample tbempExample=new TbempExample();
		Criteria criteria = tbempExample.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count=tbempMapper.countByExample(tbempExample);
		return count==0;
	}
	/**
	 * ����Ա��id��ѯԱ��
	 * @param id
	 * @return
	 */
	public Tbemp getEmp(Integer id) {
		// TODO Auto-generated method stub
		Tbemp tbemp = tbempMapper.selectByPrimaryKey(id);
		return tbemp;
		
	}
	/**
	 * Ա������
	 * @param tbemp
	 */
	public void updateEmp(Tbemp tbemp) {
		// TODO Auto-generated method stub
		tbempMapper.updateByPrimaryKeySelective(tbemp);
	}
	/**
	 * Ա������ɾ��
	 * @param id
	 */
	public void deleteEmpById(Integer id) {
		// TODO Auto-generated method stub
		tbempMapper.deleteByPrimaryKey(id);
	}
	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		TbempExample tbempExample=new TbempExample();
		Criteria criteria = tbempExample.createCriteria();
		criteria.andEmpIdIn(ids);
		tbempMapper.deleteByExample(tbempExample);
	}

}
