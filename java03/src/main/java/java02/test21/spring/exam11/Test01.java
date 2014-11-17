package java02.test21.spring.exam11;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/*호출할 생성자 지정
 * 
 */
public class Test01 {
	//Car c = new Car();
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(
				new String[]{"java02/test21/spring/exam11/application-context.xml"});
		
		Engine e01 = (Engine)ctx.getBean("engine");
		System.out.println(e01);
		
		Engine e02 = (Engine)ctx.getBean("engine");
		System.out.println(e02);
		
		if (e01 != e02) System.out.println("e01 != e02");
	
		

	}
	
}
