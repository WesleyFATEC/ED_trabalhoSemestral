package utils;
import java.util.Iterator;

public class Iterar {
	public static <T> void iterar(Iterable<T> iterable) {
	        Iterator<T> iterator = iterable.iterator();
	        while (iterator.hasNext()) {
	            T elemento = iterator.next();
	            System.out.println(elemento);
	        }
	    }
}
