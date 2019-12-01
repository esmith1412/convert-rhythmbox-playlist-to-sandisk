package convert_rhythmbox_player_to_sandisk;

/* To open the Rhythmbox playlist, and get the filename for each song, from each
line of the playlist */
import java.io.File;
// To read the Rhythmbox file
import java.util.Scanner;
// To store all the song names
import java.util.ArrayList;
// To handle issues opening the playlist file
import java.io.FileNotFoundException;

public class RhythmboxPlaylist extends Playlist {
  private File file;
  private Scanner rhythmboxPlaylistScanner;

  RhythmboxPlaylist(String filename) {
    super(filename, "/home/elijah/Music/");
    file = new File( getAbsolutePath() );
  }

  public ArrayList<File> getPlaylistSongs() {
    // To store the filename for each song in the playlist
    ArrayList<File> songFiles = new ArrayList<File>();

    try {
      rhythmboxPlaylistScanner = new Scanner(file);
    } catch(FileNotFoundException err) {
      System.out.println("Rhythmbox playlist file could not be opened: " + err);
    }

    // To find each song's file in the Rhythmbox playlist
    while ( rhythmboxPlaylistScanner.hasNextLine() ) {
      String line = rhythmboxPlaylistScanner.nextLine();
      if ( !line.startsWith("#") ) {
        /* To get songs from lines where song is in subfolder
        of Music directory */
        File song = new File(line);
        // To add each song to the ArrayList
        songFiles.add(song);
      }
    }
    // To close the Rhythmbox playlist file
    rhythmboxPlaylistScanner.close();
    return songFiles;
  }

  public ArrayList<String> getPlaylistSongFilenames() {
    // To store the filename for each song in the playlist
    ArrayList<String> songFilenames = new ArrayList<String>();

    try {
      rhythmboxPlaylistScanner = new Scanner(file);
    } catch(FileNotFoundException err) {
      System.out.println("Rhythmbox playlist file could not be opened: " + err);
    }

    // To find each song's file in the Rhythmbox playlist
    while ( rhythmboxPlaylistScanner.hasNextLine() ) {
      String line = rhythmboxPlaylistScanner.nextLine();
      if ( !line.startsWith("#") ) {
        /* To get songs from lines where song is in subfolder
        of Music directory */
        File song = new File(line);
        // To add each song filename to the ArrayList
        songFilenames.add( song.getName() );
      }
    }
    // To close the Rhythmbox playlist file
    rhythmboxPlaylistScanner.close();
    return songFilenames;
  }
}
