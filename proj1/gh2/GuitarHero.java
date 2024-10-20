package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static GuitarString[] guitarStrings = new GuitarString[37];
    public static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static void main(String[] args) {
        for (int i = 0; i < 37; i++) {
            double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
            guitarStrings[i] = new GuitarString(frequency);
        }
        while(true){
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index >= 0 && index < 37) {
                    guitarStrings[index].pluck();
                }
        }
            double sample = 0;
            for (int i = 0; i < 37; i++) {
                sample += guitarStrings[i].sample();
            }
            StdAudio.play(sample);
            for (int i = 0; i < 37; i++) {
                guitarStrings[i].tic();
            }
        }
    }
}
