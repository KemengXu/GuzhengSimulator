import java.io.IOException;

public class GuzhengHero {
    public static final String keyboard = "67890yuiophjkl;nm,./ ";
    public static void main(String[] args) throws IOException {
        GuzhengString[] stringCollection = new GuzhengString[keyboard.length()];
        stringCollection[0] = new GuzhengString(73.416);
        stringCollection[1] = new GuzhengString(82.407);
        stringCollection[2] = new GuzhengString(92.499);
        stringCollection[3] = new GuzhengString(110);
        stringCollection[4] = new GuzhengString(123.417);
        stringCollection[5] = new GuzhengString(146.832);
        stringCollection[6] = new GuzhengString(164.814);
        stringCollection[7] = new GuzhengString(184.997);
        stringCollection[8] = new GuzhengString(220);
        stringCollection[9] = new GuzhengString(246.942);
        stringCollection[10] = new GuzhengString(293.665);
        stringCollection[11] = new GuzhengString(329.629);
        stringCollection[12] = new GuzhengString(369.994);
        stringCollection[13] = new GuzhengString(440);
        stringCollection[14] = new GuzhengString(493.883);
        stringCollection[15] = new GuzhengString(587.330);
        stringCollection[16] = new GuzhengString(659.225);
        stringCollection[17] = new GuzhengString(739.989);
        stringCollection[18] = new GuzhengString(880);
        stringCollection[19] = new GuzhengString(987.767);
        stringCollection[20] = new GuzhengString(1174.659);
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if(!keyboard.contains(Character.toString(key))){continue;}
                stringCollection[keyboard.indexOf(key)].pluck();
            }

            /* compute the superposition of samples */
            double sample=0.0;
            for (int i = 0; i < stringCollection.length; i ++) {
                sample += stringCollection[i].sample();
            }
            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < stringCollection.length; i ++) {
                stringCollection[i].tic();
            }
        }
    }
}
