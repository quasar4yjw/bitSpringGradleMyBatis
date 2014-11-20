package java63.servlets.test02;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java63.servlets.test02.dao.ProductDao;
import java63.servlets.test02.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//@WebServlet("/test02/product/delete")
public class ProductDeleteServlet01 extends GenericServlet{
	private static final long serialVersionUID = 1L;

	SqlSessionFactory sqlSessionFactory;
	ProductDao productDao;

	public ProductDeleteServlet01() {
		String resource = "java63/servlets/test02/dao/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);

		productDao = new ProductDao();
		productDao.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		productDao.delete(no);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet'");
		out.println("			 href='../../css/bootstrap.min.css'>");
		out.println("<link rel='stylesheet'");
		out.println("			 href='../../css/bootstrap-theme.min.css'>");
		out.println("<link rel='stylesheet'");
		out.println("			 href='../../css/common.css'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='container'>");
		out.println("<h1>삭제 결과</h1>");
		out.println("<p>삭제하였습니다.</p>");
		
  	out.println("</div>");
  	out.println("<script>");
  	out.println("setTimeout(function(){");
  	out.println("	location.href = 'list';");
  	out.println("}, 1000);");
  	
  	
  	out.println("</script>");
  	out.println("</body>");
		out.println("</html>");
	}

}
