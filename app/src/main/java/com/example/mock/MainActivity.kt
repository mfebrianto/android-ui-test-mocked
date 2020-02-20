package com.example.mock

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.example.mock.models.Characters
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        disableStrictMode()
        setListener()
    }

    private fun disableStrictMode() {
        //WARNING: do not do this on real application
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    private fun setListener() {
        button.setOnClickListener { hello_world.text = callAPI() }
    }

    private fun callAPI(): String? {
        var reader: BufferedReader? = null
        var witcherChars: Characters?

        try {
            val myUrl = URL(BuildConfig.SERVER_URL)
            val conn: HttpURLConnection = myUrl.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.connect()

            val input = BufferedReader(
                InputStreamReader(
                    conn.inputStream
                )
            )

            var totalLine = ""
            var inputLine: String?
            while (input.readLine().also { inputLine = it } != null) {
                println(inputLine)
                totalLine += inputLine
            }
            input.close()

            var gson = Gson()
            witcherChars = gson?.fromJson(totalLine, Characters::class.java)
            return witcherChars.characterCollection.collection.first().name

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        return null
    }
}
