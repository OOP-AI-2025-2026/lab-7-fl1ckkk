import java.util.Arrays;
import java.util.function.Predicate;

class Student {
    private String name;
    private String group;
    private int[] marks;

    // Конструктор
    public Student(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    // Гетери
    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int[] getMarks() {
        return marks;
    }

    // Сетери
    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', group='" + group + "', marks=" + Arrays.toString(marks) + "}";
    }
}

public class StudentFilter {

    // Метод фільтрації масиву студентів
    public static Student[] filterStudents(Student[] students, Predicate<Student> predicate) {
        Student[] result = new Student[students.length];
        int counter = 0;

        for (Student student : students) {
            if (predicate.test(student)) {
                result[counter] = student;
                counter++;
            }
        }

        return Arrays.copyOf(result, counter);
    }

    public static void main(String[] args) {
        // Створюємо тестовий масив студентів
        Student[] students = {
                new Student("Іван Іваненко", "Група А", new int[]{85, 72, 90, 65, 78}),
                new Student("Марія Петренко", "Група Б", new int[]{45, 58, 62, 70, 55}),
                new Student("Петро Сидоренко", "Група А", new int[]{90, 85, 88, 92, 87}),
                new Student("Олена Коваленко", "Група Б", new int[]{75, 80, 59, 82, 77}),
                new Student("Андрій Шевченко", "Група А", new int[]{65, 70, 45, 80, 72})
        };

        // Предикат для відсіювання студентів з заборгованостями (оцінка < 60)
        Predicate<Student> noDebtsPredicate = student -> {
            int[] marks = student.getMarks();
            for (int mark : marks) {
                if (mark < 60) {
                    return false; // Знайдено заборгованість
                }
            }
            return true; // Немає заборгованостей
        };

        // Фільтруємо студентів
        Student[] studentsWithoutDebts = filterStudents(students, noDebtsPredicate);

        // Виводимо результат
        System.out.println("Всі студенти:");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("\nСтуденти без заборгованостей (всі оцінки ≥ 60):");
        for (Student student : studentsWithoutDebts) {
            System.out.println(student);
        }

        // Додатковий приклад: фільтр студентів з групи А
        Predicate<Student> groupAPredicate = student -> "Група А".equals(student.getGroup());
        Student[] groupAStudents = filterStudents(students, groupAPredicate);

        System.out.println("\nСтуденти групи А:");
        for (Student student : groupAStudents) {
            System.out.println(student);
        }
    }
}
