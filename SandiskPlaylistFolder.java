package convert_rhythmbox_player_to_sandisk;

// To figure out which Rhythmbox playlist songs are in the folder, or not
import java.util.ArrayList;
// To get all the songs from the folder
import java.io.File;
// To copy the songs from the Music folder to the SanDisk playlist folder
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class SandiskPlaylistFolder {
  private String name; // SanDisk playlist folder name (e.g., Anime, Rap)
  // Absolute path to the SanDisk playlist folder, from user's root directory
  private String path;
  // To search for a filename to delete
  private ArrayList<String> rhythmboxPlaylistSongFilenames;
  // To contain all the song names that are in the SanDisk playlist folder
  private ArrayList<String> sandiskPlaylistSongFilenames;
  // To contain all the song names from the Rhythmbox playlist
  private ArrayList<File> rhythmboxPlaylistSongFiles;

  public SandiskPlaylistFolder(String name, RhythmboxPlaylist playlist) {
    this.name = name;
    this.path = "/media/elijah/16GB Rockbo/SD CARD MUSIC/" + name + "/";
    sandiskPlaylistSongFilenames = this.getSongFilenamesFromSandiskFolder();
    rhythmboxPlaylistSongFiles = playlist.getPlaylistSongs();
    rhythmboxPlaylistSongFilenames = playlist.getPlaylistSongFilenames();
  }

  private ArrayList<String> getSongFilenamesFromSandiskFolder() {
    ArrayList<String> songNames = new ArrayList<String>();
    // To get the File objects from the playlist folder's path
    File[] files = new File(this.path).listFiles();
    for (File file : files) {
      songNames.add( file.getName() );
    }
    return songNames;
  }

  // Helper function to delete songs from the SanDisk playlist folder
  private void deleteSong(String songName) {
    File fileToDelete = new File(this.path + songName);
    try {
      fileToDelete.delete();
    } catch(Exception err) {
      System.out.println("File doesn't exist, so it can't be deleted.");
    }
  }

  /* Add any songs from Rhythmbox playlist to the SanDisk folder that are
  not already there */
  public void copyMissingSongsToFolder() {
    for (File file : rhythmboxPlaylistSongFiles) {
      if ( !sandiskPlaylistSongFilenames.contains(file.getName()) ) {
        File musicFolderSong = new File( "/home/elijah/Music/" + file.toPath() );
        // To get the file path for the file in the SanDisk folder
        File sandiskSong = new File( this.path + file.getName() );
        // Copy the files
        System.out.println( "Copying the following song: " + file.getName() );
        try {
          Files.copy( musicFolderSong.toPath(), sandiskSong.toPath() );
        } catch(IOException err) {
          System.out.println("Song could not be copied to folder: " + err);
        }
      }
      else
        continue;
    }
  }

  /* Delete any songs not found in the Rhythmbox playlist from the SanDisk
  playlist folder */
  public void deleteExtraSongsFromFolder() {
    for (String filename : sandiskPlaylistSongFilenames) {
      // If the song is not in the playlist, delete it from the filesystem
      if ( !rhythmboxPlaylistSongFilenames.contains(filename) ) {
        System.out.println("Deleting the following song: " + filename);
        this.deleteSong(filename);
      }
      // If it's in the playlist, move to the next file
      else
        continue;
    }
  }
}
