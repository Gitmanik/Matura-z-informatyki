import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Zadanie4();

	}

	private static void Zadanie4() throws IOException {

		List<String> dataset = Files.readAllLines(Path.of("DANE_LIPIEC2020/identyfikator_przyklad.txt"));
		
		//4.1
		HashMap<String, Integer> numsuma = new HashMap<>();
		for (String linia : dataset)
		{
			numsuma.put(linia, 0);
			for (char c : linia.toCharArray())
				if (Character.isDigit(c)) numsuma.put(linia, numsuma.get(linia) + Integer.parseInt("" + c)); 
		}
		
		System.out.println("4.1: " + numsuma.entrySet().stream().max(Comparator.comparingInt(Entry<String,Integer>::getValue)).get());
		
		//4.2
		
		System.out.println("4.2: ");
		for (String linia : dataset)
		{
			if ((linia.charAt(0) == linia.charAt(2)) || (linia.charAt(3) == linia.charAt(8) && linia.charAt(4) == linia.charAt(7) && linia.charAt(5) == linia.charAt(6)))
				System.out.println(linia);
		}

		
		//4.3
		
		System.out.println("4.3: ");
		for (String linia : dataset)
		{
			int[] x = {7,3,1,7,3,1,7,3};
			int suma = 0;
			for (int idx = 0; idx < 3; idx++)
			{
				suma += ((int) linia.charAt(idx) - 65) * x[idx];
			}

			for (int idx = 4; idx < 9; idx++)
			{
				suma += Integer.parseInt("" + linia.charAt(idx)) * x[idx - 1];
			}
			if (suma % 10 != Integer.parseInt("" + linia.charAt(3)))
				System.out.println(linia);
		}
		
	}
}
