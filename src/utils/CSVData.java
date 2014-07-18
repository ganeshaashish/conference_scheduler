package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVData 
{
	private ArrayList<PresenterInfo> presenter_data = new ArrayList<PresenterInfo>();
	
	public CSVData(){}
	public CSVData(boolean isRandomTestData)
	{
		System.out.println("Presenter Data Loading: ");
		System.out.println("-----------------------------");
		System.out.println("PresenterName\tHours Of Presentation\tPresenter Cost\n");
		for(int i=0; i<4; i++)
		{
			int hours = (int) ((Math.random() * (2- 1)) + 1);
			int cost = (int) ((Math.random() * (1000 - 100)) + 100);
			
			PresenterInfo pi = new PresenterInfo();
			pi.setPresenterName("P"+(i+1));
			pi.setPresntationTime(hours);
			pi.setCostOfPresentation(cost);
			
			presenter_data.add(pi);
			
			System.out.print("P"+(i+1));
			System.out.print("\t\t"+hours);
			System.out.print("\t\t\t$"+cost);
			System.out.println();
		}
		System.out.println("-----------------------------");
	}
	
	public ArrayList<PresenterInfo> getTestData()
	{
		return presenter_data;
	}
	
	public List<PresenterInfo> readCSV(File file)
	{
		ArrayList<PresenterInfo> csv_data = new ArrayList<PresenterInfo>();
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
					
					csv_data.add(pi);
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
