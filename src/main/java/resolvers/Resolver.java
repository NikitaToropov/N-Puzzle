package resolvers;

import dto.Goal;
import dto.State;
import utils.ResolvingUtil;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Resolver {
    final Goal goal;
    final ResolvingUtil util;
    final State start;
    Queue<State> open;
    final Set<State> close;

    public Resolver(Goal goal, State start, ResolvingUtil util) {
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
            tmp = getNextState(open, close);
            close.add(tmp);
        }

        for (State state = tmp; state != null; state = state.parent) {
            ResolvingUtil.printState(state);
        }
        System.out.println("num of steps = " + tmp.g);

    }

    /**
     * Возвращает первое по приоритету состояние которое еще не содержиться в close.
     */
    private State getNextState(Queue<State> open, Set<State> close) {
        while (!open.isEmpty()) {
            State tmp = open.remove();
            if (!close.contains(tmp)) {
                return tmp;
            }
        }
        return null;
    }
}
