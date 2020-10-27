package com.mycoupang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycoupang.mapper.MainMapper;
import com.mycoupang.model.ProductVO;

@Service
public class MainService {

	@Autowired
	MainMapper MainMapper;
	
	public ProductVO selectProduct(int product_no) {
		
		ProductVO pro = MainMapper.selectProduct(product_no);
		
		return pro;
	}
	
}
