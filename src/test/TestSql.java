package test;

import org.junit.Test;

import com.jlg.mango.service.impl.StoreServiceImple;

public class TestSql {

	@Test
	public void testSql(){
//		new UserServiceImpl().updateUserHead(12,"newName");
		System.out.println(new StoreServiceImple().searchGoodsById(32));
	}
	
}
