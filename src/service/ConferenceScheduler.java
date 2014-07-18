package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.CSVData;
import utils.PresenterInfo;

public class ConferenceScheduler 
{
	private static final int number_of_slots = 3;
	public static void main(String[] args) 
	{
		int conference_hours = 8;
		Map<String, PresenterInfo> pi_map = new CSVData(true).getTestData();
		ArrayList<String> response  = ConferenceScheduler.maximize_presenter_scheduler(conference_hours, number_of_slots, pi_map);
		System.out.println();
		System.out.println("MAXIMIZE PRESENTERS --------------------");
		for(int i=0; i<response.size(); i++)
			System.out.println(response.get(i));
		System.out.println();
		System.out.println("MINIMIZE THE COST ----------------------");
		response  = ConferenceScheduler.minimize_the_cost(conference_hours, number_of_slots, pi_map);
		for(int i=0; i<response.size(); i++)
			System.out.println(response.get(i));
	}
	
	//*** Maximize the number of presenters *********************
	public static ArrayList<String> maximize_presenter_scheduler(int conference_hours, int number_of_slots, Map<String, PresenterInfo> pi_map)
	{
		//*** Response handler array list ********************
		ArrayList<String> response = new ArrayList<String>();
		int total_presenter = pi_map.size();
		//*** If total presenter greater than number of slots than do this *******
		if(total_presenter >= number_of_slots)
		{
			//*** Required variables while iterating over combinations ***********
			int hr_sum = 0;		//*** counter variable for sum of hours of ppt ***
			int cost_sum = 0;	//*** counter variable for sum of cost of ppt ****
			int total_time_all_presenter = 0;
			String presenters = "";
			//*** List of all combinations with presenter > 3 *********************
			List<PresenterInfo> selected_presenter = new ArrayList<PresenterInfo>();
			//*** Calculate total time for all presenters *************************
			for (Map.Entry<String, PresenterInfo> entry : pi_map.entrySet())
			{
			    total_time_all_presenter +=  entry.getValue().getPresntationTime();
			}
			//*** If total time is greate than conference time begin scheduling ***
			if(total_time_all_presenter < conference_hours)
			{
				response.add("Not enough presenters");
			}
			else
			{
				//*** Get array list out of map ******************
				Set<String> all_presenter_set = pi_map.keySet();
				List<String> all_presenter_list = new ArrayList<String>();
				all_presenter_list.addAll(all_presenter_set);
				//*** Get list of all possible combinations ******
				List<List<String>> possCombi = possibleCombinations(all_presenter_list);
				for (List<String> currentComb : possCombi) 
				{
					//*** Iterate over combinations with size > number of slots ****
					if(currentComb.size()>=number_of_slots)
					{
						for(int i=0; i<currentComb.size(); i++)
						{
							PresenterInfo current_pi = pi_map.get(currentComb.get(i));
							hr_sum += current_pi.getPresntationTime();
							cost_sum += current_pi.getCostOfPresentation();
							presenters += current_pi.getPresenterName() + ", ";
						}
						//*** if sum of hours is equal to conference hour add the combination ***
						if(hr_sum == conference_hours)
						{
							selected_presenter.add(new PresenterInfo(presenters, hr_sum, cost_sum));
						}
						//*** reset the counter variables for reprocesing *******
						else
						{
							hr_sum = 0;
							cost_sum = 0;
							presenters = "";
						}
					}
				}
				//*** Calculate max length of possible combinations *****
				int max_len = 0;
				for(int i=0; i<selected_presenter.size(); i++)
				{
					String current_presenter = selected_presenter.get(i).getPresenterName();
					int current_len = current_presenter.split(",").length;
					if(i==0)
						max_len = current_len;
					else
					{
						if(max_len < current_len)
							max_len = current_len;
					}
				}
				//*** Final selection list with maximum number of presenters *************
				ArrayList<PresenterInfo> final_selection = new ArrayList<PresenterInfo>();
				for(int i=0; i<selected_presenter.size(); i++)
				{
					String current_presenter = selected_presenter.get(i).getPresenterName();
					int current_len = current_presenter.split(",").length;
					if(current_len == max_len)
						final_selection.add(selected_presenter.get(i));
				}
				//*** Calculate minimum cost for all finalized cominations **************
				int min_cost = 0;
				for(int i=0; i<final_selection.size(); i++)
				{
					int currentCost = final_selection.get(i).getCostOfPresentation();
					if(i == 0)
						min_cost = currentCost;
					else
					{
						if(min_cost > currentCost)
							min_cost = currentCost;
					}
				}
				//*** Put the final response in the array list *******
				for(int i=0; i<final_selection.size(); i++)
				{
					int currentCost = final_selection.get(i).getCostOfPresentation();
					if(currentCost == min_cost)
						response.add(final_selection.get(i).getPresenterName()+"$"+final_selection.get(i).getCostOfPresentation());
				}
			}
		}
		else
		{
			response.add("Not enough presenters");
		}
		
		return response;
	}
	
	//*** Minimize the cost case 2 **************************
	public static ArrayList<String> minimize_the_cost(int conference_hours, int number_of_slots, Map<String, PresenterInfo> pi_map)
	{
		//*** Response handler array list ********************
		ArrayList<String> response = new ArrayList<String>();
		int total_presenter = pi_map.size();
		//*** If total presenter greater than number of slots than do this *******
		if(total_presenter >= number_of_slots)
		{
			//*** Required variables while iterating over combinations ***********
			int hr_sum = 0;		//*** counter variable for sum of hours of ppt ***
			int cost_sum = 0;	//*** counter variable for sum of cost of ppt ****
			int total_time_all_presenter = 0;
			String presenters = "";
			//*** List of all combinations with presenter > 3 *********************
			List<PresenterInfo> selected_presenter = new ArrayList<PresenterInfo>();
			//*** Calculate total time for all presenters *************************
			for (Map.Entry<String, PresenterInfo> entry : pi_map.entrySet())
			{
			    total_time_all_presenter +=  entry.getValue().getPresntationTime();
			}
			//*** If total time is greate than conference time begin scheduling ***
			if(total_time_all_presenter < conference_hours)
			{
				response.add("Not enough presenters");
			}
			else
			{
				//*** Get array list out of map ******************
				Set<String> all_presenter_set = pi_map.keySet();
				List<String> all_presenter_list = new ArrayList<String>();
				all_presenter_list.addAll(all_presenter_set);
				//*** Get list of all possible combinations ******
				List<List<String>> possCombi = possibleCombinations(all_presenter_list);
				for (List<String> currentComb : possCombi) 
				{
					//*** Iterate over combinations with size > number of slots ****
					if(currentComb.size()>=number_of_slots)
					{
						for(int i=0; i<currentComb.size(); i++)
						{
							PresenterInfo current_pi = pi_map.get(currentComb.get(i));
							hr_sum += current_pi.getPresntationTime();
							cost_sum += current_pi.getCostOfPresentation();
							presenters += current_pi.getPresenterName() + ", ";
						}
						//*** if sum of hours is equal to conference hour add the combination ***
						if(hr_sum == conference_hours)
						{
							selected_presenter.add(new PresenterInfo(presenters, hr_sum, cost_sum));
						}
						//*** reset the counter variables for reprocesing *******
						else
						{
							hr_sum = 0;
							cost_sum = 0;
							presenters = "";
						}
					}
				}
				//*** Calculate minimum cost for all finalized cominations **************
				int min_cost = 0;
				for(int i=0; i<selected_presenter.size(); i++)
				{
					int currentCost = selected_presenter.get(i).getCostOfPresentation();
					if(i == 0)
						min_cost = currentCost;
					else
					{
						if(min_cost > currentCost)
							min_cost = currentCost;
					}
				}
				//*** Put the final response in the array list *******
				for(int i=0; i<selected_presenter.size(); i++)
				{
					int currentCost = selected_presenter.get(i).getCostOfPresentation();
					if(currentCost == min_cost)
						response.add(selected_presenter.get(i).getPresenterName()+"$"+selected_presenter.get(i).getCostOfPresentation());
				}
			}
		}
		else
		{
			response.add("Not enough presenters");
		}
		
		return response;
	}
	//*** Possible combination code *************************************
	static public <A> List<List<A>> possibleCombinations(List<A> elements) 
	{
		List<List<A>> results = new ArrayList<List<A>>();
		results.add(new ArrayList<A>());
		for(A element : elements)
		{
			List<List<A>> tempResults = new ArrayList<List<A>>();
			for (List<A> prevResult : results) 
			{
				List<A> newResult = new ArrayList<A>(prevResult);
				newResult.add(element);
				tempResults.add(newResult);
			}
			results.addAll(tempResults);
		}
		return results;
	}
}
