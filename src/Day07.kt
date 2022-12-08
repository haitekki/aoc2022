fun main() {
    fun initFileSystem (input: String): Directory {
        val root = Directory(
            parentDirectory = null,
            name = "root"
        )

        var current: Directory = root

        for (line in input.lines()) {
            val splittedLine = line.split(" ")
            when (splittedLine[0]) {
                "$" -> {
                    when (splittedLine[1]) {
                        "cd" -> {
                            when (splittedLine[2]) {
                                "/" -> current = root
                                ".." -> current = current.parentDirectory!!
                                else -> {
                                    val newDir = current.getDirectoryWithName(splittedLine[2])
                                    if (newDir != null) current = newDir
                                }
                            }
                        }
                    }
                }
                "dir" -> {
                    val newDir = current.getDirectoryWithName(splittedLine[1])
                    if (newDir == null) {
                        current.directories.add(
                            Directory(
                                parentDirectory = current,
                                name = splittedLine[1]
                            )
                        )
                    }
                }
                else -> {
                    val newFile = current.getFileWithName(splittedLine[1])
                    if (newFile == null) {
                        current.files.add(
                            File(
                                name = splittedLine[1],
                                size = splittedLine[0].toLong()
                            )
                        )
                    }
                }
            }
        }

        return root
    }

    fun part1(input: String): Long {
        val root = initFileSystem(input)
        val sizeList = root.getSizeList(mutableListOf())
        return sizeList.filter {
            it <= 100000L
        }.sum()
    }

    fun part2(input:String): Long {
        val root = initFileSystem(input)
        val neededSpace = 30000000L - (70000000L - root.getTotalSize())
        val sizeList = root.getSizeList(mutableListOf())
        for (dirSize in sizeList.sorted()) {
            if (dirSize >= neededSpace) return dirSize
        }
        return 0
    }
    
    val testInput =
        """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
        """.trimIndent()

    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

class Directory (
    val parentDirectory: Directory?,
    val name: String,
    val directories: MutableList<Directory> = mutableListOf(),
    val files: MutableList<File> = mutableListOf()
) {
    fun getSizeList(sizeList : MutableList<Long>): MutableList<Long> {
        val tSize = directories.map{
            it.getSizeList(sizeList).last()
        }.sum() + files.map{
            it.size
        }.sum()

        sizeList.add(tSize)

        return sizeList
    }

    fun getTotalSize(): Long {
        return directories.map{
            it.getTotalSize()
        }.sum() + files.map{
            it.size
        }.sum()
    }

    fun getDirectoryWithName(name: String): Directory? {
        for (directory in directories) {
            if (directory.name == name) {
                return directory
            }
        }
        return null
    }

    fun getFileWithName(name: String): File? {
        for (file in files) {
            if (file.name == name) {
                return file
            }
        }
        return null
    }
}

data class File (
    val name: String,
    val size: Long
)