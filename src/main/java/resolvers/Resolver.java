package resolvers;

import dto.Goal;
import dto.Report;
import dto.State;
import utils.ResolvingHelper;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Resolver {
    final Goal goal;
    final ResolvingHelper util;
    final State start;
    Queue<State> open;
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

        State tmp = start;
        while (tmp.f != tmp.g) {
            open.addAll(util.expandTheState(tmp));
            sizeComplexity = Math.max(open.size() + close.size(), sizeComplexity);
            tmp = getNextState(open, close);
            close.add(tmp);
        }

        new Report(tmp, timeComplexity, sizeComplexity);
//        for (State state = tmp; state != null; state = state.parent) {
//            BoardUtil.printState(state);
//        }
//        System.out.println("num of steps = " + tmp.g);

    }

    /**
     * Возвращает первое по приоритету состояние которое еще не содержиться в close.
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
