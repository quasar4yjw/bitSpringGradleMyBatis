package java63.assign01.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java63.assign01.annotation.Component;
import java63.assign01.dao.ProductDao;
import java63.assign01.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@Component("common")
@WebServlet("/product/list")
public class ProductListServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		ProductDao productDao = null;
		try {
			productDao = new ProductDao();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			
		
		int pageNo = 0;
		int pageSize = 0;
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
    if (req.getParameter("pageNo") != null) {
      pageNo = Integer.parseInt(req.getParameter("pageNo"));
      pageSize = 3;
    }
    
    if (req.getParameter("pageSize") != null) {
      pageSize = Integer.parseInt(req.getParameter("pageSize"));
    }
    
    out.println("<html>");
		out.println("<bdoy>");
		
    for (Product product : productDao.selectList(pageNo, pageSize)) {
      out.printf("<h3>%-3d %-20s %7d %-3d\n<br>", 
          product.getNo(), 
          product.getName(), 
          product.getQuantity(), 
          product.getMakerNo());
			
    }
    out.println("</body>");
		out.println("</html>");
    
		
	}

}
