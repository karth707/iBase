package com.iBase.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ImageFileTest {

	private ImageFile file;
	
	@Before 
    public void initialize() throws Exception {
		file = new ImageFile();
    }
	
	@Test
	public void testSetAndGetName(){
		String name = "name";
		assertEquals("n/a",file.getName());
		file.setName(name);
		assertEquals(name, file.getName());
	}
	
	//mock commonMultipartFile 
}
