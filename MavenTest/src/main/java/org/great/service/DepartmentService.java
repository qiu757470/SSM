package org.great.service;

import java.util.List;

import org.great.bean.Tbdept;
import org.great.dao.TbdeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DepartmentService {
	@Autowired
	private TbdeptMapper tbdeptMapper;

	public List<Tbdept> getDept() {
		List<Tbdept> list = tbdeptMapper.selectByExample(null);
		return list;
	}

}
