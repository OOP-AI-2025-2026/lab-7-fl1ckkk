import java.util.Arrays;
import java.util.function.Function;

public class StringifyFunction {

    // Метод stringify, який перетворює масив чисел у масив рядків
    public static String[] stringify(Integer[] array, Function<Integer, String> function) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = function.apply(array[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        // Створюємо масив з 10 цілих чисел від 0 до 9
        Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println("=== Початковий масив чисел ===");
        System.out.println(Arrays.toString(numbers));

        // Function, який перетворює число у рядкове представлення
        Function<Integer, String> numberToWord = n -> {
            switch (n) {
                case 0: return "нуль";
                case 1: return "один";
                case 2: return "два";
                case 3: return "три";
                case 4: return "чотири";
                case 5: return "п'ять";
                case 6: return "шість";
                case 7: return "сім";
                case 8: return "вісім";
                case 9: return "дев'ять";
                default: return "невідоме число";
            }
        };

        // Перевірка роботи Function для окремих чисел
        System.out.println("\n=== Перевірка Function для окремих чисел ===");
        for (int num : numbers) {
            String result = numberToWord.apply(num);
            System.out.println(num + " -> \"" + result + "\"");
        }

        // Перевірка роботи методу stringify
        System.out.println("\n=== Робота методу stringify() ===");
        String[] stringifiedNumbers = stringify(numbers, numberToWord);
        System.out.println("Результат: " + Arrays.toString(stringifiedNumbers));
    }
}