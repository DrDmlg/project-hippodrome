import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    //1
    @Test
    void testConstructor_ShouldThrowIllegalArgumentException_WhenListIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    //2
    @Test
    void testConstructor_ShouldReturnExceptionMessage_WhenListIsNull() {
        String expectedMessage = "Horses cannot be null.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals(expectedMessage, exception.getMessage());
    }

    //3
    @Test
    void testConstructor_ShouldThrowIllegalArgumentException_WhenListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
    }

    //4
    @Test
    void testConstructor_ShouldReturnExceptionMessage_WhenListIsEmpty() {
        String expectedMessage = "Horses cannot be empty.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
        assertEquals(expectedMessage, exception.getMessage());
    }

    //5
    @Test
    public void testGetHorses_ShouldReturnUnmodifiableList_WhenValuesAreSetToTheList() {
        List<Horse> horses = generationHorses();
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    private List<Horse> generationHorses() {
        return IntStream.range(1, 31)
                .mapToObj(i -> new Horse("Horse" + i, i * 2, i * 3))
                .collect(Collectors.toList());
    }
}