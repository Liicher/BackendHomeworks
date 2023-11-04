package edu.hw4;

import edu.hw4.errors.AgeError;
import edu.hw4.errors.HeightError;
import edu.hw4.errors.WeightError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class AnimalUtilsTest {
    private List<Animal> animals;

    @BeforeEach
    public void init() {
        animals = new ArrayList<>();
    }

    @Test
    @DisplayName("Task 1")
    public void getSortedAnimalByHeight() {
        animals.add(Animal.builder().height(20).build());
        animals.add(Animal.builder().height(40).build());
        animals.add(Animal.builder().height(10).build());
        animals.add(Animal.builder().height(30).build());

        List<Integer> response = AnimalUtils.getSortedAnimalByHeight(animals)
            .stream().map(Animal::height).toList();
        assertThat(response).containsExactly(10, 20, 30, 40);
    }

    @Test
    @DisplayName("Task 2")
    void getReverseSortedAnimalByWeightAndChoseAmount() {
        animals.add(Animal.builder().weight(20).build());
        animals.add(Animal.builder().weight(40).build());
        animals.add(Animal.builder().weight(10).build());
        animals.add(Animal.builder().weight(30).build());

        List<Integer> response =
            AnimalUtils.getReverseSortedAnimalByWeightAndChoseAmount(animals, 3)
                .stream().map(Animal::weight).toList();
        assertThat(response).containsExactly(40, 30, 20);
    }

    @Test
    @DisplayName("Task 3")
    void getAmountOfEveryAnimalType() {
        animals.add(Animal.builder().type(Animal.Type.DOG).build());
        animals.add(Animal.builder().type(Animal.Type.CAT).build());
        animals.add(Animal.builder().type(Animal.Type.DOG).build());
        animals.add(Animal.builder().type(Animal.Type.FISH).build());
        animals.add(Animal.builder().type(Animal.Type.SPIDER).build());
        animals.add(Animal.builder().type(Animal.Type.FISH).build());
        animals.add(Animal.builder().type(Animal.Type.DOG).build());

        Map<Animal.Type, Long> response = AnimalUtils.getAmountOfEveryAnimalType(animals);
        assertThat(response).contains(
            entry(Animal.Type.DOG, 3L),
            entry(Animal.Type.CAT, 1L),
            entry(Animal.Type.FISH, 2L),
            entry(Animal.Type.SPIDER, 1L)
        );
    }

    @Test
    @DisplayName("Task 4")
    void getAnimalLongestName() {
        animals.add(Animal.builder().name("Bob").build());
        animals.add(Animal.builder().name("Roberto").build());
        animals.add(Animal.builder().name("Ayaks").build());
        animals.add(Animal.builder().name("Lesley").build());

        Animal animal = AnimalUtils.getAnimalLongestName(animals);
        assertThat(animal.name()).isEqualTo("Roberto");
    }

    @Test
    @DisplayName("Task 5")
    void getMostAnimalSex() {
        animals.add(Animal.builder().sex(Animal.Sex.M).build());
        animals.add(Animal.builder().sex(Animal.Sex.M).build());
        animals.add(Animal.builder().sex(Animal.Sex.F).build());
        animals.add(Animal.builder().sex(Animal.Sex.M).build());

        Animal.Sex response = AnimalUtils.getMostAnimalSex(animals);
        assertThat(response).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Task 6")
    void getMostWeightAnimalsOfEveryType() {
        animals.add(Animal.builder().type(Animal.Type.DOG).weight(20).build());
        animals.add(Animal.builder().type(Animal.Type.CAT).weight(40).build());
        animals.add(Animal.builder().type(Animal.Type.DOG).weight(10).build());
        animals.add(Animal.builder().type(Animal.Type.FISH).weight(30).build());
        animals.add(Animal.builder().type(Animal.Type.SPIDER).weight(70).build());
        animals.add(Animal.builder().type(Animal.Type.FISH).weight(35).build());
        animals.add(Animal.builder().type(Animal.Type.DOG).weight(5).build());

        Map<Animal.Type, Animal> response = AnimalUtils.getMostWeightAnimalsOfEveryType(animals);
        assertThat(response).contains(
            entry(Animal.Type.DOG, Animal.builder().type(Animal.Type.DOG).weight(20).build()),
            entry(Animal.Type.CAT, Animal.builder().type(Animal.Type.CAT).weight(40).build()),
            entry(Animal.Type.FISH, Animal.builder().type(Animal.Type.FISH).weight(35).build()),
            entry(Animal.Type.SPIDER, Animal.builder().type(Animal.Type.SPIDER).weight(70).build())
        );
    }

    @Test
    @DisplayName("Task 7")
    void getKsMostOlderAnimal() {
        animals.add(Animal.builder().age(20).build());
        animals.add(Animal.builder().age(40).build());
        animals.add(Animal.builder().age(10).build());
        animals.add(Animal.builder().age(30).build());

        Animal response = AnimalUtils.getKsMostOlderAnimal(animals, 2);
        assertThat(response.age()).isEqualTo(30);
    }

    @Test
    @DisplayName("Task 8")
    void getMostWeightAnimalLowerKsHeight() {
        animals.add(Animal.builder().height(10).weight(20).build());
        animals.add(Animal.builder().height(20).weight(40).build());
        animals.add(Animal.builder().height(30).weight(10).build());
        animals.add(Animal.builder().height(40).weight(30).build());

        Animal response = AnimalUtils.getMostWeightAnimalLowerKsHeight(animals, 31);
        assertThat(response).isEqualTo(Animal.builder().height(20).weight(40).build());
    }

    @Test
    @DisplayName("Task 9")
    void getSumPaws() {
        animals.add(Animal.builder().type(Animal.Type.DOG).build());
        animals.add(Animal.builder().type(Animal.Type.CAT).build());
        animals.add(Animal.builder().type(Animal.Type.SPIDER).build());
        animals.add(Animal.builder().type(Animal.Type.FISH).build());

        Integer response = AnimalUtils.getSumPaws(animals);
        assertThat(response).isEqualTo(16);
    }

    @Test
    @DisplayName("Task 10")
    void getAnimalWithPawsUnequalToAge() {
        animals.add(Animal.builder().age(2).type(Animal.Type.DOG).build());
        animals.add(Animal.builder().age(4).type(Animal.Type.CAT).build());
        animals.add(Animal.builder().age(1).type(Animal.Type.SPIDER).build());
        animals.add(Animal.builder().age(3).type(Animal.Type.FISH).build());

        List<Animal> response = AnimalUtils.getAnimalWithPawsUnequalToAge(animals);
        assertThat(response.stream().map(Animal::age).toList()).containsExactly(2, 1, 3);
    }

    @Test
    @DisplayName("Task 11")
    void getAnimalsBitesAndHigherThan100cm() {
        animals.add(Animal.builder().bites(true).height(140).build());
        animals.add(Animal.builder().bites(false).height(20).build());
        animals.add(Animal.builder().bites(false).height(10).build());
        animals.add(Animal.builder().bites(true).height(30).build());

        List<Animal> response = AnimalUtils.getAnimalsBitesAndHigherThan100cm(animals);
        assertThat(response).hasSize(1);
    }

    @Test
    @DisplayName("Task 12")
    void getAnimalsWithWeightGreaterThanHeight() {
        animals.add(Animal.builder().height(10).weight(20).build());
        animals.add(Animal.builder().height(20).weight(40).build());
        animals.add(Animal.builder().height(30).weight(10).build());
        animals.add(Animal.builder().height(40).weight(30).build());

        Long response = AnimalUtils.getAnimalsWithWeightGreaterThanHeight(animals);
        assertThat(response).isEqualTo(2);
    }

    @Test
    @DisplayName("Task 13")
    void getAnimalsWithNamesOverTwoWords() {
        animals.add(Animal.builder().name("Bob Brown").build());
        animals.add(Animal.builder().name("Roberto").build());
        animals.add(Animal.builder().name("Ayaks Smith").build());
        animals.add(Animal.builder().name("Lesley").build());

        List<Animal> response = AnimalUtils.getAnimalsWithNamesOverTwoWords(animals);
        assertThat(response.stream().map(Animal::name).toList()).containsExactly("Bob Brown", "Ayaks Smith");
    }

    @Test
    @DisplayName("Task 14")
    void getDogHigherThanKsCm() {
        animals.add(Animal.builder().type(Animal.Type.DOG).height(10).build());
        animals.add(Animal.builder().type(Animal.Type.CAT).height(20).build());
        animals.add(Animal.builder().type(Animal.Type.DOG).height(130).build());
        animals.add(Animal.builder().type(Animal.Type.BIRD).height(40).build());

        Boolean response = AnimalUtils.getDogHigherThanKsCm(animals, 100);
        assertThat(response).isTrue();
    }

    @Test
    @DisplayName("Task 15")
    void getEveryTypesSumWeightFromKsToLsAge() {
        animals.add(Animal.builder().type(Animal.Type.DOG).weight(20).age(6).build());
        animals.add(Animal.builder().type(Animal.Type.CAT).weight(30).age(1).build());
        animals.add(Animal.builder().type(Animal.Type.DOG).weight(10).age(9).build());
        animals.add(Animal.builder().type(Animal.Type.FISH).weight(40).age(3).build());

        Map<Animal.Type, Integer> response = AnimalUtils.getEveryTypesSumWeightFromKsToLsAge(animals, 2, 7);
        assertThat(response).contains(
            entry(Animal.Type.DOG, 20),
            entry(Animal.Type.FISH, 40)
        );
    }

    @Test
    @DisplayName("Task 16")
    void getSortedAnimalList() {
        animals.add(Animal.builder().type(Animal.Type.DOG).sex(Animal.Sex.M).name("Dog").build());
        animals.add(Animal.builder().type(Animal.Type.CAT).sex(Animal.Sex.M).name("Cat").build());
        animals.add(Animal.builder().type(Animal.Type.DOG).sex(Animal.Sex.F).name("Dog2").build());
        animals.add(Animal.builder().type(Animal.Type.BIRD).sex(Animal.Sex.F).name("Bird").build());

        List<Animal> actual = AnimalUtils.getSortedAnimalList(animals);
        assertThat(actual).containsExactly(
            Animal.builder().type(Animal.Type.CAT).sex(Animal.Sex.M).name("Cat").build(),
            Animal.builder().type(Animal.Type.DOG).sex(Animal.Sex.M).name("Dog").build(),
            Animal.builder().type(Animal.Type.DOG).sex(Animal.Sex.F).name("Dog2").build(),
            Animal.builder().type(Animal.Type.BIRD).sex(Animal.Sex.F).name("Bird").build()
        );
    }

    @Test
    @DisplayName("Task 17")
    void isSpidersBitesOftenThanDogs() {
        animals.add(Animal.builder().type(Animal.Type.SPIDER).bites(true).build());
        animals.add(Animal.builder().type(Animal.Type.DOG).bites(false).build());
        animals.add(Animal.builder().type(Animal.Type.SPIDER).bites(true).build());
        animals.add(Animal.builder().type(Animal.Type.DOG).bites(true).build());

        Boolean response = AnimalUtils.isSpidersBitesOftenThanDogs(animals);
        assertThat(response).isTrue();
    }

    @Test
    @DisplayName("Task 18")
    void getMostWeightFish() {
        animals.add(Animal.builder().type(Animal.Type.FISH).weight(20).build());
        animals.add(Animal.builder().type(Animal.Type.DOG).weight(30).build());

        List<Animal> animals1 = new ArrayList<>();
        animals1.add(Animal.builder().type(Animal.Type.SPIDER).weight(40).build());

        List<Animal> animals2 = new ArrayList<>();
        animals1.add(Animal.builder().type(Animal.Type.FISH).weight(10).build());

        List<Animal> animals3 = new ArrayList<>();
        animals1.add(Animal.builder().type(Animal.Type.CAT).weight(10).build());

        List<List<Animal>> animalsList = new ArrayList<>();
        animalsList.add(animals);
        animalsList.add(animals1);
        animalsList.add(animals2);
        animalsList.add(animals3);

        Animal response = AnimalUtils.getMostWeightFish(animalsList);
        assertThat(response).isEqualTo(animals.get(0));
    }

    @Test
    @DisplayName("Task 19")
    void getNameAndListOfErrors() {
        animals.add(Animal.builder().name("1").age(-1).height(-1).weight(-1).build());
        Map<String, Set<ValidationError>> result = new HashMap<>();
        result.put("1", Set.of(
            new AgeError("Invalid age!"),
            new HeightError("Invalid height!"),
            new WeightError("Invalid weight!")
        ));

        Map<String, Set<ValidationError>> response = AnimalUtils.getNameAndListOfErrors(animals);
        assertThat(response).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(result);
    }

    @Test
    @DisplayName("Task 20")
    void getNameAndErrors() {
        List<Animal> animals2 = new ArrayList<>();
        animals2.add(Animal.builder().name("2").weight(1).height(1).age(-1).build());
        Map<String, String> response2 = AnimalUtils.getNameAndErrors(animals2);
        Map<String, String> result2 = new HashMap<>();

        animals.add(Animal.builder().name("1").weight(-1).height(-1).age(-1).build());
        Map<String, String> response1 = AnimalUtils.getNameAndErrors(animals);
        Map<String, String> result1 = new HashMap<>();

        result1.put("1", "Invalid height!, Invalid age!, Invalid weight!");
        result2.put("2", "Invalid age!");

        assertThat(response1).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(result1);
        assertThat(response2).isEqualTo(result2);
    }
}
