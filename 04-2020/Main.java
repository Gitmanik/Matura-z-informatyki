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

		List<String> dataset = Files.readAllLines(Path.of("DANE_KWIECIEN2020/dane4.txt"));
//		List<String> dataset = Arrays.asList("4, 11, 4, 1, 4, 7, 11, 12, 13, 14, 7, 0, 3".split(", "));
//		List<String> dataset = Arrays.asList("5, 2, 7, 10".split(", "));
		
		int maxLuka = 0, minLuka = 999999999;
		
		
		for (int idx = 0; idx < dataset.size() - 1; idx++) //4.1
		{
			int liczba1 = Integer.parseInt(dataset.get(idx));
			int liczba2 = Integer.parseInt(dataset.get(idx+1));
			
			int luka = Math.abs(liczba1-liczba2);
			
			if (luka > maxLuka)
				maxLuka = luka;
			if (luka < minLuka)
				minLuka = luka;
//			
//			System.out.println(liczba1 + " " + liczba2 + ": " + Math.abs(liczba1-liczba2));
		}
		System.out.println("4.1: " + maxLuka + ", " + minLuka);
		
		int maxPoczLuka= 0, maxKonLuka = 0, maxMax = 999999999;
		int curPoczLuka = 0, curKonLuka = 0, curMax = 999999999;
		for (int idx = 0; idx < dataset.size() - 1; idx++)
		{
			int liczba1 = Integer.parseInt(dataset.get(idx));
			int liczba2 = Integer.parseInt(dataset.get(idx+1));
			
			int luka = Math.abs(liczba1-liczba2);
			
			if (luka == maxMax)
			{
				maxKonLuka = idx+1;
			}
			else
			{
				if (luka == curMax)
				{
					curKonLuka = idx+1;
				}
				else
				{
					if (curKonLuka - curPoczLuka >= maxKonLuka - maxPoczLuka)
					{
						maxPoczLuka = curPoczLuka;
						maxKonLuka = curKonLuka;
						maxMax = curMax;
					}
					curPoczLuka = curKonLuka = idx;
					curMax = luka;
				}
			
			}
//			System.out.println(liczba1 + " " + liczba2 + ": " + curPoczLuka + "->" + curKonLuka + ": " + curMax + "   " + maxPoczLuka + "->" + maxKonLuka + ": " + maxMax);

			
		}
		
		System.out.println("4.2: " + dataset.get(maxPoczLuka) + "->" + dataset.get(maxKonLuka) + ": " + maxMax + " = " + (maxKonLuka-maxPoczLuka+1));
		
		
		int maxKrotnosc = 0;
		
		HashMap<Integer,Integer> o = new HashMap<>();
		
		for (int idx = 0; idx < dataset.size() - 1; idx++) //4.1
		{
			int liczba1 = Integer.parseInt(dataset.get(idx));
			int liczba2 = Integer.parseInt(dataset.get(idx+1));
			
			int luka = Math.abs(liczba1-liczba2);
			
			o.putIfAbsent(luka,0);
			o.put(luka,o.get(luka)+1);
			
		}
		
		int max = o.entrySet().stream().max(Comparator.comparingInt(Entry<Integer,Integer>::getValue)).get().getValue();
		
		List<Entry<Integer,Integer>> maxx = o.entrySet().stream().filter(x -> x.getValue() == max).collect(Collectors.toList());
		
		System.out.println("4.3: " + maxx);
	}
}
