package com.acme.craft.fixme.commons.string;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class NameValidator {

	public boolean valid(String name) {
		if (StringUtils.isNotEmpty(name)) {
			return true;
		}
		return false;
	}

	public boolean isJohn(String name) {
		String johnName = "John";
		return johnName.equals(name); // equals
	}

	public String validationMessage(String firstName, String lastName, String nick) {

		String validationMessage = String.format("Provided name is not valid. First name: ", firstName, "lastname",
				lastName, "nick", nick);

		// log.info("Provided name is not valid. First name: ",firstName,
		// "lastname", lastName, "nick", nick );

		return validationMessage;

	}

}
