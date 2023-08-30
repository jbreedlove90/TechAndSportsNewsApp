package com.jasminebreedlove.techandsportsnews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.jasminebreedlove.techandsportsnews.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
