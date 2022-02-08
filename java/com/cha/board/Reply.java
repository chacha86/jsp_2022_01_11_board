package com.cha.board;

public class Reply {

	private int idx;
	private int parentIdx;
	private String body;
	private int memberIdx;
	private String nickname;
	private String regDate;

	public Reply(int idx, int parentIdx, String body, int memberIdx, String nickname, String regDate) {
		super();
		this.idx = idx;
		this.parentIdx = parentIdx;
		this.body = body;
		this.memberIdx = memberIdx;
		this.nickname = nickname;
		this.regDate = regDate;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getParentIdx() {
		return parentIdx;
	}

	public void setParentIdx(int parentIdx) {
		this.parentIdx = parentIdx;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	
	
}
