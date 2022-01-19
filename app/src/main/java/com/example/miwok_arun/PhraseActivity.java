package com.example.miwok_arun;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhraseActivity extends AppCompatActivity {
    MediaPlayer aaaudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase);

        final ArrayList<Words> wr = new ArrayList<Words>();
        wr.add(new Words("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
        wr.add(new Words("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        wr.add(new Words("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        wr.add(new Words("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling));
        wr.add(new Words("I’m feeling good.", "kuchi achit",R.raw.phrase_im_feeling_good));
        wr.add(new Words("Are you coming?", "әәnәs'aa?",R.raw.phrase_are_you_coming));
        wr.add(new Words("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        wr.add(new Words("I’m coming.", "әәnәm",R.raw.phrase_yes_im_coming));
        wr.add(new Words("Let’s go.", "yoowutis",R.raw.phrase_lets_go));
        wr.add(new Words("Come here.", "әnni'nem",R.raw.phrase_come_here));

        WordAdapter list = new WordAdapter (this,wr,R.color.category_phrases);
        //first create an array adapter object

        ListView wordlist = (ListView) findViewById(R.id.list2);//Modify this line to change it from ListView to GridView
        //and change root layout from List to Grid in activity_number
        wordlist.setAdapter(list);

        wordlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Words aPosition=wr.get(position);
                aaaudio = MediaPlayer.create(PhraseActivity.this,aPosition.getmaudio());
                aaaudio.start();
            }
        });
    }
}