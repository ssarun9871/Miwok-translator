package com.example.miwok_arun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter  extends ArrayAdapter<Words> {

    private  Context mcontext;
    private  int mcolorid;
    public WordAdapter(@NonNull Context context,ArrayList<Words> wr,int colorResourceId)
    {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // Second argument i.e. resource is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.\
        super(context, 0, wr);
        mcontext=context;
        mcolorid=colorResourceId; }


    public View getView(int position, View convertView, ViewGroup parent)//this getView is method override (manually by pressing alt+insert)
    {
        //varibles written below are for positions
        String miwokPositon = getItem(position).getmMiwok();
        String mengPosition = getItem(position).getmeng();
        int mimgPosition    = getItem(position).getnImg();

        convertView = LayoutInflater.from(mcontext).inflate(R.layout.sample,parent,false);
        TextView miwok1 = convertView.findViewById(R.id.tv);
        TextView eng1 = convertView.findViewById(R.id.dtv);
        ImageView mimg1=convertView.findViewById(R.id.imgsample);
        View textContainer = convertView.findViewById(R.id.textContainer);

        miwok1.setText(miwokPositon);
        eng1.setText(mengPosition);

        Words current=getItem(position);
        if (current.hasImage()) //if listItem has image ,then it will be displayed
        {
            mimg1.setImageResource(mimgPosition);
            mimg1.setVisibility(View.VISIBLE);
        }
        else//if listItem doesn't have image ,then the imageView present in sample Layout is set to GONE
            {
            mimg1.setVisibility(View.GONE);
        }



        int color = ContextCompat.getColor(getContext(),mcolorid);// Find the color that the resource ID maps to
        textContainer.setBackgroundColor(color);// Set the background color of the text container View

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return convertView;
    }
}
