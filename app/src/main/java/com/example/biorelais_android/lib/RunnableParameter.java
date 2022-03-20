package com.example.biorelais_android.lib;



public interface RunnableParameter extends Runnable {

    @Override
    void run();

    void run(Object param);

}
