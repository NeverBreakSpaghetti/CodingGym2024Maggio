package src;

import java.util.*;

public class EvenForest {
    private record Edge(int from, int to) {} // comment this if you are using Java 8
    public static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {
        Map<Integer, Integer> nodeDimensions = new HashMap<>(t_nodes);
//        class Edge { // uncomment this if you are using Java 8
//            int from;
//            int to;
//            Edge(int from, int to){
//                this.from = from;
//                this.to = to;
//            }
//        }

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < t_edges; i++){
            int from = t_from.get(i);
            int to = t_to.get(i);
            edges.add( new Edge( Math.max(from, to), Math.min(from, to) ) );
        }

        edges.sort(
                Comparator.comparingInt((Edge edge) -> edge.from).reversed()
        );

        for (Edge edge : edges) {
            int fromNode = edge.from;
            int toNode = edge.to;
            nodeDimensions.put(fromNode, nodeDimensions.getOrDefault(fromNode, 1));
            nodeDimensions.put(toNode, nodeDimensions.getOrDefault(toNode, 1)+ nodeDimensions.get(fromNode));
        }

        int cutEdges = 0;
        for (Map.Entry<Integer, Integer> entry : nodeDimensions.entrySet()) {
            if (entry.getValue()%2==1 || entry.getKey() == 1)
                continue;
            cutEdges++;
        }
        return cutEdges;
    }
}
