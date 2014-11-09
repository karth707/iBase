package com.iBase.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
		assertNull(file.getName());
		file.setName(name);
		assertEquals(name, file.getName());
	}
	
	//mock commonMultipartFile 
}
