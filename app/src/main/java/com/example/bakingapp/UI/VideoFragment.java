package com.example.bakingapp.UI;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakingapp.Models.Step;
import com.example.bakingapp.R;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoFragment extends Fragment {
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.videoView)
    PlayerView mPlayerView;
    @BindView(R.id.buttonsLayout)
    ConstraintLayout buttonLayout;
    @BindView(R.id.next)
    ImageView nextButton;
    @BindView(R.id.prev)
    ImageView prevButton;

    private SimpleExoPlayer player;

    private int position;
    private String videoUrl;
    private String videoDescription;
    private ArrayList<Step> mStepArrayList;

    private int mResumeWindow;
    private long mResumePosition;

    private OnFragmentInteractionListener mListener;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mStepArrayList = getArguments().getParcelableArrayList("all");
            position = getArguments().getInt("position");

            videoUrl = mStepArrayList.get(position).getVideoURL();
            videoDescription = mStepArrayList.get(position).getDescription();

            if (savedInstanceState != null){
                mResumePosition = savedInstanceState.getLong("position");
                player.seekTo(mResumePosition);
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);

        description.setText(videoDescription);


        if (videoUrl == null){
            mPlayerView.setVisibility(View.GONE);
        } else {
            mPlayerView.setVisibility(View.VISIBLE);
        }

        if (getResources().getBoolean(R.bool.isTablet)){
            buttonLayout.setVisibility(View.GONE);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < mStepArrayList.size()-1){
                    releasePlayer();
                    position++;
                    description.setText(mStepArrayList.get(position).getDescription());
                    initiatePlayer(mStepArrayList.get(position).getVideoURL());
                } else {
                    Toast.makeText(getContext(), getString(R.string.end_of_steps), Toast.LENGTH_SHORT).show();
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0){
                    releasePlayer();
                    position--;
                    description.setText(mStepArrayList.get(position).getDescription());
                    initiatePlayer(mStepArrayList.get(position).getVideoURL());
                } else {
                    Toast.makeText(getContext(), getString(R.string.start_of_steps), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void initiatePlayer(String videoUrl){
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(),
                new DefaultLoadControl()
        );

        mPlayerView.setPlayer(player);
        player.setPlayWhenReady(true);

        Uri uri = Uri.parse(videoUrl);
        String userAgent = Util.getUserAgent(getContext(), "BakingApp");
        MediaSource mediaSource = new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory(userAgent)).createMediaSource(uri);

        player.prepare(mediaSource);

    }

    private void releasePlayer() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean haveResumePosition = mResumeWindow != C.INDEX_UNSET;
        if (haveResumePosition && mPlayerView != null) {
            mPlayerView.getPlayer().seekTo(mResumeWindow, mResumePosition);
        }

        if ((Util.SDK_INT <= 23 || player == null)) {
            initiatePlayer(videoUrl);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPlayerView != null && mPlayerView.getPlayer() != null){
            mResumeWindow = mPlayerView.getPlayer().getCurrentWindowIndex();
            mResumePosition = Math.max(0, mPlayerView.getPlayer().getContentPosition());
            if (Util.SDK_INT <= 23) {
                releasePlayer();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initiatePlayer(videoUrl);
        }
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putLong("position", mResumePosition);
        super.onSaveInstanceState(outState);
    }

}
