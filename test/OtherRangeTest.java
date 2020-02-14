package org.jfree.data;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OtherRangeTest {
	private Range exampleRange;
	private Range exampleRange2;
	private Range result;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void centralValueShouldBeZero() {
		exampleRange = new Range(-1, 1);
		assertEquals(0, exampleRange.getCentralValue(), .01d, "The central value of -1 and 1 should be 0");
	}
	
	@Test
	void centralValueShouldBeNegative() {
		exampleRange = new Range(-5, -1);
		assertEquals(-3, exampleRange.getCentralValue(), .01d, "The central value of -5 and -1 should be -3");
	}
	
	@Test
	void centralValueShouldBePositive() {
		exampleRange = new Range(1, 6);
		assertEquals(3.5, exampleRange.getCentralValue(), .01d, "The central value of 1 and 6 should be 3.5");
	}
	
	@Test
	void lengthShouldBePositive() {
		exampleRange = new Range(1, 5);
		assertEquals(4, exampleRange.getLength());
	}
	
	@Test
	void lengthShouldBeZero() {
		exampleRange = new Range(5, 5);
		assertEquals(0, exampleRange.getLength());
	}
	
	@Test
	void lengthShouldntBeNegative() {
		exampleRange = new Range(-5, 5);
		assertEquals(10, exampleRange.getLength());
	}
	
	@Test
	void positiveUpperBound() {
		exampleRange = new Range(1, 6);
		assertEquals(6, exampleRange.getUpperBound());
	}
	
	@Test
	void negativeUpperBound() {
		exampleRange = new Range(-6, -1);
		assertEquals(-1, exampleRange.getUpperBound());
	}
	
	@Test
	void UpperAndLowerBoundIsSame() {
		exampleRange = new Range(-6, -6);
		assertEquals(-6, exampleRange.getUpperBound());
	}
	
	@Test
	void UpperAndLowerBoundIsZero() {
		exampleRange = new Range(0, 0);
		assertEquals(0, exampleRange.getUpperBound());
	}
	

	@Test
	void positiveLowerBound() {
		exampleRange = new Range(1, 6);
		assertEquals(1, exampleRange.getLowerBound());
	}
	
	@Test
	void negativeLowerBound() {
		exampleRange = new Range(-6, -1);
		assertEquals(-6, exampleRange.getLowerBound());
	}
	
	@Test
	void LowerAndUpperBoundIsSame() {
		exampleRange = new Range(-6, -6);
		assertEquals(-6, exampleRange.getLowerBound());
	}
	
	@Test
	void LowerAndUpperBoundIsZero() {
		exampleRange = new Range(0, 0);
		assertEquals(0, exampleRange.getLowerBound());
	}
	
	@Test
	void expandToIncludeShouldBePositive() {
		exampleRange = new Range(1, 5); 
		exampleRange2 = new Range(1, 6);
		result = Range.expandToInclude(exampleRange, 6);
		assertEquals(exampleRange2, result);
	}
	
	@Test
	void combinePositve() {
		exampleRange = new Range(1, 10);
		exampleRange2 = new Range(1, 11);
		assertTrue(exampleRange.equals(Range.combine(exampleRange, exampleRange2)));
	}
	
	@Test
	void combineNegative() {
		exampleRange = new Range(-10, -1);
		exampleRange2 = new Range(1, 11);
		assertTrue(exampleRange.equals(Range.combine(exampleRange, exampleRange2)));
	}
	
	@Test
	void combineZero() {
		exampleRange = new Range(0, 0);
		exampleRange2 = new Range(1, 11);
		assertTrue(exampleRange.equals(Range.combine(exampleRange, exampleRange2)));
	}
	
	@Test
	void combineNull() {
		exampleRange2 = new Range(1, 11);
		assertThrows(NullPointerException.class, () -> exampleRange.equals(Range.combine(null, exampleRange2)));
	}
	
	@Test
	void containWithinUpperRange() {
		exampleRange = new Range (0,10);
		assertTrue(exampleRange.contains(10));
	}

	@Test
	void containWithinLowerRange() {
		exampleRange = new Range (0,10);
		assertTrue(exampleRange.contains(1));
	}
	
	@Test
	void containOutOfUpperRange() {
		exampleRange = new Range (0,10);
		assertFalse(exampleRange.contains(12));
	}
	
	@Test
	void containOutOfLowerRange() {
		exampleRange = new Range (0,10);
		assertFalse(exampleRange.contains(-2));
	}
	
	@Test
	void expandPositive() {
		exampleRange = new Range (1,8);
		exampleRange2 = new Range (2,6);
		assertEquals(exampleRange, Range.expand(exampleRange2, 0.25, 0.5));
	}
	
	@Test
	void expandNegative() {
		exampleRange = new Range (-8, -1);
		exampleRange2 = new Range (-6, -2);
		assertEquals(exampleRange, Range.expand(exampleRange2, 0.5, 0.25));
	}
	
	@Test
	void noExpansion() {
		exampleRange = new Range (-8, -1);
		exampleRange2 = new Range (-8, -1);
		assertEquals(exampleRange, Range.expand(exampleRange2, 0, 0));
	}

	@Test
	void checkingEqualWithinRange() {
		exampleRange = new Range (1, 5);
		exampleRange2 = new Range (1, 5);
		assertTrue(exampleRange.equals(exampleRange2));
	}
	
	@Test
	void checkingEqualOutOfRange() {
		exampleRange = new Range (1, 5);
		exampleRange2 = new Range (2, 10);
		assertFalse(exampleRange.equals(exampleRange2));
	}
}

