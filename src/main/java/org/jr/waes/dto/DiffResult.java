package org.jr.waes.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response object that holds all related data shown to the user after
 * requesting a difference operation
 * 
 * @author Jose Ramirez
 *
 */
@Data
@AllArgsConstructor
public class DiffResult {

	private boolean match;

	private boolean sizeMismatch;

	private Set<Integer> mismatchOffsets;
}
