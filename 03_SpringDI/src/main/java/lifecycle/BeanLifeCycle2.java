package lifecycle;

public class BeanLifeCycle2 {

	@PostConstruct		// �ڹ� 8 ������ �ִ°�
	public void initMethod() {
		System.out.println("�� �ʱ�ȭ ����");
	}
	
	@PreDestroy			// �ڹ� 8 ������ �ִ°�
	public void destroyMethod() {
		System.out.println("�� �Ҹ� ����");
	}
}
