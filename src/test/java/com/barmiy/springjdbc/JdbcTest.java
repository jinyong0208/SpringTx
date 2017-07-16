package com.barmiy.springjdbc;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.barmiy.springjdbc.vo.orders;
import com.barmiy.springjdbc.vo.products;

public class JdbcTest {
	private ApplicationContext ctx = null ;
	private JdbcTemplate jdbctemplate ;
	private OrdersDAO dao ;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;
	{
		ctx=new ClassPathXmlApplicationContext("jdbc.xml");
		jdbctemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		dao=ctx.getBean(OrdersDAO.class);
		namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class) ;
	}
	/**
	 * 使用具名参数时，可以使用	update（String Sql ,SqlParameterSource paramMap）方法进行更新操作
	 * 可以使用SqlParameterSource的实现类，直接传入对象
	 * 使用SQL语句中的参数名和类的属性一致
	 */
	@Test
	public void testnamedParameterJdbcTemplate2(){
		String sql = "INSERT INTO products (product_no,name,price)VALUES(:productno,:name,:price)" ;
		products products = new products();
		products.setProductno(004);
		products.setName("potato");
		products.setPrice(9);
		SqlParameterSource paramMap = new BeanPropertySqlParameterSource(products);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}	
	/**
	 * 可以为参数取名字
	 * 1、好处若有多个参数，则不用去对应位置，直接对应参数名，便于维护
	 * 2、较为麻烦
	 */
	@Test
	public void testnamedParameterJdbcTemplate(){
		String sql = "INSERT INTO products (product_no,name,price)VALUES(:no,:name,:price)" ;
		Map<String,Object> paramMap = new HashMap<>(); 
		paramMap.put("no", 003);
		paramMap.put("name","Carrot");
		paramMap.put("price", 18);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	/**
	 * 开发实际中经常用的方法
	 */
	@Test
	public void testOrders(){
		System.out.println(dao.get(6));
	}
	/**
	 * 执行数据统计
	 */
	@Test
	public void testQueryForObject2(){
		String sql = "SELECT COUNT (order_id) FROM orders" ;
		Long count = jdbctemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}
	/**
	 * 执行多条数据的查询
	 */
	@Test
	public void testQueryForList(){
		String sql = "SELECT order_id orderid,quantity FROM orders WHERE order_id>?" ;
		RowMapper <orders> rowMapper = new BeanPropertyRowMapper<>(orders.class);
		List<orders> orders = jdbctemplate.query(sql, rowMapper,2);
		System.out.println(orders);
	}
	/**
	 * 执行单条数据查询
	 */
	@Test
	public void testQueryForObject(){
		String sql = "SELECT order_id orderid,quantity FROM orders WHERE order_id= ?" ;
		RowMapper <orders> rowMapper = new BeanPropertyRowMapper<>(orders.class);
		orders orders = jdbctemplate.queryForObject(sql, rowMapper,6);
		System.out.println(orders);
	}
	/**
	 * 批量执行执行 update、delete insert 操作
	 */
	@Test
	public void testBatchUpdate(){	
		String sql="INSERT INTO orders(order_id,product_no,quantity)VALUES(?,?,?)";
		List<Object []> listarrgs = new ArrayList<>();
		listarrgs.add(new Object[]{5,001,78});
		listarrgs.add(new Object[]{6,001,79});
		listarrgs.add(new Object[]{7,001,73});
		listarrgs.add(new Object[]{8,002,78});
		jdbctemplate.batchUpdate(sql, listarrgs);
	}
		
	
	/**
	 * 执行 update、delete insert 操作
	 */
	@Test
	public void testUpdate(){
		String sql = "UPDATE orders SET quantity = ? WHERE order_id = ?";
		jdbctemplate.update(sql, 150,4);
	}
	/**
	 * 测试C3p0是否链接正常
	 * @throws SQLException
	 */
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
