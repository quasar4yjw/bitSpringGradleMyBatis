package java02.test21.spring.exam13;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/*호출할 생성자 지정
 * 
 */
public class Test02 {
	//Car c = new Car();
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(
				new String[]{"java02/test21/spring/exam13/application-context02.xml"});
		Car2 c02 = (Car2)ctx.getBean("c02");
		System.out.println(c02);
		
		Car3 c03 = (Car3)ctx.getBean("c03");
		System.out.println(c03);
		
		Car4 c04 = (Car4)ctx.getBean("c04");
		System.out.println(c04);
		
		

	}
	
}
