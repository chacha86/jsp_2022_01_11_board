package com.cha.board.model.article;

public class Pagination {

	// 한페이지당 게시물 개수
	private int itemCntPerPage = 5;
	
	// 한 페이지블럭 당 페이지 개수
	private int pageCntPerPageBlock = 5;

	// 현재 페이지 번호
	private int currPageNo = 1;
	
	// 전체 게시물 개수
	int totalItemCount = 0;
	
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
		int startNo = pageCntPerPageBlock * (getCurrPageBlockNo() - 1) + 1;
		
		if(startNo < getStartPageNo()) {
			startNo = getStartPageNo();
		}
		return startNo;
	}
	
	public int getCurrPageBlockEndNo() {
		
		int endNo = getCurrPageBlockStartNo() + (pageCntPerPageBlock - 1);
		if(endNo > getLastPageNo()) {
			endNo = getLastPageNo();
		}
		
		return endNo;
	}
	
	public int getCurrPageNo() {
		return currPageNo;
	}
	
	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}
	
	// 1 - 0
	// 2 - 5
	// 3 - 10
	// 4 - 15 ....
	// n = 5 * (n - 1)
	// 한페이지당게시물개수 * (현재페이지 - 1) 
	public int getStartIndex() {
		return itemCntPerPage * (currPageNo - 1);
	}

	// 시작페이지 번호,
	public int getStartPageNo() {
		return 1;
	}
	
	//마지막 페이지 번호 -> 전체 게시물 개수 / 한페이지당 게시물 개수 == 전체 페이지 개수
	public int getLastPageNo() {		
		return totalItemCount / itemCntPerPage;
	}
	
	public int getItemCntPerPage() {
		return itemCntPerPage;
	}


	public void setItemCntPerPage(int itemCntPerPage) {
		this.itemCntPerPage = itemCntPerPage;
	}


	public int getPageCntPerPageBlock() {
		return pageCntPerPageBlock;
	}


	public void setPageCntPerPageBlock(int pageCntPerPageBlock) {
		this.pageCntPerPageBlock = pageCntPerPageBlock;
	}
	
}
