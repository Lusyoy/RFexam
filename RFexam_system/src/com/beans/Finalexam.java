package com.beans;

/**
 * 最终生成试卷
 * @author liuyang
 *
 */
public class Finalexam {
	private int id;
	private int eid;
	private int tid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public Finalexam(int id, int eid, int tid) {
		super();
		this.id = id;
		this.eid = eid;
		this.tid = tid;
	}
	public Finalexam() {
		super();
	}
	
}
