import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {

		List<String> przyklad = Files.readAllLines(Path.of("DANE/przyklad.txt"));
		List<String> instrukcje = Files.readAllLines(Path.of("DANE/instrukcje.txt"));
		
		Zadanie4(instrukcje);
		
	}

	private static void Zadanie4(List<String> dataset) {

		HashMap<Character, Integer> czteryTrzy = new HashMap<>();
		
		StringBuilder wyjscie = new StringBuilder();
		
		
		String docelowy = "";
		int max = 0;
		
		String testowany = "";
		int test = 0;
		
		for (String linia : dataset)
		{
			String[] tokens = linia.split(" ");
			
			if (testowany.equals(tokens[0]))
				test++;
			else
			{
				if (test > max)
				{
					docelowy = testowany;
					max = test;
				}
				testowany = tokens[0];
				test = 0;
			}
			
			switch(tokens[0])
			{
			
			case "PRZESUN":
				int x = wyjscie.indexOf(tokens[1]);
				if (x == -1) break;
				char lit = wyjscie.charAt(x);
				if (lit == 'Z')
					lit = 'A';
				else lit = (char) ((int) lit + 1);
				
				wyjscie = new StringBuilder(wyjscie.toString().replaceFirst("" + wyjscie.charAt(x), "" + lit));
				break;
			
			case "ZMIEN":
				wyjscie.setCharAt(wyjscie.length() - 1, tokens[1].toCharArray()[0]);
				break;
			case "DOPISZ":
				czteryTrzy.putIfAbsent(tokens[1].toCharArray()[0], 0);
				czteryTrzy.put(tokens[1].toCharArray()[0], czteryTrzy.get(tokens[1].toCharArray()[0]) + 1);
				wyjscie.append(tokens[1]);
				break;
			
			case "USUN":
				wyjscie.deleteCharAt(wyjscie.length() - 1);
				break;
			}
			
//			System.out.println(String.join(",", tokens) + "  -> " + wyjscie);
		}
		
		System.out.println("4.1: " + wyjscie.length());
		System.out.println("4.2: " + docelowy + ", " + (max + 1));
		System.out.println("4.3: " + czteryTrzy.entrySet().stream().max(Comparator.comparingInt(Entry<Character, Integer>::getValue)).get());
		System.out.println("4.4: " + wyjscie);
	}
}