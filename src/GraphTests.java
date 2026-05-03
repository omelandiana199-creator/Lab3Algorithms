
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GraphTests {
    private Graph graph;

    @BeforeEach
    void setUp() {
        graph = new Graph();
        // Побудова графу із Завдання 1: 0–1, 0–2, 1–3, 1–4, 2–5, 2–6
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
    }

    @Test
    void testDfsTraversalOrder() {
        // Очікуваний порядок обходу в глибину
        List<Integer> expectedDfs = List.of(0, 1, 3, 4, 2, 5, 6);
        List<Integer> actualDfs = graph.dfs(0);

        assertEquals(expectedDfs, actualDfs, "Порядок обходу DFS не відповідає очікуваному");
    }

    @Test
    void testBfsTraversalOrder() {
        // Очікуваний порядок обходу в ширину (по рівнях)
        List<Integer> expectedBfs = List.of(0, 1, 2, 3, 4, 5, 6);
        List<Integer> actualBfs = graph.bfs(0);

        assertEquals(expectedBfs, actualBfs, "Порядок обходу BFS не відповідає очікуваному");
    }

    @Test
    void testCountConnectedComponents() {
        // Граф із завдання 1 є повністю зв'язним
        assertEquals(1, graph.countConnectedComponents(), "Зв'язний граф повинен мати 1 компоненту");

        // Тестування на незв'язному графі
        Graph disconnectedGraph = new Graph();
        disconnectedGraph.addEdge(1, 2);
        disconnectedGraph.addEdge(3, 4);

        assertEquals(2, disconnectedGraph.countConnectedComponents(), "Незв'язний граф повинен мати 2 компоненти");
    }

    @Test
    void testShortestPathBfs() {
        // Найкоротший шлях між вершинами 0 та 6
        List<Integer> expectedPath = List.of(0, 2, 6);
        List<Integer> actualPath = graph.shortestPathBfs(0, 6);

        assertEquals(expectedPath, actualPath, "Найкоротший шлях повинен бути 0 -> 2 -> 6");
    }
}