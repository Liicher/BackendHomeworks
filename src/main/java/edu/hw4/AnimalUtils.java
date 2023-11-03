package edu.hw4;

import edu.hw4.errors.AgeError;
import edu.hw4.errors.HeightError;
import edu.hw4.errors.WeightError;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnimalUtils {

    /** Задание 1. */
    // Отсортировать животных по росту от самого маленького к самому большому -> List<Animal>
    public static List<Animal> getSortedAnimalByHeight(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingLong(Animal::height)).toList();
    }

    /** Задание 2. */
    // Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List<Animal>
    public static List<Animal> getReverseSortedAnimalByWeightAndChoseAmount(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparingLong(Animal::weight).reversed()).limit(k).toList();
    }

    /** Задание 3. */
    // Сколько животных каждого вида -> Map<Animal.Type, Integer>
    public static Map<Animal.Type, Long> getAmountOfEveryAnimalType(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    /** Задание 4. */
    // У какого животного самое длинное имя -> Animal
    public static Animal getAnimalLongestName(List<Animal> animals) {
        return animals.stream().max(Comparator.comparingInt(a -> a.name().length())).get();
    }

    /** Задание 5. */
    // Каких животных больше: самцов или самок -> Sex
    public static Animal.Sex getMostAnimalSex(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)).map(Map.Entry::getKey).get();
    }

    /** Задание 6. */
    // Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal>
    public static Map<Animal.Type, Animal> getMostWeightAnimalsOfEveryType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors
                .toMap(Animal::type, Function.identity(), (a1, a2) -> a1.weight() >= a2.weight() ? a1 : a2));
    }

    /** Задание 7. */
    // K-е самое старое животное -> Animal
    public static Animal getKsMostOlderAnimal(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparingLong(Animal::age).reversed()).skip(k - 1).findFirst().get();
    }

    /** Задание 8. */
    // Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
    public static Animal getMostWeightAnimalLowerKsHeight(List<Animal> animals, int k) {
        return animals.stream().filter(a -> a.height() < k)
            .max(Comparator.comparingLong(Animal::weight)).get();
    }

    /** Задание 9. */
    // Сколько в сумме лап у животных в списке -> Integer
    public static Integer getSumPaws(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    /** Задание 10. */
    // Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    public static List<Animal> getAnimalWithPawsUnequalToAge(List<Animal> animals) {
        return animals.stream().filter(a -> a.paws() != a.age()).toList();
    }

    /** Задача 11. */
    // Список животных, которые могут укусить (bites == true) и рост которых превышает 100 см -> List<Animal>
    public static List<Animal> getAnimalsBitesAndHigherThan100cm(List<Animal> animals) {
        return animals.stream().filter(Animal::bites).filter(a -> a.height() > 100).toList();
    }

    /** Задача 12. */
    // Сколько в списке животных, вес которых превышает рост -> Integer
    public static Long getAnimalsWithWeightGreaterThanHeight(List<Animal> animals) {
        return animals.stream().filter(a -> a.weight() > a.height()).count();
    }

    /** Задача 13. */
    // Список животных, имена которых состоят из более чем двух слов -> List<Animal>
    public static List<Animal> getAnimalsWithNamesOverTwoWords(List<Animal> animals) {
        return animals.stream().filter(a -> a.name().contains(" ")).toList();
    }

    /** Задача 14. */
    // Есть ли в списке собака ростом более k см -> Boolean
    public static Boolean getDogHigherThanKsCm(List<Animal> animals, int k) {
        return animals.stream().anyMatch(a -> a.type() == Animal.Type.DOG && a.height() > k);
    }

    /** Задача 15. */
    // Найти суммарный вес животных каждого вида, которым от k до l лет -> Map<Animal.Type, Integer>
    public static Map<Animal.Type, Integer> getEveryTypeSSumWeightFromKsToLsAge(List<Animal> animals, int k, int l) {
        return animals.stream().filter(a -> a.age() >= k && a.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    /** Задача 16. */
    // Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Animal>
    public static List<Animal> getSortedAnimalList(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::type)
            .thenComparing(Animal::sex)
            .thenComparing(Animal::name)).toList();
    }

    /** Задача 17. */
    // Правда ли, что пауки кусаются чаще, чем собаки -> Boolean (если данных для ответа недостаточно, вернуть false)
    public static Boolean isSpidersBitesOftenThanDogs(List<Animal> animals) {
        long spiders = animals.stream().filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites()).count();
        long dogs = animals.stream().filter(animal -> animal.type() == Animal.Type.DOG && animal.bites()).count();
        return spiders > dogs;
    }

    /** Задача 18. */
    // Найти самую тяжелую рыбку в 2-х или более списках -> Animal
    public static Animal getMostWeightFish(List<List<Animal>> animals) {
        return animals.stream().flatMap(List::stream).filter(a -> a.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight)).get();
    }

    /** Задача 19. */
    // Животные, в записях о которых есть ошибки: вернуть имя и список ошибок -> Map<String, Set<ValidationError>>.
    // Класс ValidationError и набор потенциальных проверок нужно придумать самостоятельно.
    public static Map<String, Set<ValidationError>> getNameAndListOfErrors(List<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(Animal::name, AnimalUtils::getValidationErrors));
    }
    // Задача 20.
    // Сделать результат предыдущего задания более читабельным: вернуть имя и названия полей с ошибками, объединенные в строку -> Map<String, String>
    public static Map<String, String> getNameAndErrors(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toMap(
                    Animal::name,
                    animal -> getValidationErrors(animal).stream().map(Throwable::toString)
                        .collect(Collectors.joining(", "))
                ),
                map -> {
                    map.values().removeIf(String::isEmpty);
                    return map;
                }
            ));
    }

    public static Set<ValidationError> getValidationErrors(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (animal.age() < 0) {
            errors.add(new AgeError("Invalid age! Must be positive!"));
        }
        if (animal.height() <= 0) {
            errors.add(new HeightError("Invalid height! Must be greater than zero!"));
        }
        if (animal.weight() <= 0) {
            errors.add(new WeightError("Invalid weight! Must be greater than zero!"));
        }
        return errors;
    }
}

