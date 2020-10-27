package com.mycoupang.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mycoupang.model.ProductVO;

@Mapper
public interface MainMapper {

	ProductVO selectProduct(int product_no);
}
