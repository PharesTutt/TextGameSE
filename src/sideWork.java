import java.io.BufferedReader;
import java.io.FileReader;

public class sideWork {


String PPath,TPath,MPath,B1Path,B1Function,B2Path,
	   B2Function,B3Path,B3Function,B4Path,B4Function;
	
	
public  void SetSceneVar(String SFN)//SFN = SceneFileName 
{
	//use buffer tool to read picture path
	//<BUFFER CODE HERE>
	try 
	{
		BufferedReader br= new BufferedReader(new FileReader(SFN));
		while(br!=null) 
		{
			PPath =br.readLine();
		//read path for text file
			TPath = textReader(br.readLine());
			
		//read path for audio if available
			MPath = br.readLine();
		//read path for button text 1 and function
			B1Path =br.readLine();
			B1Function =br.readLine();
		//read path for button text 2 and function
			B2Path =br.readLine();
			B2Function =br.readLine();
		//read path for button text 3 and function
			B3Path =br.readLine();
			B3Function =br.readLine();
		//read path for button text 4 and function
			B4Path =br.readLine();
			B4Function =br.readLine();
			br.close();
		}
		
	}
	

	catch(Exception e) 
	{
		
	}
	
	
	//SceneSetup();
}
private static String textReader(String filePath) 
{
    StringBuilder contentBuilder = new StringBuilder();
    try (BufferedReader tr = new BufferedReader(new FileReader(filePath))) 
    {

        String sCurrentLine;
        while ((sCurrentLine = tr.readLine()) != null) 
        {
            contentBuilder.append(sCurrentLine).append("\n");
        }
    } 
    catch (Exception e) 
    {
        e.printStackTrace();
    }
    return contentBuilder.toString();
}

public void SetBlank() {
	PPath ="";
	TPath = "";
	MPath = "";
	B1Path ="";
	B1Function ="";
	B2Path ="";
	B2Function ="";
	B3Path ="";
	B3Function ="";
	B4Path ="";
	B4Function ="";
}

































}