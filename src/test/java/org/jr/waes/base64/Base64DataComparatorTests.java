package org.jr.waes.base64;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jr.waes.dto.DiffResult;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Base64DataComparatorTests {

	private static final String MOCK_LEFT = "dGhpcyBpcyBhIHRlc3QNCg==";
	private static final String MOCK_RIGHT = "dGhpcyBpcyBhIHRlc3QNCg==";
	private static final String MOCK_RIGHT_MISSMATCH = "dGh34yBpcDBhIHRfc3QN9g==";
	private static final String BIG_BASE64 = "dGhpcyBpcyBhIHRlc3QNCgdGhpcyBpcyBhIHRlc3QNCgdGhpcyBpcyBhIHRlc3QNCgdGhpcyBpcyBhIHRlc3QNCg==";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testCompareBinariesNullData() {
		Base64DataComparator underTest = new Base64DataComparator();
		thrown.expect(IllegalArgumentException.class);
		underTest.compareBinaries(null, null);
	}

	@Test
	public void testCompareBinariesNullLeft() {
		Base64DataComparator underTest = new Base64DataComparator();
		thrown.expect(IllegalArgumentException.class);
		underTest.compareBinaries(null, MOCK_RIGHT);
	}

	@Test
	public void testCompareBinariesNullRight() {
		Base64DataComparator underTest = new Base64DataComparator();
		thrown.expect(IllegalArgumentException.class);
		underTest.compareBinaries(MOCK_LEFT, null);
	}

	@Test
	public void testCompareBinariesDifferentSize() {
		Base64DataComparator underTest = new Base64DataComparator();
		DiffResult result = underTest.compareBinaries(MOCK_LEFT, BIG_BASE64);
		assertFalse("Match must be false for different size", result.isMatch());
		assertTrue("MatchSize must be true for different size", result.isSizeMismatch());
	}

	@Test
	public void testCompareBinariesMatch() {
		Base64DataComparator underTest = new Base64DataComparator();
		DiffResult result = underTest.compareBinaries(MOCK_LEFT, MOCK_RIGHT);
		assertTrue(result.isMatch());
	}

	@Test
	public void testCompareBinariesNoMatch() {
		Base64DataComparator underTest = new Base64DataComparator();
		DiffResult result = underTest.compareBinaries(MOCK_LEFT, MOCK_RIGHT_MISSMATCH);
		assertFalse(result.isMatch());
	}
}
