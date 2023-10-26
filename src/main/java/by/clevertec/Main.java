package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.println("task1");
        task1();
        System.out.println("task2");
        task2();
        System.out.println("task3");
        task3();
        System.out.println("task4");
        task4();
        System.out.println("task5");
        task5();
        System.out.println("task6");
        task6();
        System.out.println("task7");
        task7();
        System.out.println("task8");
        task8();
        System.out.println("task9");
        task9();
        System.out.println("task10");
        task10();
        System.out.println("task11");
        task11();
        System.out.println("task12");
        task12();
        System.out.println("task13");
        task13();
        System.out.println("task14");
        task14();
        System.out.println("task15");
        task15();
        System.out.println("task16");
        task16();
        System.out.println("task17");
        task17();
        System.out.println("task18");
        task18();
        System.out.println("task19");
        task19();
        System.out.println("task20");
        task20();
        System.out.println("task21");
        task21();
        System.out.println("task22");
        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 10 && animal.getAge() < 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(2 * 7)
                .limit(7)
                .forEach(System.out::println);
    }

    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getOrigin().equals("Japanese"))
                .peek(animal -> animal.setBread(animal.getBread().toUpperCase()))
                .peek(animal -> {
                    if (animal.getGender().equals("Female")) {
                        animal.setGender(animal.getGender().toUpperCase());
                    }
                })
                .forEach(System.out::println);
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> (animal.getAge() > 30 && animal.getOrigin().startsWith("A")))
                .map(Animal::getOrigin)
                .distinct()
                .forEach(System.out::println);
    }

    public static void task4() {
        List<Animal> animals = Util.getAnimals();
        System.out.println("всех животных пола = Female - " + animals.stream()
                .filter(animal -> animal.getGender().equalsIgnoreCase("Female"))
                .count());
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        System.out.println("хоть один из страны Венгрия - " + animals.stream()
                .anyMatch(animal -> animal.getAge() >= 20 && animal.getAge() <= 30 && animal.getBread()
                        .equals("Hungarian")));
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        System.out.println("Все ли они пола Male и Female - " + animals.stream()
                .anyMatch(animal -> animal.getGender().equals("Male ")
                        || animal.getGender().equals("Female ")));
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        System.out.println("ни одно из них не имеет страну происхождения Oceania - " + animals.stream()
                .anyMatch(animal -> !(animal.getOrigin().equals("Oceania"))));
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .map(Animal::getAge)
                .max(Integer::compareTo)
                .ifPresent(x -> System.out.println("возраст самого старого животного - " + x));
    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .map(animal -> animal.getBread().toCharArray())
                .map(chars -> chars.length)
                .min(Integer::compareTo)
                .ifPresent(x -> System.out.println("длину самого короткого массива - " + x));
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .map(Animal::getAge)
                .reduce(Integer::sum)
                .ifPresent(x -> System.out.println("суммарный возраст всех животных - " + x));
    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge)
                .average()
                .ifPresent(x -> System.out.println("средний возраст всех животных из индонезии - " + x));
    }

    public static void task12() {
        final long limitPerson = 200;
        final long minAge = 18;
        final long maxAge = 27;
        List<Person> persons = Util.getPersons();

        persons.stream()
                .filter(person -> person.getGender().equals("Male"))
                .filter(person -> {
                    LocalDate current = LocalDate.now();
                    LocalDate minAgeLimit = current.minusYears(minAge);
                    LocalDate maxAgeLimit = current.minusYears(maxAge);
                    LocalDate dateOfBirth = person.getDateOfBirth();
                    return (dateOfBirth.isBefore(minAgeLimit) || dateOfBirth.equals(minAgeLimit))
                            && (dateOfBirth.isAfter(maxAgeLimit) || dateOfBirth.isEqual(maxAgeLimit));
                })
                .filter(person -> person.getRecruitmentGroup() >= 1 && person.getRecruitmentGroup() <= 3)
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(limitPerson)
                .forEach(System.out::println);
    }

    public static void task13() {
        final long minAge = 18;
        final long maxAge = 60;
        final long maxSize = 500;
        LocalDate current = LocalDate.now();
        LocalDate minAgeLimit = current.minusYears(minAge);
        LocalDate maxAgeLimit = current.minusYears(maxAge);
        List<House> houses = Util.getHouses();
        List<Person> evacuationList = new ArrayList<>();

        houses.stream()
                .filter(house -> house.getBuildingType().equals("Hospital"))
                .flatMap(house -> house.getPersonList().stream())
                .forEach(evacuationList::add);

        houses.stream()
                .filter(house -> !(house.getBuildingType().equals("Hospital")))
                .flatMap(house -> house.getPersonList().stream())
                .filter(person -> {
                    LocalDate dateOfBirth = person.getDateOfBirth();

                    return (dateOfBirth.isAfter(minAgeLimit)) || (dateOfBirth.isBefore(maxAgeLimit));
                })
                .forEach(evacuationList::add);

        houses.stream()
                .filter(house -> !(house.getBuildingType().equals("Hospital")))
                .flatMap(house -> house.getPersonList().stream())
                .filter(person -> {
                    LocalDate dateOfBirth = person.getDateOfBirth();

                    return (dateOfBirth.isBefore(minAgeLimit) || dateOfBirth.equals(minAgeLimit))
                            && (dateOfBirth.isAfter(maxAgeLimit) || dateOfBirth.isEqual(maxAgeLimit));
                })
                .limit(maxSize - evacuationList.size())
                .forEach(evacuationList::add);

        evacuationList.forEach(System.out::println);
        System.out.println(evacuationList.size());
    }

    public static void task14() {
        List<Car> cars = Util.getCars();

        Map<String, List<Car>> countryCars = new LinkedHashMap<>();

        countryCars.put("Turkmenistan", cars.stream()
                .filter(car -> car.getCarMake().equals("Jaguar") || car.getColor().equals("White")).toList());

        countryCars.put("Uzbekistan", cars.stream()
                .filter(car -> car.getMass() <= 1500
                        && (car.getCarMake().equals("BMW") || car.getCarMake().equals("Lexus")
                        || car.getCarMake().equals("Chrysler") || car.getCarMake().equals("Toyota"))).toList());

        countryCars.put("Kazakhstan", cars.stream()
                .filter(car -> car.getColor().equals("Black") && car.getMass() > 4000
                        || (car.getCarMake().equals("GMC") || car.getCarMake().equals("Dodge"))).toList());

        countryCars.put("Kyrgyzstan", cars.stream()
                .filter(car -> car.getReleaseYear() <= 1982 || (car.getCarModel().equals("Civic")
                        || car.getCarModel().equals("Cherokee"))).toList());

        countryCars.put("Russia", cars.stream()
                .filter(car -> !Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car.getColor())
                        || car.getPrice() > 40000).toList());

        countryCars.put("Mongolia", cars.stream().filter(car -> car.getVin().contains("59")).toList());

        countryCars.values().stream()
                .map(carList -> carList.stream().mapToDouble(Car::getMass).sum() * 7.14)
                .peek(System.out::println)
                .reduce(Double::sum)
                .ifPresent(x -> System.out.println("общая выручка логистической кампании = " + x));
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
        double waterPrice = 1.39;
        System.out.println("Total Sum = " + flowers.stream()
                .sorted((o1, o2) -> o2.getOrigin().compareTo(o1.getOrigin()))
                .sorted((o1, o2) -> o2.getPrice() - o1.getPrice())
                .sorted((o1, o2) -> (int) (o2.getWaterConsumptionPerDay() - o1.getWaterConsumptionPerDay()))
                .filter(flower -> flower.getCommonName().substring(0, 1).compareTo("S") <= 0
                        && flower.getCommonName().substring(0, 1).compareTo("C") >= 0)
                .filter(Flower::isShadePreferred)
                .filter(flower -> flower.getFlowerVaseMaterial().stream().anyMatch(material -> material.equals("Glass")
                        || material.equals("Aluminum") || material.equals("Steel")))
                .mapToDouble(flower -> flower.getPrice() + flower.getWaterConsumptionPerDay() * 365 * 5 * waterPrice)
                .sum());
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(student -> student.getAge() > 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .forEach(x -> System.out.println("Name = " + x.getSurname() + "   " + "Age = " + x.getAge()));
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
        students.stream()
                .map(Student::getGroup)
                .distinct()
                .forEach(x -> System.out.println("group = " + x));
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty, Collectors.averagingDouble(Student::getAge)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new))
                .forEach((faculty, age) -> System.out.println("Факультет: " + faculty
                        + ", Средний возраст: " + age));
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        students.stream()
                .filter(student -> student.getGroup().equals("C-2"))
                .filter(student -> examinations.stream()
                        .filter(examination -> examination.getStudentId() == student.getId())
                        .mapToInt(Examination::getExam3)
                        .anyMatch(value -> value > 4))
                .toList()
                .forEach(System.out::println);
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty, Collectors.averagingDouble(
                        student -> examinations.stream()
                                .filter(examination -> examination.getStudentId() == student.getId())
                                .mapToDouble(Examination::getExam1)
                                .findFirst()
                                .orElse(0.0))))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .stream().collect(Collectors.toSet())
                .forEach(System.out::println);
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()))
                .forEach((group, count) -> System.out.println("Группа+ " + group
                        + ", Количество студентов: " + count));

    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty,
                        Collectors.mapping(Student::getAge, Collectors.minBy(Integer::compare))))
                .forEach((faculty, age) -> System.out.println("Факультет: " + faculty
                        + ", Минимальный возраст: " + age.orElse(0)));
    }
}
