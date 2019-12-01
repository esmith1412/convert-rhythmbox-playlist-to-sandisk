package convert_rhythmbox_player_to_sandisk;

// To create the SanDisk playlist file
import java.io.PrintWriter;
// To store the song filenames
import java.util.ArrayList;
// To handle issues creating the SanDisk playlist file
import java.io.FileNotFoundException;
// To store the song files in an ArrayList
import java.io.File;

public class SandiskPlaylist extends Playlist {
  private PrintWriter sandiskPlaylistWriter;

  SandiskPlaylist(String filename) {
    super(filename, "/media/elijah/16GB Rockbo/SD CARD MUSIC/");

    try {
      sandiskPlaylistWriter = new PrintWriter( getAbsolutePath() );
    } catch(FileNotFoundException err) {
      System.out.println("SanDisk playlist file could not be created: " + err);
    }
  }

  public void createPlaylistFromSongs(ArrayList<File> songs) {
    for (File song : songs) {
      // To set the absolute path to each song in the playlist
      sandiskPlaylistWriter.println(
        "/<microSD1>/SD CARD MUSIC/" + this.getName() + "/" + song.getName()
      );
    }
    // To save/close the SanDisk playlist file
    sandiskPlaylistWriter.close();
  }
}
