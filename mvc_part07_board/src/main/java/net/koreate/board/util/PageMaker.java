package net.koreate.board.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageMaker {
	
	private int totalCount;
	private int startPage;
	private int endPage;
	private int maxPage;
	private int displayPageNum;
	
	private boolean first;
	private boolean last;
	private boolean prev;
	private boolean next;
	
	private Criteria cri;
	
	public PageMaker() {
		this(new Criteria(), 0);
	}
	
	public PageMaker(Criteria cri, int totalCount) {
		this.displayPageNum = 5;
		this.cri = cri;
		this.totalCount = totalCount;
		calcPaging();
	}
	
	public void setCri(Criteria cri) {
		this.cri = cri;
		calcPaging();
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcPaging();
	}
	
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
		calcPaging();
	}
	
	
	private void calcPaging() {
		endPage = (int)Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum;
		startPage = endPage - displayPageNum + 1;
		maxPage = (int)Math.ceil(totalCount / (double)cri.getPerPageNum());
		
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		first = (cri.getPage() != 1) ? true : false;
		last = (cri.getPage() != maxPage) ? true : false;
		prev = (startPage != 1) ? true : false;
		next = (endPage == maxPage) ? false : true;		
	}
	
	public String mkQueryStr(int page) {
		
//		String query = "?";
//		query += "page="+page;
//		query += "&perPageNum="+cri.getPerPageNum();
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page).queryParam("perPageNum", cri.getPerPageNum()).build();
		String query = uriComponents.toUriString();
		System.out.println(query);
		return query;
	}
}
