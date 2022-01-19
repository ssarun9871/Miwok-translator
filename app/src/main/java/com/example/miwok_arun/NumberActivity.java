package com.example.miwok_arun;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Words> wr = new ArrayList<Words>();//here "Words" is an object (we can put datatype or object inside <>)


        //word w=new word("one","lutti");
        //words.add(w);  the consize form of this code is written in below
        wr.add(new Words("one", "lutti", R.drawable.number_one,R.raw.number_one));
        wr.add(new Words("two", "otiiko", R.drawable.number_two,R.raw.number_two));
        wr.add(new Words("three", "tolookosu", R.drawable.number_three,R.raw.number_three));
        wr.add(new Words("four", "oyyisa", R.drawable.number_four,R.raw.number_four));
        wr.add(new Words("five", "massokka", R.drawable.number_five,R.raw.number_five));
        wr.add(new Words("six", "temmokka", R.drawable.number_six,R.raw.number_six));
        wr.add(new Words("seven", "kenekaku", R.drawable.number_seven,R.raw.number_seven));
        wr.add(new Words("eight", "kawinta", R.drawable.number_eight,R.raw.number_eight));
        wr.add(new Words("nine", "wo’e", R.drawable.number_nine,R.raw.number_nine));
        wr.add(new Words("ten", "na’aacha", R.drawable.number_ten,R.raw.number_ten));


        WordAdapter list = new WordAdapter(this, wr, R.color.category_numbers);
        //first create an array adapter object
        ListView wordlist = (ListView) findViewById(R.id.list);//Modify this line to change it from ListView to GridView
        //and change root layout from List to Grid in activity_number

        wordlist.setAdapter(list);
        wordlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               releaseMediaPlayer();

               Words aPosition = wr.get(position);
               // Request audio focus so in order to play the audio file. The app needs to play a
               // short audio file, so we will request audio focus with a short amount of time
               // with AUDIOFOCUS_GAIN_TRANSIENT.
               mMediaPlayer = MediaPlayer.create(NumberActivity.this, aPosition.getmaudio());
               int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                       AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

               if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                   // We have audio focus now.

                   // Create and setup the {@link MediaPlayer} for the audio resource associated
                   // with the current word


                   // Start the audio file
                   mMediaPlayer.start();

                   // Setup a listener on the media player, so that we can stop and release the
                   // media player once the sound has finished playing.
                   mMediaPlayer.setOnCompletionListener(mCompletionListener);
                   }
           }
       });
    }



    @Override
    protected void onStop() {
        super.onStop();//if u remove super class then app will crash unexpectedly
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
