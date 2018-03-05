package org.jr.waes.base64;

import java.util.Set;
import java.util.TreeSet;

import org.jr.waes.dto.DiffResult;

import com.google.common.base.Preconditions;

/**
 * Comparator class to match diff in two base64 Strings
 * 
 * @author Jose Ramirez
 *
 */
public class Base64DataComparator extends BinaryDataComparator {

	@Override
	public DiffResult compareBinaries(final String left, final String right) {

		Preconditions.checkArgument(left != null, "left base64 String cannot be null");
		Preconditions.checkArgument(right != null, "right base64 String cannot be null");

		DiffResult result = new DiffResult(false, false, null);

		// Check length first to avoid unnecessary comparisons
		if (left.length() != right.length()) {
			result.setSizeMismatch(true);
			return result;
		} else {
			// Go through all the positions to find all the mismatchs
			char[] charsLeft = left.toCharArray();
			char[] charsRight = right.toCharArray();
			
			Set<Integer> mismatchOffsets = new TreeSet<>();

			for (int i = 0; i < left.length(); i++) {

				if (charsLeft[i] != charsRight[i]) {
					mismatchOffsets.add(i);
				}
			}
			if (mismatchOffsets.isEmpty()) {
				result.setMatch(true);
			} else {
				result.setMismatchOffsets(mismatchOffsets);
			}
		}
		return result;
	}
}
