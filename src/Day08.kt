fun main() {

    fun part1(input: String): Int {
        val grid = input.lines()
        val seeableTrees = mutableSetOf<String>()
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (i <= 0 ||
                    i == grid.lastIndex ||
                    j <= 0 ||
                    j == grid[i].lastIndex) {
                    seeableTrees.add("${i}-${j}")
                } else {
                    val currentTreeHeight = grid[i][j].digitToInt()
                    val currentRow = grid[i].map {
                        it.digitToInt()
                    }
                    val currentColumn = grid.map {
                        it[j].digitToInt()
                    }

                    if (currentRow.subList(0,j).maxOrNull()!! < currentTreeHeight ||
                        currentRow.subList(j+1,currentRow.size).maxOrNull()!! < currentTreeHeight ||
                        currentColumn.subList(0,i).maxOrNull()!! < currentTreeHeight ||
                        currentColumn.subList(i+1,currentColumn.size).maxOrNull()!! < currentTreeHeight) {
                        seeableTrees.add("${i}-${j}")
                    }
                }
            }
        }
        return seeableTrees.size
    }

    fun part2(input:String): Int {
        val grid = input.lines()
        var max = 0
        for (i in 1..grid.size-2) {
            for (j in 1..grid[i].length-2) {
                val currentTreeHeight = grid[i][j].digitToInt()
                val currentRow = grid[i].map { it.digitToInt() }
                val currentColumn = grid.map {
                    it[j].digitToInt() }

                val values = listOf(currentRow.subList(0,j).reversed(),
                    currentRow.subList(j+1,currentRow.size),
                    currentColumn.subList(0,i).reversed(),
                    currentColumn.subList(i+1,currentColumn.size)
                ).map {
                    var k = 0
                    while (k < it.size) {
                        if (it[k] >= currentTreeHeight) {
                            break
                        }
                        k++
                    }
                    if (k == it.size) it.size else k+1
                }
                val scenicValue = values[0]*values[1]*values[2]*values[3]
                if (scenicValue > max) {
                    max = scenicValue
                }

            }
        }

        return max
    }
    
    val testInput =
        """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent()

    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}