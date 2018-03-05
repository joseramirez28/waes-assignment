package org.jr.waes.dto;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

/**
 * Data Object for MongoDB
 * 
 * @author Jose Ramirez
 *
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BinaryDataComparison {

	@Id
	private Integer id;

	private String left;

	private String right;

	private boolean compared;

	private boolean equal;
}
