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
import android.widget.*
import androidx.cardview.widget.CardView
import com.behad.Interstitial.R
import com.behad.Interstitial.ui.constan.BehadInterstitialConstants
import com.behad.Interstitial.ui.model.BehadInterInterstitialData
import com.bumptech.glide.Glide

private const val TIME_OUT_TIME = 6000L
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
            BehadInterstitialConstants.IMAGE_TYPE -> {
                setContentView(R.layout.layout_behad_interstital_image_dialog)
                setupImageInterstitialAd()
            }
            else -> dismiss()
        }
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        Log.d("interData", "onCreate: $data")
    }

    private var interImageFinishCountDownTimer: CountDownTimer? =
        object : CountDownTimer(TIME_OUT_TIME, INTERVAL) {
            override fun onTick(p0: Long) {
                skipAfterInterImageView?.visibility = View.VISIBLE
                tvSkipAfterInterImage?.text =
                    "${tvSkipAfterInterImage?.context?.getString(R.string.you_can_close_image)} ${p0 / 1000}"
            }

            override fun onFinish() {
                skipAfterInterImageView?.visibility = View.GONE
                closeImageView?.visibility = View.VISIBLE
                interImageContainer?.visibility = View.VISIBLE
            }
        }

    private fun setupImageInterstitialAd() {
        initInterImageViews()
        interImageView?.let {
            Glide.with(it).load(data.advertisementLink).into(it)
        }
        interImageFinishCountDownTimer?.start()
        interImageTitle?.text = data.advertisementTitle
        interImageDesc?.text = data.advertisementDescription
        initInterImageListeners()
    }

    private fun initInterImageListeners() {
        btnInterGoToLinkImage?.setOnClickListener {
            videoGoToLink()
        }
        closeImageView?.setOnClickListener {
            dismiss()
        }
        interImageView?.setOnClickListener {
            videoGoToLink()
        }
    }

    private var interImageView: ImageView? = null
    private var skipAfterInterImageView: CardView? = null
    private var tvSkipAfterInterImage: TextView? = null
    private var closeImageView: CardView? = null
    private var interImageContainer: LinearLayout? = null
    private var interImageTitle: TextView? = null
    private var interImageDesc: TextView? = null
    private var btnInterGoToLinkImage: Button? = null
    private fun initInterImageViews() {
        interImageView = findViewById(R.id.img_inter)
        skipAfterInterImageView = findViewById(R.id.skip_image_after)
        tvSkipAfterInterImage = findViewById(R.id.tv_skip_image_after)
        closeImageView = findViewById(R.id.stop_image_container)
        interImageContainer = findViewById(R.id.image_inter_container)
        interImageTitle = findViewById(R.id.tv_image_title)
        interImageDesc = findViewById(R.id.tv_image_description)
        btnInterGoToLinkImage = findViewById(R.id.btn_go_to_link_image)
    }

    private var interVideoFinishCountDownTimer: CountDownTimer? =
        object : CountDownTimer(TIME_OUT_TIME, INTERVAL) {
            override fun onTick(p0: Long) {
                skipVideAfterView?.visibility = View.VISIBLE
                skipVideoAfterText?.text =
                    "${skipVideoAfterText?.context?.getString(R.string.you_can_skip_video)} ${p0 / 1000}"
            }

            override fun onFinish() {
                skipVideAfterView?.visibility = View.GONE
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
                    interVideoFinishCountDownTimer?.start()
                    videoHasEnded = false
                }
                Log.d("videoViewState", "initVideoListeners: $i")
                false
            }
        }
        interVideoView?.setOnCompletionListener {
            closeVideView?.visibility = View.VISIBLE
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
    private var skipVideAfterView: CardView? = null
    private var skipVideoAfterText: TextView? = null
    private fun initInterVideoViews() {
        interVideoView = findViewById(R.id.interVideoView)
        tvVideoTitle = findViewById(R.id.tv_video_title)
        tvVideoDescription = findViewById(R.id.tv_video_description)
        btnGoToLinkVideo = findViewById(R.id.btn_go_to_link_video)
        skipVideoView = findViewById(R.id.skip_video_container)
        closeVideView = findViewById(R.id.stop_video_container)
        videoInterContainer = findViewById(R.id.video_inter_container)
        skipVideAfterView = findViewById(R.id.skip_video_after)
        skipVideoAfterText = findViewById(R.id.tv_skip_video_after)
    }

    override fun onClick(view: View?) {
        Log.d("dialogViewClickTag", "onClick: ${view?.id}")
    }
}
