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

@WebServlet("/test04/board/view")
public class BoardViewServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		//Board board = AppInitServlet.boardDao.selectOne(no);
		//Board board = ContextLoaderListener.boardDao.selectOne(no);


		// BoardDao를 ServletContext 보관소에서 꺼내는 방식을 사용
		// => 단점: 위의 방식보다 코드가 늘었다.
		// => 장점: 특정 클래스에 종속되지 않는다. 유지보수에서 더 중요!
		BoardDao boardDao = (BoardDao)this.getServletContext()
				.getAttribute("boardDao");

		Board board = boardDao.selectOne(no);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");

		out.println("<head>");
		// 다른 서블릿을 실행 => 실행 후 되돌아 제어권이 되돌아 온다.
		RequestDispatcher rd = 
				request.getRequestDispatcher("/common/header");
		rd.include(request, response);
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='container'>");
		out.println("<h1>길냥이 상세정보</h1>");
		/*out.println("<tr>");
		out.println("<th>#</th><th>제품</th><th>수량</th><th>제조사</th>");
		out.println("</tr>");*/
		out.println("<form class='form-horizontal' role='form' "
				+ "action='update' method='post'>");
		out.println(" <div class='form-group'>");
		out.println(" <label for='no' class='col-sm-2 control-label'>글쓴이</label>");
		out.println(" <div class='col-sm-10'>");
		out.println("  <input type='text' class='form-control'  readonly");
		out.println("  			id='name' name='name' value='" + board.getNo() + "'>");
		out.println("  </div>");
		out.println("</div>");


		out.println("<div class='form-group'>");
		out.println("  <label for='name' class='col-sm-2 control-label'>연락처</label>");
		out.println(" <div class='col-sm-10'>");
		out.println("   <input type='text' class='form-control' ");
		out.println("    		id='tel' name='tel' value='" + "112" 	+ "'>");
		out.println("  </div>");
		out.println("  </div>");

		out.println("<div class='form-group'>");
		out.println("<label for='qty' class='col-sm-2 control-label'>발견날짜</label>");
		out.println("<div class='col-sm-10'>");
		out.println("   <input type='text' class='form-control' ");
		out.println("  			id='findDate' name='findDate' value='" +board.getFindDate()	+ "'>");
		out.println(" </div>");
		out.println(" </div>");

		out.println("<div class='form-group'>");
		out.println("<label for='mkno' class='col-sm-2 control-label'>발견장소</label>");
		out.println("<div class='col-sm-10'>");
		out.println("  <input type='text' class='form-control' ");
		out.println(" 		id='findPlace' name='findPlace' value='" + board.getFindPlace() + "'>");
		out.println(" </div>");
		out.println("</div>");
		
		out.println("<div class='form-group'>");
		out.println("<label for='mkno' class='col-sm-2 control-label'>성별</label>");
		out.println("<div class='col-sm-10'>");
		out.println("  <input type='text' class='form-control' ");
		out.println(" 		id='gender' name='gender' value='" + board.getGender() + "'>");
		out.println(" </div>");
		out.println("</div>");
		
		out.println("<div class='form-group'>");
		out.println("<label for='mkno' class='col-sm-2 control-label'>제목</label>");
		out.println("<div class='col-sm-10'>");
		out.println("  <input type='text' class='form-control' ");
		out.println(" 		id='title' name='title' value='" + board.getTitle() + "'>");
		out.println(" </div>");
		out.println("</div>");
		
		out.println("<div class='form-group'>");
		out.println("<label for='mkno' class='col-sm-2 control-label'>내용</label>");
		out.println("<div class='col-sm-10'>");
		out.println("  <input type='text' class='form-control' ");
		out.println(" 		id='content' name='content' value='" + board.getContent() + "'>");
		out.println(" </div>");
		out.println("</div>");
		
		out.println("<div class='form-group'>");
		out.println("<label for='mkno' class='col-sm-2 control-label'>사진1</label>");
		out.println("<div class='col-sm-10'>");
		out.println("  <input type='text' class='form-control' ");
		out.println(" 		id='pic1' name='pic1' value='" + "1.jpg" + "'>");
		out.println(" </div>");
		out.println("</div>");
		
		out.println("<div class='form-group'>");
		out.println("<label for='mkno' class='col-sm-2 control-label'>사진2</label>");
		out.println("<div class='col-sm-10'>");
		out.println("  <input type='text' class='form-control' ");
		out.println(" 		id='pic2' name='pic2' value='" + "2.jpg" + "'>");
		out.println(" </div>");
		out.println("</div>");
		

		out.println("<div class='form-group'>");
		out.println("	<div class='col-sm-offset-2 col-sm-10'>");
		out.println("  <button id='btnUpdate' type='submit' class='btn btn-primary'>변경</button>");
		out.println("  <button id='btnDelete' type='button' class='btn btn-primary'>삭제</button>");
		out.println("  <button id='btnCancel' type='button' class='btn btn-primary'>취소</button>");
		out.println(" </div>");
		out.println(" </div>");

		out.println("</form>");
		out.println("</div>");
		out.println("<script src='../../js/jquery-1.11.1.js'></script>");
		out.println("<script>");
		out.println("	$('#btnCancel').click(function(){");
		out.println("		history.back();");
		out.println("	});");

		out.println("$('#btnDelete').click(function(){");
		out.println("	if (confirm('삭제하시겠습니까?')){");
		out.println("		location.href = 'delete?no=" + board.getNo() + "';");
		out.println("	}");
		out.println("});");


		out.println("$('#btnUpdate').click(function() {");
		out.println("	if ($('#name').val().length == 0) {");
		out.println("		alert('글쓴이는 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
		out.println("	if ($('#tel').val().length == 0) {");
		out.println("		alert('연락처는 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
		out.println("	if ($('#findDate').val().length == 0) {");
		out.println("		alert('발견날짜는 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");

		out.println("	if ($('#findPlace').val().length == 0) {");
		out.println("		alert('발견장소는 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
		out.println("	if ($('#gender').val().length == 0) {");
		out.println("		alert('성별은 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
		out.println("	if ($('#title').val().length == 0) {");
		out.println("		alert('제목은 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
		
		out.println("	if ($('#content').val().length == 0) {");
		out.println("		alert('내용은 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
		out.println("	if ($('#pic1').val().length == 0) {");
		out.println("		alert('사진1은 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
		out.println("	if ($('#pic2').val().length == 0) {");
		out.println("		alert('사진2는 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
		out.println("});");


		out.println("</script>");
		
	// 다른 서블릿을 실행 => 실행 후 되돌아 제어권이 되돌아 온다.
			rd = 
					request.getRequestDispatcher("/common/footer");
			rd.include(request, response);
		
		out.println("</body>");
		out.println("</html>");
	}

}
