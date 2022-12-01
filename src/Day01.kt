fun main() {
    fun part1(input: String): Int {
        return input.split(System.lineSeparator()+System.lineSeparator()).map {
            it.lines().map { number ->
                number.toInt()
            }.sum()
        }.maxOrNull()?: 0
    }

    fun part2(input:String): Int {
        return input.split(System.lineSeparator()+System.lineSeparator()).map {
            it.lines().map { number ->
                number.toInt()
            }.sum()
        }.sortedDescending().subList(0,3).sum()
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
