package com.rinbows.soft.pranklam.activity

import android.media.MediaPlayer
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rinbows.soft.pranklam.R
import com.rinbows.soft.pranklam.data.AppDatabase
import com.rinbows.soft.pranklam.data.DataListDTO
import com.rinbows.soft.pranklam.databinding.ActivityPlayerBinding
import com.rinbows.soft.pranklam.tools.AppConstant
import com.rinbows.soft.pranklam.tools.DataTools
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class PlayerActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var dataList: DataListDTO
    private var isSeekbarChaning: Boolean = false
    private var isDownload = false
    private var timer: Timer = Timer()
    override fun getActivityContentView(): View {
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        dataList = intent.getSerializableExtra(AppConstant.KEY_EXTRA) as DataListDTO
        initImg()
        initMediaPlayer()
        initButton()
        initSeekbar()
        initTitle()
    }

    private fun initTitle() {
        binding.playTitleName.text = dataList.title
    }

    private fun initImg() {
        try {
            Glide.with(this).load(dataList.preUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.img_error).into(binding.playPreImg)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initMediaPlayer() {
        try {
            mediaPlayer = MediaPlayer()
            mediaPlayer.isLooping = true
            dataList.downloadUrl.let {
                mediaPlayer.setDataSource(it)
                mediaPlayer.prepare()
                isDownload = true
            }
            binding.tvStart.text = calculateTime(mediaPlayer.currentPosition / 1000)
            binding.tvEnd.text = calculateTime(mediaPlayer.duration / 1000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initSeekbar() {
        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val duration2: Int = mediaPlayer.duration / 1000
                val position: Int = mediaPlayer.currentPosition
                binding.tvStart.text = calculateTime(position / 1000)
                binding.tvEnd.text = calculateTime(duration2)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                isSeekbarChaning = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                isSeekbarChaning = false
                mediaPlayer.seekTo(seekBar.progress)
                binding.tvStart.text = calculateTime(mediaPlayer.currentPosition / 1000)
            }
        })
    }

    private fun calculateTime(time: Int): String {
        val minute: Int
        val second: Int
        return if (time >= 60) {
            minute = time / 60
            second = time % 60
            if (minute < 10) {
                if (second < 10) {
                    "0$minute:0$second"
                } else {
                    "0$minute:$second"
                }
            } else {
                if (second < 10) {
                    "$minute:0$second"
                } else {
                    "$minute:$second"
                }
            }
        } else {
            second = time
            if (second in 0..9) {
                "00:0$second"
            } else {
                "00:$second"
            }
        }
    }

    private fun initButton() {
        binding.playBack.setOnClickListener(this)
        binding.play.setOnClickListener(this)
        binding.pause.setOnClickListener(this)
        binding.playLike.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.playBack -> {
                finish()
            }

            binding.play -> {
                if (!mediaPlayer.isPlaying) {
                    if (isDownload) {
                        play()
                    } else {
                        binding.progressbar.isVisible = true
                        startDownload()
                    }
                }
            }

            binding.pause -> {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                }
            }

            binding.playLike -> {
                if (!binding.playLike.isSelected) {
                    binding.playLike.isSelected = !binding.playLike.isSelected
                    Toast.makeText(
                        this@PlayerActivity, "You have collected this sound.", Toast.LENGTH_SHORT
                    ).show()
                    CoroutineScope(Dispatchers.IO).launch {
                        AppDatabase.dataBase.getDataListDao().insertData(dataList.apply {
                            isCollect = binding.playLike.isSelected
                        })
                    }

                } else {
                    binding.playLike.isSelected = !binding.playLike.isSelected
                    Toast.makeText(
                        this@PlayerActivity, "You have unfavorite this sound.", Toast.LENGTH_SHORT
                    ).show()
                    CoroutineScope(Dispatchers.IO).launch {
                        AppDatabase.dataBase.getDataListDao().deleteData(dataList.apply {
                            isCollect = binding.playLike.isSelected
                        })
                    }
                }
            }
        }
    }

    private fun startDownload() {
        dataList.mp3Url.let {
            DataTools.getDataMp3(this, it) { file ->
                if (file == null) {
                    isDownload = false
                    Toast.makeText(
                        this@PlayerActivity, "Sorry, the download failed.", Toast.LENGTH_SHORT
                    ).show()
                    binding.progressbar.isVisible = false
                } else {
                    binding.progressbar.isVisible = false
                    file.absolutePath.let { path ->
                        mediaPlayer.setDataSource(path)
                        mediaPlayer.prepare()
                        isDownload = true
                        play()
                        CoroutineScope(Dispatchers.IO).launch {
                            AppDatabase.dataBase.getDataListDao().updateData(dataList.apply {
                                downloadUrl = path
                            })
                        }
                    }
                }
            }
        }
    }

    private fun play() {
        binding.seekbar.setMax(mediaPlayer.duration)
        timer?.run {
            schedule(object : TimerTask() {
                override fun run() {
                    if (!isSeekbarChaning) {
                        binding.seekbar.progress = mediaPlayer.currentPosition
                    }
                }
            }, 0, 50)
        }
        mediaPlayer.start()
    }
}