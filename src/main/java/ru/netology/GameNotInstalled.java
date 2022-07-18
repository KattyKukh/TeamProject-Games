package ru.netology;

public class GameNotInstalled extends RuntimeException{
    private String msg;

    public GameNotInstalled(String msg){
        super(msg);
    }
}
