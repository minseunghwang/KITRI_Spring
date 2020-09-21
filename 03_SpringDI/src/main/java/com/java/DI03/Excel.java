package com.java.DI03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Excel {
	
	@Autowired(required=false)
	@Qualifier("print-C")
	Print print;
	
	public Excel() {
		System.out.println("Excel ����");
	}
	
	public void excelPrint() {
		if(print != null) {
			print.print();
		} else {
			System.out.println("print�� null�Դϴ�.");
		}
	}
}
