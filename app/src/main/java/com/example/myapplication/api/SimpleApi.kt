package com.example.app.api



import com.example.app.classes.Person
import com.example.app.classes.PersonList
import io.reactivex.rxjava3.core.Flowable
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

//    @GET("3/movie/550?api_key=461062f3ca455541c4c57750fcbf6759")
//    fun getMovie(): Call<Movie>
//
//    @GET("3/movie/{key}/images?api_key=461062f3ca455541c4c57750fcbf6759")
//    fun getImage(@Path("key") movieId : Int ):Call<Image>
//
//    @GET("3/movie/{key}?api_key=461062f3ca455541c4c57750fcbf6759")
//    fun getMovie(@Path("key") movieId : Int ): Call<Person>
//
//    @GET("3/search/movie?api_key=461062f3ca455541c4c57750fcbf6759")
//    fun getMovieByName(@Query("query") query : String ): Call<MoviesList>
//
//    @GET("3/movie/popular?api_key=461062f3ca455541c4c57750fcbf6759")
//    fun getPopularMovie(): Call<MoviesList>
}