package ohm.softa.a04;

import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T> {
	/**
	 * Add a given object to the back of the list.
	 */
	void add(T o);

	/**
	 * @return current size of the list
	 */
	int size();

	default <R> SimpleList<R> map(Function<T, R> transform) {
		SimpleList<R> sl = new SimpleListImpl<R>();
		for (T item : this) {
			sl.add(transform.apply(item));
		}
		return sl;
	}

	default SimpleList<T> filter(SimpleFilter<T> filter) {
		SimpleListImpl<T> result = new SimpleListImpl<>();
		for(T o : this){
			if(filter.include(o)) {
				result.add(o);
			}
		}
		return result;
	}


	void addDefault(Class<T> clazz) throws IllegalAccessException, InstantiationException;

}
