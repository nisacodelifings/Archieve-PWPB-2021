package com.example.ulangan_aulia;

public class Note {
    String id, name, tgl, isi;

    public Note(String name, String note,String tgl){
        this.name = name;
        this.tgl = tgl;
        this.isi = note;
        this.id=tgl+name;
    }

    public Note(String name, String note,String tgl, String id){
        this.name = name;
        this.tgl = tgl;
        this.isi = note;
        this.id=id;
    }
    public Note(){}

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getTgl(){
        return tgl;
    }
    public void setTgl(String t){
        this.tgl = t;
    }

    public String getIsi(){
        return isi;
    }
    public void setIsi(String i){
        this.isi=i;
    }

}
