package resolvers.astar;

import dto.Coordinate;
import dto.State;
import resolvers.Resolver;
import utils.BoardUtil;

import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarResolver implements Resolver {
    final Map<Integer, Coordinate> goal;
    final State start;
    final BoardUtil util;
    PriorityQueue<State> open;
    final Set<State> close;

    public AStarResolver(Map<Integer, Coordinate> goal, State start, BoardUtil util) {
        this.goal = goal;
        this.start = start;
        this.util = util;
        this.open = new PriorityQueue<>();
        this.close = new HashSet<>();
    }

    @Override
    public void resolveIt() {
        System.out.println();
        System.out.println();
        System.out.println("======== RESOLVING STARTS HERE ========");

        State tmp = start;
        open = util.expandTheState(start);
        while (tmp.f != tmp.g && tmp != null) {
            close.add(tmp);
            tmp = getNewState(open, close);
            System.out.println();
            System.out.println("step n = " + tmp.g);
            BoardUtil.printState(tmp);

            close.addAll(open);
            open = util.expandTheState(tmp);
        }
    }

    private State getNewState(PriorityQueue<State> open, Set<State> close) {
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
}
