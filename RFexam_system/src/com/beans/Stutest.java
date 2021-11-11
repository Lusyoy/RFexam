package com.beans;
/**
 * 学生答题表
 * @author liuyang
 *
 */
public class Stutest {
	private int id;
	private int sid;
	private int eid;
	private int  istrue;
	private int tid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getIstrue() {
		return istrue;
	}
	public void setIstrue(int istrue) {
		this.istrue = istrue;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public Stutest(int id, int sid, int eid, int istrue, int tid) {
		super();
		this.id = id;
		this.sid = sid;
		this.eid = eid;
		this.istrue = istrue;
		this.tid = tid;
	}
	public Stutest() {
		super();
	}
	
}
