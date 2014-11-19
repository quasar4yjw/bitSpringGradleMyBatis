package java63.assign01.servlets;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import java63.assign01.annotation.Command;
import java63.assign01.annotation.Component;

@Component("common")
@WebServlet("/help")
public class HelpServlet extends GenericServlet{

	@Override
	@Command("help")
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		//PrintStream out = (PrintStream)params.get("out");
		/*out.println("list");
		out.println("view 제품번호");
		out.println("add");
		out.println("delete 제품번호");
		out.println("update 제품번호");
		out.println();
*/
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<bdoy>");
		out.println("<h3>http://localhost:8080/web02/list</h3>");
		out.println("<h3>리스트를 보여줍니다.</h3>");
		out.println("<h3>http://localhost:8080/web02/list?pageNo=3</h3>");
		out.println("<h3>리스트를 보여줍니다.</h3>");
		out.println("<h3>http://localhost:8080/web02/list?pageNo=2&pageSize=5</h3>");
		out.println("<h3>리스트를 보여줍니다.</h3>");
		out.println("<h3>http://localhost:8080/web02/add?name=O&qty=O&mkno=O</h3>");
		out.println("<h3>제품정보를 추가합니다.<h3>");
		out.println("<h3>http://localhost:8080/web02/delete?no=O</h3>");
		out.println("<h3>해당 제품코드의 제품정보를 지웁니다</h3>");
		out.println("<h3>http://localhost:8080/web02/update?no=O&name=O&qty=O&mkno=O</h3>");
		out.println("<h3>해당 제품코드의 제품정보를 업데이트합니다.</h3>");
		out.println("<h3>http://localhost:8080/web02/view?no=O</h3>");
		out.println("<h3>해당 제품코드의 제품 상세정보를 보여줍니다.</h3>");
		out.println("</body>");
		out.println("</html>");
		/*list
	list?pageNo=2&pageSize=5
	list?pageNo=3
	add?name=O&qty=O&mkno=O
	delete?no=O
	update?no=O&name=O&qty=O&mkno=O
	view?no=O
		 */
	}
}
