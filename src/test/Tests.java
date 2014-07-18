package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import service.ConferenceScheduler;
import utils.PresenterInfo;

public class Tests 
{
	@Test
	public void voidTestSchedule()
	{
		List<PresenterInfo> pi_list = new ArrayList<PresenterInfo>();
		pi_list.add(new PresenterInfo("P1", 1, 200));
		pi_list.add(new PresenterInfo("P2", 2, 120));
		pi_list.add(new PresenterInfo("P3", 1, 100));
		pi_list.add(new PresenterInfo("P4", 1, 200));
		pi_list.add(new PresenterInfo("P5", 2, 100));
		pi_list.add(new PresenterInfo("P6", 4, 100));
		
		int conference_hours = 8;
		int number_of_slots = 3;
		ArrayList<String> response = ConferenceScheduler.scheduler(conference_hours, number_of_slots, pi_list);
		assertEquals("P3, P5, P6, $300", response.get(0));
	}
	@Test
	public void voidTestNotEnoughPresenters()
	{
		List<PresenterInfo> pi_list = new ArrayList<PresenterInfo>();
		pi_list.add(new PresenterInfo("P1", 1, 200));
		pi_list.add(new PresenterInfo("P2", 2, 120));
		
		int conference_hours = 8;
		int number_of_slots = 3;
		ArrayList<String> response = ConferenceScheduler.scheduler(conference_hours, number_of_slots, pi_list);
		assertEquals("Not enough presenters", response.get(0));
	}
}
