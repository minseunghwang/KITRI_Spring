package com.java.di01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appCTX.xml");
		Su su = (Su) ctx.getBean("su");		// ���⿡ �� appCTX.xml�� id���� ���±�("su")
		su.disp();
		
		ctx.close();		// �� ���̴� �ݵ�� �ݾ���� �Ѵ�.
			
	}	
}


// Ŭ������ �� 40�� �� �װ� �� new ������ ���ŷο� -> �׷��� .xml�� �� �־���ٰ� �̷������� �ҷ��� ����ϱ� ���� ������ ����