package java63.servlets.test05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java63.servlets.test05.dao.ProductDao;
import java63.servlets.test05.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/test05/product/list")
public class ProductListServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	static final int PAGE_DEFAULT_SIZE = 3;
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {


		int pageNo = 0;
		int pageSize = 0;

		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageSize = PAGE_DEFAULT_SIZE;
		}

		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
	/*	response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();*/

		
		ApplicationContext appCtx =
				WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());

		ProductDao productDao = (ProductDao)appCtx.getBean("productDao");
		
		List<Product> products = productDao.selectList(pageNo, pageSize);
		request.setAttribute("products", products);
		
		// include를 수행할 때는 여기에서 콘텐츠 타입을 설정해야 한다.
		response.setContentType("text/html;charset=UTF-8");
		
		// 결과를 출력하기 위해 JSP에게 위임한다.
		
		// 다른 서블릿을 실행 => 실행 후 되돌아 제어권이 되돌아 온다.
		RequestDispatcher rd = 
				request.getRequestDispatcher("/test05/product/ProductList.jsp");
		rd.include(request, response);

	}

}
