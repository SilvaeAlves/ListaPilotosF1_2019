package br.com.pilotosf1.app

import com.google.gson.annotations.SerializedName

data class GetDriveResponse(
    @SerializedName("MRData") val data: MRData)

data class MRData(
    @SerializedName("DriverTable") val driverTable:DriverTable)

data class DriverTable(
    @SerializedName("Drivers") val drivers:List<Driver>)






