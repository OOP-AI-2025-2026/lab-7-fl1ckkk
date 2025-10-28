import java.util.function.Predicate;

public class PrimeNumberPredicate {
    public static void main(String[] args) {
        // Предикат для перевірки простого числа
        Predicate<Integer> isPrime = n -> {
            if (n <= 1) {
                return false;
            }
            if (n == 2) {
                return true;
            }
            if (n % 2 == 0) {
                return false;
            }

            // Перевіряємо непарні дільники до квадратного кореня з n
            for (int i = 3; i * i <= n; i += 2) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        };

        // Тестування предиката
        System.out.println("2 - просте число: " + isPrime.test(2));     // true
        System.out.println("3 - просте число: " + isPrime.test(3));     // true
        System.out.println("4 - просте число: " + isPrime.test(4));     // false
        System.out.println("17 - просте число: " + isPrime.test(17));   // true
        System.out.println("25 - просте число: " + isPrime.test(25));   // false
        System.out.println("29 - просте число: " + isPrime.test(29));   // true
        System.out.println("1 - просте число: " + isPrime.test(1));     // false
        System.out.println("0 - просте число: " + isPrime.test(0));     // false
    }
}
