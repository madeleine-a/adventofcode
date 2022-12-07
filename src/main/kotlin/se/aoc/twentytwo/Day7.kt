package se.aoc.twentytwo

import se.aoc.Stack
import se.aoc.peek


fun main() {
    val d = Day7()
    d.run()
}

class Day7 {
    private val root = Directory("/")
    private val commands: Stack<Command> = mutableListOf()
    fun run() {
        val input = Day7::class.java.getResource("/twentytwo/day7.txt").readText()
        createList(input)
        println("Day 7")
        println("Part 1")
        println(part1())

        println("Part 2")
        println(part2())
    }

    fun createList(str: String) {
        val list = str.split(System.lineSeparator()).map { s -> s.split(" ") }
        var currDir: Directory
        list.forEach {
            if (it[0] == "$") {
                if (it.size == 3)
                    commands.add(Command(CommandEnum.valueOf(it[1].uppercase()), it[2]))
                else
                    commands.add(Command(CommandEnum.valueOf(it[1].uppercase())))
                commands.peek()?.let { it1 -> root.excecuteCommand(it1) }
            } else if (it[0] == "dir") {
                currDir = Directory(it[1])
                root.addDirectory(currDir)
            } else {
                root.addFile(File(it[1], it[0].toInt()))
            }
        }
    }

    fun part1(): Number {
        val list = root.getAllSizes()
        return list.filter { it < 100000 }.sum()
    }

    fun part2(): Number {
        val map = root.getAllSizesMap()
        val total = 70000000
        val left = total - root.getSize()
        return map.values.filter { it > (30000000-left) }.minOf { it }
    }
}

enum class CommandEnum {
    LS, CD
}

data class Command(val what: CommandEnum, val where: String? = null)

data class Directory(val name: String, val parent: Directory? = null) {
    var files = mutableListOf<File>()
    var directories = mutableListOf<Directory>()
    var currDir: Directory? = null

    fun getSize(): Int {
        var sum = files.sumOf { it.size }
        sum += directories.sumOf { d -> d.getSize() }
        return sum
    }

    fun getAllSizes(): List<Int>{
        val list = mutableListOf<Int>()
        list.add(getSize())
        val dirList = directories.map { d -> d.getAllSizes() }
        list.addAll(dirList.flatten())
        return list
    }

    fun getAllSizesMap(): Map<String, Int>{
        val map = mutableMapOf<String, Int>()
        map[this.name] = getSize()
        val dirList = directories.map { d -> d.getAllSizesMap() }
        dirList.forEach { m -> map.putAll(m) }
        return map
    }

    fun excecuteCommand(command: Command) {
        if (command.what == CommandEnum.CD) {
            if (command.where == "..") {
                currDir = currDir?.parent ?: currDir
            } else if (command.where == "/") {
                currDir = null
            } else {
                currDir = currDir?.directories?.first { d -> d.name == command.where }
                    ?: this.directories.first { d -> d.name == command.where }
            }
        } else if (command.what == CommandEnum.LS) {
//            if (currDir == null)
//                println(this)
//            else
//                println(currDir)
        }

    }

    fun addFile(file: File) {
        if (currDir == null) {
            files.add(file)
        } else {
            currDir!!.addFile(file)
        }
    }

    fun addDirectory(directory: Directory) {
        if (currDir == null) {
            directories.add(Directory(directory.name, this))
        } else {
            currDir!!.addDirectory(Directory(directory.name, currDir))
        }
    }

    override fun toString(): String {
        return "Directory(name='$name', files=$files, directories=$directories) ${System.lineSeparator()}"
    }


}

data class File(val name: String, val size: Int) {
    override fun toString(): String {
        return "File(name='$name', size=$size) ${System.lineSeparator()}"
    }
}



