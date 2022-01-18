package algorithms;
import dataStructures.Node;
import enums.Player;

public class MinMax {

    public static int[] makeMove(Node node, int numPlys)
    {
        int coords[] = {0, 0};
        Node root = new Node(node);
        int best = minMax(root, numPlys, Integer.MIN_VALUE, Integer.MAX_VALUE);
        for(Node n : root.children)
        {
            if(n.val == best)
            {
                coords[0] = n.getChangedRow();
                coords[1] = n.getChangedCol();
                return coords;
            }
        }
        return coords;
    }

    private static int minMax(Node root, int plys, int alpha, int beta)
    {
        root.addChildren();
        if(root.depth >= plys || root.children.isEmpty())
        {
            root.eval();
            return root.val;
        }
        if(root.getType() == Player.MAX)
        {
            int bestVal = Integer.MIN_VALUE;
            int value;
            for(Node n : root.children)
            {
                value = minMax(n, plys - 1, alpha, beta);
                if(value > bestVal)
                {
                    bestVal = value;
                }
                if(bestVal > alpha)
                {
                    alpha = bestVal;
                }
                if(beta <= alpha)
                {
                    break;
                }
            }
            root.val = bestVal;
            return bestVal;
        }
        else
        {
            int bestVal = Integer.MAX_VALUE;
            int value;
            for(Node n : root.children)
            {
                value = minMax(n, plys - 1, alpha, beta);
                if(value < bestVal)
                {
                    bestVal = value;
                }
                if(bestVal < beta)
                {
                    beta = bestVal;
                }
                if(beta <= alpha)
                {
                    break;
                }
            }
            root.val = bestVal;
            return bestVal;
        }
    }
}