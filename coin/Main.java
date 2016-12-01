
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		// resources may be declared outside the try statement
		Reader reader = new InputStreamReader(new FileInputStream("Main.java"));
		BufferedReader in = new BufferedReader(reader);
		List<String> lines = new ArrayList<>();
		try(in) {
			in.lines().forEach(n -> lines.add(n));
		}

		ListProcessor processor = new ListProcessor() {};
		int numOriginal = lines.size();
		int numFlat = processor.flatten(lines).size();
		System.out.println("number of duplicate lines: " + (numOriginal - numFlat));
	}

	interface ListProcessor {

		default List<String> flatten(List<String>... lists) {
			return flattenStrings(lists);
		}

		// @SafeVarargs can be put on a private method
		// interfaces can have private methods
		@SafeVarargs
		private List<String> flattenStrings(List<String>... lists) {

			// anonymous classes can use type inference
			// single underscore now can NOT be used as variable name
			Set<String> _strings = new HashSet<>(){};
			for(List<String> list : lists) {
				_strings.addAll(list);
			}
			return new ArrayList<>(_strings);
		}

	}
}

