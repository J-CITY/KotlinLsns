package Patterns.Builder

enum class Type {
    CITY_CAR, SPORTS_CAR, SUV
}
class Car(
    val type: Type, val seats: Int, val engine: String, val transmission: String,
    val tripComputer: String, val gpsNavigator: String
) {
    var fuel = 0.0
    init {

    }
}

class CarBuilder {
    private var type: Type? = null
    private var seats: Int = 0
    private var engine: String=""
    private var transmission: String=""
    private var tripComputer: String=""
    private var gpsNavigator: String=""

    val result: Car
        get() = Car(type!!, seats, engine!!, transmission!!, tripComputer!!, gpsNavigator!!)

    fun setType(type: Type) {
        this.type = type
    }

    fun setSeats(seats: Int) {
        this.seats = seats
    }

    fun setEngine(engine: String) {
        this.engine = engine
    }

    fun setTransmission(transmission: String) {
        this.transmission = transmission
    }

    fun setTripComputer(tripComputer: String) {
        this.tripComputer = tripComputer
    }

    fun setGPSNavigator(gpsNavigator: String) {
        this.gpsNavigator = gpsNavigator
    }
}

fun main() {
    val builder = CarBuilder()
    builder.setType(Type.CITY_CAR)
    builder.setSeats(2)
    builder.setEngine("engine")
    builder.setTransmission("Transmission.AUTOMATIC")
    builder.setTripComputer("TripComputer")
    builder.setGPSNavigator("GPSNavigator")
}