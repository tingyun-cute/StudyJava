package study.day.thread;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {
    public static void main(String[] args) {
        new Thread(){
            public void run() {
                enjoyMusic();
            }
        }.start();
        browseNews();
    }

    private static void browseNews(){
        for(; ;){
            System.out.println("Uh-hub, the good news");
            sleep(1);
        }
    }

    private static void enjoyMusic(){
        for (; ;){
            System.out.println("Uh-hub, the nice music");
            sleep(1);
        }
    }

    private static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
