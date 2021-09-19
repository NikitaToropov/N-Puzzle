package resolvers;

import dto.Goal;
import dto.Report;
import dto.State;

import java.util.PriorityQueue;

/**
 * Класс для решения пазла алгоритмом IDA Star.
 */
public class IDAStarResolver {
    final Goal goal;
    final ResolvingHelper helper;
    final State start;
    private int threshold;

    public IDAStarResolver(Goal goal, State start, ResolvingHelper helper) {
        this.goal = goal;
        this.start = start;
        this.helper = helper;
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

    /**
     * Метод проверяет не является ли текущий стейт своей "бабушкой".
     *
     * @param state Текущий стейт, требующий проверки.
     * @return false если у стейта нет родителей или нет бабушки или стейт не равен бабушке.
     */
    private boolean isGranny(State state) {
        return state.parent != null
                && state.parent.parent != null
                && state.parent.parent.equals(state);
    }
}
