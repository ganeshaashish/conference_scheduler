package utils;

import java.util.Comparator;

public class PresenterInfo  implements Comparator<PresenterInfo>
{
	private String presenterName;
	private int presntationTime;
	public String getPresenterName() {
		return presenterName;
	}
	public void setPresenterName(String presenterName) {
		this.presenterName = presenterName;
	}
	public int getPresntationTime() {
		return presntationTime;
	}
	public void setPresntationTime(int presntationTime) {
		this.presntationTime = presntationTime;
	}
	public int getCostOfPresentation() {
		return costOfPresentation;
	}
	public void setCostOfPresentation(int costOfPresentation) {
		this.costOfPresentation = costOfPresentation;
	}
	private int costOfPresentation;
	
	public PresenterInfo(){}
	public PresenterInfo(String presenterName, int hours, int cost)
	{
		this.presenterName = presenterName;
		this.costOfPresentation = cost;
		this.presntationTime = hours;
	}
	
	public int compare(PresenterInfo o1, PresenterInfo o2) 
	{
		return o1.getCostOfPresentation() - o2.getCostOfPresentation();
	}
	
}
