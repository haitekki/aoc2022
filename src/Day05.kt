fun main() {

    fun compileArragementsTable (input: String): ArrayList<ArrayDeque<Char>> {
        val arragmentTable = ArrayList<ArrayDeque<Char>>(9)

        for (i in 0..8) {
            arragmentTable.add(ArrayDeque())
        }

        for (charS: CharSequence in input.lines()) {
            if (charS.contains("1")) break

            if (charS.getOrNull(1) != ' ' && charS.getOrNull(1) != null) arragmentTable[0].addLast(charS[1])
            if (charS.getOrNull(5) != ' ' && charS.getOrNull(5) != null) arragmentTable[1].addLast(charS[5])
            if (charS.getOrNull(9) != ' ' && charS.getOrNull(9) != null) arragmentTable[2].addLast(charS[9])
            if (charS.getOrNull(13) != ' ' && charS.getOrNull(13) != null) arragmentTable[3].addLast(charS[13])
            if (charS.getOrNull(17) != ' ' && charS.getOrNull(17) != null) arragmentTable[4].addLast(charS[17])
            if (charS.getOrNull(21) != ' ' && charS.getOrNull(21) != null) arragmentTable[5].addLast(charS[21])
            if (charS.getOrNull(25) != ' ' && charS.getOrNull(25) != null) arragmentTable[6].addLast(charS[25])
            if (charS.getOrNull(29) != ' ' && charS.getOrNull(29) != null) arragmentTable[7].addLast(charS[29])
            if (charS.getOrNull(33) != ' ' && charS.getOrNull(33) != null) arragmentTable[8].addLast(charS[33])
        }

        return arragmentTable
    }

    fun part1(input: String): String {
        val arragment = input.substringBefore(System.lineSeparator()+System.lineSeparator())
        val instructions = input.substringAfter(System.lineSeparator()+System.lineSeparator())

        val arragmentTable = compileArragementsTable(arragment)

        instructions.lines().forEach {
            val splittedInstr = it.split(" ")

            for (i in 1..splittedInstr[1].toInt()) {
                arragmentTable[splittedInstr[5].toInt()-1].addFirst(arragmentTable[splittedInstr[3].toInt()-1].removeFirst())
            }
        }

        return "${arragmentTable[0].first()}${arragmentTable[1].first()}${arragmentTable[2].first()}${arragmentTable[3].first()}" +
            "${arragmentTable[4].first()}${arragmentTable[5].first()}${arragmentTable[6].first()}${arragmentTable[7].first()}" +
            "${arragmentTable[8].first()}"
    }

    fun part2(input: String): String {
        val arragment = input.substringBefore(System.lineSeparator()+System.lineSeparator())
        val instructions = input.substringAfter(System.lineSeparator()+System.lineSeparator())

        val arragmentTable = compileArragementsTable(arragment)

        instructions.lines().forEach {
            val splittedInstr = it.split(" ")

            val elements = ArrayDeque(arragmentTable[splittedInstr[3].toInt()-1].take(splittedInstr[1].toInt()))
            arragmentTable[splittedInstr[3].toInt()-1] = ArrayDeque(arragmentTable[splittedInstr[3].toInt()-1].drop(splittedInstr[1].toInt()))

            for (i in 1..splittedInstr[1].toInt()) {
                arragmentTable[splittedInstr[5].toInt()-1].addFirst(elements.removeLast())
            }

        }

        return "${arragmentTable[0].first()}${arragmentTable[1].first()}${arragmentTable[2].first()}${arragmentTable[3].first()}" +
            "${arragmentTable[4].first()}${arragmentTable[5].first()}${arragmentTable[6].first()}${arragmentTable[7].first()}" +
            "${arragmentTable[8].first()}"
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
