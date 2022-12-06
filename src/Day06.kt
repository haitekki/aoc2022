fun main() {
    fun part1(input: String): Int {
        var i = 3
        while (i < input.length) {
            if (input.subSequence(i-3, i+1).toSet().size > 3) break
            i++
        }
        return i+1
    }

    fun part2(input:String): Int {
        var i = 13
        while (i < input.length) {
            if (input.subSequence(i-13, i+1).toSet().size > 13) break
            i++
        }
        return i+1
    }


    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
