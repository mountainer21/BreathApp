package com.bam.navigationexample

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.bam.navigationexample.databinding.FragmentFirstBinding
import com.bam.navigationexample.databinding.FragmentForthBinding
import com.bam.navigationexample.databinding.FragmentSecondBinding
import java.io.IOException


@Suppress("DEPRECATION")
class ForthFragment : Fragment() {
    var mediaPlayer: MediaPlayer? = null

    lateinit var binding: FragmentForthBinding
    private var timer: CountDownTimer? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForthBinding.inflate(inflater)
        return binding.root
//        return inflater.inflate(R.layout.fragment_second, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            play.setOnClickListener {
                startCountDownTimer(3000)
                binding.play.animate().apply {
                    duration = 1000
                    rotationYBy(360F)
                }.start()
                binding.play.text = "STOP"
            }
        }

        binding.floatHome.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_forthFragment_to_menuFragment)
        }
        binding.floatBack.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_forthFragment_to_thirdFragment)
        }
        binding.floatMusic.setOnClickListener {
            val audioUrl = "https://cdn.bensound.com/bensound-sunnyevening.mp3"
            //https://cdn.bensound.com/bensound-slowmotion.mp3
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
            try {
                mediaPlayer!!.setDataSource(audioUrl)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()

            }catch (e: IOException){
                    e.printStackTrace()
                }
//                Toast.makeText(this, "Audio started playing", Toast.LENGTH_SHORT).show()
            }
        binding.floatPause.setOnClickListener {
            if(mediaPlayer!!.isPlaying) {
                mediaPlayer!!.stop()
                mediaPlayer!!.reset()
                mediaPlayer!!.release()
            }
        }

        }

    private fun startCountDownTimer(timeMills: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMills, 1) {
            override fun onTick(timeM: Long) {
                binding.breathView.text = "ВДОХ"
                binding.timerView.text = ((timeM/1000)+1).toString()
                binding.apply {
                    play.setOnClickListener {
                        binding.timerView.text = "0"
                        binding.breathView.text = "ВДОХ"
                        timer?.cancel()
                        binding.play.text = "START"
                        binding.apply {
                            play.setOnClickListener {
                                startCountDownTimer(3000)
                                binding.play.animate().apply {
                                    duration = 1000
                                    rotationYBy(360F)
                                }.start()
                                binding.play.text = "STOP"
                            }
                        }
                    }
                }
            }

            override fun onFinish() {
                binding.timerView.text = "0"
                startCountDownTimer2(1000)
            }

        }.start()
    }


    private fun startCountDownTimer2(timeMills: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMills, 1) {
            override fun onTick(timeM: Long) {
                binding.breathView.text = "ВЫДОХ"
                binding.timerView.text = ((timeM/1000)+1).toString()
            }

            override fun onFinish() {
                binding.timerView.text = "0"
                startCountDownTimer(3000)
            }

        }.start()
    }
}
