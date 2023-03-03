package com.example.week4activities.week4

fun main() {
    val dictionary = HashMap<String, String>()
    while (true) {
        println("  Dictionary!  ")
        println("1. Add Word in Dictionary.")
        println("2. Search Word.")
        println("3. View Dictionary.")
        println("4. Exit")
        println("")
        print("Choose Option: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("\nEnter a Word to add in Dictionary: ")
                val word = readLine() ?: ""

                println("Enter the Word Definition: ")
                val definition = readLine() ?: ""

                dictionary[word] = definition
                println("\nWord added successfully!\n")
            }
            2 -> {
                print("\nEnter a Word to Search: ")
                val searchWord = readLine()

                if (dictionary.contains(searchWord)) {
                    println("Definition: ${dictionary.entries.find { it.key == searchWord }?.value}\n")
                } else if (!dictionary.equals(searchWord)) {
                    println("Word not found!\n")
                } else {
                    println("Word not found!\n")
                }
            }
            3 -> {
                println("Dictionary: ")
                dictionary.forEach {
                    val key = it.key
                    val value = it.value
                    println("$key - $value")
                    println("")
                }
            }
            4 -> {
                println("Exiting Dictionary...")
                return
            }
        }
    }
}