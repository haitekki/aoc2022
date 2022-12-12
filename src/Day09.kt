import kotlin.math.absoluteValue

fun main() {

    fun calculateLocation(hLocation: Pair<Int, Int>, tLocation: Pair<Int, Int>): Pair<Int, Int> {
        val relativePosition = Pair(hLocation.first-tLocation.first, hLocation.second-tLocation.second)

        return if (relativePosition.first == 0 &&
            relativePosition.second > 1) {
            //      println("-> 0,+1")
            Pair(tLocation.first, tLocation.second + 1)
        } else if (relativePosition.first == 0 &&
            relativePosition.second < -1) {
            //    println("-> 0,-1")
            Pair(tLocation.first, tLocation.second - 1)
        } else if (relativePosition.first > 1 &&
            relativePosition.second == 0) {
            //    println("-> +1,0")
            Pair(tLocation.first + 1, tLocation.second)
        } else if (relativePosition.first < -1 &&
            relativePosition.second == 0) {
            //    println("-> -1,0")
            Pair(tLocation.first - 1, tLocation.second)
        } else if (relativePosition.first > 0 &&
            relativePosition.second > 0 &&
            (relativePosition.first * relativePosition.second).absoluteValue > 1) {
            //    println("-> +1,+1")
            Pair(tLocation.first+1, tLocation.second+1)
        } else if (relativePosition.first > 0 &&
            relativePosition.second < 0 &&
            (relativePosition.first * relativePosition.second).absoluteValue > 1) {
            //    println("-> +1,-1")
            Pair(tLocation.first+1, tLocation.second-1)
        } else if (relativePosition.first < 0 &&
            relativePosition.second > 0 &&
            (relativePosition.first * relativePosition.second).absoluteValue > 1) {
            //    println("-> -1,+1")
            Pair(tLocation.first-1, tLocation.second+1)
        } else if (relativePosition.first < 0 &&
            relativePosition.second < 0 &&
            (relativePosition.first * relativePosition.second).absoluteValue > 1) {
            //    println("-> -1,-1")
            Pair(tLocation.first-1, tLocation.second-1)
        } else {
            //    println("-> 0,0")
            Pair(tLocation.first, tLocation.second)
        }
    }

    fun moveHead(inst: String, hLocation: Pair<Int, Int>): Pair<Int, Int> {
        return when (inst) {
            "U" -> Pair(hLocation.first + 1, hLocation.second)
            "D" -> Pair(hLocation.first - 1, hLocation.second)
            "R" -> Pair(hLocation.first, hLocation.second + 1)
            "L" -> Pair(hLocation.first, hLocation.second - 1)
            else -> Pair(0,0)
        }
    }

    fun part1(input: String): Int {
        var hLocation = Pair(0,0)
        var tLocation = Pair(0,0)
        val moveHistory = mutableSetOf("${tLocation.first}-${tLocation.second}")

        for(instruction in input.lines()) {

            val splittedInstr = instruction.split(" ")

            for (moveN in 1..splittedInstr[1].toInt()) {

                hLocation = moveHead(splittedInstr[0], hLocation)

                tLocation = calculateLocation(hLocation, tLocation)

                moveHistory.add("${tLocation.first}-${tLocation.second}")
            }
        }

        return moveHistory.size
    }

    fun part2(input:String): Int {
        var hLocation = Pair(0,0)
        val tList: MutableList<Pair<Int,Int>> = mutableListOf(Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0))
        val moveHistory = mutableSetOf("${tList.last().first}-${tList.last().second}")

        for(instruction in input.lines()) {

            val splittedInstr = instruction.split(" ")

            for (moveN in 1..splittedInstr[1].toInt()) {

                hLocation = moveHead(splittedInstr[0], hLocation)

                for (i in tList.indices) {
                    tList[i] = when (i) {
                        0 -> calculateLocation(hLocation, tList[i])
                        else -> calculateLocation(tList[i-1], tList[i])
                    }
                }

                moveHistory.add("${tList[8].first}-${tList[8].second}")
            }
        }

        return moveHistory.size
    }
    
    val testInput =
        """
            R 5
            U 8
            L 8
            D 3
            R 17
            D 10
            L 25
            U 20
        """.trimIndent()

    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}