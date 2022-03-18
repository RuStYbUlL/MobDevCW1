package com.example.mobdevcw1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //About button
        var aboutButton = findViewById<Button>(R.id.button2)
        aboutButton.setOnClickListener(){
            val popupWindow = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.layout_popup,null)
            popupWindow.contentView = view
            val textView = view.findViewById<TextView>(R.id.textView2)

            // Dismiss popup window when it is tapped on
            textView.setOnClickListener{
                popupWindow.dismiss()
            }
            popupWindow.showAsDropDown(aboutButton)

            //New game
            var newGameButton = findViewById<Button>(R.id.button)
            newGameButton.setOnClickListener(){
                gameScreenActivity()

            }
        }
    }
    fun gameScreenActivity() {
        val gameScreenIntent = Intent(this, GameScreen::class.java)
        startActivity(gameScreenIntent)


    }

}


/*
* References
* https://www.youtube.com/watch?v=Zgx3C9DQyjM&t=185s - popup window
* */