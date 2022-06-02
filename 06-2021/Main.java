import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) throws IOException {
	
		List<String> przyklad = Files.readAllLines(Path.of("DANE/przyklad.txt"));
		List<String> napisy = Files.readAllLines(Path.of("DANE/napisy.txt"));
		
		Zadanie4(napisy);
		
		Zadanie5();
	}
	static HashMap<Integer, HashMap<Month, Integer>> przychody = new HashMap<>();
	static HashMap<Integer, HashMap<Month, Integer>> koszty = new HashMap<>();
	
	private static void Zadanie5() {
		
		int kosztRoweru = 800;
		int cenaWypozyczenia = 30;
		int cenaSerwisowania = 15;
		
		int iloscRowerow = 10;
		
		przychody.put(2023, new HashMap<Month, Integer>());
		przychody.put(2024, new HashMap<Month, Integer>());
		
		przychody.get(2023).put(Month.JANUARY, 0);
		przychody.get(2023).put(Month.FEBRUARY, 0);
		przychody.get(2023).put(Month.MARCH, 0);
		przychody.get(2023).put(Month.APRIL, 0);
		przychody.get(2023).put(Month.MAY, 0);
		przychody.get(2023).put(Month.JUNE, 0);
		przychody.get(2023).put(Month.JULY, 0);
		przychody.get(2023).put(Month.AUGUST, 0);
		przychody.get(2023).put(Month.SEPTEMBER, 0);
		przychody.get(2023).put(Month.OCTOBER, 0);
		przychody.get(2023).put(Month.NOVEMBER, 0);
		przychody.get(2023).put(Month.DECEMBER, 0);
		
		przychody.get(2024).put(Month.JANUARY, 0);
		przychody.get(2024).put(Month.FEBRUARY, 0);
		przychody.get(2024).put(Month.MARCH, 0);
		przychody.get(2024).put(Month.APRIL, 0);
		przychody.get(2024).put(Month.MAY, 0);
		przychody.get(2024).put(Month.JUNE, 0);
		przychody.get(2024).put(Month.JULY, 0);
		przychody.get(2024).put(Month.AUGUST, 0);
		przychody.get(2024).put(Month.SEPTEMBER, 0);
		przychody.get(2024).put(Month.OCTOBER, 0);
		przychody.get(2024).put(Month.NOVEMBER, 0);
		przychody.get(2024).put(Month.DECEMBER, 0);
		

		koszty.put(2023, new HashMap<Month, Integer>());
		koszty.put(2024, new HashMap<Month, Integer>());
		
		koszty.get(2023).put(Month.JANUARY, kosztRoweru * iloscRowerow);
		koszty.get(2023).put(Month.FEBRUARY, 0);
		koszty.get(2023).put(Month.MARCH, 0);
		koszty.get(2023).put(Month.APRIL, 0);
		koszty.get(2023).put(Month.MAY, 0);
		koszty.get(2023).put(Month.JUNE, 0);
		koszty.get(2023).put(Month.JULY, 0);
		koszty.get(2023).put(Month.AUGUST, 0);
		koszty.get(2023).put(Month.SEPTEMBER, 0);
		koszty.get(2023).put(Month.OCTOBER, 0);
		koszty.get(2023).put(Month.NOVEMBER, 0);
		koszty.get(2023).put(Month.DECEMBER, 0);
		
		koszty.get(2024).put(Month.JANUARY, 0);
		koszty.get(2024).put(Month.FEBRUARY, 0);
		koszty.get(2024).put(Month.MARCH, 0);
		koszty.get(2024).put(Month.APRIL, 0);
		koszty.get(2024).put(Month.MAY, 0);
		koszty.get(2024).put(Month.JUNE, 0);
		koszty.get(2024).put(Month.JULY, 0);
		koszty.get(2024).put(Month.AUGUST, 0);
		koszty.get(2024).put(Month.SEPTEMBER, 0);
		koszty.get(2024).put(Month.OCTOBER, 0);
		koszty.get(2024).put(Month.NOVEMBER, 0);
		koszty.get(2024).put(Month.DECEMBER, 0);

		
		Boolean zadanie51trigger = true;
		
		LocalDate dzis = LocalDate.of(2023, 1, 1);
		LocalDate koniec = LocalDate.of(2024, 12,31);

		DayOfWeek dniRoboczeArray[] = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY};
		List<DayOfWeek> dniRobocze = Arrays.asList(dniRoboczeArray);
		
		
		while (!dzis.isAfter(koniec))
		{
			Boolean trig = false;
//			System.out.println(start);
			
			if (dzis.getDayOfWeek() == DayOfWeek.SUNDAY)
			{
				koszty.get(dzis.getYear()).put(dzis.getMonth(), koszty.get(dzis.getYear()).get(dzis.getMonth()) + cenaSerwisowania * iloscRowerow);
			}
			float mnoznik = 0;
			
			if (!dzis.equals(LocalDate.of(2024,12,31)) && dzis.equals(dzis.with(TemporalAdjusters.lastDayOfMonth()))) //5.4
			{
				if (LacznyZarobek() - LaczneKoszty() > 3 * kosztRoweru)
				{
					koszty.get(dzis.getYear()).put(dzis.getMonth(), koszty.get(dzis.getYear()).get(dzis.getMonth()) + 3 * kosztRoweru);
					trig = true;
				}
			}
			
			if (dniRobocze.contains(dzis.getDayOfWeek()))
			{
				
				if ((dzis.isAfter(LocalDate.of(2022, 12, 20)) && dzis.isBefore(LocalDate.of(2023, 03,21 ))) || (dzis.isAfter(LocalDate.of(2023, 12, 20)) && dzis.isBefore(LocalDate.of(2024, 03,21))) || dzis.isAfter(LocalDate.of(2024,12,20)) ) //zima
				{
					mnoznik = 0.2f;
				}
				else if (dzis.isAfter(LocalDate.of(dzis.getYear(), 3, 20)) && dzis.isBefore(LocalDate.of(dzis.getYear(),6,21))) //wiosna
				{
					mnoznik = 0.5f;
				}
				else if (dzis.isAfter(LocalDate.of(dzis.getYear(), 6, 20)) && dzis.isBefore(LocalDate.of(dzis.getYear(),9,23))) //late
				{
					mnoznik = 0.9f;
				}
				else if (dzis.isAfter(LocalDate.of(dzis.getYear(), 9, 22)) && dzis.isBefore(LocalDate.of(dzis.getYear(),12,21))) //jesien
				{
					mnoznik = 0.4f;
				}
				
				przychody.get(dzis.getYear()).put(dzis.getMonth(), przychody.get(dzis.getYear()).get(dzis.getMonth()) + (int)Math.floor(iloscRowerow * mnoznik) * cenaWypozyczenia);

			}
			
//			System.out.println("Dzien: " + dzis+ ". Mnożnik: " + mnoznik);
			
			if (dzis.getYear() == 2023 && dzis.getDayOfMonth() == 1 && dzis.getMonth() != Month.JANUARY)
			{
				System.out.println("5.2: " + dzis + " - Dochód: " + (przychody.get(dzis.getYear()).get(Month.of(dzis.getMonthValue() - 1)) - koszty.get(dzis.getYear()).get(Month.of(dzis.getMonthValue() - 1))));
			}
			
			if (dzis.equals(LocalDate.of(2024, 1, 1)))
			{
				System.out.println("5.2: " + dzis + " - Dochód: " + (przychody.get(2023).get(Month.of(12)) - koszty.get(2023).get(Month.of(12))));
			}
			
			if (dzis.equals(LocalDate.of(2023, 3, 31)))
			{
				System.out.println(dzis + ": " + (LacznyZarobek() - LaczneKoszty()));
			}
			
			if (dzis.equals(LocalDate.of(2023, 12, 31)))
			{
				System.out.println(dzis + ": " + (LacznyZarobek() - LaczneKoszty()));
			}
			
			if (dzis.equals(LocalDate.of(2024, 12, 31)))
			{
				System.out.println(dzis + ": Ilość rowerów: " + iloscRowerow + ", Przychod: " + LacznyZarobek() + ", Koszty: " + LaczneKoszty() + ". Dochód: "+ (LacznyZarobek() - LaczneKoszty()));
			}
			
			if (LacznyZarobek() > LaczneKoszty() && zadanie51trigger)
			{
				zadanie51trigger = false;
				System.out.println("5.1: " + dzis);
			}
				
			if (trig)
			{
				trig = false;					
				iloscRowerow += 3;
			}
			
			dzis = dzis.plusDays(1);
		}
		
	}
	
	private static long LacznyZarobek()
	{
		int x = 0;
		for (Entry<Integer, HashMap<Month, Integer>> ks : przychody.entrySet())
		{
			for (Entry<Month,Integer> xx : ks.getValue().entrySet())
			{
				x += xx.getValue();
			}
		}
		return x;
	}
	
	private static long LaczneKoszty()
	{
		int x = 0;
		for (Entry<Integer, HashMap<Month, Integer>> ks : koszty.entrySet())
		{
			for (Entry<Month,Integer> xx : ks.getValue().entrySet())
			{
				x += xx.getValue();
			}
		}
		return x;
	}
	

	private static void Zadanie4(List<String> dataset) {

		String zad42wynik = "";
		String zad43wynik = "";
		String zad44wynik = "";
		int wszystkieCyfry = 0;
		int liniaCtr = 0;
		int cc = 0;
		for (String linia : dataset)
		{
			int cCtr = 0;
			
			Boolean wyn = true, wyn2 = true; //4.3
			
			String test1 = linia.charAt(49) + linia;
			String test2 = linia + linia.charAt(0);
			for (int idx = 1; idx < 24; idx++)
			{
				if (test1.charAt(idx) != test1.charAt(50-idx))
				{
					wyn = false;
					break;
				}
			}
			
			for (int idx = 1; idx < 24; idx++)
			{
				if (test2.charAt(idx) != test2.charAt(50-idx))
				{
					wyn2 = false;
					break;
				}
			}
			
			if (wyn)
			{
				zad43wynik += test1.charAt(25);
			} else if (wyn2)
			{
				zad43wynik += test2.charAt(25);
			}
			
			if (liniaCtr != 0 && (liniaCtr + 1) % 20 == 0) { // 4.2
				zad42wynik += linia.charAt(cc);
				cc++;
			}
			
			char tmp = '-';
			for (char c : linia.toCharArray())
			{
				if (Character.isDigit(c)) wszystkieCyfry++; //4.1	
				
				
				//4.4
				
				if (Character.isDigit(c) && tmp != '-')
				{
					int x = Integer.parseInt(Character.toString(tmp) + Character.toString(c));
					if (x > 64 && x < 91) {
						if (zad44wynik.length() < 3  || !(zad44wynik.charAt(zad44wynik.length() - 1) == 'X' &&
								zad44wynik.charAt(zad44wynik.length() - 2) == 'X' &&
								zad44wynik.charAt(zad44wynik.length() - 3) == 'X'))
						zad44wynik += (char) x;
					}
					tmp = '-';
				} else if (Character.isDigit(c) && tmp == '-')
					tmp = c;

				
				cCtr++;
			}
			liniaCtr++;
		}
		
		System.out.println("4.1 " + wszystkieCyfry);
		System.out.println("4.2 " + zad42wynik);
		System.out.println("4.3 " + zad43wynik);
		System.out.println("4.4 " + zad44wynik);
	}
}
