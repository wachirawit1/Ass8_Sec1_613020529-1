package layout

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface EmployeeAPI {
    @GET("allemp")
    fun retrieveEmployee(): Call<List<Employee>>


    @FormUrlEncoded
    @POST("emp")
    fun insertStd(
        @Field("name") emp_name: String,
        @Field("gender") emp_gender: String,
        @Field("email") emp_email: String ,
        @Field("salary") emp_salary: Int
    ):Call<Employee>
}