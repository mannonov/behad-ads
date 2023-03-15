package com.behad.Interstitial.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.motion.widget.MotionLayout
import com.behad.Interstitial.R
import com.behad.Interstitial.ui.constan.BehadInterstitialConstants
import com.behad.Interstitial.ui.model.InterstitialData

private const val TIME_OUT_TIME = 5000L
private const val INTERVAL = 1000L

class BehadInterstitialDialog(context: Context, private val interData: InterstitialData) :
    Dialog(context) {

    override fun onStart() {
        super.onStart()
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.window?.setLayout(-1, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (interData.advertisementMediaType) {
            BehadInterstitialConstants.VIDEO_TYPE -> {
                setContentView(R.layout.layout_behad_interstital_video_dialog)
                setupVideoInterstitialAd()
            }
            else -> dismiss()
        }
        setCanceledOnTouchOutside(false)
        setCancelable(false)

        Log.d("interData", "onCreate: $interData")
    }

    private var interFinishCountDownTimer: CountDownTimer? =
        object : CountDownTimer(TIME_OUT_TIME, INTERVAL) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                checkForVideoEnd()
            }
        }

    private var interVideoView: VideoView? = null
    private var interMotionLayout: MotionLayout? = null
    private var tvVideoTitle: TextView? = null
    private var tvVideoDescription: TextView? = null
    private var btnGoToLinkVideo: Button? = null
    private var videoHasEnded: Boolean = false
    private var skipVideoView: CardView? = null
    private var closeVideView: CardView? = null
    private fun setupVideoInterstitialAd() {
        initInterVideoViews()
        interVideoView?.setVideoPath("https://user-images.githubusercontent.com/74708507/225076216-e07f6177-b9d9-4400-ad26-8939965a6dbf.MP4")
        interVideoView?.start()
        tvVideoTitle?.text = interData.advertisementTitle
        tvVideoDescription?.text = interData.advertisementDescription
        interVideoView?.setOnPreparedListener {
            it.setOnInfoListener { _, i, _ ->
                if (i == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    interFinishCountDownTimer?.start()
                    videoHasEnded = false
                }
                false
            }
        }
        interVideoView?.setOnCompletionListener {
            videoHasEnded = true
            checkForVideoEnd()
        }
        skipVideoView?.setOnClickListener {
            closeVideView?.visibility = View.VISIBLE
        }
        closeVideView?.setOnClickListener {
            interVideoView?.stopPlayback()
        }
    }

    private fun checkForVideoEnd() {
        if (videoHasEnded) {
            interMotionLayout?.transitionToEnd()
        } else {
            skipVideoView?.visibility = View.VISIBLE
        }
    }

    private fun initInterVideoViews() {
        interVideoView = findViewById(R.id.interVideoView)
        interMotionLayout = findViewById(R.id.inter_video_motion)
        tvVideoTitle = findViewById(R.id.tv_video_title)
        tvVideoDescription = findViewById(R.id.tv_video_description)
        btnGoToLinkVideo = findViewById(R.id.btn_go_to_link_video)
        skipVideoView = findViewById(R.id.skip_video_container)
        closeVideView = findViewById(R.id.stop_video_container)
    }
}
