import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileReaderClass {
	private String textFileName;

	public FileReaderClass(String txt){
		textFileName = txt;
	}


	
	public void parse() {
		try {
			File teacherFile = new File(textFileName);
			teacherFile.createNewFile();
			//These 2 lines create the file
			
			// FileWriter fileWriter = new FileWriter(teacherFile, false); //Write mode
			// BufferedWriter writer = new BufferedWriter(fileWriter);
			// writer.write("Name = John \n");
			// writer.flush();
			// writer.close(); //You MUST close a file and cannot leave it open
			
			FileReader fileReader = new FileReader(teacherFile);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = reader.readLine();
			reader.close();
			
			System.out.println(line);
		} catch (Exception e) {}

	}

}
