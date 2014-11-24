package java63.servlets.test04;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/* 공통으로 사용하는 자원은 보통 ServletContext에 보관한다.
 *  
 *  ServletContext => 웹 애플리케이션 시작 시 생성됨. 종료하면 제거됨.
 *  HttpSession => 최초 요청 시 생성. 타임아웃 또는 무효화 명령시 제거됨.
 *  HttpServletRequest => 요청 때마다 생성. 응답 후 제거됨.
 *  PageContext => JSP에서 사용됨.
 *  		각 JSP 실행할 때마다 생성되고 실행 완료되면 제거됨.
 * 
 */

//@WebListener
public class ContextLoaderListener implements ServletContextListener {
	static ApplicationContext appCtx;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		

	 	appCtx = 
				new ClassPathXmlApplicationContext(
				new String[]{"java63/servlets/test04/application-context.xml"});
	 	
		//ServletContext 보관소에 객체 저장!
		
		
		/*BoardDao boardDao = new BoardDao();
		boardDao.setSqlSessionFactory(sqlSessionFactory);

		//ServletContext 보관소에 객체 저장!
		ctx.setAttribute("boardDao", boardDao);*/
	}

	/* 웹 애플리케이션이 종료할 때 호출됨
	 * => 서블릿이 사용한 자원을 해제할 때
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
