package recursion;

public class SumDigitsInANum {

    static int sumDigits(int num) {
        if (num<10) return num;
        return num%10 + sumDigits(num/10); //n%10 --> give last digit
    }

    public static void main(String[] args) {
        System.out.println(sumDigits(114));
    }
}
