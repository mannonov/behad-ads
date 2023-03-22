package com.behad.Interstitial.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import com.behad.Interstitial.R
import com.behad.Interstitial.ui.constan.BehadInterstitialConstants
import com.behad.Interstitial.ui.model.BehadInterInterstitialData

private const val TIME_OUT_TIME = 5000L
private const val INTERVAL = 1000L

internal class BehadInterstitialDialog(
    private val activity: Activity,
    private val data: BehadInterInterstitialData,
) :
    Dialog(activity), View.OnClickListener {

    override fun onStart() {
        super.onStart()
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.window?.setLayout(-1, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (data.advertisementMediaType) {
            BehadInterstitialConstants.VIDEO_TYPE -> {
                setContentView(R.layout.layout_behad_interstital_video_dialog)
                setupVideoInterstitialAd()
            }
            else -> dismiss()
        }
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        Log.d("interData", "onCreate: $data")
    }

    private var interFinishCountDownTimer: CountDownTimer? =
        object : CountDownTimer(TIME_OUT_TIME, INTERVAL) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                checkForVideoEnd()
            }
        }

    private fun setupVideoInterstitialAd() {
        initInterVideoViews()
        interVideoView?.setVideoPath(data.advertisementLink)
        interVideoView?.start()
        tvVideoTitle?.text = data.advertisementTitle
        tvVideoDescription?.text = data.advertisementDescription
        initVideoListeners()
    }

    private fun checkForVideoEnd() {
        if (videoHasEnded) {
            videoInterContainer?.visibility = View.VISIBLE
            skipVideoView?.visibility = View.GONE
            setCanceledOnTouchOutside(true)
            setCancelable(true)
        } else {
            skipVideoView?.visibility = View.VISIBLE
        }
    }

    private fun videoGoToLink() {
        dismiss()
        activity.startActivity(
            Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://redirect.behad.uz/redirect/${this@BehadInterstitialDialog.data.campaignId}/${this@BehadInterstitialDialog.data.adId}/${this@BehadInterstitialDialog.data.userId}")
            },
        )
    }

    private fun initVideoListeners() {
        interVideoView?.setOnPreparedListener {
            it.setOnInfoListener { _, i, _ ->
                if (i == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    interFinishCountDownTimer?.start()
                    videoHasEnded = false
                }
                Log.d("videoViewState", "initVideoListeners: $i")
                false
            }
        }
        interVideoView?.setOnCompletionListener {
            videoHasEnded = true
            checkForVideoEnd()
        }
        skipVideoView?.setOnClickListener {
            closeVideView?.visibility = View.VISIBLE
            videoHasEnded = true
            interVideoView?.stopPlayback()
            checkForVideoEnd()
        }
        closeVideView?.setOnClickListener {
            dismiss()
        }
        interVideoView?.setOnClickListener {
            videoGoToLink()
        }
        btnGoToLinkVideo?.setOnClickListener {
            videoGoToLink()
        }
    }

    private var interVideoView: VideoView? = null
    private var tvVideoTitle: TextView? = null
    private var tvVideoDescription: TextView? = null
    private var btnGoToLinkVideo: Button? = null
    private var videoHasEnded: Boolean = false
    private var skipVideoView: CardView? = null
    private var closeVideView: CardView? = null
    private var videoInterContainer: LinearLayout? = null
    private fun initInterVideoViews() {
        interVideoView = findViewById(R.id.interVideoView)
        tvVideoTitle = findViewById(R.id.tv_video_title)
        tvVideoDescription = findViewById(R.id.tv_video_description)
        btnGoToLinkVideo = findViewById(R.id.btn_go_to_link_video)
        skipVideoView = findViewById(R.id.skip_video_container)
        closeVideView = findViewById(R.id.stop_video_container)
        videoInterContainer = findViewById(R.id.video_inter_container)
    }

    override fun onClick(view: View?) {
        Log.d("dialogViewClickTag", "onClick: ${view?.id}")
    }
}
