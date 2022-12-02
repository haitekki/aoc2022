fun main() {
    fun part1(input: String): Int {
        return input.lines().map {
            it.split(" ")
                .zipWithNext()
                .first()
                .let { pair ->
                    when (pair.second) {
                        "X" -> {
                            when (pair.first) {
                                "A" -> 4
                                "B" -> 1
                                "C" -> 7
                                else -> 0
                            }
                        }
                        "Y" -> {
                            when (pair.first) {
                                "A" -> 8
                                "B" -> 5
                                "C" -> 2
                                else -> 0
                            }
                        }
                        "Z" -> {
                            when (pair.first) {
                                "A" -> 3
                                "B" -> 9
                                "C" -> 6
                                else -> 0
                            }
                        }
                        else -> 0
                    }
                }
        }.sum()
    }

    fun part2(input: String): Int {
        return input.lines().map {
            it.split(" ")
                .zipWithNext()
                .first()
                .let { pair ->
                    when (pair.first) {
                        "A" -> {
                            when (pair.second) {
                                "Y" -> 4
                                "X" -> 3
                                "Z" -> 8
                                else -> 0
                            }
                        }
                        "B" -> {
                            when (pair.second) {
                                "Y" -> 5
                                "X" -> 1
                                "Z" -> 9
                                else -> 0
                            }
                        }
                        "C" -> {
                            when (pair.second) {
                                "Y" -> 6
                                "X" -> 2
                                "Z" -> 7
                                else -> 0
                            }
                        }
                        else -> 0
                    }
                }
        }.sum()
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
