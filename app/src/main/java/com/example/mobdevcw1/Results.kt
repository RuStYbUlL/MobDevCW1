package com.example.mobdevcw1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val result = findViewById<TextView>(R.id.resultView)
        result.setText("You got " + GameScreen.correct + " correct and " + GameScreen.incorrect + " incorrect");
    }

//    override fun onBackPressed() {
//        var backToMenueIntent = Intent(this, MainActivity::class.java);
//
//        //super.onBackPressed()
//    }

}