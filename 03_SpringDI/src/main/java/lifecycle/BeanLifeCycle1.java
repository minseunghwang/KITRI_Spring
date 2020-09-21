package lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanLifeCycle1 implements InitializingBean,DisposableBean{
	
	@Override
	public void destroy() throws Exception{
		System.out.println("�� �Ҹ� ����");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("������");		
	}
}
