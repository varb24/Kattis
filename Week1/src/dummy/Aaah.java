
package dummy;

import java.util.Scanner;

public class Aaah {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String patient = input.nextLine();
		String doctor  = input.nextLine();
		
		System.out.println(compareString(patient,doctor));

	}
	public static String compareString(String one, String two) {
		String comparison = "";
		
		if(one.compareTo(two) < 1) {
			return  "go";
		}
		else return "no";
	}

}
