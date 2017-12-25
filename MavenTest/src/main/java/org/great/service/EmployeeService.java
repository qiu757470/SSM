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
	 * 查询所有员工
	 * @return
	 */
	public List<Tbemp> getAll() {
		
		return tbempMapper.selectByExampleWithDept(null);
	}
	/**
	 * 员工保存
	 * @param tbemp
	 */
	public void saveEmp(Tbemp tbemp) {
		// TODO Auto-generated method stub
		tbempMapper.insertSelective(tbemp);
	}
	/**
	 * 检验员工用户名是否可用
	 * @param empName
	 * @return true 代表当前用户名可用，false代表不可用
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
	 * 按照员工id查询员工
	 * @param id
	 * @return
	 */
	public Tbemp getEmp(Integer id) {
		// TODO Auto-generated method stub
		Tbemp tbemp = tbempMapper.selectByPrimaryKey(id);
		return tbemp;
		
	}
	/**
	 * 员工更新
	 * @param tbemp
	 */
	public void updateEmp(Tbemp tbemp) {
		// TODO Auto-generated method stub
		tbempMapper.updateByPrimaryKeySelective(tbemp);
	}
	/**
	 * 员工单个删除
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
