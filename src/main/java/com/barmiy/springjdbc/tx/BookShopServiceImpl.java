package com.barmiy.springjdbc.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
	@Autowired
	private BookShopDAO bookShopDAO ;
	@Transactional
	public void purchase(String name, Integer isbn) {
		//1.获取书的单价
		float price = bookShopDAO.findBookPiceByIsbn(isbn);
		//2.更新书的库存
		bookShopDAO.updateBookStock(isbn);
		//3.更新用户余额
		bookShopDAO.updateUserAccount(name, price);
	}

}
