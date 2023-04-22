package core.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Scanner;

/**
 * A utility class for reading script files and retrieving the script data as a StringReader. The
 * class reads the file specified by the given path, and returns the contents of the file as a
 * StringReader object.
 */
public class ReadScriptFile {

  private final StringReader getScriptData;

  /**
   * Constructs a new ReadScriptFile object with the specified script path.
   *
   * @param scriptPath the path to the script file to be read
   * @throws IOException if the file specified by scriptPath cannot be found or read
   */
  public ReadScriptFile(String scriptPath) throws IOException {
    InputStream scriptFile;

    try {
      scriptFile = new FileInputStream(scriptPath);
    } catch (FileNotFoundException e) {
      throw new IOException("Filepath \"" + scriptPath + "\" not found !!");
    }

    Scanner readPPM = new Scanner(scriptFile);
    Appendable builder;
    builder = new StringBuilder();

    while (readPPM.hasNextLine()) {
      String s = readPPM.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    this.getScriptData = new StringReader(builder.toString());
  }

  /**
   * Returns the script data as a StringReader object.
   *
   * @return the script data as a StringReader object
   */
  public StringReader getScriptData() {
    StringReader data = this.getScriptData;
    return data;
  }

}
