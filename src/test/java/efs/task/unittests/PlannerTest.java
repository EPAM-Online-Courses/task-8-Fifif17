package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {
    private Planner planner;

    @BeforeEach
    void setUp() {
        planner = new Planner();
    }

    
    @ParameterizedTest(name = "Calculate daily calories demand for activity level: {0}")
    @EnumSource(ActivityLevel.class)
    void shouldCalculateDailyCaloriesDemand(ActivityLevel activityLevel) {
        // given
        double expectedCalories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);
        User user = TestConstants.TEST_USER;

        // when
        int calculatedCalories = planner.calculateDailyCaloriesDemand(user, activityLevel);

        // then
        assertEquals(expectedCalories, calculatedCalories);
    }


    @Test
    void shouldCalculateDailyIntake() {
        // given
        DailyIntake expectedIntake = TestConstants.TEST_USER_DAILY_INTAKE;
        User user = TestConstants.TEST_USER;

        // when
        DailyIntake calculatedIntake = planner.calculateDailyIntake(user);

        // then
        // assertEquals(expectedIntake, calculatedIntake);
        assertEquals(expectedIntake.getCalories(), calculatedIntake.getCalories());
        assertEquals(expectedIntake.getProtein(), calculatedIntake.getProtein());
        assertEquals(expectedIntake.getFat(), calculatedIntake.getFat());
        assertEquals(expectedIntake.getCarbohydrate(), calculatedIntake.getCarbohydrate());

    }
}
