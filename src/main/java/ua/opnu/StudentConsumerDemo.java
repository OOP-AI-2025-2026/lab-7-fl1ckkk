import java.util.Arrays;
import java.util.function.Consumer;

class StudentData {
    private String name;
    private String group;
    private int[] marks;

    public StudentData(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int[] getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', group='" + group + "', marks=" + Arrays.toString(marks) + "}";
    }
}

public class StudentConsumerDemo {

    // Метод forEach для масиву студентів
    public static void forEachStudent(StudentData[] students, Consumer<StudentData> action) {
        for (StudentData student : students) {
            action.accept(student);
        }
    }

    public static void main(String[] args) {
        // Створюємо масив з кількох студентів
        StudentData[] students = {
                new StudentData("Іваненко Іван", "Група А", new int[]{85, 72, 90, 65, 78}),
                new StudentData("Петренко Марія", "Група Б", new int[]{75, 80, 82, 85, 78}),
                new StudentData("Сидоренко Петро", "Група А", new int[]{90, 85, 88, 92, 87}),
                new StudentData("Коваленко Олена", "Група Б", new int[]{95, 92, 90, 88, 91}),
                new StudentData("Шевченко Андрій", "Група А", new int[]{65, 70, 72, 68, 71}),
                new StudentData("Бондаренко Катерина", "Група В", new int[]{88, 85, 90, 92, 87}),
                new StudentData("Мельник Сергій", "Група Б", new int[]{55, 60, 58, 62, 59})
        };

        // Consumer для виводу у форматі "ПРІЗВИЩЕ + ІМ'Я"
        Consumer<StudentData> nameFormatter = student -> {
            String fullName = student.getName();
            String[] nameParts = fullName.split(" ");
            if (nameParts.length >= 2) {
                String lastName = nameParts[0].toUpperCase();
                String firstName = nameParts[1].toUpperCase();
                System.out.println(lastName + " + " + firstName);
            } else {
                System.out.println(fullName.toUpperCase());
            }
        };

        System.out.println("=== Перевірка роботи функції forEach() ===");
        System.out.println("1. Вивід у форматі ПРІЗВИЩЕ + ІМ'Я:");
        forEachStudent(students, nameFormatter);

        // Додаткові Consumer для перевірки роботи forEach з різними діями
        System.out.println("\n2. Вивід повної інформації про студентів:");
        Consumer<StudentData> fullInfo = student -> {
            System.out.println("Студент: " + student.getName() +
                    ", Група: " + student.getGroup() +
                    ", Оцінки: " + Arrays.toString(student.getMarks()));
        };
        forEachStudent(students, fullInfo);

        System.out.println("\n3. Вивід середнього балу кожного студента:");
        Consumer<StudentData> averageMark = student -> {
            int[] marks = student.getMarks();
            double sum = 0;
            for (int mark : marks) {
                sum += mark;
            }
            double average = sum / marks.length;
            System.out.printf("%s - середній бал: %.2f%n", student.getName(), average);
        };
        forEachStudent(students, averageMark);

        System.out.println("\n4. Вивід студентів з високим середнім балом (≥80):");
        Consumer<StudentData> highAchievers = student -> {
            int[] marks = student.getMarks();
            double sum = 0;
            for (int mark : marks) {
                sum += mark;
            }
            double average = sum / marks.length;
            if (average >= 80) {
                System.out.printf("%s - високий середній бал: %.2f%n", student.getName(), average);
            }
        };
        forEachStudent(students, highAchievers);

        // Перевірка з лямбда-виразом безпосередньо
        System.out.println("\n5. Вивід імен студентів групи А (за допомогою лямбда):");
        forEachStudent(students, student -> {
            if ("Група А".equals(student.getGroup())) {
                System.out.println(student.getName());
            }
        });
    }
}
