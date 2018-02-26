package fr.maxime.main;

import fr.maxime.lawnmower.Lawn;
import fr.maxime.lawnmower.Maneuver;
import fr.maxime.reader.LawnFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Lawn lawn = LawnFileReader.read(args[0]);
        List<Maneuver> maneuvers = lawn.performFinalPositionOfControllableMower();
        maneuvers.forEach(System.out::println);
    }
}
