package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
	public static String getPageArea(String url, int curPage, int totPage, int pageRange) {
		//페이지 범위
		int startPage = (curPage-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange -1; //종료페이지
		if (endPage > totPage) endPage = totPage;
		
		
		String ret = "";
			ret += " <div class=\"pagenate clear\">\r\n"
			+ "                        <ul class='paging'>\r\n";
			if(startPage > pageRange) {
				ret += "                       <li><a href=\""+url+"?page="+(startPage-1)+"\"> < </a>\r\n";
			}
			for (int rp = startPage; rp <= endPage; rp++) {
				ret += "                            <li><a href='javascript:location.href=\""+url+"?page="+rp+"\";'";
			if (rp == curPage) ret += "class='current'";
				ret += ">" +rp+"</a></li>\r\n";
			}
			if(totPage > endPage) {
				ret += "                        	<li><a href=\""+url+"?page="+(endPage+1)+"\"> > </a>\r\n";
			}
				ret += "                        </ul> \r\n"
			+ "                    </div>";
			return ret;
	}
	public static String getPageAreaCount(String url, int curPage, int totPage, int pageRange, int pageCount) {
		//페이지 범위
		int startPage = (curPage-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange -1; //종료페이지
		if (endPage > totPage) endPage = totPage;
		
		
		String ret = "";
			ret += " <div class=\"pagenate clear\">\r\n"
			+ "                        <ul class='paging'>\r\n";
			if(startPage > pageRange) {
				ret += "                       <li><a href=\""+url+"?page="+(startPage-1)+"\"> < </a>\r\n";
			}
			for (int rp = startPage; rp <= endPage; rp++) {
				ret += "                            <li><a href='javascript:location.href=\""+url+"?page="+rp+"&pageCount="+pageCount+"\";'";
			if (rp == curPage) ret += "class='current'";
				ret += ">" +rp+"</a></li>\r\n";
			}
			if(totPage > endPage) {
				ret += "                        	<li><a href=\""+url+"?page="+(endPage+1)+"\"> > </a>\r\n";
			}
				ret += "                        </ul> \r\n"
			+ "                    </div>";
			return ret;
	}
	public static String getPageArea(String url, int curPage, int totPage, int pageRange, String param) {
		//페이지 범위
		int startPage = (curPage-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange -1; //종료페이지
		if (endPage > totPage) endPage = totPage;
		
		
		String ret = "";
		ret += " <div class=\"pagenate clear\">\r\n"
				+ "                        <ul class='paging'>\r\n";
		if(startPage > pageRange) {
			ret += "                       <li><a href=\""+url+"?page="+(startPage-1)+param+"\"> < </a>\r\n";
		}
		for (int rp = startPage; rp <= endPage; rp++) {
			ret += "                            <li><a href='javascript:location.href=\""+url+"?page="+rp+param+"\";'";
			if (rp == curPage) ret += "class='current'";
			ret += ">" +rp+"</a></li>\r\n";
		}
		if(totPage > endPage) {
			ret += "                        	<li><a href=\""+url+"?page="+(endPage+1)+param+"\"> > </a>\r\n";
		}
		ret += "                        </ul> \r\n"
				+ "                    </div>";
		return ret;
	}
	


	public static String getYoutubeImage(String url) {
		String img = "";
		if (url != null && !"".equals(url)) {
			img = "https://img.youtube.com/vi"+url.substring(url.lastIndexOf("/"))+"/0.jpg";
		}
		return img;
	}
	
	public static String getImgUrl(String content) {
		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img 태그 src 추출 정규표현식
		Matcher matcher = pattern.matcher(content);
		String url = "";
		while(matcher.find()){
			url = matcher.group(1);
			break;
        }
		return url;
	}
	
	public static void main(String[] args) {
		System.out.println(getYoutubeImage("https://www.youtube.com/embed/duC1HoAGo-8"));
		System.out.println(getImgUrl("asdfasdf <html> <br> <img src='aaa'></html>"));
	}
}
