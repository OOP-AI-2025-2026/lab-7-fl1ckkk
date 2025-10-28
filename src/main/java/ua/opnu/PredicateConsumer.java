import java.util.function.Predicate;
import java.util.function.Consumer;

public class PredicateConsumer {

    // Метод, який виконує Consumer тільки якщо Predicate повертає true
    public static <T> void processIfCondition(T[] array, Predicate<T> predicate, Consumer<T> consumer) {
        for (T element : array) {
            if (predicate.test(element)) {
                consumer.accept(element);
            }
        }
    }

    public static void main(String[] args) {
        // Створюємо масив цілих чисел
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        System.out.println("=== Початковий масив чисел ===");
        System.out.println(java.util.Arrays.toString(numbers));

        // Приклад 1: Виводимо тільки парні числа
        System.out.println("\n=== Приклад 1: Вивід парних чисел ===");
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Consumer<Integer> printNumber = n -> System.out.println("Парне число: " + n);
        processIfCondition(numbers, isEven, printNumber);

        // Приклад 2: Виводимо квадрати чисел, які діляться на 3
        System.out.println("\n=== Приклад 2: Квадрати чисел, що діляться на 3 ===");
        Predicate<Integer> divisibleBy3 = n -> n % 3 == 0;
        Consumer<Integer> printSquare = n -> System.out.println(n + "² = " + (n * n));
        processIfCondition(numbers, divisibleBy3, printSquare);

        // Приклад 3: Виводимо числа більші за 7 у вигляді зірочок
        System.out.println("\n=== Приклад 3: Числа більші за 7 у вигляді зірочок ===");
        Predicate<Integer> greaterThan7 = n -> n > 7;
        Consumer<Integer> printStars = n -> {
            System.out.print(n + ": ");
            for (int i = 0; i < n; i++) {
                System.out.print("*");
            }
            System.out.println();
        };
        processIfCondition(numbers, greaterThan7, printStars);

        // Приклад 4: Виводимо прості числа з позначкою "ПРОСТЕ"
        System.out.println("\n=== Приклад 4: Прості числа ===");
        Predicate<Integer> isPrime = n -> {
            if (n <= 1) return false;
            if (n == 2) return true;
            if (n % 2 == 0) return false;
            for (int i = 3; i * i <= n; i += 2) {
                if (n % i == 0) return false;
            }
            return true;
        };
        Consumer<Integer> markPrime = n -> System.out.println(n + " - ПРОСТЕ ЧИСЛО");
        processIfCondition(numbers, isPrime, markPrime);
    }
}
