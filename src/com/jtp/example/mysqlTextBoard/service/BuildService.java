package com.jtp.example.mysqlTextBoard.service;

import java.util.List;

import com.jtp.example.mysqlTextBoard.Container;
import com.jtp.example.mysqlTextBoard.dto.Article;
import com.jtp.example.mysqlTextBoard.util.Util;

public class BuildService {

	private ArticleService articleService;
	private MemberService memberService;

	public BuildService() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void buildSite() {
		System.out.println("site/article 폴더 생성");
		Util.mkdirs("site/article");

		List<Article> articles = articleService.getArticles();

		for (Article article : articles) {
			StringBuilder sb = new StringBuilder();

			sb.append("<!DOCTYPE html>");
			sb.append("<html lang=\"ko\">");

			sb.append("<head>");
			sb.append("<meta charset=\"UTF-8\">");
			sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
			sb.append("<title>게시물 상세페이지 - " + article.title + "</title>");
			sb.append("</head>");

			sb.append("<body>");

			sb.append("<h1>게시물 상세페이지</h1>");

			sb.append("<div>");

			sb.append("번호 : " + article.id + "<br>");
			sb.append("작성날짜 : " + article.regDate + "<br>");
			sb.append("갱신날짜 : " + article.updateDate + "<br>");
			sb.append("제목 : " + article.title + "<br>");
			sb.append("내용 : " + article.body + "<br>");
			sb.append("<a href=\"" + (article.id - 1) + ".html\">이전글</a><br>");
			sb.append("<a href=\"" + (article.id + 1) + ".html\">다음글</a><br>");

			sb.append("</div>");

			sb.append("</body>");

			sb.append("</html>");

			String fileName = article.id + ".html";

			String filePath = "site/article/" + fileName;

			Util.writeFile(filePath, sb.toString());

			System.out.println(filePath + " 생성");
		}
	}
}