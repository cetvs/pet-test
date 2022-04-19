package com.example.myapplication.data.source.remote



import com.example.myapplication.domain.models.PersonList
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface SimpleApi {
        @Headers(
                "Content-Type: application/json",
                "Prefer: code=200, dynamic=true"
        )
        @GET("mocks/kode-education/trainee-test/25143926/users")
        fun getPersons(): Call<PersonList>

        @Headers(
                "Content-Type: application/json",
                "Prefer: code=200, dynamic=true"
        )
        @GET("mocks/kode-education/trainee-test/25143926/users")
        fun getPeopleRx(): Single<PersonList>

}