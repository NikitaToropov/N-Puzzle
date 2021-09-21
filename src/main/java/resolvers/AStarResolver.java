package resolvers;

import dto.Goal;
import dto.Report;
import dto.State;

import java.util.*;

public class AStarResolver implements Resolver{
    final Goal goal;
    final ResolvingHelper util;
    final State start;
    final Queue<State> open;
    final Set<State> close;
    private int timeComplexity = 0;
    private int sizeComplexity = 0;

    public AStarResolver(Goal goal, State start, ResolvingHelper util) {
        this.goal = goal;
        this.start = start;
        this.util = util;
        this.open = new PriorityQueue<>();
        this.close = new HashSet<>();
    }

    @Override
    public Report resolveIt() {
        long startTime = System.nanoTime();

        State tmp = start;
        while (tmp.f != tmp.g) {
            open.addAll(util.expandTheState(tmp));
            close.add(tmp);
            sizeComplexity = Math.max(open.size() + close.size(), sizeComplexity);
            tmp = getNextState(open, close);
        }
        long timeInterval = System.nanoTime() - startTime;
        return new Report(tmp, timeComplexity, sizeComplexity, timeInterval);
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
