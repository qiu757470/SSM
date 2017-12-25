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
 * 处理和部门有关的请求
 * @author Administrator
 *
 */
@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	/**
	 * 返回所有部门信息
	 * @return
	 */
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDept() {
		//查出所有部门信息
		List<Tbdept>depts=departmentService.getDept();
		return Msg.success().add("depts", depts);
		
	}
}
