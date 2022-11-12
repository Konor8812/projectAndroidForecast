package com.illia.finalproject.model


class Location (private var country: String,
                private var city: String,
                private var timeZone: String,
                private var longitude: String,
                private var latitude: String,
                private var altitude: String) {

    override fun toString(): String {
        return if (city.isEmpty() || country.isEmpty()) {
            "Undefined"
        } else "$city, $country"
    }


    fun getCountry(): String {
        return country
    }

    fun getCity(): String {
        return city
    }

    fun getTimeZone(): String {
        return timeZone
    }

    fun getLongitude(): String {
        return longitude
    }

    fun getLatitude(): String {
        return latitude
    }

    fun getAltitude(): String {
        return altitude
    }

    fun setCountry(country: String) {
        this.country = country
    }

    fun setCity(city: String) {
        this.city = city
    }

    fun setTimeZone(timeZone: String) {
        this.timeZone = timeZone
    }

    fun setLongitude(longitude: String) {
        this.longitude = longitude
    }

    fun setLatitude(latitude: String) {
        this.latitude = latitude
    }

    fun setAltitude(altitude: String) {
        this.altitude = altitude
    }

}