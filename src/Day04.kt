fun main() {
    fun part1(input: String): Int {
        return input.lines().map {
            Pair(it.substringBefore(',').trim(), it.substringAfter(',').trim()).let { pair ->
                val fSet = (pair.first.substringBefore("-").toInt() .. pair.first.substringAfter("-").toInt()).toSet()
                val sSet = (pair.second.substringBefore("-").toInt() .. pair.second.substringAfter("-").toInt()).toSet()
                if (fSet.containsAll(sSet) || sSet.containsAll(fSet)) 1 else 0
            }
        }.sum()
    }

    fun part2(input: String): Int {
        return input.lines().map {
            Pair(it.substringBefore(',').trim(), it.substringAfter(',').trim()).let { pair ->
                val fSet = (pair.first.substringBefore("-").toInt() .. pair.first.substringAfter("-").toInt()).toSet()
                val sSet = (pair.second.substringBefore("-").toInt() .. pair.second.substringAfter("-").toInt()).toSet()
                if (fSet.any { v -> sSet.contains(v) }|| sSet.any{ v -> fSet.contains(v) }) 1 else 0
            }
        }.sum()
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
