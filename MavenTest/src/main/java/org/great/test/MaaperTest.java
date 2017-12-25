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
 * ����dao��Ĺ���
 * @author Administrator
 *1.����SpringTestģ��
 *2.@ContextConfigurationָ��Spring�����ļ���λ��
 *3.ֱ��autowiredҪʹ�õ��������
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
	 * ����TbdeptMapper
	 */
	@Test
	public void testCRUD() {
		//1.����Springioc����
//		ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
//		//2.�������л�ȡmapper
//		TbdeptMapper bean=ioc.getBean(TbdeptMapper.class);
		System.out.println(tbdeptMapper);
		//1.���뼸������
//		tbdeptMapper.insertSelective(new Tbdept(null, "������"));
//		tbdeptMapper.insertSelective(new Tbdept(null, "���Բ�"));
//		tbdeptMapper.insertSelective(new Tbdept(null, "���²�"));
		//2.����Ա�����ݣ�����Ա������
		//tbempMapper.insertSelective(new Tbemp(null, "����", 11, "��", 1));
		//3.����������Ա��������ʹ������������sqlSession
		TbempMapper empMapper=sqlSession.getMapper(TbempMapper.class);
		for(int i=0;i<1000;i++) {
			String uid=UUID.randomUUID().toString().substring(0,5)+i;
			empMapper.insertSelective(new Tbemp(null, uid, 20, "��", 1));
		}
		System.out.println("�������");
	}
	@Test
	public void testAdd() {
		System.out.println("111111");
	}
}
