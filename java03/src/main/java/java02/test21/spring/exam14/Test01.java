package java02.test21.spring.exam14;

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
				new String[]{"java02/test21/spring/exam14/application-context.xml"});
		Car c01 = (Car)ctx.getBean("car");
		System.out.println(c01);
		
		

	}
	
}
