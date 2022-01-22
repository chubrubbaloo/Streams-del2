package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        /* 1: Skapa en lista med 10 anställda som har ett id, namn, lön, kontorsplacering (Stockholm, göteborg eller malmö)
         och en boolean som anger om de är inhyrda konsulter.*/
        List<Employee> employeeList = List.of(
                new Employee(9573, "Haris", 25_000, Employee.Occupation.GÖTEBORG, true),
                new Employee(1337, "Filip", 28_000, Employee.Occupation.MALMÖ, true),
                new Employee(5555, "Karl", 35_000, Employee.Occupation.STOCKHOLM, true),
                new Employee(1991, "Albin", 27_000, Employee.Occupation.GÖTEBORG, false),
                new Employee(9911, "Oliver", 30_000, Employee.Occupation.GÖTEBORG, true),
                new Employee(7700, "Linda", 24_000, Employee.Occupation.GÖTEBORG, false),
                new Employee(1100, "Johanna", 21_500, Employee.Occupation.GÖTEBORG, true),
                new Employee(3303, "Felicia", 20_800, Employee.Occupation.STOCKHOLM, false),
                new Employee(1299, "Petra", 40_000, Employee.Occupation.STOCKHOLM, true),
                new Employee(9553, "Sofia", 20_000, Employee.Occupation.MALMÖ, true)
        );


        // 2: Plocka ut den anställde i Göteborg med högst lön.

        System.out.println("----");
        Employee highestSalaryGbg = employeeList
                .stream()
                .filter(e -> e.getOccupation().equals(Employee.Occupation.GÖTEBORG))
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow();

        System.out.println("Högsta lönen i Göteborg: " + highestSalaryGbg);


        System.out.println("----");


        // 3: Plocka ut den anställde med lägsta lönen i Stockholm.
        Employee lowestSalarySthlm = employeeList
                .stream()
                .filter(e -> e.getOccupation().equals(Employee.Occupation.STOCKHOLM))
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow();

        System.out.println("Lägsta lönen i Stockholm: " + lowestSalarySthlm);


        System.out.println("----");


        // 4: Plocka ut en anställd med ett visst hårdkodat ID.
        System.out.print("ID-nummer 9553 är: ");
        employeeList
                .stream()
                .filter(e -> e.getId() == 9553)
                .findFirst()
                .ifPresent(System.out::println);
        System.out.println("----");


        // 5: Plocka ut snittlönen för anställda i Malmö med summaryStatistics.

        System.out.println("----");
        System.out.print("Snittlönen i Malmö: ");
        System.out.println(
                employeeList
                        .stream()
                        .filter(e->e.getOccupation().equals(Employee.Occupation.MALMÖ))
                        .mapToDouble(Employee::getSalary)
                        .summaryStatistics()
                        .getAverage()
        );
        System.out.println("----");


        // 6: Plocka ut den totala lönekostnaden för alla konsulter i Stockholm.
        System.out.print("Totala lönekostnaden i Stockholm: ");
        System.out.println(
                employeeList
                        .stream()
                        .filter(e->e.getOccupation().equals(Employee.Occupation.STOCKHOLM))
                        .mapToDouble(Employee::getSalary)
                        .summaryStatistics()
                        .getSum()
        );




        System.out.println("----");

        // 7: Plocka ut antalet anställda i Stockholm och Göteborg.
        System.out.print("Antalet anställda i Stockholm och Göteborg: ");
        System.out.println(
                employeeList
                        .stream()
                        .filter(e -> e.getOccupation().equals(Employee.Occupation.STOCKHOLM) ||
                                e.getOccupation().equals(Employee.Occupation.GÖTEBORG))
                        .count()


        );
        System.out.println("----");

        // 8: Spara resultatet av summarStatistics i en variabel av typenDoubleSummarStatistics,
        // undersök först vilka metoder du därigenom har tillgång till och testas sedan att printa ut objektet.
        DoubleSummaryStatistics allTheInfo =
                employeeList
                        .stream()
                        .mapToDouble(Employee::getSalary)
                        .summaryStatistics();

        System.out.println("Info om alla anställda: " + allTheInfo);
        System.out.println("----");

        // Nivå 2:

        // 1: Använd reduce för att summera ihop lönerna från alla anställda.
        System.out.print("Totala lönekostnaden för alla anställda: ");
        System.out.println(
                employeeList
                        .stream()
                        .map(Employee::getSalary)
                        .reduce(0, Integer::sum)
        );
        System.out.println("----");

        // 2: Använd collect för att returnera en map som håller varje stad som key och det totala antalet anställda
        // för respektive stad som value.
        System.out.println("Antalet anställda i varje stad: " +
                employeeList
                        .stream()
                        .collect(Collectors.groupingBy(Employee::getOccupation, Collectors.counting()))
        );
        System.out.println("----");


        // 3: Samma som ovan, men byt ut totala antalet anställda mot totala lönekostnaden.
        System.out.println("Totala lönekostnaden för varje stad: " +
                employeeList
                        .stream()
                        .collect(Collectors.groupingBy(
                                Employee::getOccupation, Collectors.summingDouble(Employee::getSalary)))
        );
        System.out.println("----");

        // 4: Samma som ovan, men byt ut den totala lönekostnaden mott snittlönen.
        System.out.println("Snittlönen för varje stad: " +
                employeeList
                        .stream()
                        .collect(Collectors.groupingBy(Employee::getOccupation,
                                Collectors.averagingInt(Employee::getSalary)))
        );
        System.out.println("----");

        // 5: Lagra två strömmar i två olika variabler som var för sig är strömmar av 4 eller fler siffror.
        Stream<Integer> streamOne = Stream.of(1, 2, 3, 4);
        Stream<Integer> streamTwo = Stream.of(5, 6, 7, 8);


        // 6: SLå ihop strömmarna med hjälp av concat.
        Stream.concat(streamOne, streamTwo).forEach(System.out::println);
        System.out.println("----");
        // 7: Skapa en Stream.of() fyra listor som argument.
        // Vad utgörs varje element i strömmen av?
        // Hur kan du istället skapa ens ström bestående respektive listas element samlade direkt.
        Stream.of(
                        List.of("Haris", "Urban"),
                        List.of("Karl", "Pelle"),
                        List.of("Håkan", "Lelle"),
                        List.of("Lars", "Mikael")
                ).flatMap(Collection::stream)
                .forEach(System.out::println);



    }
}
