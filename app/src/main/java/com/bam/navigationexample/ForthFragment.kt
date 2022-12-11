package com.bam.navigationexample

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bam.navigationexample.databinding.FragmentFirstBinding
import com.bam.navigationexample.databinding.FragmentForthBinding
import com.bam.navigationexample.databinding.FragmentSecondBinding


class ForthFragment : Fragment() {
    lateinit var binding: FragmentForthBinding
    private var timer: CountDownTimer? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForthBinding.inflate(inflater)
        return binding.root
        return inflater.inflate(R.layout.fragment_second, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            play?.setOnClickListener {
                startCountDownTimer(4000)
                binding.play?.text = "STOP"
            }
        }
    }

    private fun startCountDownTimer(timeMills: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMills, 1) {
            override fun onTick(timeM: Long) {
                binding.breathView.text = "ВДОХ"
                binding.timerView?.text = ((timeM/1000)+1).toString()
                binding.apply {
                    play?.setOnClickListener {
                        binding.timerView?.text = "0"
                        binding.breathView.text = "ВДОХ"
                        timer?.cancel()
                        binding.play?.text = "START"
                        binding.apply {
                            play?.setOnClickListener {
                                startCountDownTimer(4000)
                                binding.play?.text = "STOP"
                            }
                        }
                    }
                }
            }

            override fun onFinish() {
                binding.timerView?.text = "0"
                startCountDownTimer2(7000)
            }

        }.start()
    }


    private fun startCountDownTimer2(timeMills: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMills, 1) {
            override fun onTick(timeM: Long) {
                binding.breathView.text = "СТОП"
                binding.timerView?.text = ((timeM/1000)+1).toString()
            }

            override fun onFinish() {
                binding.timerView?.text = "0"
                startCountDownTimer3(8000)
            }

        }.start()
    }
    private fun startCountDownTimer3(timeMills: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMills, 1) {
            override fun onTick(timeM: Long) {
                binding.breathView.text = "ВЫДОХ"
                binding.timerView?.text = ((timeM/1000)+1).toString()
            }
            override fun onFinish() {
                binding.timerView?.text = "0"
                startCountDownTimer(4000)
            }
        }.start()
    }
}
