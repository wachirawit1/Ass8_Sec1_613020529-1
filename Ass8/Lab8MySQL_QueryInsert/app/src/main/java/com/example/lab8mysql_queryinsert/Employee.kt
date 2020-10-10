package layout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Employee(
    @Expose
    @SerializedName("name") val emp_name: String,

    @Expose
    @SerializedName("gender") val emp_gender:String,

    @Expose
    @SerializedName("email") val emp_email: String ,

    @Expose
    @SerializedName("salary") val emp_salary: Int){}