package edu.project1;

import java.util.List;
import org.jetbrains.annotations.NotNull;

interface Preparation {
    @NotNull List<Character> randomWord();

    List<Character> userAnswer(List<Character> answer);

    List<Character> letters();
}
