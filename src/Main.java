import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.math.MathContext;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Scanner scanner = new Scanner(System.in);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("choose an audio file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("WAV files (*.wav)", "wav"));
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(selectedFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            System.out.println("Now playing: " + selectedFile.getName());
            String response = "";
            while (!response.equals("Q")) {
                System.out.println("Press P to play or pause, Press R to reset, Press Q to quit");
                System.out.print("enter your choice");
                response = scanner.next();
                response = response.toUpperCase();
                switch (response) {
                    case ("P") -> {
                        if (clip.isActive()) {
                            clip.stop();
                            long pausedTime = clip.getMicrosecondPosition();
                            pausedTime = StrictMath.floorDiv(pausedTime, 1000000);
                            System.out.println("paused at " + pausedTime);
                        }
                        else{
                                clip.start();
                            long pausedTime = clip.getMicrosecondPosition();
                            pausedTime = StrictMath.floorDiv(pausedTime, 1000000);
                            System.out.println("continued at " + pausedTime);
                            }
                    }
                    case ("R") -> clip.setMicrosecondPosition(0);
                    case ("Q") -> clip.close();
                    default -> System.out.println("not a valid response");
                }
            }
        }
    }
}







