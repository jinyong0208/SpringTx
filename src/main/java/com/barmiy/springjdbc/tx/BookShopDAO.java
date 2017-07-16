package com.barmiy.springjdbc.tx;

public interface BookShopDAO {
	//根据书号查询对应的单价
	public float findBookPiceByIsbn(Integer isbn);
	//更新书的库存使书号对应的库存-1
	public void updateBookStock(Integer isbn );
	//更新用户余额使UserBalance-price 
	public void updateUserAccount (String name, float price) ;

}
