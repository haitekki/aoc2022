fun main() {
    fun calculateCycles(input: String): Pair<Int, List<Int>> {
        var x = 1
        val cycles: MutableList<Int> = mutableListOf()

        input.lines().forEach {
            val inst = it.split(" ")
            when (inst[0]) {
                "noop" -> {
                    cycles.add(x)
                }
                "addx" -> {
                    cycles.add(x)
                    cycles.add(x)
                    x += inst[1].toInt()
                }
            }
        }

        return Pair(x,cycles.toList())

    }

    fun part1(input: String): Int {
        val pair = calculateCycles(input)
        return (pair.second[19] * 20) + (pair.second[59] * 60) + (pair.second[99] * 100) + (pair.second[139] * 140) + (pair.second[179] * 180) + (pair.second[219] * 220)
    }

    fun part2(input:String): Int {
        val pair = calculateCycles(input)
        val chunked = pair.second.chunked(40)
        chunked.forEach {
            for (i in it.indices) {
                if (i in it[i]-1..it[i]+1) {
                    print("#")
                } else {
                    print(".")
                }
            }
            print(System.lineSeparator())
        }
        return 0
    }

    val testInput =
        """
addx 15
addx -11
addx 6
addx -3
addx 5
addx -1
addx -8
addx 13
addx 4
noop
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx -35
addx 1
addx 24
addx -19
addx 1
addx 16
addx -11
noop
noop
addx 21
addx -15
noop
noop
addx -3
addx 9
addx 1
addx -3
addx 8
addx 1
addx 5
noop
noop
noop
noop
noop
addx -36
noop
addx 1
addx 7
noop
noop
noop
addx 2
addx 6
noop
noop
noop
noop
noop
addx 1
noop
noop
addx 7
addx 1
noop
addx -13
addx 13
addx 7
noop
addx 1
addx -33
noop
noop
noop
addx 2
noop
noop
noop
addx 8
noop
addx -1
addx 2
addx 1
noop
addx 17
addx -9
addx 1
addx 1
addx -3
addx 11
noop
noop
addx 1
noop
addx 1
noop
noop
addx -13
addx -19
addx 1
addx 3
addx 26
addx -30
addx 12
addx -1
addx 3
addx 1
noop
noop
noop
addx -9
addx 18
addx 1
addx 2
noop
noop
addx 9
noop
noop
noop
addx -1
addx 2
addx -37
addx 1
addx 3
noop
addx 15
addx -21
addx 22
addx -6
addx 1
noop
addx 2
addx 1
noop
addx -10
noop
noop
addx 20
addx 1
addx 2
addx 2
addx -6
addx -11
noop
noop
noop
        """.trimIndent()

    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
