import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
			String roomNumber = reader.readLine();
			String temp = reader.readLine();
			if (temp.indexOf(" ")!=-1){
				int numRows = Integer.parseInt(temp.substring(0, temp.indexOf(" ")));
			}
			int numCols = Integer.parseInt(reader.readLine());
			int totalDesks = Integer.parseInt(reader.readLine());

			//Prining
			System.out.println(roomNumber);
			//System.out.println(numRows);
			System.out.println(numCols);

			
			reader.close();
			
			
		} catch (NumberFormatException e) {
			System.out.println("There was error with INPUT!");
		}
		catch (Exception e) {
			System.out.println("There was an error!");
		}

	}

}