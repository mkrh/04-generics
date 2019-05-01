package ohm.softa.a04.tests;

import ohm.softa.a04.SimpleFilter;
import ohm.softa.a04.SimpleList;
import ohm.softa.a04.SimpleListImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListTests {

	private final Logger logger = LogManager.getLogger();
	private SimpleList<Integer> testList;

	@BeforeEach
	void setup(){
		testList = new SimpleListImpl<>();

		testList.add(1);
		testList.add(2);
		testList.add(3);
		testList.add(4);
		testList.add(5);
	}

	@Test
	void testAddElements(){
		logger.info("Testing if adding and iterating elements is implemented correctly");
		int counter = 0;
		for(Integer o : testList){
			counter++;
		}
		assertEquals(5, counter);
	}

	@Test
	void testSize(){
		logger.info("Testing if size() method is implemented correctly");
		assertEquals(5, testList.size());
	}

	@Test
	void testFilterAnonymousClass(){
		logger.info("Testing the filter possibilities by filtering for all elements greater 2");
		SimpleList<Integer> result = testList.filter(new SimpleFilter<Integer>() {
			@Override
			public boolean include(Integer item) {
				int current = item;
				return current > 2;
			}
		});

		for(int i : result){
			assertTrue(i > 2);
		}
	}

	@Test
	void testFilterLambda(){
		logger.info("Testing the filter possibilities by filtering for all elements which are dividable by 2");
		SimpleList<Integer> result = testList.filter(i -> ((i) % 2 == 0));
		for(int i : result){
			assertEquals(0, i % 2);
		}
	}

	@Test
	void testMap(){
		SimpleList<Integer> intList = new SimpleListImpl<>();
		intList.add(1);
		intList.add(2);
		intList.add(3);

		SimpleList<String> stringList = new SimpleListImpl<>();
		stringList.add("1");
		stringList.add("2");
		stringList.add("3");

		Function<Integer, String> f = Object::toString;

		SimpleList<String> result = intList.map(f);

		String[] resultsArray = StreamSupport.stream(result.spliterator(), false).toArray(String[]::new);
		String[] stringsArray = StreamSupport.stream(stringList.spliterator(), false).toArray(String[]::new);

		assertArrayEquals(stringsArray, resultsArray);
	}
}