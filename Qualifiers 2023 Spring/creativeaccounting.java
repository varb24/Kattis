package mockCompHome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//Not sure if DP is the solution here. I don't know how to turn this into sub problem I can store.
public class creativeaccounting {
	static int profitMax = 0, profitMin = Integer.MAX_VALUE;
	public static void main(String[] args) throws InterruptedException {
		Scanner input = new Scanner(System.in);
		
		int days = input.nextInt();
		int min = input.nextInt();
		int max = input.nextInt();
		int startSum = 0, prefixSum = 0;
		int[] profits = new int[days];
		int[] prefix = new int[days];
		int tempk = 0;
		ArrayList<Integer> regions = new ArrayList<>();
		
		for(int i = 0; i < days; i++) {
			profits[i] = input.nextInt();
		}
		//Checks 1 - 1 edge case and terminates
		if(min == 1 && max ==1) {
			oneEdgeCase(profits, regions);
			return;
		}
		//System.out.println(Arrays.toString(profits));
		
		//This controls the size of our sum, which we will use after selecting the size
		//of our starting range, using -1 to make it zero index
		for(int prefixSize=min-1; prefixSize<= max; prefixSize++) {
			//System.out.println("This is prefixSize: " + prefixSize);
			
			//iterates through backwards to find the different starting conditions.
			//Doing this to make it easier to deal with prefixes
			//Starting conditions should be from prefix to prefix > 0
			for(int startBuffer = prefixSize; startBuffer > 0; startBuffer--) {
				//System.out.println("This is startBuffer " + startBuffer);
				//Thread.sleep(1000);
				//finds the starting prefix which may be less than min
				startSum = addPrefix(profits, 0 , startBuffer - 1 );
				regions.add(startSum);
				startSum = 0;
				//finds all prefix size, also calculates the region 
				tempk = startBuffer;
				for(int k=startBuffer; k< days - prefixSize; k+=prefixSize) {	
					//System.out.println("This is k " + k);
					prefix[startBuffer] = addPrefix(profits,k, k+prefixSize-1);
					//System.out.println("This is the prefix calculated " + prefix[startBuffer]);
					regions.add(prefix[startBuffer]);
					tempk += prefixSize;
				}
				//Calculates any 'remainder' postfix region
				if(tempk < days ) {
					//System.out.println(tempk + " " + days );
					regions.add(addPrefix(profits, tempk, days - 1));
				}
				//Calculates any 'remainder' prefix region
				
				//This part calls checkProfit to find the profit of each region and find the total # of profit/not profit
				//Also resets regions
				//System.out.println(regions);				
				checkProfit(regions);
				
			}
		}
		
		if (profitMin == Integer.MAX_VALUE) {
			profitMin = 0;
		}
		//System.out.println("Min :" + profitMin +" Max : " + profitMax);
		System.out.println( profitMin +" " + profitMax);
		
	}
	static int addPrefix(int[] profits, int start, int end) {
		//System.out.println("Calculating " + start + " ~ " + end);
		int sum = 0;
		for(int i = start; i <= end; i++) {
			
			sum+= profits[i];
		}
		return sum;
	}
	
	static void checkProfit(ArrayList<Integer> regions) throws InterruptedException {
		//Index 0 holds profitable,  1 holds not profitable(this is not useful as far as I can tell)
		int mm = 0;
		for(int p:regions) {
			if(p>0) {
				mm++;
			}
		}
		regions.clear();
		if(mm > profitMax) {
			//System.out.println("Updating Max" + profitMax + " " + mm[0]);
			//Thread.sleep(100);
			profitMax = mm;
			
		}
		if(mm < profitMin) {
			//System.out.println("Updating Min" + profitMin+ " " + mm[0]);
			//Thread.sleep(100);
			profitMin = mm;
		}		
		
		//System.out.println("Print max " + profitMax + " Print min " + profitMin);
	}
	
	static void oneEdgeCase(int[] profits, ArrayList<Integer> regions) throws InterruptedException {
		for(int p: profits) {
			regions.add(p);
			//System.out.println(regions);
		}
		checkProfit(regions);
		System.out.println(profitMin + " " + profitMax);

	}

}
