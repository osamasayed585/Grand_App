package com.osandroid.grandapp.roomDatabase.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.osandroid.grandapp.roomDatabase.model.Address
import com.osandroid.grandapp.roomDatabase.model.Company
import com.osandroid.grandapp.roomDatabase.model.Geo
import com.osandroid.grandapp.roomDatabase.model.User

class Converters {

    @TypeConverter
    fun fromUserToString(user: User): String {
        return Gson().toJson(user)
    }

    @TypeConverter
    fun fromStringToUser(strUser: String): User {
        return Gson().fromJson(strUser, User::class.java)
    }

    @TypeConverter
    fun fromAddressToString(address: Address): String {
        return Gson().toJson(address)
    }

    @TypeConverter
    fun fromStringToAddress(strAddress: String): Address {
        return Gson().fromJson(strAddress, Address::class.java)
    }

    @TypeConverter
    fun fromCompanyToString(company: Company): String {
        return Gson().toJson(company)
    }

    @TypeConverter
    fun fromCompanyToAddress(strCompany: String): Company {
        return Gson().fromJson(strCompany, Company::class.java)
    }

    @TypeConverter
    fun fromGeoToString(geo: Geo): String {
        return Gson().toJson(geo)
    }

    @TypeConverter
    fun fromCompanyToGeo(strGeo: String): Geo {
        return Gson().fromJson(strGeo, Geo::class.java)
    }
}