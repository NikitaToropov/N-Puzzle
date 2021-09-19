package resolvers;

import dto.Goal;
import dto.Report;
import dto.State;

import java.util.PriorityQueue;

public class IDAStarResolver {
    final Goal goal;
    final ResolvingHelper helper;
    final State start;
    private int threshold;
//    Queue<State> open;
//    final Set<State> close;

    public IDAStarResolver(Goal goal, State start, ResolvingHelper helper) {
        this.goal = goal;
        this.start = start;
        this.helper = helper;
//        this.open = new PriorityQueue<>();
//        this.close = new HashSet<>();
        this.threshold = start.f;
    }

    public void resolveIt() {
        System.out.println();
        System.out.println();
        System.out.println("======== RESOLVING STARTS HERE ========");

        State tmp = start;
        while (tmp.f != tmp.g) {
            System.out.println("resolving threshold = " + threshold);
            tmp = search(start);
            this.threshold++;
        }

        new Report(tmp, 0, 0);
    }

    private State search(State parent) {
        if (parent.f > threshold
                || parent.f == parent.g) {
            return parent;
        }
        PriorityQueue<State> children = helper.expandTheState(parent);
        State closest = parent;

        while (!children.isEmpty()) {
            State tmp = children.remove();
            if (isGranny(tmp)) {
                continue;
            }
            tmp = search(tmp);
            if (tmp.f == tmp.g) {
                return tmp;
            }
            closest = tmp.f < closest.f
                    ? tmp
                    : closest;
        }
        return closest;
    }

    private boolean isGranny(State state) {
        return state.parent != null
                && state.parent.parent != null
                && state.parent.parent.equals(state);
    }
}
