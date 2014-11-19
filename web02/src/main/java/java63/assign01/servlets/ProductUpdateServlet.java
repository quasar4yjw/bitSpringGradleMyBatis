package java63.assign01.servlets;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import java63.assign01.dao.ProductDao;
import java63.assign01.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/product/update")
public class ProductUpdateServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		ProductDao productDao = null;
		try {
			productDao = new ProductDao();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int no = Integer.parseInt(req.getParameter("no"));
		String name = req.getParameter("name");
		int qty = Integer.parseInt(req.getParameter("qty"));
		int mkno = Integer.parseInt(req.getParameter("mkno"));
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		
		try {
			Product product = new Product();
			product.setNo(no);
			product.setName(name);
			product.setQuantity(qty);
			product.setMakerNo(mkno);

			productDao.update(product);
			out.println("<html>");
			out.println("<bdoy>");
			out.println("<h3>변경하였습니다.</h3>");
			out.println("</body>");
			out.println("</html>");

		} catch (Exception e) {
			e.printStackTrace();
			out.println("<html>");
			out.println("<bdoy>");
			out.println("<h3>서버가 바쁩니다. 잠시 후 다시 시도하세요.</h3>");
			out.println("</body>");
			out.println("</html>");
		}

	}

}
