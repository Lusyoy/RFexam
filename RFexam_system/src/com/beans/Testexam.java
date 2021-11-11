package com.beans;
/**
 * 试卷名称表
 * @author liuyang
 *
 */
public class Testexam {
	private int id;
	private String examname;
	private int classid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExamname() {
		return examname;
	}
	public void setExamname(String examname) {
		this.examname = examname;
	}
	public int getClassid() {
		return classid;
	}
	public void setClassid(int classid) {
		this.classid = classid;
	}
	public Testexam(int id, String examname, int classid) {
		super();
		this.id = id;
		this.examname = examname;
		this.classid = classid;
	}
	public Testexam() {
		super();
	}
	
}
