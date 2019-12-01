package convert_rhythmbox_player_to_sandisk;

// To choose the desired Rhythmbox playlist file
import java.util.Scanner;
// To store the song filenames
import java.util.ArrayList;
// To create the ArrayList of songs from the Rhythmbox playlist
import java.io.File;

public class ConvertRhythmboxPlaylistToSandisk {
  public static void main(String[] args) {
    // To choose the Rhythmbox playlist for conversion
    Scanner keyboardInput = new Scanner(System.in);
    System.out.print("Which Rhythmbox playlist file did you want to convert? ");
    RhythmboxPlaylist rhythmboxPlaylist = new RhythmboxPlaylist(
      keyboardInput.next()
    );

    // Get the playlist name from the Rhythmbox playlist filename
    String rhythmboxPlaylistName = rhythmboxPlaylist.getName();
    // Create the SanDisk playlist
    System.out.println("Creating the SanDisk playlist file...");
    SandiskPlaylist sandiskPlaylist = new SandiskPlaylist(
      rhythmboxPlaylistName + "_sandisk.m3u"
    );

    // Get all the songs from the playlist
    System.out.println("Getting all the songs from the Rhythmbox playlist...");
    ArrayList<File> songs = rhythmboxPlaylist.getPlaylistSongs();

    // Create the SanDisk playlist
    System.out.println("Adding all the songs to the SanDisk playlist...");
    sandiskPlaylist.createPlaylistFromSongs(songs);

    /* Delete songs that aren't in the playlist from the folder,
    and add songs that are in the playlist, but not currently in the folder,
    to the folder */
    SandiskPlaylistFolder folder = new SandiskPlaylistFolder(
      rhythmboxPlaylistName, rhythmboxPlaylist
    );
    System.out.println("Deleting songs not in Rhythmbox playlist...");
    folder.deleteExtraSongsFromFolder();

    System.out.println("Copying Rhythmbox playlist songs to SanDisk folder...");
    folder.copyMissingSongsToFolder();

    System.out.println("Done.");
  }
}
