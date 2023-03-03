@file:Suppress("NAME_SHADOWING")

package com.example.week4activities.week4

class Beverage(val name: String, val type: String, val price: Double)

val totalRevenue = mutableSetOf<Triple<String, Int, Double>>()

val beverages = listOf(
    Beverage("Nescafe Original", "Instant Coffee", 12.0),
    Beverage("Nescafe CreamyLatte", "Macchiato", 13.0),
    Beverage("Nescafe CreamyWhite", "Flat White", 14.0),
    Beverage("Kopiko Black", "Americano", 11.0),
    Beverage("Kopiko Blanca", "Mocha", 15.0)
)

fun main() {
    while (true) {
        println("\n  Coffee Shop Tracker!")
        println("1. Menu")
        println("2. Total Revenue By Average Type.")
        println("3. Total Revenue.")
        println("4. Exit\n")
        print("Choose option: ")
        val choice = readLine()?.toIntOrNull() ?: 5
        if (choice > 5) {
            println("\nInvalid option!")
        }
        when (choice) {
            1 -> menu()
            2 -> totalRevenueByBeverageType()
            3 -> totalRevenue()
            4 -> {
                println("\nTracker Terminated!")
                return
            }
            5 -> {
                println("\nInvalid option!")
            }
        }
    }
}

fun menu() {
    print("\nMenu:")
    beverages.forEach {
        val name = it.name
        val type = it.type
        val price = it.price
        print("\n>$name ($type) - ₱$price")
    }
    println("")
}

fun totalRevenueByBeverageType() {

    while (true) {
        print("\n(type 'done' to exit)\nEnter Beverage type: ")
        val query = readLine().toString()
        if (query.isBlank()) {
            println("\nPlease enter beverage type!")
            continue
        }
        val searchResults = search(beverages, query)
        if (query.contains("done")) {
            break
        } else if (searchResults.isEmpty()) {
            println("\nBeverage type not found!")
            continue
        }

        print("Enter the number of units sold: ")
        val numberOfUnitSold = readLine()!!.toIntOrNull() ?: 0
        if (searchResults.isNotEmpty()) {
            print("Total Revenue of $query: ")
            searchResults.forEach {
                val result = it.price * numberOfUnitSold
                totalRevenue.add(Triple(query, numberOfUnitSold, result))
                print("₱$result")
                println("")
            }
        }
    }
}

fun search(beverages: List<Beverage>, query: String): List<Beverage> {
    val keywords = query.split(" ").map { it.trim() }
    return beverages.filter { beverages ->
        keywords.all {
            beverages.type.contains(it, ignoreCase = true)
        }
    }
}

fun totalRevenue() {
    if (totalRevenue.isEmpty()) {
        println("\nNo sales!")
        return
    }

    println("\nTotal Revenue: ")
    totalRevenue.forEach {
        println("   ${it.second}pcs. ${it.first} = ₱${it.third}")
    }
    println("Total sales of the Day: ₱${totalRevenue.sumOf { it.third }}")
}