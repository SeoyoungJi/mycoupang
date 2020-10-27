package com.mycoupang.model;

public class ProductVO {

	private int PRODUCT_NO;
	private int FK_CATE_NO;
	private String PRODUCT_BRAND;	
	private String PRODUCT_NAME;
	private int PRODUCT_PRICE;
	private String PRODUCT_IMAGE1;
	private String PRODUCT_IMAGE2;
	private String PRODUCT_SELLER;
	private int FK_PRODUCT_DELIVERY;
	
	public ProductVO() { }

	public int getPRODUCT_NO() {
		return PRODUCT_NO;
	}

	public void setPRODUCT_NO(int pRODUCT_NO) {
		PRODUCT_NO = pRODUCT_NO;
	}

	public int getFK_CATE_NO() {
		return FK_CATE_NO;
	}

	public void setFK_CATE_NO(int fK_CATE_NO) {
		FK_CATE_NO = fK_CATE_NO;
	}

	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}

	public void setPRODUCT_NAME(String pRODUCT_NAME) {
		PRODUCT_NAME = pRODUCT_NAME;
	}

	public String getPRODUCT_BRAND() {
		return PRODUCT_BRAND;
	}

	public void setPRODUCT_BRAND(String pRODUCT_BRAND) {
		PRODUCT_BRAND = pRODUCT_BRAND;
	}

	public int getPRODUCT_PRICE() {
		return PRODUCT_PRICE;
	}

	public void setPRODUCT_PRICE(int pRODUCT_PRICE) {
		PRODUCT_PRICE = pRODUCT_PRICE;
	}

	public String getPRODUCT_IMAGE1() {
		return PRODUCT_IMAGE1;
	}

	public void setPRODUCT_IMAGE1(String pRODUCT_IMAGE1) {
		PRODUCT_IMAGE1 = pRODUCT_IMAGE1;
	}

	public String getPRODUCT_IMAGE2() {
		return PRODUCT_IMAGE2;
	}

	public void setPRODUCT_IMAGE2(String pRODUCT_IMAGE2) {
		PRODUCT_IMAGE2 = pRODUCT_IMAGE2;
	}

	public String getPRODUCT_SELLER() {
		return PRODUCT_SELLER;
	}

	public void setPRODUCT_SELLER(String pRODUCT_SELLER) {
		PRODUCT_SELLER = pRODUCT_SELLER;
	}

	public int getFK_PRODUCT_DELIVERY() {
		return FK_PRODUCT_DELIVERY;
	}

	public void setFK_PRODUCT_DELIVERY(int fK_PRODUCT_DELIVERY) {
		FK_PRODUCT_DELIVERY = fK_PRODUCT_DELIVERY;
	}
	
	

}
