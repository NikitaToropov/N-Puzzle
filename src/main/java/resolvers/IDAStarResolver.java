package resolvers;

import dto.Goal;
import dto.Report;
import dto.State;

import java.util.PriorityQueue;

/**
 * Класс для решения пазла алгоритмом IDA Star.
 */
public class IDAStarResolver implements Resolver{
    final Goal goal;
    final ResolvingHelper helper;
    final State start;
    private int threshold;
    private int timeComplexity = 0;
    private int sizeComplexity = 0;
    private int tmpSizeComplexity = 0;

    public IDAStarResolver(Goal goal, State start, ResolvingHelper helper) {
        this.goal = goal;
        this.start = start;
        this.helper = helper;
        this.threshold = start.f;
    }

    @Override
    public Report resolveIt() {
        long startTime = System.nanoTime();

        State tmp = start;
        while (tmp.f != tmp.g) {
            System.out.println("Resolving threshold = " + threshold);
            tmpSizeComplexity = 0;
            tmp = search(start);
            sizeComplexity = Math.max(tmpSizeComplexity, sizeComplexity);
            this.threshold++;
        }
        long timeInterval = System.nanoTime() - startTime;

        return new Report(tmp, timeComplexity, sizeComplexity, timeInterval);
    }

    private State search(State parent) {
        if (parent.f > threshold
                || parent.f == parent.g) {
            return parent;
        }
        PriorityQueue<State> children = helper.expandTheState(parent);
        tmpSizeComplexity += children.size();
        State closest = parent;

        while (!children.isEmpty()) {
            State tmp = children.remove();
            timeComplexity++;
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
