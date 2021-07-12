package test;

import org.json.JSONObject;
import org.junit.Test;

import unsw.loopmania.Goals;
import unsw.loopmania.Statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class GoalsTest {
    /**
    * Unit tests for goals
    */

    /**
     * Checks if we read in MAP 1 goals accurately from a JSON object
     */
    @Test
    public void test_MAP1_goals(){
        Helper helper = new Helper();
        Goals world_goals = new Goals(helper.goalCondition1());
        world_goals.printAllGoals();
        JSONObject world_goals_JSONobject = world_goals.getAllGoals();
        assertEquals(world_goals_JSONobject.getInt("ANDLoops"), 0);
        assertEquals(world_goals_JSONobject.getInt("ANDExp"), 100);
        assertEquals(world_goals_JSONobject.getInt("ANDGold"), 0);
        assertEquals(world_goals_JSONobject.getInt("ORLoops"), 0);
        assertEquals(world_goals_JSONobject.getInt("ORExp"), 123456);
        assertEquals(world_goals_JSONobject.getInt("ORGold"), 1000);
    }

    /**
     * Checks if we read in MAP 2 goals accurately from a JSON object
     */
    @Test
    public void test_MAP2_goals(){
        Helper helper = new Helper();
        Goals world_goals = new Goals(helper.goalCondition2());
        world_goals.printAllGoals();
        JSONObject world_goals_JSONobject = world_goals.getAllGoals();
        assertEquals(world_goals_JSONobject.getInt("ANDLoops"), 0);
        assertEquals(world_goals_JSONobject.getInt("ANDExp"), 123456);
        assertEquals(world_goals_JSONobject.getInt("ANDGold"), 0);
        assertEquals(world_goals_JSONobject.getInt("ORLoops"), 0);
        assertEquals(world_goals_JSONobject.getInt("ORExp"), 0);
        assertEquals(world_goals_JSONobject.getInt("ORGold"), 0);
    }


    /**
    * Integration tests for goals
    */

    /**
     * Check if the goals are met and return that the player has won
     */
    @Test
    public void test_goals_met(){
        Helper helper = new Helper();
        Goals world_goals = new Goals(helper.goalCondition1());
        JSONObject world_goals_JSONobject = world_goals.getAllGoals();
        assertEquals(world_goals_JSONobject.getInt("ANDLoops"), 0);
        assertEquals(world_goals_JSONobject.getInt("ANDExp"), 100);
        assertEquals(world_goals_JSONobject.getInt("ANDGold"), 0);
        assertEquals(world_goals_JSONobject.getInt("ORLoops"), 0);
        assertEquals(world_goals_JSONobject.getInt("ORExp"), 123456);
        assertEquals(world_goals_JSONobject.getInt("ORGold"), 1000);

        // No conditions are met, so therefore will return false
        Statistics statistic = new Statistics(100, 10, 0, 0, 0);
        int numLoops = 0;
        assertEquals(world_goals.checkGoalsMet(statistic, numLoops), false);

        // Only one condition is met, but the additional condition isn't met so therefore will return false
        statistic = new Statistics(100, 10, 100, 0, 0);
        assertEquals(world_goals.checkGoalsMet(statistic, numLoops), false);

        statistic = new Statistics(100, 10, 0, 0, 1000);
        assertEquals(world_goals.checkGoalsMet(statistic, numLoops), false);

        // Both conditions are met, and will return true
        statistic = new Statistics(100, 10, 0, 123456, 0);
        assertEquals(world_goals.checkGoalsMet(statistic, numLoops), true);

    }
}
