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

class Galeria
{
	public String panstwo, miasto;
	public List<Sklep> sklepy = new ArrayList<>();
	public Galeria(String p, String m) {
		panstwo = p;
		miasto = m;
	}
}

class Sklep
{
	public int w, h;
	public Sklep(int w, int h)
	{
		this.w = w;
		this.h = h;
	}
	
	public int powierzchnia() {
		return w * h;
	}

	@Override
	public int hashCode()
	{
		return powierzchnia();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return powierzchnia() == ((Sklep) obj).powierzchnia();
	}
}

class Polaczenie
{
	public String callerID;
	public LocalDate data;
	public LocalTime start, koniec;
	public Polaczenie(String c, LocalDate d, LocalTime s, LocalTime k)
	{
		callerID = c;
		data = d;
		start = s;
		koniec = k;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {

		List<String> galerie = Files.readAllLines(Path.of("DANE/galerie.txt"));
	
//		List<String> galerie_przyklad = Files.readAllLines(Path.of("DANE/galerie_przyklad.txt"));

		Zadanie4(galerie);
		
		
		List<String> telefony = Files.readAllLines(Path.of("DANE/telefony.txt"));
		Zadanie5(telefony);
		
	}

	private static void Zadanie5(List<String> dataset) {

		List<Polaczenie> polaczenia = new ArrayList<>();
		
		dataset.remove(0);
		for (String linia : dataset)
		{
			String[] tokens = linia.split(" ");
			Polaczenie p = new Polaczenie(tokens[0], LocalDate.parse(tokens[1], DateTimeFormatter.ofPattern("d-M-y")), LocalTime.parse(tokens[2]), LocalTime.parse(tokens[3]));
			polaczenia.add(p);
		}
		
		//5.1
		HashMap<String, Integer> iloscPolaczen = new HashMap<>();
		
		for (Polaczenie p : polaczenia)
		{
			iloscPolaczen.putIfAbsent(p.callerID, 0);
			iloscPolaczen.put(p.callerID, iloscPolaczen.get(p.callerID) + 1);
		}
		
		List<Entry<String,Integer>> x = iloscPolaczen.entrySet().stream().sorted(Comparator.comparingInt(Entry<String,Integer>::getValue)).collect(Collectors.toList());
		
		for (int i = 0; i < 3; i++)
			System.out.println("5.1: " + i + " - " +x.get(x.size() -1 - i));
		
	}

	private static void Zadanie4(List<String> dataset) {
		
		List<Galeria> galerie = new ArrayList<>();
		for (String linia : dataset)
		{
			String[] tokens = linia.split(" ");
			Galeria gal = new Galeria(tokens[0], tokens[1]);
			
			for (int idx = 2; idx < tokens.length - 2; idx += 2)
			{
				String token = tokens[idx];
				String token2 = tokens[idx+1];
				if (token.equals("0") || token2.equals("0"))
					break;
				
//				System.out.println(tokens[1] + " " + tokens.length + " " + " " + idx + ": " + token + " " + token2);
				
				gal.sklepy.add(new Sklep(Integer.parseInt(token),Integer.parseInt(token2)));
			}
			
			galerie.add(gal);
//			return;
		}

		HashMap<String, Integer> panstwo_miasto = new HashMap<>();
		
		//4.2
		
		for (Galeria s : galerie)
		{
			panstwo_miasto.putIfAbsent(s.panstwo, 0);
			panstwo_miasto.put(s.panstwo, panstwo_miasto.get(s.panstwo) + 1);
		}
		
		System.out.println("4.1:");
		panstwo_miasto.entrySet().stream().forEach(x -> System.out.println(x.toString().replace('=', ' ')));
		
		System.out.println("4.2a:");
		
		galerie.stream().forEach(x -> System.out.println(x.miasto + " " + x.sklepy.stream().map(Sklep::powierzchnia).collect(Collectors.summingInt(Integer::intValue)) + " " + x.sklepy.size()));
		
		HashMap<String, Integer> xx = new HashMap<>();
		
		galerie.stream().forEach(x -> xx.put(x.miasto, x.sklepy.stream().map(Sklep::powierzchnia).collect(Collectors.summingInt(Integer::intValue))));

	
		
		System.out.println("4.2b: ");
		System.out.println(xx.entrySet().stream().max(Comparator.comparingInt(x -> x.getValue())).get());
		System.out.println(xx.entrySet().stream().min(Comparator.comparingInt(x -> x.getValue())).get());

		System.out.println("4.3: ");
		
		HashMap<String, Integer> gg = new HashMap<>();
		galerie.stream().forEach(g -> gg.put(g.miasto, (int) g.sklepy.stream().distinct().count()));
		System.out.println(gg.entrySet().stream().max(Comparator.comparingInt(x -> x.getValue())).get());
		System.out.println(gg.entrySet().stream().min(Comparator.comparingInt(x -> x.getValue())).get());

	 
 		
	}

}
