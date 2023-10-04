package WritingFiles;

import java.time.LocalDate;

public class Estudiante {
        private String name;
        private int age;

        public Estudiante(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return name + "," + age;
        }
    }
