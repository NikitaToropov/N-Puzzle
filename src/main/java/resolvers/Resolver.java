package resolvers;

import dto.Goal;
import dto.Report;
import dto.State;

import java.util.*;

public class Resolver {
    final Goal goal;
    final ResolvingHelper util;
    final State start;
    final Queue<State> open;
    final Set<State> close;
    private int timeComplexity = 0;
    private int sizeComplexity = 0;

    public Resolver(Goal goal, State start, ResolvingHelper util) {
        this.goal = goal;
        this.start = start;
        this.util = util;
        this.open = new PriorityQueue<>();
        this.close = new HashSet<>();
    }

    public void resolveIt() {
        System.out.println();
        System.out.println();
        System.out.println("======== RESOLVING STARTS HERE ========");
        long startTime = System.nanoTime();

        State tmp = start;
        while (tmp.f != tmp.g) {
            open.addAll(util.expandTheState(tmp));
            close.add(tmp);
            sizeComplexity = Math.max(open.size() + close.size(), sizeComplexity);
            tmp = getNextState(open, close);
        }

        new Report(tmp, timeComplexity, sizeComplexity);
        long finishTime = System.nanoTime();
        System.out.println("==========================================");
        System.out.println("time = " + (finishTime - startTime) / 1_000_000  + " миллисекунд");
    }

    /**
     * Возвращает первое по приоритету состояние которое еще не содержится в close.
     */
    private State getNextState(Queue<State> open, Set<State> close) {
        while (!open.isEmpty()) {
            State tmp = open.remove();
            timeComplexity += 1;
            if (!close.contains(tmp)) {
                return tmp;
            }
        }
        return null;
    }
}
