package java63.servlets.test04;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java63.servlets.test04.dao.BoardDao;
import java63.servlets.test04.domain.Board;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/test04/board/list")
public class BoardListServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	static final int PAGE_DEFAULT_SIZE = 5;
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		System.out.println("service() 실행 시작");

		int pageNo = 1;
		int pageSize = 5;

		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageSize = PAGE_DEFAULT_SIZE;
		}

		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}


		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		// 다른 서블릿을 실행 => 실행 후 되돌아 제어권이 되돌아 온다.
		RequestDispatcher rd = 
				request.getRequestDispatcher("/common/header");
		rd.include(request, response);
		out.println("</head>");

		out.println("<div class='container'>");
		out.println("<table class='table table-hover'>");
		out.println("<br>");
		out.println("<br>");
		out.println("<br>");

		out.println("<tr>");
		out.println("<th>#</th>");
		out.println("<th>사진</th>");
		out.println("<th>품종</th>");
		out.println("<th>제목</th>");
		out.println("<th>발견장소</th>");
		out.println("<th>발견날짜</th>");
		out.println("<th>조회</th>");
		out.println("</tr>");

		BoardDao boardDao = (BoardDao)this.getServletContext()
				.getAttribute("boardDao");
		/*for (int i = boardDao.selectList(pageNo, pageSize).size(); i > 0; i--) {
	Board board = boardDao.selectList(pageNo, pageSize).get(i-1);*/

		for(Board board : boardDao.selectList(pageNo, pageSize)){
			out.println("<tr>");
			out.println("<td>" + board.getNo() + "</td>");
			out.println("<td><a href='view?no=" + board.getNo() + "' style='background: url(http://cfile10.uf.tistory.com/image/2357144153CF4FE92847EF)'>" +" "+ "</a></td>");
			out.println("<td>" + board.getBreed() + "</td>");
			out.println("<td><a href='view?no=" + board.getNo() + "'>" + board.getTitle() + "</a></td>");
			out.println("<td>" + board.getFindPlace() + "</td>");
			out.println("<td>" + board.getFindDate() + "</td>");
			out.println("<td>" + "3" + "</td>");
			out.println("</tr>");
		}




		out.println("</table>");
		out.println("<div class='container'>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td class='customPadding'>");
		out.println("<select class='form-control'>");
		out.println("<option>품종</option>");
		out.println(" <option>코숏</option>");
		out.println("<option>페르시안</option>");
		out.println(" <option>먼치킨</option>");
		out.println(" <option>러시안 블루</option>");
		out.println("</select>");

		out.println("</td>");
		out.println("<td class='customPadding'>");
		out.println("<nav>");
		out.println("<ul class='pagination'>");
		out.println(" <li><a class='pageClass' href='#'><span aria-hidden='true'>&laquo;</span><span class='sr-only'>Previous</span></a></li>");
		out.println("  <li><a class='pageClass' href='#'><span aria-hidden='true'>&lt;</span><span class='sr-only'>Previous</span></a></li>");
		out.println("  <li><a class='pageClass' href='list?pageNo=1'>1</a></li>");
		out.println("  <li><a class='pageClass' href='list?pageNo=2'>2</a></li>");
		out.println("  <li><a class='pageClass' href='list?pageNo=3'>3</a></li>");
		out.println("  <li><a class='pageClass' href='list?pageNo=4'>4</a></li>");
		out.println("  <li><a class='pageClass' href='list?pageNo=5'>5</a></li>");
		out.println("  <li><a class='pageClass' href='#'><span aria-hidden='true'>&gt;</span><span class='sr-only'>Next</span></a></li>");
		out.println("  <li><a class='pageClass' href='#'><span aria-hidden='true'>&raquo;</span><span class='sr-only'>Next</span></a></li>");
		out.println(" </ul>");
		out.println("</nav>");

		out.println("</td>");
		out.println("<td class='customPadding'>");
		out.println("<a href='board-form.html' class='btn btn-primary'>유기묘 등록</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");

		out.println("</div>");
		out.println("	</div>");

		out.println("<script src='../../js/jquery-1.11.1.js'></script>");
		out.println("<script>");
		/*out.println("$('.pageClass').click(function(){");
		out.println("	$(this).parent().attr('class', 'active');");
		out.println("});");*/
		out.println("$('a[href=" + "list?pageNo=1"+ "]')");
		out.println(".parent().attr('class','active');");
		out.println("</script>");
		// 다른 서블릿을 실행 => 실행 후 되돌아 제어권이 되돌아 온다.
		rd = 
				request.getRequestDispatcher("/common/footer");
		rd.include(request, response);

		out.println("</body>");
		out.println("</html>");
		System.out.println("service() 실행 완료");
	}

}
