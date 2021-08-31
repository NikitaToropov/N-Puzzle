package resolvers;

import dto.Coordinate;
import dto.State;
import utils.BoardUtil;

import java.util.*;

public class Resolver {
    final Map<Integer, Coordinate> goal;
    final BoardUtil util;
    final State start;
    Queue<State> open;
    final Set<State> close;

    public Resolver(Map<Integer, Coordinate> goal, State start, BoardUtil util) {
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
        open = util.expandTheState(start);
        int i = 0;
//        while (i < 10 && tmp != null && tmp.f != tmp.g) {
        while (tmp != null && tmp.f != tmp.g) {
            close.add(tmp);
            tmp = getNewState(open, close);
            System.out.println();
            if (tmp == null)
                break;
            System.out.println("step n = " + tmp.g);
            BoardUtil.printState(tmp);

            close.addAll(open);
            open = util.expandTheState(tmp);
            i++;
        }
    }

    private State getNewState(Queue<State> open, Set<State> close) {
        State tmp;
        while (!open.isEmpty()) {
            tmp = open.remove();
            if (!close.contains(tmp)) {
                close.addAll(open);
                return tmp;
            }
            close.add(tmp);
        }
        return null;
    }

    public static Comparator<State> stateComparator = new Comparator<State>() {

        @Override
        public int compare(State firstState, State secondState) {
            return -(secondState.f - firstState.f);
        }
    };
}
