package com.barmiy.springjdbc.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookShopDAO")
public class BookShopDAOImpl implements BookShopDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate ;

	@Override
	public float findBookPiceByIsbn(Integer isbn) {
		String sql = "SELECT price FROM book WHERE isbn = ?" ;
		return jdbcTemplate.queryForObject(sql, Float.class,isbn);

	}

	@Override
	public void updateBookStock(Integer isbn) {
		String sql2 = "SELECT stock FROM book_stock WHERE isbn=?" ;
		int stock = jdbcTemplate.queryForObject(sql2, Integer.class,isbn);
		if(stock==0){
			throw new BookStockException("库存不足");
		}
		String sql = "UPDATE book_stock set stock=stock-1 WHERE isbn=?" ;
		jdbcTemplate.update(sql, isbn);

	}

	@Override
	public void updateUserAccount(String name, float price) {
		String sql2 ="SELECT balance FROM account WHERE username=?";
		float balance = jdbcTemplate.queryForObject(sql2, float.class,name);
		if(balance<price){
			throw new UserAccountException("余额不足");
		}
		String sql  = "UPDATE account SET balance = balance-? WHERE username=?" ;
		jdbcTemplate.update(sql, price,name);

	}

}
