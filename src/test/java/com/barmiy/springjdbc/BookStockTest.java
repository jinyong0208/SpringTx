package com.barmiy.springjdbc;



import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.barmiy.springjdbc.tx.BookShopDAO;
import com.barmiy.springjdbc.tx.BookShopService;

public class BookStockTest {
	private ApplicationContext ctx = null ;
	private BookShopDAO  bookshop=null ;
	private BookShopService bookShopService =null;
	{
		ctx=new ClassPathXmlApplicationContext("jdbc.xml");
		bookshop =ctx.getBean(BookShopDAO.class);
		bookShopService=ctx.getBean(BookShopService.class);
	}
	@Test
	public void testpurchase(){
		bookShopService.purchase("AA", 1001);
	}
	

	@Test
	public void testfindBookPiceByIsbn() {
		System.out.println(bookshop.findBookPiceByIsbn(1002));
	}
	
	@Test
	public void testupdateBookStock(){
		bookshop.updateBookStock(1001);
	}
	@Test
	public void testupdateUserAccount(){
		bookshop.updateUserAccount("AA",80);
	}

}
