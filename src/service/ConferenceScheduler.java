package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.CSVData;
import utils.PresenterInfo;

public class ConferenceScheduler 
{
	public static void main(String[] args) 
	{
		int conference_hours = 8;
		int number_of_slots = 3;
		List<PresenterInfo> pi_list = new CSVData().getTestData();
		ConferenceScheduler.scheduler(conference_hours, number_of_slots, pi_list);
	}
	public static ArrayList<String> scheduler(int conference_hours, int number_of_slots, List<PresenterInfo> pi_list)
	{
		ArrayList<String> response = new ArrayList<String>();
		if(pi_list.size() >= number_of_slots)
		{
			int hr_sum = 0;
			int cost_sum = 0;
			List<PresenterInfo> selected_list = new ArrayList<PresenterInfo>();
			for(int i=0; i<pi_list.size()-(number_of_slots-1); i++)
			{
				for(int j=i+1; j<pi_list.size(); j++)
				{
					for(int k=j+1; k<pi_list.size(); k++)
					{
						hr_sum += pi_list.get(i).getPresntationTime() + pi_list.get(j).getPresntationTime() +
								pi_list.get(k).getPresntationTime();
						cost_sum += pi_list.get(i).getCostOfPresentation() + pi_list.get(j).getCostOfPresentation() +
								pi_list.get(k).getCostOfPresentation();
						String presnter_name = pi_list.get(i).getPresenterName() + ", " + 
								pi_list.get(j).getPresenterName() + ", " +
								pi_list.get(k).getPresenterName();
						
						PresenterInfo pi = new PresenterInfo();
						pi.setPresenterName(presnter_name);
						pi.setCostOfPresentation(cost_sum);
						pi.setPresntationTime(hr_sum);
						selected_list.add(pi);
						
						hr_sum = 0;
						cost_sum = 0;
					}
				}
			}
			
			Collections.sort(selected_list, new PresenterInfo());
			
			int min_cost = selected_list.get(0).getCostOfPresentation();
			for(int i=0; i<selected_list.size(); i++)
			{
				PresenterInfo pi = selected_list.get(i);
				if(pi.getCostOfPresentation() <= min_cost)
				{
					//System.out.println(pi.getPresenterName()+", $"+pi.getCostOfPresentation());
					response.add(pi.getPresenterName()+", $"+pi.getCostOfPresentation());
				}
			}
		}
		else
		{
			//System.out.println("Not enough presenters");
			response.add("Not enough presenters");
		}
		
		return response;
	}
}
