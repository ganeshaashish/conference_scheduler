package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import service.ConferenceScheduler;
import utils.PresenterInfo;

public class Tests 
{
	@Test
	public void voidTestMaximizeThePresenters()
	{
		Map<String, PresenterInfo> pi_map = new HashMap<String, PresenterInfo>();
		pi_map.put("P1", new PresenterInfo("P1", 1, 200));
		pi_map.put("P2", new PresenterInfo("P2", 1, 120));
		pi_map.put("P3", new PresenterInfo("P3", 1, 100));
		pi_map.put("P4", new PresenterInfo("P4", 1, 200));
		pi_map.put("P5", new PresenterInfo("P5", 2, 100));
		pi_map.put("P6", new PresenterInfo("P6", 4, 100));
		
		int conference_hours = 8;
		int number_of_slots = 3;
		ArrayList<String> response = ConferenceScheduler.maximize_presenter_scheduler(conference_hours, number_of_slots, pi_map);
		assertEquals(true, response.contains("P4, P6, P1, P3, P2, $720"));
	}
	@Test
	public void voidTestNotEnoughPresenters1()
	{
		Map<String, PresenterInfo> pi_map = new HashMap<String, PresenterInfo>();
		pi_map.put("P1", new PresenterInfo("P1", 1, 200));
		pi_map.put("P2", new PresenterInfo("P2", 2, 120));
		
		int conference_hours = 8;
		int number_of_slots = 3;
		ArrayList<String> response = ConferenceScheduler.maximize_presenter_scheduler(conference_hours, number_of_slots, pi_map);
		assertEquals("Not enough presenters", response.get(0));
	}
	@Test
	public void voidTestNotEnoughPresenters2()
	{
		//*** Hours < conference_hours case *************************************
		Map<String, PresenterInfo> pi_map = new HashMap<String, PresenterInfo>();
		pi_map.put("P1", new PresenterInfo("P1", 1, 200));
		pi_map.put("P2", new PresenterInfo("P2", 2, 120));
		pi_map.put("P2", new PresenterInfo("P2", 2, 120));
		
		int conference_hours = 8;
		int number_of_slots = 3;
		ArrayList<String> response = ConferenceScheduler.maximize_presenter_scheduler(conference_hours, number_of_slots, pi_map);
		assertEquals("Not enough presenters", response.get(0));
	}
	@Test
	public void voidTestMinimizeTheCost()
	{
		Map<String, PresenterInfo> pi_map = new HashMap<String, PresenterInfo>();
		pi_map.put("P1", new PresenterInfo("P1", 1, 200));
		pi_map.put("P2", new PresenterInfo("P2", 1, 120));
		pi_map.put("P3", new PresenterInfo("P3", 1, 100));
		pi_map.put("P4", new PresenterInfo("P4", 1, 200));
		pi_map.put("P5", new PresenterInfo("P5", 2, 100));
		pi_map.put("P6", new PresenterInfo("P6", 4, 100));
		
		int conference_hours = 8;
		int number_of_slots = 3;
		ArrayList<String> response = ConferenceScheduler.minimize_the_cost(conference_hours, number_of_slots, pi_map);
		assertEquals(true, response.contains("P5, P6, P3, P2, $420"));
	}
	@Test
	public void voidTestMinimizeTheCost_NotEnoughPresenter()
	{
		Map<String, PresenterInfo> pi_map = new HashMap<String, PresenterInfo>();
		pi_map.put("P1", new PresenterInfo("P1", 1, 200));
		pi_map.put("P2", new PresenterInfo("P2", 1, 120));
		pi_map.put("P3", new PresenterInfo("P3", 1, 100));
		pi_map.put("P4", new PresenterInfo("P4", 1, 200));
		pi_map.put("P5", new PresenterInfo("P5", 2, 100));
		
		int conference_hours = 8;
		int number_of_slots = 3;
		ArrayList<String> response = ConferenceScheduler.minimize_the_cost(conference_hours, number_of_slots, pi_map);
		assertEquals(true, response.contains("Not enough presenters"));
	}
	@Test
	public void voidTestMinimizeTheCost_NotEnoughPresenter2()
	{
		Map<String, PresenterInfo> pi_map = new HashMap<String, PresenterInfo>();
		pi_map.put("P1", new PresenterInfo("P1", 4, 200));
		pi_map.put("P2", new PresenterInfo("P2", 4, 120));
		
		int conference_hours = 8;
		int number_of_slots = 3;
		ArrayList<String> response = ConferenceScheduler.minimize_the_cost(conference_hours, number_of_slots, pi_map);
		assertEquals(true, response.contains("Not enough presenters"));
	}
}
