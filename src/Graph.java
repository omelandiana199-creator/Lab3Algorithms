
import java.util.*;

public class Graph {
    // Використовуємо список суміжності для зберігання графу
    private final Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }
    //Додає неорієнтоване ребро між вершинами.
    public void addEdge(int u, int v) {
        adjacencyList.putIfAbsent(u, new ArrayList<>());
        adjacencyList.putIfAbsent(v, new ArrayList<>());
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
    }

    // Реалізація алгоритму DFS (в глибину)
    public List<Integer> dfs(int startVertex) {
        List<Integer> visitedOrder = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfsRecursive(startVertex, visited, visitedOrder);
        return visitedOrder;
    }

    private void dfsRecursive(int vertex, Set<Integer> visited, List<Integer> visitedOrder) {
        visited.add(vertex);
        visitedOrder.add(vertex);

        for (int neighbor : adjacencyList.getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited, visitedOrder);
            }
        }
    }

    // Реалізація алгоритму BFS (в ширину)
    public List<Integer> bfs(int startVertex) {
        List<Integer> visitedOrder = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            visitedOrder.add(current);

            for (int neighbor : adjacencyList.getOrDefault(current, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return visitedOrder;
    }

    // Підрахунок кількості зв'язних компонент
    public int countConnectedComponents() {
        Set<Integer> visited = new HashSet<>();
        int componentsCount = 0;

        for (int vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                dfsRecursive(vertex, visited, new ArrayList<>());
                componentsCount++;
            }
        }
        return componentsCount;
    }

    // Пошук найкоротшого шляху у незваженому графі
    public List<Integer> shortestPathBfs(int start, int target) {
        Map<Integer, Integer> predecessors = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == target) {
                return buildPath(predecessors, target);
            }

            for (int neighbor : adjacencyList.getOrDefault(current, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessors.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Integer> buildPath(Map<Integer, Integer> predecessors, int target) {
        LinkedList<Integer> path = new LinkedList<>();
        Integer step = target;

        while (step != null) {
            path.addFirst(step);
            step = predecessors.get(step);
        }
        return path;
    }
}