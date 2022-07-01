import javax.sound.midi.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        try{
            /* https://stackoverflow.com/questions/16462854/midi-beginner-need-to-play-one-note
             * Create a new Sythesizer and open it. Most of
             * the methods you will want to use to expand on this
             * example can be found in the Java documentation here:
             * https://docs.oracle.com/javase/7/docs/api/javax/sound/midi/Synthesizer.html
             */
            Synthesizer midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();

            //get and load default instrument and channel lists
            Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
            MidiChannel[] mChannels = midiSynth.getChannels();

            midiSynth.loadInstrument(instr[0]);//load an instrument

            System.out.println("Type 'q' to quit");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if ("q".equals(input)) {
                    break;
                }
                //https://www.inspiredacoustics.com/en/MIDI_note_numbers_and_center_frequencies
                // C2 = 36, G4 = 67, C6 = 84
                HashMap<Integer, String> notes = getNoteMap();

                int randomNote = getRandomNumberInRange(36, 67);

                System.out.println("Play note " + notes.get(randomNote));

                //Give trainee time to make a guess
                try {
                    Thread.sleep(3000);
                } catch( InterruptedException e ) { }

                System.out.println("PLAYING CORRECT NOTE");
                mChannels[0].noteOn(randomNote, 100);//On channel 0, play note number 60 with velocity 100
                try { Thread.sleep(3000); // wait time in milliseconds to control duration
                } catch( InterruptedException e ) { }
                mChannels[0].noteOff(randomNote);//turn of the note
            }

        } catch (MidiUnavailableException e) {}
    }

    private static HashMap<Integer, String> getNoteMap() {
        HashMap<Integer, String> notes = new HashMap<>();
        notes.put(36, "C2");
        notes.put(37, "C#2/Db2");
        notes.put(38, "D2");
        notes.put(39, "D#2/Eb2");
        notes.put(40, "E2");
        notes.put(41, "F2");
        notes.put(42, "F#2/Gb2");
        notes.put(43, "G2");
        notes.put(44, "G#2/Ab2");
        notes.put(45, "A2");
        notes.put(46, "A#2/Bb2");
        notes.put(47, "B2");
        notes.put(48, "C3");
        notes.put(49, "C#3/Db3");
        notes.put(50, "D3");
        notes.put(51, "D#3/Eb3");
        notes.put(52, "E3");
        notes.put(53, "F3");
        notes.put(54, "F#3/Gb3");
        notes.put(55, "G3");
        notes.put(56, "G#3/Ab3");
        notes.put(57, "A3");
        notes.put(58, "A#3/Bb3");
        notes.put(59, "B3");
        notes.put(60, "C4 (middle C)");
        notes.put(61, "C#4/Db4");
        notes.put(62, "D4");
        notes.put(63, "D#4/Eb4");
        notes.put(64, "E4");
        notes.put(65, "F4");
        notes.put(66, "F#4/Gb4");
        notes.put(67, "G4");
        return notes;
    }

    //https://mkyong.com/java/java-generate-random-integers-in-a-range/
    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}