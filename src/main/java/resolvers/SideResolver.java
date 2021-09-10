package resolvers;

import dto.Goal;
import dto.State;
import utils.AbstractBoardUtil;
import utils.SideBoardUtil;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class SideResolver {

    final Goal goal;
    final SideBoardUtil util;
    final State start;
    Queue<State> open;
    final Set<State> close;

    public SideResolver(Goal goal, State start, SideBoardUtil util) {
        this.goal = goal;
        this.util = util;
        this.start = start;
        this.close = new HashSet<>();
        this.open = new PriorityQueue<>();
    }

    public void resolveIt() {
        System.out.println();
        System.out.println();
        System.out.println("========= RESOLVING STARTS HERE =========");

        resolve(start.matrix.length, this.start);

        System.out.println("======== RESOLVING FINISHED HERE ========");

    }

    /**
     * ТОЛЬКО ДЛЯ ЭКСПЕРИМЕНТОВ!!!
     * TODO НЕ ЗАБВТЬ УДАЛИТЬ!!!
     */
    private State resolve(int n, State start) {
        AbstractBoardUtil.printMatrix(util.solvingMatrix);

        int m = n, l = 0, t = 0, r = m - 1, b = n - 1;
        State tmp = start;

        System.out.println();

//        up side solving
        System.out.println("l = " + l + "; r = " + r + "; ");
        if (l <= r) {
            solveUpSide(tmp, l, r, t);
//                val += r - l + 1;
//            t++;
        }
        return start;
    }
//    private void resolve(int n) {
//        int m = n, l = 0, t = 0, r = m - 1, b = n - 1
//
//        int val = 1;
//        while (l <= r && t <= b) {
//            // up side solving
//            if (l <= r) {
//                solveUpSide(l, r, t);
////                val += r - l + 1;
//                t++;
//            }
//
//            // right side solving
//            if (t <= b) {
//                solveRightSide(t, b, r);
////                val += b - t + 1;
//                r--;
//            }
////            for (int i = t; i <= b; i++) {
////                matrix[i][r] = val++;
////            }
////            r--;
//
//
//            // down side solving
//            if (r >= l) {
//                solveDownSide(r, l, b);
////                val += r - l + 1;
//                b--;
//            }
////            for (int i = r; i >= l; i--) {
////                matrix[b][i] = val++;
////            }
////            b--;
//
//
//            // left side solving
//            if (b >= t) {
//                solveLeftSide(b, t, l);
////                val += b - t + 1;
//                l++;
//            }
////            for (int i = b; i >= t; i--) {
////                matrix[i][l] = val++;
////            }
////            l++;
//        }
//    }


    private void solveUpSide(State state, int l, int r, int i) {
        AbstractBoardUtil.printState(state);
        this.open = new PriorityQueue<>();
        util.setLimits(l, r, i, i);
        State tmp = state;
        while (tmp.f != tmp.g) {
            open.addAll(util.expandTheState(tmp));
//          printTheOpen();
            close.add(tmp);
            tmp = getNewState(open, close);
            AbstractBoardUtil.printState(tmp);
        }
//        util.printResolvingReverse(tmp);

    }

    /**
     * TODO код дублируется
     */
    private State getNewState(Queue<State> open, Set<State> close) {
        while (!open.isEmpty()) {
            State tmp = open.remove();
            if (!close.contains(tmp)) {
                return tmp;
            }
        }
        return null;
    }

    public void printTheOpen() {
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\ PRINT THE OPEN \\\\\\\\\\\\\\\\\\\\\\\\");
        open.forEach(AbstractBoardUtil::printState);
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\ PRINT THE OPEN \\\\\\\\\\\\\\\\\\\\\\\\");
    }
}
