package dto;

import utils.BoardUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Report {
    public List<State> states;
    public int sizeComplexity;
    public int timeComplexity;
    public long timeInterval;
    public int numOfSteps;

    public Report(State finish, int timeComplexity, int sizeComplexity, long timeInterval) {
        this.sizeComplexity = sizeComplexity;
        this.timeComplexity = timeComplexity;
        this.numOfSteps = finish.g;
        this.timeInterval = TimeUnit.NANOSECONDS.toMillis(timeInterval);
        this.states = createArrayOfStates(finish);
    }

    private List<State> createArrayOfStates(State state) {
        LinkedList<State> solution = new LinkedList<>();
        while (state != null) {
            solution.addFirst(state);
            state = state.parent;
        }
        return solution;
    }

    public void printResolvingResult() {
        System.out.println("================ REPORT STARTS HERE ===============");
        states.forEach(BoardUtil::printState);
        System.out.println();
        System.out.println("Size complexity = " + sizeComplexity);
        System.out.println("Time complexity = " + timeComplexity);
        System.out.println("Number of steps = " + numOfSteps);
        if (timeInterval > 100) {
            System.out.println("Working time = " + TimeUnit.MILLISECONDS.toSeconds(timeInterval) + " seconds");
        } else {
            System.out.println("Working time = " + timeInterval + " milliseconds");
        }
        System.out.println("================= REPORT ENDS HERE ================");
    }
}
