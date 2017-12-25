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
 * ����Ա��CRUD����
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * ��������ɾ������һԱ��
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmpId(@PathVariable("ids")String ids) {
		if(ids.contains("-")) {
			List<Integer>del_ids=new ArrayList<>();
			String[] str_ids = ids.split("-");
			//��װid�ļ���
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
	 * Ա�����·���
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
	 * ����id��ѯԱ��
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
	 * ����û����Ƿ����
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName")String empName) {
		//�ж��û����Ƿ��ǺϷ��ı��ʽ
		String regx="(^[a-z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "�û���������2-5λ���Ļ���6-16Ӣ�ĺ����ֵ����");
		}
		//���ݿ��û����ظ�У��
		boolean b=employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "�û���������");
		}
		
	}
	/**
	 * Ա������
	 * 1��֧��JSR303У��
	 * 2������Hibernate-Validator
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Tbemp tbemp,BindingResult result) {
		if(result.hasErrors()) {
			//У��ʧ�ܣ�Ӧ�÷���ʧ�ܣ���ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ
			Map<String, Object>map=new HashMap<String, Object>();
			List<FieldError> list = result.getFieldErrors();
			for (FieldError fieldError : list) {
				System.out.println("������ֶ�����"+fieldError.getField());
				System.out.println("�������Ϣ��"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}
		employeeService.saveEmp(tbemp);
		return Msg.success();
		
	}
	
	/**
	 * ����jackson��
	 * @param pn
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		//�ⲻ��һ����ҳ��ѯ
				//����PageHelper��ҳ���
				//�ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ���Լ�ÿҳ�Ĵ�С
				PageHelper.startPage(pn, 5);
				//startPage��������������ѯ���Ƿ�ҳ��ѯ
				List<Tbemp>emps=employeeService.getAll();
				//ʹ��PageInfo��װ��ѯ��Ľ����ֻ��Ҫ��PageIfon����ҳ�������
				//��װ����ϸ�ķ�ҳ��Ϣ�����������ǲ�ѯ��������Ϣ,����������ʾ��ҳ��
				PageInfo info=new PageInfo(emps,5);
				return Msg.success().add("pageInfo",info);
	}
	/**
	 * 
	 * ��ѯԱ�����ݣ���ҳ��ѯ��
	 * @return
	 */
	/*@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,
			Model model) {
		//�ⲻ��һ����ҳ��ѯ
		//����PageHelper��ҳ���
		//�ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ���Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		//startPage��������������ѯ���Ƿ�ҳ��ѯ
		List<Tbemp>emps=employeeService.getAll();
		//ʹ��PageInfo��װ��ѯ��Ľ����ֻ��Ҫ��PageIfon����ҳ�������
		//��װ����ϸ�ķ�ҳ��Ϣ�����������ǲ�ѯ��������Ϣ,����������ʾ��ҳ��
		PageInfo info=new PageInfo(emps,5);
		model.addAttribute("pageInfo", info);
		return "list";
	}*/
}
