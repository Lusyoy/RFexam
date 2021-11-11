package com.beans;

/**
 * 最终生成卷 试题表 view
 * @author liuyang
 *
 */
public class V_test {
	private int tid;
	private int eid;
	private String  question;
	private String  ansA;
	private String  ansB;
	private String  ansC;
	private String  ansD;
	private String  ans;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnsA() {
		return ansA;
	}
	public void setAnsA(String ansA) {
		this.ansA = ansA;
	}
	public String getAnsB() {
		return ansB;
	}
	public void setAnsB(String ansB) {
		this.ansB = ansB;
	}
	public String getAnsC() {
		return ansC;
	}
	public void setAnsC(String ansC) {
		this.ansC = ansC;
	}
	public String getAnsD() {
		return ansD;
	}
	public void setAnsD(String ansD) {
		this.ansD = ansD;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public V_test(int tid, int eid, String question, String ansA, String ansB,
			String ansC, String ansD, String ans) {
		super();
		this.tid = tid;
		this.eid = eid;
		this.question = question;
		this.ansA = ansA;
		this.ansB = ansB;
		this.ansC = ansC;
		this.ansD = ansD;
		this.ans = ans;
	}
	public V_test() {
		super();
	}
	
}
