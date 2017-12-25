package org.great.controller;

import java.util.List;

import org.great.bean.Msg;
import org.great.bean.Tbdept;
import org.great.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ����Ͳ����йص�����
 * @author Administrator
 *
 */
@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	/**
	 * �������в�����Ϣ
	 * @return
	 */
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDept() {
		//������в�����Ϣ
		List<Tbdept>depts=departmentService.getDept();
		return Msg.success().add("depts", depts);
		
	}
}
