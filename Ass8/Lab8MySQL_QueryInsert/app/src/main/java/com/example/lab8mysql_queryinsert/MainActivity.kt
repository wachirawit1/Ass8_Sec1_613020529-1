package com.example.lab8mysql_queryinsert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import layout.Employee
import layout.EmployeeAPI
import layout.StudentsAdapter
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var employeeList = arrayListOf<Employee>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goadd.setOnClickListener(){
            val intent = Intent(this, insertActivity::class.java)
            startActivity(intent)
        }

        recycler_view.adapter = StudentsAdapter(this.employeeList,applicationContext)
        recycler_view.layoutManager= LinearLayoutManager(applicationContext)
    }

    override fun  onResume(){
        super.onResume()
        callEmployeedata()
    }
    fun callEmployeedata(){
        employeeList.clear()
        val serv: EmployeeAPI= Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmployeeAPI::class.java)

        serv.retrieveEmployee()
            .enqueue(object : Callback<List<Employee>>{
                override fun onResponse(
                    call: Call<List<Employee>>,
                    response: Response<List<Employee>>
                ){
                    response.body()?.forEach{
                        employeeList.add(Employee(it.emp_name, it.emp_gender, it.emp_email, it.emp_salary))
                    }
                    recycler_view.adapter=StudentsAdapter(employeeList,applicationContext)
                }
                override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                    return t.printStackTrace()
                }
            })

}


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.option_menu,menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        when(id){
//            R.id.item1 -> {
//                val intent = Intent(this@MainActivity,insertActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }
}