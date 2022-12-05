fun main() {
    fun part1(input: String): Int {
        return input.lines().map {
            Pair(
                it.subSequence(0, it.length/2),
                it.subSequence(it.length/2, it.length)
            ).let { pair ->
                var number = 0
                for (fChar in pair.first) {
                    for (sChar in pair.second) {
                        when (fChar.compareTo(sChar)) {
                            0 -> {
                                number =
                                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(fChar)+1
                                break
                            }
                        }
                    }
                    if (number > 0) break
                }
                number
            }
        }.sum()
    }

    fun part2(input: String): Int {
        return input.lines().chunked(3).map {
            var charList: CharSequence = ""
            for (charF in it[0]) {
                for (charS in it[1]) {
                    when (charF.compareTo(charS)) {
                        0 -> charList = "$charList + $charF"
                    }
                }
            }
            var number = 0
            for (charL in charList) {
                for (charT in it [2]) {
                    when (charL.compareTo(charT)) {
                        0 -> {
                            number =
                                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(charL)+1
                            break
                        }
                    }
                }
            }
            number
        }.sum()
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
