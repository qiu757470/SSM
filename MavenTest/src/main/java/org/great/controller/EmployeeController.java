package org.great.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.great.bean.Msg;
import org.great.bean.Tbemp;
import org.great.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
 * 处理员工CRUD请求
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * 单个批量删除二合一员工
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmpId(@PathVariable("ids")String ids) {
		if(ids.contains("-")) {
			List<Integer>del_ids=new ArrayList<>();
			String[] str_ids = ids.split("-");
			//组装id的集合
			for (String str : str_ids) {
				del_ids.add(Integer.parseInt(str));
			}
			employeeService.deleteBatch(del_ids);
		}else {
			Integer id=Integer.parseInt(ids);
			employeeService.deleteEmpById(id);
		}
		
		return Msg.success();
	}
	
	/**
	 * 员工更新方法
	 * @param tbemp
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{id}",method=RequestMethod.PUT)
	public Msg saveEmp(Tbemp tbemp) {
		employeeService.updateEmp(tbemp);
		return null;
		
	}
	
	
	/**
	 * 根据id查询员工
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	public Msg getEmp(@PathVariable("id") Integer id) {
		Tbemp tbemp=employeeService.getEmp(id);
		return Msg.success().add("emp", tbemp);
		
	}
	
	
	
	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName")String empName) {
		//判断用户名是否是合法的表达式
		String regx="(^[a-z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "用户名必须是2-5位中文或者6-16英文和数字的组合");
		}
		//数据库用户名重复校验
		boolean b=employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "用户名不可用");
		}
		
	}
	/**
	 * 员工保存
	 * 1、支持JSR303校验
	 * 2、导入Hibernate-Validator
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Tbemp tbemp,BindingResult result) {
		if(result.hasErrors()) {
			//校验失败，应该返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object>map=new HashMap<String, Object>();
			List<FieldError> list = result.getFieldErrors();
			for (FieldError fieldError : list) {
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误的信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}
		employeeService.saveEmp(tbemp);
		return Msg.success();
		
	}
	
	/**
	 * 导入jackson包
	 * @param pn
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		//这不是一个分页查询
				//引入PageHelper分页插件
				//在查询之前只需要调用,传入页码以及每页的大小
				PageHelper.startPage(pn, 5);
				//startPage后面紧跟的这个查询就是分页查询
				List<Tbemp>emps=employeeService.getAll();
				//使用PageInfo包装查询后的结果，只需要将PageIfon交给页面就行了
				//封装了详细的分页信息，包括有我们查询出来的信息,传入连续显示的页数
				PageInfo info=new PageInfo(emps,5);
				return Msg.success().add("pageInfo",info);
	}
	/**
	 * 
	 * 查询员工数据（分页查询）
	 * @return
	 */
	/*@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,
			Model model) {
		//这不是一个分页查询
		//引入PageHelper分页插件
		//在查询之前只需要调用,传入页码以及每页的大小
		PageHelper.startPage(pn, 5);
		//startPage后面紧跟的这个查询就是分页查询
		List<Tbemp>emps=employeeService.getAll();
		//使用PageInfo包装查询后的结果，只需要将PageIfon交给页面就行了
		//封装了详细的分页信息，包括有我们查询出来的信息,传入连续显示的页数
		PageInfo info=new PageInfo(emps,5);
		model.addAttribute("pageInfo", info);
		return "list";
	}*/
}
