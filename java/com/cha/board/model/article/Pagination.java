package com.cha.board.model.article;

public class Pagination {

	// 한페이지당 게시물 개수
	private int itemCntPerPage = 5;
	
	// 한 페이지블럭 당 페이지 개수
	private int pageCntPerPageBlock = 5;

	// 현재 페이지 번호
	private int currPageNo = 1;
	
	// 현재 페이지 블럭 번호 구하기
	// 현재페이지 1 ~ 5 : 1
	// 현재페이지 6 ~ 10 : 2
	// 현재페이지 11 ~ 15 : 3
	// ..
	// 1 ~ 5 / 5 : 올림(0.xxx ~ 1.0) == 1
	// 6 ~ 10 / 5 : 올림(1.xxx ~ 2.0) == 2
	// 11 ~ 15 / 5 : 올림(2.xxx ~ 3.0) == 3
	

	
	public int getCurrPageBlockNo() {
		double result1 = (double)currPageNo / pageCntPerPageBlock; 
		double result2 = Math.ceil(result1);
		
		return (int)result2 ;
	}

	
	// 블럭이 정해지면, 그 블럭의 시작, 끝
	// 1 - 1 ~ 5
	// 2 - 6 ~ 10
	// 3 - 11 ~ 15 ...
	// 한블럭당페이지개수 * (현재페이지블럭번호 - 1) + 1
	public int getCurrPageBlockStartNo() {
		return pageCntPerPageBlock * (getCurrPageBlockNo() - 1) + 1;
	}
	
	public int getCurrPageBlockEndNo() {
		return getCurrPageBlockStartNo() + (pageCntPerPageBlock - 1);
	}
	
	public int getCurrPageNo() {
		return currPageNo;
	}
	
	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}
	
}
