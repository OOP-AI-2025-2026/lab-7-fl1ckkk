import java.util.Arrays;
import java.util.function.Function;

public class PowerFunction {

    // Метод для обробки масиву з використанням Function
    public static Integer[] processArray(Integer[] array, Function<Integer, Integer> function) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = function.apply(array[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        // Створюємо масив з 10 цілих чисел
        Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println("=== Початковий масив чисел ===");
        System.out.println(Arrays.toString(numbers));

        // Function, який обчислює 2^n
        Function<Integer, Integer> powerOfTwo = n -> {
            if (n == 0) return 1;
            int result = 1;
            for (int i = 0; i < n; i++) {
                result *= 2;
            }
            return result;
        };

        // Альтернативний варіант з Math.pow (повертає double, тому потрібно приведення)
        Function<Integer, Integer> powerOfTwoAlt = n -> (int) Math.pow(2, n);

        // Перевірка роботи Function
        System.out.println("\n=== Перевірка Function 2^n ===");
        for (int num : numbers) {
            int result = powerOfTwo.apply(num);
            System.out.println("2^" + num + " = " + result);
        }

        // Обробка всього масиву за допомогою методу processArray
        System.out.println("\n=== Обробка всього масиву ===");
        Integer[] poweredNumbers = processArray(numbers, powerOfTwo);
        System.out.println("Результат: " + Arrays.toString(poweredNumbers));

        // Додатковий приклад: Function для обчислення n!
        System.out.println("\n=== Додатковий приклад: Факторіал чисел ===");
        Function<Integer, Integer> factorial = n -> {
            if (n == 0) return 1;
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        };

        for (int num : numbers) {
            int result = factorial.apply(num);
            System.out.println(num + "! = " + result);
        }

        // Обробка масиву з факторіалами
        Integer[] factorialNumbers = processArray(numbers, factorial);
        System.out.println("Факторіали: " + Arrays.toString(factorialNumbers));
    }
}
