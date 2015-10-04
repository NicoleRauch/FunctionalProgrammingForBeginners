

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Test;


public class ExamplesTest {

	
	@Test
	public void lambdasAreFirstOrderCitizens() {
		IntBinaryOperator times = (x, y) -> x * y;
		IntBinaryOperator timesVar = times;
		assertThat(timesVar.applyAsInt(3, 5), is(15));
	}
	
	@Test
	public void lambdasAreFirstOrderCitizens_withSelfDefinedInterface() {
		TimesFunction times = (x, y) -> x * y;
		TimesFunction timesVar = times;
		assertThat(timesVar.eval(3, 5), is(15));
	}
	
	@Test
	public void staticMethodsAreFirstOrderCitizens() {
		IntBinaryOperator timesVar = Examples::staticTimes;
		assertThat(timesVar.applyAsInt(3, 5), is(15));
	}
	
	@Test
	public void nonstaticMethodsFirstOrderCitizens() {
		Examples examples = new Examples();
		IntBinaryOperator timesVar = examples::times;
		assertThat(timesVar.applyAsInt(3, 5), is(15));
	}
	
	@Test
	public void functionsCanBePassedAsFunctionArguments() {
		IntUnaryOperator func = y -> 3 * y;
		
		assertThat(Examples.apply(func, 5), is(15));
		assertThat(Examples.apply(Examples::staticFunc, 5), is(15));
		Examples examples = new Examples();
		assertThat(Examples.apply(examples::func, 5), is(15));
	}

	@Test
	public void functionsCanBeReturnedByFunctions() {
		FunctionFunction times = x -> { return y -> x * y; };
		assertThat(times.eval(3).applyAsInt(5), is(15));
	}
	
	@Test
	public void filter() {
		assertThat(asList(1, 2, 3, 4).stream().filter(x -> x % 2 == 0).toArray(), is(new Integer[]{2, 4}));
	}
	
	@Test
	public void map() {
		assertThat(asList(1, 2, 3, 4).stream().map(x -> x + 5).toArray(), is(new Integer[]{6, 7, 8, 9}));
	}
	
	@Test
	public void fold() {
		assertThat(asList(2, 3, 4, 5).stream().reduce(1, (x, y) -> x*y), is(120));
	}
	
	@Test
	public void sequenceOfNumbers() throws Exception {
		IntStream sequence = IntStream.range(1, 11);
		assertThat(sequence.toArray(), is(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
	}
	
	@Test
	public void squaredSequenceOfNumbers() throws Exception {
		IntStream sequence = IntStream.rangeClosed(1, 10); // or IntStream.range(1, 11);
		IntUnaryOperator square = x -> x*x;
		IntStream squaredSequence = sequence.map(square);
		assertThat(squaredSequence.toArray(), is(new Integer[]{1, 4, 9, 16, 25, 36, 49, 64, 81, 100}));
	}

	@Test
	public void summedSequenceOfNumbers() throws Exception {
		IntStream squaredSequence = IntStream.of(1, 4, 9, 16, 25, 36, 49, 64, 81, 100);
		IntBinaryOperator add = (x,y) -> x+y;
		int sum = squaredSequence.reduce(0, add);
		assertThat(sum, is(385));
	}
	
	@Test
	public void combiningAllSteps() throws Exception {
		IntUnaryOperator square = x -> x*x;
		IntBinaryOperator add = (x,y) -> x+y;
		assertThat(IntStream.range(1, 11).map(square).reduce(0, add), is(385));
	}
	

}
