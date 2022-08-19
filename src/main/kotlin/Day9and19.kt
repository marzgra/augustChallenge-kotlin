import taxi.Driver
import taxi.Passenger
import taxi.TaxiPark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
    allDrivers.minus(trips.map { it.driver }.toSet())

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    if (minTrips == 0) return allPassengers

    return trips.flatMap { it.passengers }
        .groupingBy { it }
        .eachCount()
        .filter { it.value >= minTrips }
        .keys
}

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
    trips.filter { it.driver == driver }
        .flatMap { it.passengers }
        .groupingBy { it }
        .eachCount()
        .filter { it.value > 1 }
        .keys

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
    this.allPassengers
        .filter { p: Passenger ->
            this.trips.count { p in it.passengers && it.discount != null } >
                    this.trips.count { p in it.passengers && it.discount == null }
        }
        .toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return if(this.trips.isEmpty()) {
        null
    } else {
        val maxDuration:Int = trips.map{ it.duration }.max() ?: 0
        val mapByNumberOfTrips = HashMap<Int, IntRange>()
        for (i in 0..maxDuration step 10) {
            val range = IntRange(i, i + 9)
            val numberOfTripsInThisRange = this.trips.count { it.duration in range }
            mapByNumberOfTrips[numberOfTripsInThisRange] = range
        }

        mapByNumberOfTrips[mapByNumberOfTrips.toSortedMap().lastKey()]
    }
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if(this.trips.isEmpty()) {
        return false
    } else {
        val totalTripsCost = this.trips.sumOf { it.cost }
        val mapCostByDriverSorted =  trips
            .groupBy { it.driver }
            .mapValues { (_, trips) -> trips.sumByDouble { it.cost }}
            .toList()
            .sortedByDescending { (_, value) -> value}.toMap()

        var currentSum = 0.0
        var numberOfDrivers = 0
        for (value in mapCostByDriverSorted.values){
            numberOfDrivers++
            currentSum += value
            if (currentSum >= (totalTripsCost * 0.8)) break
        }

        return numberOfDrivers <= (allDrivers.size * 0.2)
    }
}