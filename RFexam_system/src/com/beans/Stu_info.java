package com.beans;
/**
 * 学生表
 * @author liuyang
 *
 */
public class Stu_info {
	private int id;
	private String sname;
	private int sage;
	private int classid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getSage() {
		return sage;
	}
	public void setSage(int sage) {
		this.sage = sage;
	}
	public int getClassid() {
		return classid;
	}
	public void setClassid(int classid) {
		this.classid = classid;
	}
	public Stu_info(int id, String sname, int sage, int classid) {
		super();
		this.id = id;
		this.sname = sname;
		this.sage = sage;
		this.classid = classid;
	}
	public Stu_info() {
		super();
	}
	
}
