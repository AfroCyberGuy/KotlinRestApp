package com.codeguru.kotlinapp

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    private var editTextName: EditText? = null
    private var editTextPosition: EditText? = null
    private var editTextSalary: EditText? = null
    private var editTextExperience: EditText? = null

    private var button: Button? = null

    private var pd: ProgressDialog? = null

    private val URL = "http://10.0.2.2/mycompany/employee.php"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pd = ProgressDialog(this@MainActivity)

        editTextName = findViewById(R.id.editTextName) as EditText
        editTextPosition = findViewById(R.id.editTextPosition) as EditText
        editTextSalary = findViewById(R.id.editTextSalary) as EditText
        editTextExperience = findViewById(R.id.editTextExperience) as EditText
        button = findViewById(R.id.button) as Button

        button?.setOnClickListener({

            sendData()
        })
    }

    private fun sendData() {

        pd!!.setMessage("Sending data please wait..")
        pd!!.show()

        val stringRequest = object: StringRequest(Request.Method.POST, URL,
                Response.Listener<String>{response ->

                    pd!!.dismiss()

                    val obj = JSONObject(response)

                    Toast.makeText(applicationContext, obj.getString("name"), Toast.LENGTH_LONG).show()

                }, object: Response.ErrorListener{

            override fun onErrorResponse(p0: VolleyError?) {

                pd!!.dismiss()
                Toast.makeText(applicationContext, p0?.message, Toast.LENGTH_LONG).show()

            }
        })
        {
            override fun getParams(): MutableMap<String, String> {

                val params = HashMap<String, String>()

                params.put("name", editTextName?.text.toString())
                params.put("position", editTextPosition?.text.toString())
                params.put("salary", editTextSalary?.text.toString())
                params.put("experience", editTextExperience?.text.toString())


                return params
            }
        }

        VolleySingleton.instance?.addToRequestQueue(stringRequest)


    }
}
