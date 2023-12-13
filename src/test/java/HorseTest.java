import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    private final String name = "Blaze";
    private final int speed = 1;
    private final int distance = 45;

    private Horse horse;

    @BeforeEach
    void init() {
        horse = new Horse(name, speed, distance);
    }

    //1
    @Test
    void testConstructor_ShouldThrowIllegalArgumentException_WhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
    }

    //2
    @Test
    void testConstructor_ShouldReturnExceptionMessage_WhenNameIsNull() {
        String expectedMessage = "Name cannot be null.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    //3
    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t", "\n", "\f", "\r"})
    void testConstructor_ShouldThrowIllegalArgumentException_WhenNameContainsOnlyTabulationCharacters(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    //4
    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t", "\n", "\f", "\r"})
    void testConstructor_ShouldReturnExceptionMessage_WhenNameContainsOnlyTabulationCharacters(String name) {
        String expectedMessage = "Name cannot be blank.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    //5
    @Test
    void testConstructor_ShouldThrowIllegalArgumentException_WhenSpeedIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, -speed, distance));
    }

    //6
    @Test
    void testConstructor_ShouldReturnExceptionMessage_WhenSpeedIsNegative() {
        String expectedMessage = "Speed cannot be negative.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, -speed, distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    //7
    @Test
    void testConstructor_ShouldThrowIllegalArgumentException_WhenDistanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -distance));
    }

    //8
    @Test
    void testConstructor_ShouldReturnExceptionMessage_WhenDistanceIsNegative() {
        String expectedMessage = "Distance cannot be negative.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    //9
    @Test
    void testGetName_ShouldReturnName_WhenNameWasSetToTheConstructor() {
        String expectedName = "Blaze";
        String actualName = horse.getName();
        assertEquals(expectedName, actualName);
    }

    //10
    @Test
    void testGetSpeed_ShouldReturnSpeed_WhenSpeedWasSetToTheConstructor() {
        double expectedSpeed = 1;
        double actualSpeed = horse.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    //11
    @Test
    void testGetDistance_ShouldReturnDistance_WhenDistanceWasSetToTheConstructor() {
        double expectedDistance = 45;
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    //12
    @Test
    void testGetDistance_ShouldReturnZero_WhenConstructorContainsOnlyTheFirstTwoParameters() {
        int expected = 0;
        horse = new Horse(name, speed);
        assertEquals(expected, horse.getDistance());
    }

    //13
    @Test
    void testMove_ShouldInvokeGetRandomDoubleMethodWithSpecificParameters_WhenInvokeMoveMethod() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
        //Почему mockitoStatic должен быть в try-with-resources?
    }

    //14
    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.4, 0.5, 0.6, 0.7, 0.8})
    void testMove_() { //не сделал
        Horse horseMock = Mockito.mock(Horse.class);
    }

    //15


    @Test
    @SneakyThrows
    void test2() {
        String expectedName = "Blaze";

        Field field = horse.getClass().getDeclaredField("name");
        field.setAccessible(true);
        String actualName = (String) field.get(horse);

        assertEquals(expectedName, actualName);
    }
}