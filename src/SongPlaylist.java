package src;

public class SongPlaylist {
    private Song root;

    public SongPlaylist() {
        this.root = null;
    }
    public Song getRoot() {
        return this.root;
    }

    public void addSong(Song song) {
        if (root == null) {
            this.root = song;
        }
        else {
            Song pos = root;
            while (true) {
                pos.checkArtist(song);
                //checks to add in left child, breaks to end loop
                if (pos.getLeft() == null && song.compareTo(pos) < 0) {
                    pos.setLeft(song);
                    break;
                }
                //continues traversal in left branch
                else if (song.compareTo(pos) < 0) {
                    pos = pos.getLeft();
                }
                //checks to add in right child
                else if (pos.getRight() == null && song.compareTo(pos) > 0) {
                    pos.setRight(song);
                    break;
                }
                //continues traversal in right branch
                else if (song.compareTo(pos) > 0){
                    pos = pos.getRight();
                }
                //handles duplicates so they are not added more than once.
                else if (song.compareTo(pos) == 0) {
                    pos.addStreams(song.getSongStreams());
                    break;
                }
            }
        }
    }

    public String subSet(String start, String end) {
        return subSet(root, start, end);
    }
    private String subSet(Song pos, String start, String end) {
        if (pos == null) {
            return "";
        }
        //checks if position satisfies keywords, uses if else since position cannot be both less than start and greater than end
        if (pos.getSongName().compareToIgnoreCase(start) < 0) { 
            return subSet(pos.getRight(), start, end);
        }
         else if (pos.getSongName().compareToIgnoreCase(end) > 0) {
            return subSet(pos.getLeft(), start, end);
        }
        return subSet(pos.getLeft(), start, end) + pos.getSongName() + " by: " + pos.getArtistName() + "\n" + subSet(pos.getRight(), start, end);
    }


}

class Song {
    private String songName;
    private int songStreams;
    private int songCount;
    private String artistName;
    private int artistStreams;
    private int artistCount;
    private Song leftChild;
    private Song rightChild;

    public Song(String songName, String artistName, String streamCount) { 
        this.songName = songName;
        this.artistName = artistName;
        this.artistStreams = Integer.parseInt(streamCount);
        this.artistCount++;
        this.songStreams = Integer.parseInt(streamCount);
        this.songCount++;
        this.leftChild = null;
        this.rightChild = null;
    }

    public void addStreams(int streams) {
        this.songStreams += streams;
        this.songCount++;
    }

    public void addArtistStreams(int streams) {
        this.artistStreams += streams;
        artistCount++;
    }

    public int songAverage() {
        return this.songStreams / this.songCount;
    }

    public int artistAverage() {
        return this.artistStreams / this.artistCount;
    }
    public void checkArtist(Song song) {
        //if artist is the same, adds songs streams of the current object to song and vice versa
        if (this.artistName.equals(song.getArtistName())) {
            this.addArtistStreams(song.getSongStreams());
            song.addArtistStreams(this.songStreams);
        }
    }

    public void setLeft(Song song) {
        this.leftChild = song;
    }
    public Song getLeft() {
        return this.leftChild;
    }

    public void setRight(Song song) {
        this.rightChild = song; 
    }

    public Song getRight() {
        return this.rightChild;
    }

    public String getSongName() {
        return this.songName;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public int getArtistStreams() {
        return this.artistStreams;
    }

    public int getSongStreams() {
        return this.songStreams;
    }

    //added to help clean up code
    public int compareTo(Song song) {
        return this.songName.compareToIgnoreCase(song.getSongName());
    }
}
