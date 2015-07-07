package com.acme.craft.fixme.commons.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PropertyService {

	//inicjalizacja in-line
	//public List<String> defaultProperties() {
	//	List<String> properties = new ArrayList<>();
		
	//	properties.add("property1");
	//	properties.add("property2");
	//	properties.add("property3");
	//	properties.add("property4");

	//	return properties;
	//}
	
	public List<String> defaultProperties() {
		List<String> properties = Arrays.asList("property1", "property2", "property3", "property4");
		//return properties;
		
		properties = (List<String>) Collections.singleton("pi");
		return properties;
	}
	

	//bez null checka, spr czy kolekcja jest niepusta
	public boolean valid(List<String> properties) {
		if (properties != null && properties.size() > 0) {  //CollectionUtils.isEmpty()
			boolean isValid = true;
			for (String property : properties) {
				isValid = isValid && valid(property);
			}
		}
		return false;
	}

	private boolean valid(String property) {
		return property != null && !property.isEmpty();
	}
}
