package com.barmiy.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.barmiy.springjdbc.vo.orders;
@Repository
public class OrdersDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	public orders get(Integer id){
		String sql = "SELECT order_id orderid,quantity FROM orders WHERE order_id= ?" ;
		RowMapper <orders> rowMapper = new BeanPropertyRowMapper<>(orders.class);
		orders orders = jdbcTemplate.queryForObject(sql, rowMapper,id);
		return orders ;
	}
}
