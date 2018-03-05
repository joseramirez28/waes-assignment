package org.jr.waes.controller;

import lombok.Data;

/**
 * Request Object that will hold the base64 data and can add more elements if
 * needed
 * 
 * @author Jose Ramirez
 *
 */
@Data
public class DiffRequest {

	// Base64 String with the data to be compared
	private String base64Data;
}
