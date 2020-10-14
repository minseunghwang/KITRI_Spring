package com.java.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.common.ProductImageVO;
import com.java.common.ProductSizeVO;
import com.java.product.dao.ProductDao;
import com.java.product.dto.ProductDto;


@Component
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	
	
	@Override
	public void getProductAll(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		List<ProductDto> mainpageItem = productDao.selectAll();
		System.out.println(mainpageItem);
		System.out.println(mainpageItem.size());
		mav.addObject("mainpageItem",mainpageItem);
		mav.setViewName("main/main");
	}

	@Override
	public ArrayList<ProductSizeVO> getProductsSizeAll(int p_num) {
		return productDao.selectSizeAll(p_num);
	}
	
	@Override
	public ArrayList<ProductDto> getProductManagementByPageNum(int page) {
		return productDao.selectAllByPageNum(page);
	}
	
	@Override
	public ArrayList<ProductDto> getBestProducts(int numberItems) {
		return productDao.selectBestProducts(numberItems);
	}

	@Override
	public ArrayList<ProductDto> getNewProducts(int numberItems) {
		return productDao.selectNewProducts(numberItems);
	}

	@Override
	public ArrayList<ProductDto> getCategoryProducts(String category) {
		return productDao.selectCategoryProducts(category);
	}
	
	@Override
	public ArrayList<ProductDto> getCategoryProductsByPageNum(String category, int page) {
		return productDao.selectCategoryProductsByPageNum(category, page);
	}
	
	@Override
	public ArrayList<ProductDto> getCategoryProductsSort(String category, int page, String orderBy) {
		return productDao.selectCategoryProductsSort(category, page, orderBy);
	}

	@Override
	public ArrayList<ProductDto> getKeywordProductsByPageNum(String keyword, int page) {
		return productDao.selectKeywordProductsByPageNum(keyword, page);
	}

	@Override
	public ArrayList<ProductDto> getKeywordProductsSort(String keyword, int page, String orderBy) {
		return productDao.selectKeywordProductsSort(keyword, page, orderBy);
	}
	
	@Override
	public ArrayList<ProductImageVO> getDetailImgAll(int p_num) {
		return productDao.selectDetailImages(p_num);
	}

	@Override
	public int checkQuantity(int productNum, String size) {
		return productDao.selectQuantity(productNum, size);
	}
	
	@Override
	public int makeProductNum() {
		return productDao.selectProductNum();
	}

	@Override
	public int makeProductImgNum() {
		return productDao.selectProductImgNum();
	}

	@Override
	public int makeProductSizeNum() {
		return productDao.selectProductSizeNum();
	}
	
	@Override
	public void add(ProductDto p) {
		productDao.insert(p);
	}	
	
	@Override
	public void add(ProductImageVO pi) {
		productDao.insert(pi);
	}
	
	@Override
	public void add(ProductSizeVO ps) {
		productDao.insert(ps);
	}

	@Override
	public ProductDto getProduct(int num) {
		return productDao.select(num);
	}

	@Override
	public void addQuantity(ProductSizeVO ps) {
		productDao.update(ps);
	}

	@Override
	public void delProduct(int num) {
		productDao.delete(num);
	}

	@Override
	public void recordup(ProductDto productvo) {
		productDao.recordCount(productvo);
	}

}
