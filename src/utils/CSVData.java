package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVData 
{	
	public CSVData(){}
	//*** Constructor just for testing the working of program *****
	private Map<String, PresenterInfo> presenter_data = new HashMap<String, PresenterInfo>();
	public CSVData(boolean isRandomTestData)	//*** boolean is just a parameter of no use **
	{
		System.out.println("--------------- RANDOM TEST DATA --------------");
		System.out.println("PresenterName\tHours Of Presentation\tPresenter Cost");
		for(int i=0; i<10; i++)
		{
			int hours = (int) ((Math.random() * (5 - 1)) + 1);
			int cost = (int) ((Math.random() * (1000 - 100)) + 100);
			
			PresenterInfo pi = new PresenterInfo();
			pi.setPresenterName("P"+(i+1));
			pi.setPresntationTime(hours);
			pi.setCostOfPresentation(cost);
			
			presenter_data.put("P"+(i+1), pi);
			
			System.out.print("P"+(i+1));
			System.out.print("\t\t"+hours);
			System.out.print("\t\t\t$"+cost);
			System.out.println();
		}
	}
	public Map<String, PresenterInfo> getTestData()
	{
		return presenter_data;
	}
	
	//*** Reading a CSV file and get the map of entries ****************
	public Map<String, PresenterInfo> readCSV(File file)
	{
		Map<String, PresenterInfo> csv_data = new HashMap<String, PresenterInfo>();
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			int firstLineCounter = 0;
			while((line = br.readLine()) != null)
			{
				if(firstLineCounter!=0)
				{
					String[] splits = line.split(",");
					PresenterInfo pi = new PresenterInfo();
					pi.setPresenterName(splits[0].trim());
					pi.setPresntationTime(Integer.parseInt(splits[1].trim()));
					pi.setCostOfPresentation(Integer.parseInt(splits[2].trim()));
					
					csv_data.put(splits[0].trim(), pi);
				}
				firstLineCounter++;
			}
			br.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return csv_data;
	}
	
}
