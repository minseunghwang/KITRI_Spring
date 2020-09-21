package lifecycle;

public class BeanLifeCycle2 {

	@PostConstruct		// 자바 8 버전에 있는거
	public void initMethod() {
		System.out.println("빈 초기화 실행");
	}
	
	@PreDestroy			// 자바 8 버전에 있는거
	public void destroyMethod() {
		System.out.println("빈 소멸 실행");
	}
}
