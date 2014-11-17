package java02.test21.spring.exam09;

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
				new String[]{"java02/test21/spring/exam09/application-context.xml"});
		
		Car c1 = (Car)ctx.getBean("b01");
		System.out.println(c1);
		
		Car c2 = (Car)ctx.getBean("b02");
		System.out.println(c2);
		
		Car c3 = (Car)ctx.getBean("b03");
		System.out.println(c3);

	}
	
}
