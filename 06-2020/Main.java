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
		List<String> dataset = Files.readAllLines(Path.of("DANE_PR2/pary.txt"));

		List<Integer> liczbyPierwsze = new ArrayList<>();
		
		for (int i = 1; i < 100; i++)
		{
			int ctr = 0;
			for (int j = 1; j <= i; j++)
			{
				if ((float) i / (float) j == i/j)
					ctr++;
			}
			if (ctr <= 2 && i % 2 == 1) //przez 1 i przez siebie
			{
				liczbyPierwsze.add(i);
			}
		}
		liczbyPierwsze.remove(0);
		
//		liczbyPierwsze.stream().forEach(x -> System.out.print(x + ", "));
		
		System.out.println("4.1:");
		for (String linia : dataset)
		{
			String[] tokens = linia.split(" ");
			
			int a = 0;
			int b = 0;
			
			int target = Integer.parseInt(tokens[0]);
			
			if (target % 2 == 0)
			{
				for (int i = 0; i < liczbyPierwsze.size(); i++)
				{
					for (int k = 0; k < liczbyPierwsze.size(); k++)
					{
						int aa = liczbyPierwsze.get(i);
						int bb = liczbyPierwsze.get(k);
						
						if (aa + bb == target && Math.abs(aa - bb) >= Math.abs(a - b))
						{
							a = aa;
							b = bb;
						}
					}
				}
				
				System.out.println(target + " " + a + " " + b);
			}
		}
		
		System.out.println("4.2: ");
		for (String linia : dataset)
		{
			String t = linia.split(" ")[1];
			
			char maxWysZnak = t.charAt(0);
			int maxWysIlosc = 0;
			
			char aktWysZnak = ' ';
			int aktWysIlosc = 0;			
			
			for (char c : t.toCharArray())
			{
				if (c == aktWysZnak)
				{
					aktWysIlosc++;
				}
				if (c != aktWysZnak)
				{
					if (aktWysIlosc > maxWysIlosc)
					{
						maxWysIlosc = aktWysIlosc;
						maxWysZnak = aktWysZnak;
					}
					aktWysIlosc = 1;
					aktWysZnak = c;
				}
			}
			
			for (int xxx = 0; xxx < maxWysIlosc; xxx++)
			System.out.print(maxWysZnak);
			System.out.print(" ");
			System.out.println(maxWysIlosc);
		}
		
		
		
		String out = "";
		int x = 99999;
		
		for (int idx = 1; idx < dataset.size(); idx++)
		{
			String linia = dataset.get(idx);
			String[] tokens = linia.split(" ");
			if (Integer.parseInt(tokens[0]) == tokens[1].length())
			{
				if (out == "" || x > Integer.parseInt(tokens[0]))
				{
					out = tokens[1];
					x = Integer.parseInt(tokens[0]);
				}
				else
				{
					for (int xx = 0; xx < tokens[0].length(); xx++)
					{
						if ((int) out.charAt(xx) > (int) tokens[0].charAt(xx))
						{
							out = tokens[1];
							x = Integer.parseInt(tokens[0]);
						}
					}
				}
			}
		}
		System.out.println("4.3: " + out);
		
	}
}
