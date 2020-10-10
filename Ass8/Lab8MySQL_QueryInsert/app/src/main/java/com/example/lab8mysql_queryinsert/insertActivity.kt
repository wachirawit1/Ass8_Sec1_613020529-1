package com.example.lab8mysql_queryinsert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_insert.*
import layout.Employee
import layout.EmployeeAPI
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class insertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
    }

    fun addEmployee(v: View) {
        var radioSelectedID = radio_group.checkedRadioButtonId
        var radioSelected = findViewById<RadioButton>(radioSelectedID)
        val serv: EmployeeAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmployeeAPI::class.java)

        serv.insertStd(
            edt_name.text.toString(),
            radioSelected.text.toString(),
            edt_email.text.toString(),
            edt_salary.text.toString().toInt()
        ).enqueue(object : Callback<Employee> {
            override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
                if (response.isSuccessful()) {
                    Toast.makeText(applicationContext, "Sucessfully Inserted", Toast.LENGTH_LONG)
                        .show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Employee>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    " Error onFailuer " + t.message,
                    Toast.LENGTH_LONG
                ).show()
            }

        }

        )
    }

    fun cancelEmployee(view: View) {
        finish()
    }

}