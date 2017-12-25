package org.great.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.great.bean.Tbemp;
import org.great.bean.TbempExample;

public interface TbempMapper {
    long countByExample(TbempExample example);

    int deleteByExample(TbempExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Tbemp record);

    int insertSelective(Tbemp record);

    List<Tbemp> selectByExample(TbempExample example);

    Tbemp selectByPrimaryKey(Integer empId);
    
    
    List<Tbemp> selectByExampleWithDept(TbempExample example);

    Tbemp selectByPrimaryKeyWithDept(Integer empId);
    

    int updateByExampleSelective(@Param("record") Tbemp record, @Param("example") TbempExample example);

    int updateByExample(@Param("record") Tbemp record, @Param("example") TbempExample example);

    int updateByPrimaryKeySelective(Tbemp record);

    int updateByPrimaryKey(Tbemp record);
}