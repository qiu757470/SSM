package org.great.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.great.bean.Tbdept;
import org.great.bean.Tbemp;
import org.great.dao.TbdeptMapper;
import org.great.dao.TbempMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * 测试dao层的工作
 * @author Administrator
 *1.导入SpringTest模块
 *2.@ContextConfiguration指定Spring配置文件的位置
 *3.直接autowired要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MaaperTest {
	@Autowired
	TbdeptMapper tbdeptMapper;
	@Autowired
	TbempMapper tbempMapper;
	@Autowired
	SqlSession sqlSession;
	/**
	 * 测试TbdeptMapper
	 */
	@Test
	public void testCRUD() {
		//1.创建Springioc容器
//		ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
//		//2.从容器中获取mapper
//		TbdeptMapper bean=ioc.getBean(TbdeptMapper.class);
		System.out.println(tbdeptMapper);
		//1.插入几个部门
//		tbdeptMapper.insertSelective(new Tbdept(null, "开发部"));
//		tbdeptMapper.insertSelective(new Tbdept(null, "测试部"));
//		tbdeptMapper.insertSelective(new Tbdept(null, "人事部"));
		//2.生成员工数据，测试员工插入
		//tbempMapper.insertSelective(new Tbemp(null, "张三", 11, "男", 1));
		//3.批量插入多个员工，可以使用批量操作的sqlSession
		TbempMapper empMapper=sqlSession.getMapper(TbempMapper.class);
		for(int i=0;i<1000;i++) {
			String uid=UUID.randomUUID().toString().substring(0,5)+i;
			empMapper.insertSelective(new Tbemp(null, uid, 20, "男", 1));
		}
		System.out.println("批量完成");
	}
	@Test
	public void testAdd() {
		System.out.println("111111");
	}
}
