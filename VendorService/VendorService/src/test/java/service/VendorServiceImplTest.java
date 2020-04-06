package service;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.VendorServiceApplication;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.service.VendorService;


//Following Annotation is used to tell that SpringJunit is used to run the tests 
@RunWith(SpringJUnit4ClassRunner.class)

//Following Annotation is replacement of @Configuration annotation
//it is used to point to the files having the configuration and helps to load and start the context
//Context will be cached for all test cases and classes
@SpringBootTest(classes=VendorServiceApplication.class)

//Following Annotation is used to run each test case in a individual Transaction
//with default strategy as rollback, as service layer is hitting DB layer
//so changes done to database must be undone
@Transactional
public class VendorServiceImplTest {
	
	@Autowired
	private VendorService vendorService;

	@Test
	public void testGetVendorDetails() {
		Collection<VendorBean> vendorList = vendorService.getVendorDetails();
		Assert.assertTrue(vendorList != null);
	}

}
