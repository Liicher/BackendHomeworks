package edu.project1;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

interface Preparation {
    @NotNull ArrayList<Character> randomWord();

    ArrayList<Character> userAnswer(ArrayList<Character> answer);

    ArrayList<Character> letters();
}
