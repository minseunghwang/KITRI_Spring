package lifecycle;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
		ctx.load("lifecycle/config.xml");
		System.out.println("load ����");
		
		ctx.refresh();
		System.out.println("refresh ����");
		
		
		BeanLifeCycle3 bean = ctx.getBean(BeanLifeCycle3.class);
		System.out.println("get Bean ����");
		
		System.out.println("��");
		ctx.close();
	}
}
