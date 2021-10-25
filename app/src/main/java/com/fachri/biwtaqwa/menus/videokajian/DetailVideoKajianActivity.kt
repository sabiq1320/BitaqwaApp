package com.fachri.biwtaqwa.menus.videokajian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fachri.biwtaqwa.R
import com.fachri.biwtaqwa.databinding.ActivityDetailVideoKajianBinding
import com.fachri.biwtaqwa.menus.videokajian.model.VideoKajianModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class DetailVideoKajianActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_VIDEO_KAJIAN = "extra_video_kajian"
    }

    private lateinit var binding: ActivityDetailVideoKajianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailVideoKajianBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val video = intent.getParcelableExtra<VideoKajianModel>(EXTRA_VIDEO_KAJIAN) as VideoKajianModel

        initView(video)
    }
    private fun initView(video: VideoKajianModel){
        val youTubePlayerView: YouTubePlayerView =
            findViewById(R.id.player_video)
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object:
        AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(video.urlvideo,0f)
            }
        })
        binding.tvChannel.text = video.channel
        binding.tvTitle.text=video.title
        binding.tvSpeaker.text=video.speaker
        binding.tvSummary.text=video.summary
        binding.ivShare.setOnClickListener{
            val sendIntent = Intent()
            sendIntent.action =Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Kajian dengan judul \"${video.title}\" dibawakan oleh\${video.speaker}\"dan dari channel\"${video.channel}\" \n\n\n Link Video : https://www.youtube.com/watch?v=${video.urlvideo}"
            )
            sendIntent.type= "text/plain"
            startActivity(sendIntent)
        }
    }
}