package dummy;
import java.util.Scanner;
class r2{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int r = input.nextInt();
        int s = input.nextInt();
        int r2 = (s*2) - r;
        System.out.println(r2);
    }
}