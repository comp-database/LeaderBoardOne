package com.example.leaderboardone

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import android.widget.VideoView
import com.example.leaderboardone.databinding.ActivitySplashScreenBinding

class Splash_screen : AppCompatActivity() {
    lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash_screen)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        binding.animSplashScreenAnimation.playAnimation()

        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this , Login_screen::class.java)
            startActivity(intent)
            finish()
        },3000)


//        setupVideoView()

    }

//    private fun setupVideoView() {
//        val path = "android.resource://" + packageName + "/" + R.raw.splash_screen_video
//        binding.videoView.setVideoURI(Uri.parse(path))
//            val isPlaying = binding.videoView.isPlaying
//        if (isPlaying) {
//            binding.videoView.pause()
//        } else {
//            binding.videoView.start()
//        }
//
//        binding.videoView.setOnCompletionListener {
//            val intent = Intent(this , Login_screen::class.java)
//            startActivity(intent)
//            finish()
//        }
//        }

    }
