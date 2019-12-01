package convert_rhythmbox_player_to_sandisk;

// To get the playlist name and type from the file name
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Playlist {
  protected String filename; // full playlist filename
  protected String path; // directory that holds playlist
  protected String name; // e.g., Anime, Favorites, Rap
  protected String type; // rhythmbox or sandisk

  public Playlist(String filename, String path) {
    this.filename = filename;
    this.path = path;
    type = this.getPlaylistTypeFromFilename();
    name = this.getPlaylistNameFromFilename();
  }

  public String getName() { return name; }

  protected String getAbsolutePath() {
    return path + filename;
  }

  protected String getPlaylistTypeFromFilename() {
    // To determine the pattern to match
    Pattern playlistTypePattern = Pattern.compile("(rhythmbox|sandisk)");
    // To Find the match in the filename
    Matcher playlistTypeMatcher = playlistTypePattern.matcher(filename);
    // If matches are found return the first one; if not, return null.
    while ( playlistTypeMatcher.find() ) {
      return playlistTypeMatcher.group(1);
    }
    return null;
  }

  protected String getPlaylistNameFromFilename() {
    // To determine the pattern to match
    Pattern playlistNamePattern = Pattern.compile("(\\w+)_" + type + ".m3u");
    // To Find the match in the filename
    Matcher playlistNameMatcher = playlistNamePattern.matcher(filename);
    // If matches are found return the first one; if not, return null.
    while ( playlistNameMatcher.find() ) {
      return playlistNameMatcher.group(1);
    }
    return null;
  }
}
