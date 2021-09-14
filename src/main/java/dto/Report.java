package dto;

import utils.BoardUtil;

import java.util.LinkedList;
import java.util.List;

public class Report {
    public List<State> states;
    public int sizeComplexity;
    public int timeComplexity;
    public int numOfSteps;

    public Report(State finish, int timeComplexity, int sizeComplexity) {
        this.sizeComplexity = sizeComplexity;
        this.timeComplexity = timeComplexity;
        this.numOfSteps = finish.g;
        this.states = createArrayOfStates(finish);
        System.out.println("========== REPORT STARTS HERE =========");
        states.forEach(BoardUtil::printState);
        System.out.println("size complexity = " + sizeComplexity);
        System.out.println("time complexity = " + timeComplexity);
        System.out.println("number of steps = " + numOfSteps);
        System.out.println("=========== REPORT ENDS HERE ==========");
    }

    private List<State> createArrayOfStates(State state) {
        LinkedList<State> solution = new LinkedList<>();
        while (state != null) {
            solution.addFirst(state);
            state = state.parent;
        }
        return solution;
    }
}
