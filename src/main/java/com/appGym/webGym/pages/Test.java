package com.appGym.webGym.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;

public class Test {
	@Property
    @Persist
    @Validate("required,minlength=5")
    private String name;
	
	@Property
	private String value;
	
	@Property
	@Persist
	private List<String> listOfNames;
	
	public void onActivate(){
		if(listOfNames==null) listOfNames = new ArrayList<String>();
	}
	
    void onSelectedFromSend() 
    { 
    	listOfNames.add(name);
    }
}
