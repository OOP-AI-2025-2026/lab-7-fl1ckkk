import java.util.Arrays;
import java.util.function.Predicate;

public class DoublePredicateFilter {

    // Метод фільтрації за двома умовами
    public static <T> T[] filterWithTwoConditions(T[] array, Predicate<T> predicate1, Predicate<T> predicate2) {
        T[] result = Arrays.copyOf(array, array.length);
        int counter = 0;

        for (T element : array) {
            if (predicate1.test(element) && predicate2.test(element)) {
                result[counter] = element;
                counter++;
            }
        }

        return Arrays.copyOf(result, counter);
    }

    // Приклад з числами
    public static void numberExample() {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        // Предикат 1: число парне
        Predicate<Integer> isEven = n -> n % 2 == 0;

        // Предикат 2: число більше 5
        Predicate<Integer> isGreaterThan5 = n -> n > 5;

        // Фільтруємо: парні числа більші за 5
        Integer[] filteredNumbers = filterWithTwoConditions(numbers, isEven, isGreaterThan5);

        System.out.println("Числа (парні та більші за 5): " + Arrays.toString(filteredNumbers));
    }

    // Приклад зі студентами (продовження попереднього завдання)
    public static void studentExample() {
        Student[] students = {
                new Student("Іван Іваненко", "Група А", new int[]{85, 72, 90, 65, 78}),
                new Student("Марія Петренко", "Група Б", new int[]{75, 80, 82, 85, 78}),
                new Student("Петро Сидоренко", "Група А", new int[]{90, 85, 88, 92, 87}),
                new Student("Олена Коваленко", "Група Б", new int[]{95, 92, 90, 88, 91}),
                new Student("Андрій Шевченко", "Група А", new int[]{65, 70, 72, 68, 71})
        };

        // Предикат 1: студент з групи А
        Predicate<Student> fromGroupA = student -> "Група А".equals(student.getGroup());

        // Предикат 2: середній бал студента ≥ 80
        Predicate<Student> highAchiever = student -> {
            int[] marks = student.getMarks();
            double sum = 0;
            for (int mark : marks) {
                sum += mark;
            }
            return (sum / marks.length) >= 80;
        };

        // Фільтруємо: студенти групи А з середнім балом ≥ 80
        Student[] filteredStudents = filterWithTwoConditions(students, fromGroupA, highAchiever);

        System.out.println("\nСтуденти групи А з середнім балом ≥ 80:");
        for (Student student : filteredStudents) {
            double average = Arrays.stream(student.getMarks()).average().orElse(0);
            System.out.println(student.getName() + " - середній бал: " + average);
        }
    }

    // Приклад з рядками
    public static void stringExample() {
        String[] words = {"Java", "Python", "JavaScript", "C++", "C#", "Ruby", "Go", "Swift"};

        // Предикат 1: рядок містить букву 'a'
        Predicate<String> containsA = s -> s.toLowerCase().contains("a");

        // Предикат 2: довжина рядка > 4
        Predicate<String> longerThan4 = s -> s.length() > 4;

        // Фільтруємо: слова, що містять 'a' та довші за 4 символи
        String[] filteredWords = filterWithTwoConditions(words, containsA, longerThan4);

        System.out.println("\nСлова, що містять 'a' та довші за 4 символи: " + Arrays.toString(filteredWords));
    }

    // Альтернативний варіант з використанням комбінації предикатів
    public static <T> T[] filterWithCombinedPredicate(T[] array, Predicate<T> predicate1, Predicate<T> predicate2) {
        // Комбінуємо два предикати в один за допомогою and()
        Predicate<T> combinedPredicate = predicate1.and(predicate2);

        T[] result = Arrays.copyOf(array, array.length);
        int counter = 0;

        for (T element : array) {
            if (combinedPredicate.test(element)) {
                result[counter] = element;
                counter++;
            }
        }

        return Arrays.copyOf(result, counter);
    }

    public static void main(String[] args) {
        System.out.println("=== Фільтрація за двома умовами ===");

        // Приклад 1: Числа
        numberExample();

        // Приклад 2: Студенти
        studentExample();

        // Приклад 3: Рядки
        stringExample();

        // Демонстрація альтернативного методу
        System.out.println("\n=== Альтернативний метод (комбінований предикат) ===");
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isGreaterThan5 = n -> n > 5;

        Integer[] result = filterWithCombinedPredicate(numbers, isEven, isGreaterThan5);
        System.out.println("Парні числа більші за 5: " + Arrays.toString(result));
    }
}
