package com.jasminebreedlove.techandsportsnews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // runnable for splash screen
        Handler().postDelayed({
            // to be executed when the timer is done
            val categories = Intent(this@SplashScreenActivity, CategoryChooser::class.java)
            startActivity(categories)

            // close this activity
            finish()
        }, SPLASH_TIMER)
    }

    companion object {
        // splash timer
        const val SPLASH_TIMER = 2000L
    }
}
