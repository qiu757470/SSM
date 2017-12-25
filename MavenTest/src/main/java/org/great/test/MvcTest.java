package org.great.test;

import java.util.List;

import org.great.bean.Tbemp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;

/**
 * 
 * ʹ��Spring����ģ���ṩ�Ĳ��������ܣ�����CRUD�������ȷ��
 * Spring4���Ե�ʱ����Ҫservlet3.0��֧��
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
	//����Springmvc��ioc
	@Autowired
	WebApplicationContext context;
	//����mvc����,��ȡ��������
	MockMvc mockMvc;
	@Before
	public void initMockMvc() {
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testPage() throws Exception {
		//ģ�������õ�����ֵ
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/emps")
				.param("pn", "5")).andReturn();
		//����ɹ��Ժ��������л���PageInfo,���ǻ�ȡ��PageInfo������֤
		MockHttpServletRequest request = result.getRequest();	
		PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
		System.out.println("��ǰҳ��:"+pi.getPageNum());
		System.out.println("��ҳ��:"+pi.getPages());
		System.out.println("�ܼ�¼��:"+pi.getTotal());
		System.out.println("��ҳ����Ҫ������ʾ��ҳ��");
		int[]nums=pi.getNavigatepageNums();
		for(int i:nums) {
			System.out.println(""+i);
		}
		//��ȡԱ������
		List<Tbemp> list = pi.getList();
		for (Tbemp tbemp : list) {
			System.out.println("ID,"+tbemp.getEmpId()+"==>name"+tbemp.getEmpName());
		}
	}
}
