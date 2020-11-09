package src;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
public class Main {
    public static void main(String[] args) {
        SongPlaylist test = new SongPlaylist();
        try {
            //gets all files in folder containing spotify csv files
            File folderPath = new File("./Spotify Files");
            File[] folderFiles = folderPath.listFiles();
            //loops through file array to read all files
            for (File file: folderFiles) {
                Scanner scan = new Scanner(file);
                //skips first two lines since they do not contain csv entries
                scan.nextLine();
                scan.nextLine();
                while (scan.hasNext()) {
                    String line = scan.nextLine();
                    //regex made to handle extra commas within csv entries
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
            //takes user input to create subset playlist
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the starting keyword: ");
            String start = input.nextLine();
            System.out.println("Enter the ending keyword: ");
            String end = input.nextLine();
            //checks if start keyword is greater than end keyword
            if (start.compareTo(end) > 0) {
                input.close();
                //throws error to avoid traversing tree with invalid range
                throw new RuntimeException("Start cannot be greater than end");
            }
            //since subset returns one large string, we split the string into individual songs and store in an array
            String[] songs = test.subSet(start, end).split("\n");
            writeOutput(songs);
            input.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //writes output file
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