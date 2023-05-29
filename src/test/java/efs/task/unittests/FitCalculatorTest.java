package efs.task.unittests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }


    @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        // given
        double weight = 69.5;
        double height = 1.72;

        // when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(recommended);
    }


    @Test
    void shouldThrowIllegalArgumentException_whenHeightIsZero() {
        // given
        double weight = 89.2;
        double height = 0.0;

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            FitCalculator.isBMICorrect(weight, height);
        });
    }


    @ParameterizedTest(name = "BMI is correct for weight={0} and height={1}")
    @ValueSource(doubles = {80.,
        81.1,
        82.2})
    void shouldReturnTrue_forDifferentWeights(double weight) {
        // given
        double height = 1.72;

        // when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // then
        assertTrue(recommended);
    }


    @ParameterizedTest(name = "BMI is incorrect for weight={0} and height={1}")
    @CsvSource({
            "62.3, 1.72",
            "63.3, 1.74",
            "66.3, 1.76"
    })
    void shouldReturnFalse_forDifferentWeightsAndHeights(double weight, double height) {
        // when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(recommended);
    }


    @ParameterizedTest(name = "BMI is incorrect for weight={0} and height={1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_forDifferentWeightsAndHeightsFromFile(double weight, double height) {
        // when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(recommended);
    }


    @Test
    void shouldReturnUserWithWorstBMI() {
        // given
        List<User> users = TestConstants.TEST_USERS_LIST;
        User ExpectedUserWithWorstBMI = new User(1.79, 97.3);
        
        // when
        User ActualUserWithWorstBMI = FitCalculator.findUserWithTheWorstBMI(users);

        // then
        assertEquals(ExpectedUserWithWorstBMI.getHeight(), ActualUserWithWorstBMI.getHeight());
        assertEquals(ExpectedUserWithWorstBMI.getWeight(), ActualUserWithWorstBMI.getWeight());
    }


    @Test
    void shouldReturnNull_whenEmptyUserList() {
        // given
        List<User> emptyUsersList = new ArrayList<>();

        // when
        User userWithWorstBMI = FitCalculator.findUserWithTheWorstBMI(emptyUsersList);

        // then
        assertNull(userWithWorstBMI);
    }


    @Test
    void shouldCalculateBMIScore() {
        // given
        List<User> users = TestConstants.TEST_USERS_LIST;
        double[] expectedBMIScore = TestConstants.TEST_USERS_BMI_SCORE;

        // when
        double[] actualBMIScore = FitCalculator.calculateBMIScore(users);

        // then
        assertArrayEquals(expectedBMIScore, actualBMIScore);
    }
}