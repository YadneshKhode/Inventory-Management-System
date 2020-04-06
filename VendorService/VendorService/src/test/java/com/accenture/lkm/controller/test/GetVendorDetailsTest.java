package com.accenture.lkm.controller.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accenture.lkm.VendorServiceApplication;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.controller.VendorController;
import com.accenture.lkm.custom.test.utils.JSONUtils;
import com.accenture.lkm.service.VendorService;


//Following Annotation is used to tell that SpringJunit is used to run the tests
@RunWith(SpringJUnit4ClassRunner.class)

//Following Annotation is replacement of @Configuration annotation
//it is used to point to the files having the configuration and helps to load and start the context
//Context will be cached for all test cases and classes
@SpringBootTest(classes=VendorServiceApplication.class)
//No @Transactional Required as database is never hit
public class GetVendorDetailsTest {

	//Step1: Step1: Declare Service Layer Mocks and inject to Controller
	@Mock
	VendorService vendorService;
	// this @mock annotation, instructs the mockito
	// to analyze the class or interface and produce 
	// a test stub (empty methods) with same public methods and signatures
	
	//Step1: Declare Service Layer Mocks and inject to Controller
	@InjectMocks
	VendorController controller;
	// this annotation, tells mockito to inject mocked objects
	// into the VendorController
	// in our case the vendorService will be supplied to VendorController
    protected MockMvc mockMVC;
    @Before
    public void mySetup(){ 	
	    //It is done to initialize mockito annotations for mocking
	    //prepare the objects for testing
    	MockitoAnnotations.initMocks(this);
    	//Step2:  Using Use MockMvcBuilders to create a MockMvc which is replica just of Controller
    	mockMVC = MockMvcBuilders.standaloneSetup(controller).build();
	}
    
    @Test
    public void getAllEmployeesTest() throws Exception{
    	  String uri="/vendor/controller/getVendors";
    	  
    	  //Step3: Use MockHttpServletRequestBuilder to create a request
    	  MockHttpServletRequestBuilder request= MockMvcRequestBuilders.get(uri);
    	  
    	  //Step4: Define the Mocking call for the mocked object created in Step1 and provided to controller 
    	  when(vendorService.getVendorDetails()).thenReturn(getVendorStubData());
    	  
    	  //Step5: MockMvc created in Step2 will perform() the request created in Step3 and will yield MvcResult
    	  ResultActions rest= mockMVC.perform(request);
    	  MvcResult mvcREsult= rest.andReturn();
		  
    	  //Step6: Extract the actual response content and response status from MvcResult obtained above 
    	  //and compare with the expected response content and expected response status
		  String result= mvcREsult.getResponse().getContentAsString();
		  int actualStatus= mvcREsult.getResponse().getStatus();
    	 
    	  List<VendorBean> listVendor= JSONUtils.covertFromJsonToObject2(result, List.class);
    	  
    	  //Step7: Verify if the Controller is able to delegate the call to the mock
		   verify(vendorService,times(1)).getVendorDetails();
    	  
    	  Assert.assertTrue(listVendor!=null);
    	  Assert.assertTrue(actualStatus==HttpStatus.OK.value());
    }
    
    
   public List<VendorBean> getVendorStubData(){
		return Arrays.asList(new VendorBean("V001", "Only Vimal","Delhi","John",1234567890),new VendorBean("V002", "PRR Enterprise","Mumbai","Sam",999567890),
							 new VendorBean("V003", "SRS Enterprise","Delhi","Peter",884567890),new VendorBean("V004", "Only John","Kolkata","Mario",987567890));
	}
    


}
