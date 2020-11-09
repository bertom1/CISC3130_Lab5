package src;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
public class Main {
    public static void main(String[] args) {
        SongPlaylist test = new SongPlaylist();
        try {
            File folderPath = new File("./Spotify Files");
            File[] folderFiles = folderPath.listFiles();
            for (File file: folderFiles) {
                Scanner scan = new Scanner(file);
                scan.nextLine();
                scan.nextLine();
                while (scan.hasNext()) {
                    String line = scan.nextLine();
                    String[] fileLine = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    //trims away quotes which surrounds each song and artist entry in the CSV
                    //per file formatting, index 1 contains song name and index 2 contains the artist name
                    String songName = fileLine[1].substring(1, fileLine[1].length() - 1);
                    String artistName = fileLine[2].substring(1, fileLine[2].length() - 1);
                    //per file formating, index 3 contains the song streams
                    test.addSong(new Song(songName, artistName, fileLine[3]));
                }
                scan.close();
            }
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the starting keyword: ");
            String start = input.nextLine();
            System.out.println("Enter the ending keyword: ");
            String end = input.nextLine();
            if (start.compareTo(end) > 0) {
                input.close();
                throw new RuntimeException("Start cannot be greater than end");
            }
            String[] songs = test.subSet(start, end).split("\n");
            writeOutput(songs);
            input.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void writeOutput(String[] songs) {
        try {
            PrintWriter output = new PrintWriter(new File("subSet-Playlist.txt"));
            for (String s: songs) {
                output.println(s);
            }
            output.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}