package org.jr.waes.base64;

import org.jr.waes.dto.DiffResult;

/**
 * Base class for Binary data comparators, in case we want to add other
 * implementations
 * 
 * @author Jose Ramirez
 *
 */
public abstract class BinaryDataComparator {

	/**
	 * Compare base64 strings, check size, and mismatching offsets
	 * 
	 * @param left
	 *            base64 String
	 * @param right
	 *            base64 String
	 * @return DiffResult object with the comparison results
	 */
	abstract DiffResult compareBinaries(final String left, final String right);
}
