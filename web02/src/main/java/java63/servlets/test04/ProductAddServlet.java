package java63.servlets.test04;

import java.io.IOException;
import java.io.InputStream;

import java63.servlets.test04.dao.ProductDao;
import java63.servlets.test04.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/* POST 요청 처리
 *  => 한글이 깨지는 문제 해결
 *  => getParameter()를 호출하기 전에
 *     request.setCharacterEncoding("UTF-8") 호출하라!
 *     => 클라이언트가 보내는 데이터의 문자 집합을 알려줘라(지정하라!!!)
 */

@WebServlet("/test04/product/add")
public class ProductAddServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		// 다음 코드는 필터로 대체함.
		//request.setCharacterEncoding("UTF-8");
		
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setQuantity(Integer.parseInt(request.getParameter("qty")));
		product.setMakerNo(Integer.parseInt(request.getParameter("mkno")));
		
		
		//AppInitServlet.productDao.insert(product);
		//ContextLoaderListener.productDao.insert(product);
		
		// ProductDao를 ServletContext 보관소에서 꺼내는 방식을 사용
		// => 단점: 위의 방식보다 코드가 늘었다.
		// => 장점: 특정 클래스에 종속되지 않는다. 유지보수에서 더 중요!
		ProductDao productDao = (ProductDao)this.getServletContext()
																						.getAttribute("productDao");

		try{
		productDao.insert(product);
		} catch (Exception e) {
			/* Forward로 다른 서블릿에게 제어권 위임하기
			 * => 제어권이 넘어가면 돌아오지 않는다.
			 */
		// 다른 서블릿을 실행 => 실행 후 되돌아 제어권이 되돌아 온다.
			RequestDispatcher rd = 
					request.getRequestDispatcher("/common/error");
			request.setAttribute("error", e);
			rd.forward(request, response);
		}
		
		HttpServletResponse originResponse = (HttpServletResponse)response;
		originResponse.sendRedirect("list");
	}

}
