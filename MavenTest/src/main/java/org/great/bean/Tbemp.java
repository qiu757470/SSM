package org.great.bean;

import javax.validation.constraints.Pattern;

public class Tbemp {
    private Integer empId;
    @Pattern(regexp="(^[a-z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})"
    		,message="用户名必须是2-5位中文或者6-16英文和数字的组合")
    private String empName;

    private Integer empAge;

    private String empSex;

    private Integer dId;
    //希望查询员工的同时部门信息也是查询好的
    private Tbdept tbdept;
    

    public Tbemp() {
		super();
	}

	
	public Tbemp(Integer empId, String empName, Integer empAge, String empSex, Integer dId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empAge = empAge;
		this.empSex = empSex;
		this.dId = dId;
	}


	public Tbdept getTbdept() {
		return tbdept;
	}

	public void setTbdept(Tbdept tbdept) {
		this.tbdept = tbdept;
	}

	public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex == null ? null : empSex.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
}