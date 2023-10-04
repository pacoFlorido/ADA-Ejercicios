import java.time.LocalDate;

class Student {
    private String name;
    private int age;
    private LocalDate birthday;

    public Student(String name, int age, LocalDate brirthday) {
        this.name = name;
        this.age = age;
        this.birthday = brirthday;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return name + "," + age + "," + birthday;
    }
}
