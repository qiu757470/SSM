package org.great.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.great.bean.Tbdept;
import org.great.bean.TbdeptExample;

public interface TbdeptMapper {
    long countByExample(TbdeptExample example);

    int deleteByExample(TbdeptExample example);

    int deleteByPrimaryKey(Integer deptId);

    int insert(Tbdept record);

    int insertSelective(Tbdept record);

    List<Tbdept> selectByExample(TbdeptExample example);

    Tbdept selectByPrimaryKey(Integer deptId);

    int updateByExampleSelective(@Param("record") Tbdept record, @Param("example") TbdeptExample example);

    int updateByExample(@Param("record") Tbdept record, @Param("example") TbdeptExample example);

    int updateByPrimaryKeySelective(Tbdept record);

    int updateByPrimaryKey(Tbdept record);
}