package com.example.miwok_arun;

public class Words
{ private String mMiwok;
    private  String meng;
    private int nImg=No_image;
    private static final  int No_image=-1;
    private int maudio;

    public Words(String Miwok, String eng,int Img,int audio)//this constructor is for number,family,color,
    {
        mMiwok = Miwok;
        meng = eng;
        nImg=Img;
        maudio=audio;
    }

    public Words(String Miwok, String eng,int audio)//this is nested constructor used for phrases bcz it doesn't have any image
    {
        mMiwok = Miwok;
        meng = eng;
        maudio=audio;
    }

    //below code is for get()method of all variables
    public String getmMiwok(){
        return mMiwok;
    }

    public String getmeng(){
        return meng;
    }

    public int getnImg() {
        return nImg;
    }

    public int getmaudio(){return maudio;}


//below code are for set()method of all variables
    public void setmMiwok(String mMiwok) {
        this.mMiwok = mMiwok;
    }

    public void setMeng(String meng) {
        this.meng = meng;
    }

    public void setnImg(int nImg) { this.nImg = nImg; }

    public boolean hasImage(){
        return nImg!=No_image;
    }

}
