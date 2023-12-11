package com.example.nhom9_bai4_1;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    List<Video> videoList;

    public  VideoAdapter(List<Video> videoList){
        this.videoList = videoList;
    }
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_video,parent,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoView(videoList.get(position));

        MediaController mediaController = new MediaController(holder.itemView.getContext());
        mediaController.setAnchorView(holder.videoView);
        holder.videoView.setMediaController(mediaController);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        VideoView videoView;
        TextView title, desc;
        ProgressBar videoProgressBar;
            public VideoViewHolder(@NonNull View itemView){
                super(itemView);

                videoView = itemView.findViewById(R.id.videoView);
                title = itemView.findViewById(R.id.video_title);
                desc = itemView.findViewById(R.id.video_desc);
                videoProgressBar= itemView.findViewById(R.id.videoProgressBar);
            }

            public void setVideoView(Video video){
                title.setText(video.getTitle());
                desc.setText(video.getDesc());
                videoView.setVideoPath(video.getVideoUrl());

                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();

                        float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                        float screenRatio = videoView.getWidth() / (float) videoView.getHeight();

                        float scale = videoRatio / screenRatio;
                        if (scale >= 1f){
                            videoView.setScaleX(scale);
                        }else {
                            videoView.setScaleY(1f/scale);
                        }
                    }
                });

                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }
    }
}
