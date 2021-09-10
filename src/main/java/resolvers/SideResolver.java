package resolvers;

import dto.Goal;
import dto.State;
import utils.BoardUtil;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class SideResolver {

    final Goal goal;
    final BoardUtil util;
    final State start;
    final Queue<State> open;
    final Set<State> close;

    public SideResolver(Goal goal, State start, BoardUtil util) {
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
        resolve(start.matrix.length);
        State tmp = this.start;

        System.out.println("======== RESOLVING FINISHED HERE ========");

    }

    private void resolve(int n) {
        int m = n, l = 0, t = 0, r = m - 1, b = n - 1;

        int val = 1;
        while (l <= r && t <= b) {
            // up side solving
            if (l <= r) {
                solveUpSide(l, r, t);
//                val += r - l + 1;
                t++;
            }

            // right side solving
            if (t <= b) {
                solveRightSide(t, b, r);
//                val += b - t + 1;
                r--;
            }
//            for (int i = t; i <= b; i++) {
//                matrix[i][r] = val++;
//            }
//            r--;


            // down side solving
            if (r >= l) {
                solveDownSide(r, l, b);
//                val += r - l + 1;
                b--;
            }
//            for (int i = r; i >= l; i--) {
//                matrix[b][i] = val++;
//            }
//            b--;


            // left side solving
            if (b >= t) {
                solveLeftSide(b, t, l);
//                val += b - t + 1;
                l++;
            }
//            for (int i = b; i >= t; i--) {
//                matrix[i][l] = val++;
//            }
//            l++;
        }
    }

    private void solveUpSide(int l, int r, int t) {
        //            for (int i = l; i <= r; i++) {
        //                matrix[t][i] = val++;
        //            }
        State tmp = start;
        while (tmp.f != tmp.g) {
            open.addAll(util.expandTheState(tmp));
            close.add(tmp);
            tmp = getNewState(open, close);
//            BoardUtil.printState(tmp);
            open.addAll(util.expandTheState(tmp));
        }
    }
}
