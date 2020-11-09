# CISC3130_Lab5
Java program written to store songs from Spotify CSV files in a Binary search tree and output the subtree between a starting key and an ending key.
Source code for the program can be found within the folder "src." Main.java contains the code needed to run the program and SongPlaylist.java contains the BST class used.  
A sample output file is included which is titled "subSet-Playlist.txt" and uses the start keyword "gooba" (a song in the files) and the end keyword "test end" (random string input)
while all CSV files used are stored in the folder "Spotify Files"  
While this program successfully creates the BST and adds nodes to it, finds the given subtree and outputs it, and calculates the average song streams, I had trouble implementing
the average artist streams. Due to time constraints with the assignment due date I went with the first implementation I could thing of (more details in Implementation).
## Implementation
For this program, the files stored in the CSV folder are stored in a File array so they can be accessed by index. Each file is passed through a scanner object, which reads each file
and a song object with the necessary information is passed to the method addSong from the SongPlaylist class. This method takes a Song object and traverses the tree until the Song object is added to the
tree, or an existing node with the same song title is found to avoid duplicates. The Song cronstructor takes 3 strings: the song name, the artist name, and the song streams.
A custom regex is used to split the file line and handles extra commas within the CSV entries ([Source](https://stackoverflow.com/a/15739087)).
For the output file, user input is taken for the start and end key. The program checks if the given keys are a valid range (start key must be less than or equal to the end key),
and if the range is invalid an error is thrown to avoid finding the subtree.  
The subset method uses inorder tree traversal so the output file is listed in ascending order. Since the output file is a playlist, only song name and artist name are included.
This method creates a string of all the songs in the subtree, which is then split into an array so each index contains an individual song and its corresponding artist.
This array is then passed to the method writeOutput in the Main class which writes each index to the output file.  
Average song streams is calculated using the int variables songStreams and songCount. Since the BST does not allow for duplicates, if the song is already in the tree, the song streams
from the new node are added to the existing node's streams and songCount is incremented by one. We can then call the method songAverage in the Song class, which returns streams/count.  
Similarly, average artist streams is calculated using the int variables artistStreams and artistCount. The method checkArtist is used to see if two Song nodes have the same artist
and add their song streams to each others artistStreams if they do, as well as increment artistCount in each node by one. The method artistAverage in the song class can be called,
which returns artistStreams/artistCount. This implementation takes the overall average artist streams. It does not calculate weekly/file average and use those averages to calculate
the average artist streams.
### Dependencies
* Written using Java 14
* Terminal to run commands for setup, may differ by OS used.
#### Set up
To run this program, you need to have a github account setup with an SSH key. Access to a terminal and git commands
are needed, unless your IDE of choice has git support. If you choose to run the program with this IDE,
the setup process will differ.  
1. Clone to your machine using `git clone git@github.com:bertom1/CISC3130_Lab5.git` 
2. Open the project directory with the command `cd CISC3130_Lab5`. After you run this command enter `cd src`  
3. To compile the program, use the command `javac Main.java`
4. To run the program, use the command `java Main`  
