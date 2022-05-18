package com.company;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // Стрим для подсчёта жителей до 18 лет
        Long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(count + " Количество жителей до 18 лет");

        // Стрим для поиска людей годных для службы
        List<String> forArmy = persons.stream()
                .filter(v -> v.getAge() >= 18 && v.getAge() <= 27)
                .map(Person::toString)
                .collect(Collectors.toList());
        // подсчет количества людей годных для службы
        Long count1 = (long) forArmy.size();
                System.out.println(count1 + " Количество призванных");


        //высокообразованные люди способные работать
        List<String> workers = persons.stream()
                .filter(v -> v.getEducation().equals(Education.HIGHER))
                .filter(v -> v.getSex().equals(Sex.WOMAN) && v.getAge() >= 18 && v.getAge() <= 65 ||
                        v.getSex().equals(Sex.MAN) && v.getAge() >= 18 && v.getAge() <=60)// я не придумал ничего более
                //нормального кроме как сделать много условий в одном фильтре.
                .map(Person::toString)
                .collect(Collectors.toList());

        //подсчет количества людей с высоким образованием способных работать
        Long worker = (long) workers.size();
        System.out.println(worker + "количество работспобных людей");

        //некий пример этих людей их списка
        workers.stream()
                .limit(10)
                .forEach(System.out::println );



    }
}
