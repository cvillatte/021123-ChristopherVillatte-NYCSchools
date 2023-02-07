package com.example.firstnetworkapi.service

import com.example.firstnetworkapi.model.SchoolsItem
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {

    

    /**
     * method to get the schools from server
     */
    @GET(SCHOOL_PATH)
    suspend fun getAllSchools(): Response<List<SchoolsItem>>

    @GET (SAT_SCORES)
    suspend fun getSatScores(): Response<List<SchoolsItem>>
    companion object
    {
        //School List:
        // https://data.cityofnewyork.us/resource/s3k6-pzi2.json

        //SAT SCORES:
        //https://data.cityofnewyork.us/resourse/f9bf-2cp4.json?dbn=11X253


        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        const val SCHOOL_PATH = "s3k6-pzi2.json"
        const val SAT_SCORES = "f9bf-2cp4.json"
    }
}