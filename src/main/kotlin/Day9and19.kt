import taxi.Driver
import taxi.Passenger
import taxi.TaxiPark
import taxi.Trip

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

    return trips.flatMap(Trip::passengers)
        .groupBy { passenger -> passenger }
        .filter { it.value.size >= minTrips }
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
    allPassengers
        .associateWith { p -> trips.filter { t -> p in t.passengers } }
        .filterValues { group ->
            val (withDiscount, withoutDiscount) = group
                .partition { it.discount != null }
            withDiscount.size > withoutDiscount.size
        }.keys

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return trips
        .groupBy {
            val start = it.duration / 10 * 10
            val end = start + 9
            start..end
        }
        .maxByOrNull { (_, group) -> group.size }
        ?.key
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (this.trips.isEmpty()) return false

    val totalIncome = trips.sumOf(Trip::cost)

    val sortedDriversIncome: List<Double> = trips
        .groupBy(Trip::driver)
        .map { (_, tripsByDriver) -> tripsByDriver.sumOf(Trip::cost ) }
        .sortedDescending()

    val numberOdTopDrivers = (0.2 * allDrivers.size).toInt()
    val incomeByTopDrivers = sortedDriversIncome
        .take(numberOdTopDrivers)
        .sum()
    return incomeByTopDrivers >= 0.8 * totalIncome
}