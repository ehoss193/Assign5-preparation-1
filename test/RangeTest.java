package org.jfree.data;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RangeTest {
	private Range exampleRange;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	@BeforeEach
	void setUp() throws Exception {
	}
	@Test
	public void rangeShouldIntersect() {
		exampleRange = new Range(-1.0, 1.0);
		assertEquals(true, exampleRange.intersects(-1, 1), "Expected range to intersect since they have the same values");
	}
	
	@Test
	public void rangeShouldNotIntersect(){
		exampleRange = new Range(-1.0, 1.0);
		assertEquals(false, exampleRange.intersects(2,  3), "Expected range to not intersect since they have no overlapping values");
	}
	
	@Test
	public void rangeShouldNotIntersectUpperBound(){
		exampleRange = new Range(-1.0, 1.0);
		assertEquals(false, exampleRange.intersects(1.0,  3.0), "Expected range should not intersect even with the same upper boundary value as the test's lower boundary value");
	}
	
	@Test
	public void rangeShouldNotIntersectLowerBound(){
		exampleRange = new Range(-1.0, 1.0);
		assertEquals(false, exampleRange.intersects(-3.0,  -1.0), "Expected range should not intersect even with the same lower boundary value as the test's upper boundary value");
	}
	
	@Test
	public void rangeShouldIntersectInternally(){
		exampleRange = new Range(-2.0, 2.0);
		assertEquals(true, exampleRange.intersects(-1.0, 1.0), "Expected range should intersect since the test range is within the example range");
	}
	
	@Test
	public void rangeShouldIntersectExternally(){
		exampleRange = new Range(-1.0, 1.0);
		assertEquals(true, exampleRange.intersects(-2.0, 2.0), "Expected range should intersect it is within the test range");
	}
	
	@Test
	public void upperBoundLessThanLowerBound(){
		exampleRange = new Range(-1.0, 1.0);
		assertEquals(false, exampleRange.intersects(1.0, -1.0), "Expected a fail because lower bound is higher than the upper bound");
	}
	
	@Test
	public void deltaValueZero(){
		exampleRange = new Range(1.0, 3.0);
		Range testRange = exampleRange;
		assertEquals(testRange, exampleRange = Range.shift(exampleRange, 0), "Expected range should not have been moved");
	}
	
	@Test
	public void deltaValueNegative(){
		exampleRange = new Range(2.0, 3.0);
		Range testRange = new Range(1.0, 2.0);
		assertEquals(testRange, exampleRange = Range.shift(exampleRange, -1), "Expected range should have been moved negatively by the delta value");
	}
	
	@Test
	public void deltaValuePositive(){
		exampleRange = new Range(2.0, 3.0);
		Range testRange = new Range(3.0, 4.0);
		assertEquals(testRange, exampleRange = Range.shift(exampleRange, 1), "Expected range should have been moved positively by the delta value");
	}
	//After looking at the code this method was fixed
	@Test
	public void deltaValueZeroCrossing(){
		exampleRange = new Range(1.0, 2.0);
		Range testRange = new Range(-1.0, 0.0);
		assertEquals(testRange, exampleRange = Range.shift(exampleRange, -2), "Expected the range to cross the zero line without a boolean constraint");
	}
	
	@Test
	public void deltaValueFalseZeroCrossingPositiveToNegative(){
		exampleRange = new Range(1.0, 3.0);
		Range testRange = new Range(0.0, 1.0);
		assertEquals(testRange, exampleRange = Range.shift(exampleRange, -2, false), "Expected range should not have crossed the zero line");
	}
	
	@Test
	public void deltaValueTrueZeroCrossingPositiveToNegative(){
		exampleRange = new Range(1.0, 3.0);
		Range testRange = new Range(-1.0, 1.0);
		assertEquals(testRange, exampleRange = Range.shift(exampleRange, -2, true), "Expected range should should have passed the zero line because the range has crossed the zero line with an affirmative boolean constraint");
	}
	
	@Test
	public void deltaValueFalseZeroCrossingNegativeToPositive(){
		exampleRange = new Range(-2.0, -1.0);
		Range testRange = new Range(0, 0.0);
		assertEquals(testRange, exampleRange = Range.shift(exampleRange, 2, true), "Expected range should not have crossed the zero line with a false boolean constraint");
	}
	
	@Test
	public void deltaValueTrueZeroCrossingNegativeToPositive(){
		exampleRange = new Range(-2.0, -1.0);
		Range testRange = new Range(0, 1.0);
		assertEquals(testRange, exampleRange = Range.shift(exampleRange, 2, true), "Expected range should have crossed the zero line with an affirmative boolean constraint");
	}
	
	@Test
	public void passingNullObjectAllowZeroCrossing() {
		Range nullRange = null;
		//Should throw an InvalidParameterException not a NullPointerException
		assertThrows(
				IllegalArgumentException.class,
				() -> Range.shift(nullRange, 2.0, true),
				"Expected IllegalArgumentException to throw"
		);
	}
	
	@Test
	public void passingNullObject() {
		Range nullRange = null;
		assertThrows( 
				IllegalArgumentException.class,
				() -> Range.shift(nullRange, 2.0),
				"Expected IllegalArgumentException to throw"
		);
	} 
	
	@Test
	public void toStringTestPositive(){
		exampleRange = new Range(1.0, 3.0);
		String expectedString = "Range[1.0,3.0]";
		assertEquals(expectedString, exampleRange.toString(), "Expected string pattern to match Range[lower,upper]");
	}
	
	@Test
	public void toStringTestZero(){
		exampleRange = new Range(0.0, 0.0);
		String expectedString = "Range[0.0,0.0]";
		assertEquals(expectedString, exampleRange.toString(), "Expected string pattern to match Range[lower,upper]");
	}
	
	@Test
	public void toStringTestNegative(){
		exampleRange = new Range(-3.0, -1.0);
		String expectedString = "Range[-3.0,-1.0]";
		assertEquals(expectedString, exampleRange.toString(), "Expected string pattern to match Range[lower,upper]");
	}
	
	@Test
	public void toStringTestCrossingZero(){
		exampleRange = new Range(-3.0, 3.0);
		String expectedString = "Range[-3.0,3.0]";
		assertEquals(expectedString, exampleRange.toString(), "Expected string pattern to match Range[lower,upper]");
	}
	//Specifically added this test to test method not covered in original tests.
	@Test
	public void shiftWithNoZeroCrossing() {
		exampleRange = new Range(0.0, 0.0);
		Range expectedRange = new Range(0.0, 0.0);
		Range.shift(exampleRange, 1.0, false);
		assertEquals(
				expectedRange,
				exampleRange,
				"Expected the shifted range to match the expected range."
				);
	}
	
	
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
}


