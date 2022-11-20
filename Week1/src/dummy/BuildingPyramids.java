package dummy;
import java.util.Scanner;

public class BuildingPyramids {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int blocks = input.nextInt();
		int blocksLeft = 0;
		int counter = 0;
		
		while(blocks > 0) {
			blocks = blocks - (2 * counter + 1) * (2 * counter + 1);
			counter++;
		}
		if (blocks < 0) counter--;
		System.out.println(counter);
		
	}

}

