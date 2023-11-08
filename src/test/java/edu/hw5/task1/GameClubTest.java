package edu.hw5.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class GameClubTest {
    private GameClub gameClub;

    @BeforeEach
    void init() {
        this.gameClub = new GameClub();
    }

    @Test
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> gameClub.getAveragePlayTime(null));
        assertThrows(IllegalArgumentException.class, () -> gameClub.getAveragePlayTime(List.of()));
        assertThrows(DateTimeParseException.class, () -> gameClub.getAveragePlayTime(List.of("2022-04-01, 21:30 - 2022-04-02, 25:20")));
        assertThrows(DateTimeParseException.class, () -> gameClub.getAveragePlayTime(List.of("2022-20-01, 21:30 - 2022-04-02, 22:20")));
        assertThrows(DateTimeParseException.class, () -> gameClub.getAveragePlayTime(List.of("2022-04-35, 21:30 - 2022-04-02, 22:20")));
    }

    @Test
    void givenExample() {
        List<String> input = new ArrayList<>();
        input.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        input.add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        Duration response = gameClub.getAveragePlayTime(input);
        assertThat(response).isEqualTo(Duration.ofSeconds(13200));
    }
}
