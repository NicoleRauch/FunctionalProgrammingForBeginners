

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.stream.IntStream;

import org.junit.Test;


public class ExamplesTest {

	
	@Test
	public void lambdasAreFirstOrderCitizens() {
		TimesFunction times = (x, y) -> x * y;
		TimesFunction timesVar = times;
		assertThat(timesVar.eval(3, 5), is(15));
	}
	
	@Test
	public void staticMethodsAreFirstOrderCitizens() {
		TimesFunction timesVar = Examples::staticTimes;
		assertThat(timesVar.eval(3, 5), is(15));
	}
	
	@Test
	public void nonstaticMethodsFirstOrderCitizens() {
		Examples examples = new Examples();
		TimesFunction timesVar = examples::times;
		assertThat(timesVar.eval(3, 5), is(15));
	}
	
	@Test
	public void functionsCanBePassedAsFunctionArguments() {
		Function func = (y) -> 3 * y;
		
		assertThat(Examples.apply(func, 5), is(15));
		assertThat(Examples.apply(Examples::staticFunc, 5), is(15));
		Examples examples = new Examples();
		assertThat(Examples.apply(examples::func, 5), is(15));
	}

	@Test
	public void functionsCanBeReturnedByFunctions() {
		FunctionFunction times = (x) -> { return (y) -> x * y; };
		assertThat(times.eval(3).eval(5), is(15));
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
		assertThat(IntStream.range(1, 11).toArray(), is(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
	}
	
	@Test
	public void squaredSequenceOfNumbers() throws Exception {
		assertThat(IntStream.range(1, 11).map(x -> x*x).toArray(), is(new Integer[]{1, 4, 9, 16, 25, 36, 49, 64, 81, 100}));
	}

	@Test
	public void summedSequenceOfNumbers() throws Exception {
		assertThat(asList(1, 4, 9, 16, 25, 36, 49, 64, 81, 100).stream().reduce(0, (x,y) -> x+y), is(385));
	}
	
	@Test
	public void combiningAllSteps() throws Exception {
		assertThat(IntStream.range(1, 11).map(x -> x*x).reduce(0, (x,y) -> x+y), is(385));
	}
	

}
