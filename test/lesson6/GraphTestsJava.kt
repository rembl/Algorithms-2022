package lesson6

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class GraphTestsJava : AbstractGraphTests() {
    @Test
    @Tag("6")
    fun testFindEulerLoopJava() {
        findEulerLoop { let { JavaGraphTasks.findEulerLoop(it) } }
    }

    @Test
    @Tag("7")
    fun testMinimumSpanningTreeJava() {
        minimumSpanningTree { let { JavaGraphTasks.minimumSpanningTree(it) } }
        minimumSpanningTreeMy { let { JavaGraphTasks.minimumSpanningTree(it) } }
    }

    @Test
    @Tag("10")
    fun testLargestIndependentVertexSetJava() {
        largestIndependentVertexSet { let { JavaGraphTasks.largestIndependentVertexSet(it) } }
    }

    @Test
    @Tag("8")
    fun testLongestSimplePathJava() {
        longestSimplePath { let { JavaGraphTasks.longestSimplePath(it) } }
        longestSimplePathMy { let { JavaGraphTasks.longestSimplePath(it) } }
    }

    @Test
    @Tag("6")
    fun testBaldaSearcherJava() {
        baldaSearcher { inputName, words -> JavaGraphTasks.baldaSearcher(inputName, words) }
    }
}