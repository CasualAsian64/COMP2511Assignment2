package unsw.loopmania;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Statistics for each entity
 */
public class Goals {
    private int ANDLoops;
    private int ANDGold;
    private int ANDExp;
    private int ORLoops;
    private int ORGold;
    private int ORExp;
    private JSONObject allGoals;
    private boolean ORGoalsActive;

    private SimpleIntegerProperty ANDLoopsValue = new SimpleIntegerProperty(this, "ANDLoops");
    private SimpleIntegerProperty ANDGoldValue = new SimpleIntegerProperty(this, "ANDGold");
    private SimpleIntegerProperty ANDExpValue = new SimpleIntegerProperty(this, "ANDExp");

    private SimpleIntegerProperty ORLoopsValue = new SimpleIntegerProperty(this, "ORLoops");
    private SimpleIntegerProperty ORGoldValue = new SimpleIntegerProperty(this, "ORGold");
    private SimpleIntegerProperty ORExpValue = new SimpleIntegerProperty(this, "ORExp");

    public Goals(JSONObject goal_condition){
        this.ANDLoops = 0;
        this.ANDGold = 0;
        this.ANDExp = 0;
        this.ORLoops = 0;
        this.ORGold = 0;
        this.ORExp = 0;
        // this.ANDLoopsValue.set(0);
        // this.ANDGoldValue.set(0);
        // this.ANDExpValue.set(0);
        this.ORGoalsActive = false;
        allGoals = new JSONObject();
        int quantity = 0;

        String goal = goal_condition.getString("goal");
        if (goal.equals("AND")) {
            JSONArray ANDGoals = goal_condition.getJSONArray("subgoals");
            for (int i = 0; i < ANDGoals.length(); i++) {
                JSONObject ANDGoal = ANDGoals.getJSONObject(i);
                goal = ANDGoal.getString("goal");
                if (goal.equals("OR")) {
                    JSONArray ORGoals = ANDGoal.getJSONArray("subgoals");
                    ORGoalsActive = true;
                    for (int j = 0; j < ORGoals.length(); j++) {
                        JSONObject ORGoal = ORGoals.getJSONObject(j);
                        goal = "OR" + ORGoal.getString("goal");
                        quantity = ORGoal.getInt("quantity");
                        this.setGoals(goal, quantity);
                    }
                } else {
                    quantity = ANDGoal.getInt("quantity");
                    this.setGoals(goal, quantity);
                }
            }
        } else {
            quantity = goal_condition.getInt("quantity");
            this.setGoals(goal, quantity);
        }
        allGoals.put("ANDLoops", ANDLoops);
        allGoals.put("ANDExp", ANDExp);
        allGoals.put("ANDGold", ANDGold);
        allGoals.put("ORLoops", ORLoops);
        allGoals.put("ORExp", ORExp);
        allGoals.put("ORGold", ORGold);

        setANDLoopsValueProperty(this.ANDLoops);
        setANDGoldValueProperty(this.ANDGold);
        setANDExpValueProperty(this.ANDExp);

        setORLoopsValueProperty(this.ORLoops);
        setORGoldValueProperty(this.ORGold);
        setORExpValueProperty(this.ORExp);

    }

    // Sets the goals for the character to achieve
    public void setGoals(String goal, int quantity) {
        switch(goal) {
            case "cycles":
                this.ANDLoops = quantity;
                // this.ANDLoopsValue.set(quantity);
                // setANDLoopsValueProperty(quantity);
                break;
            case "gold":
                this.ANDGold = quantity;
                // this.ANDGoldValue.set(quantity);
                // setANDGoldValueProperty(quantity);
                break;
            case "experience":
                this.ANDExp = quantity;
                // this.ANDExpValue.set(quantity);
                // setANDExpValueProperty(quantity);
                break;
            case "ORcycles":
                this.ORLoops = quantity;
                break;
            case "ORgold":
                this.ORGold = quantity;
                break;
            case "ORexperience":
                this.ORExp = quantity;
                break;
        }
    }

    // Prints out all goals for the world
    public void printAllGoals() {
        System.out.println("Mandatory goals");
        System.out.println("Loops required: " + ANDLoops);
        System.out.println("Experience required: " + ANDExp);
        System.out.println("Gold required: " + ANDGold);
        System.out.println();
        System.out.println("Optional goals");
        System.out.println("Loops: " + ORLoops);
        System.out.println("Experience : " + ORExp);
        System.out.println("Gold : " + ORGold);
        System.out.println();
    }

    // Checks if the goals have been met
    public boolean checkGoalsMet(Statistics stats, int numLoops) {
        int exp = stats.getExp();
        int gold = stats.getGold();
        if(checkANDGoals(exp, gold, numLoops)) {
            if (ORGoalsActive) {
                if (checkORGoals(exp, gold, numLoops)){
                    //System.out.println("====================");
                    //System.out.println("You've won the game!");
                    //System.out.println("====================");
                    return true;
                }
            } else {
                //System.out.println("====================");
                //System.out.println("You've won the game!");
                //System.out.println("====================");
                return true;
            }
        }
        return false;
    }

    public boolean checkStats(int current, int target) {
        return current >= target;
    }

    public boolean checkORGoals(int exp, int gold, int numLoops) {
        if ((ORLoops > 0 && checkStats(numLoops, ORLoops)) || (ORExp > 0 && checkStats(exp, ORExp)) || (ORGold > 0 && checkStats(gold, ORGold))) {
            return true;
        }
        return false;
    }

    public boolean checkANDGoals(int exp, int gold, int numLoops) {
        if (checkStats(exp, ANDExp) && checkStats(gold, ANDGold) && checkStats(numLoops, ANDLoops)) {
            return true;
        }
        return false;
    }

    public JSONObject getAllGoals() {
        return allGoals;
    }

    public IntegerProperty ANDLoopsValueProperty(){
        return ANDLoopsValue;
    }

    public IntegerProperty ANDGoldValueProperty(){
        return ANDGoldValue;
    }
    public IntegerProperty ANDExpValueProperty(){
        return ANDExpValue;
    }

    public void setANDLoopsValueProperty(int loops){ 
        this.ANDLoopsValueProperty().set(loops);
    }
    public void setANDGoldValueProperty(int gold){ 
        this.ANDGoldValueProperty().set(gold);
    }
    public void setANDExpValueProperty(int exp){ 
        this.ANDExpValueProperty().set(exp);
    }



    public IntegerProperty ORLoopsValueProperty(){
        return ORLoopsValue;
    }

    public IntegerProperty ORGoldValueProperty(){
        return ORGoldValue;
    }
    public IntegerProperty ORExpValueProperty(){
        return ORExpValue;
    }


    public void setORLoopsValueProperty(int loops){ 
        this.ORLoopsValueProperty().set(loops);
    }
    public void setORGoldValueProperty(int gold){ 
        this.ORGoldValueProperty().set(gold);
    }
    public void setORExpValueProperty(int exp){ 
        this.ORExpValueProperty().set(exp);
    }
}


