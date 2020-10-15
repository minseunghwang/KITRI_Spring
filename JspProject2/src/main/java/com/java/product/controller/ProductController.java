package com.java.product.controller;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.notice.dto.NoticeDto;
import com.java.notice.service.NoticeService;
import com.java.product.service.ProductService;


@Controller
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Autowired 
	ServletContext servletContext;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private NoticeService noticeService;
	
	
//	@RequestMapping(value="/")
//	public ModelAndView testing(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("OK.");
//		ModelAndView mav = new ModelAndView("/home");
//		return mav;
//	}
	
	@RequestMapping(value="/")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		productService.getNewProducts(mav,3);
		List<NoticeDto> listnotices = noticeService.getNoticeheader(mav);
		servletContext.setAttribute("listnotices", listnotices);
		return mav;
	}
	
	@RequestMapping(value="/category.do")
	public ModelAndView categoryTop(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		productService.getCategoryProducts(mav);

		return new ModelAndView("product/categoryList");
	} 


//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
//		response.setCharacterEncoding("UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		
//		
//		MemberManageService service = new MemberManageServiceImpl(); 
//		ArrayList<ProductVO> bestProducts = service.getBestProducts(4);
//		ArrayList<ProductVO> newProducts = service.getNewProducts(3); //선정 수정 4->3
//		
//		request.setAttribute("bestProducts", bestProducts);
//		request.setAttribute("newProducts", newProducts);
//		
//		
//		reviewService.service.Service notice_service = new reviewServiceImpl.service.ServiceImpl();
//		ArrayList<NoticeVO> listnotices = notice_service.getNoticeheader();
//		
//		
//		// 메인페이지쪽만 보임
//		//request.setAttribute("notices", notices);
//		
//		// 전체 페이지 확인가능
//		ServletConfig conf = this.getServletConfig();
//		ServletContext application = conf.getServletContext();
//		application.setAttribute("listnotices", listnotices);
//
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/main/main.jsp");
//		dispatcher.forward(request, response);
//	}
}
