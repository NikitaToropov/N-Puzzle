package resolvers;

import dto.Goal;
import dto.State;
import utils.BoardUtil;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Resolver {
    final Goal goal;
    final BoardUtil util;
    final State start;
    final Queue<State> open;
    final Set<State> close;

    public Resolver(Goal goal, State start, BoardUtil util) {
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
            close.add(tmp);
            tmp = getNewState(open, close);
//            BoardUtil.printState(tmp);
            open.addAll(util.expandTheState(tmp)); // TODO зачем 2 раза
        }

        for (State state = tmp; state != null; state = state.parent) {
            BoardUtil.printState(state);
        }
        System.out.println("num of steps = " + tmp.g);

    }

    private State getNewState(Queue<State> open, Set<State> close) {
        while (!open.isEmpty()) {
            State tmp = open.remove();
            if (!close.contains(tmp)) {
                return tmp;
            }
            close.add(tmp); // TODO в этом, кажется нет смысла
        }
        return null;
    }
}
