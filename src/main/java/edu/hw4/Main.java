package edu.hw4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        Animal animal1 = new Animal("One", Animal.Type.CAT, Animal.Sex.M,
            5, 105, 10, true);

        Animal animal2 = new Animal("Two", Animal.Type.DOG, Animal.Sex.M,
            7, 12, 7, false);

        Animal animal3 = new Animal("Three", Animal.Type.BIRD, Animal.Sex.F,
            2, 4, 1, false);

        Animal animal4 = new Animal("Four boom", Animal.Type.FISH, Animal.Sex.M,
            3, 4, 3, false);

        Animal animal5 = new Animal("Five", Animal.Type.SPIDER, Animal.Sex.F,
            1, 1, 2, true);

        Animal animal6 = new Animal("Six", Animal.Type.SPIDER, Animal.Sex.F,
            -1, 1, -2, true);

        animals.add(animal1);
        animals.add(animal2);
        animals.add(animal3);
        animals.add(animal4);
        animals.add(animal5);
        animals.add(animal6);

        System.out.println(AnimalUtils.getSortedAnimalByHeight(animals));
        System.out.println(AnimalUtils.getReverseSortedAnimalByWeightAndChoseAmount(animals, 3));
        System.out.println(AnimalUtils.getAmountOfEveryAnimalType(animals));
        System.out.println(AnimalUtils.getAnimalLongestName(animals));
        System.out.println(AnimalUtils.getMostAnimalSex(animals));
        System.out.println(AnimalUtils.getMostWeightAnimalsOfEveryType(animals));
        System.out.println(AnimalUtils.getKsMostOlderAnimal(animals, 2));
        System.out.println(AnimalUtils.getMostWeightAnimalLowerKsHeight(animals, 10));
        System.out.println(AnimalUtils.getSumPaws(animals));
        System.out.println(AnimalUtils.getAnimalWithPawsUnequalToAge(animals));
        System.out.println(AnimalUtils.getAnimalsBitesAndHigherThan100cm(animals));
        System.out.println(AnimalUtils.getAnimalsWithWeightGreaterThanHeight(animals));
        System.out.println(AnimalUtils.getAnimalsWithNamesOverTwoWords(animals));
        System.out.println(AnimalUtils.getDogHigherThanKsCm(animals, 20));
        System.out.println(AnimalUtils.getEveryTypeSSumWeightFromKsToLsAge(animals, 2, 9));
        System.out.println(AnimalUtils.getSortedAnimalList(animals));
        System.out.println(AnimalUtils.isSpidersBitesOftenThanDogs(animals));
        System.out.println(AnimalUtils.getMostWeightFish(List.of(animals)));
        System.out.println(AnimalUtils.getNameAndListOfErrors(animals));
    }
}
