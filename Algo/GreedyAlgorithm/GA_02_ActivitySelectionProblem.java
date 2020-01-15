import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/*
https://www.geeksforgeeks.org/activity-selection-problem-greedy-algo-1/

Problem: You are given n activities with their start and finish times. Select the maximum number of 
    activities that can be performed by a single person, assuming that a person can only work on a 
    single activity at a time.

Solution: The greedy choice is to always pick the next activity whose finish time is least among the 
    remaining activities and the start time is more than or equal to the finish time of previously 
    selected activity. We can sort the activities according to their finishing time so that we always 
    consider the next activity as minimum finishing time activity.
    1) Sort the activities according to their finishing time
    2) Select the first activity from the sorted array and print it.
    3) Do following for remaining activities in the sorted array.
        a) If the start time of this activity is greater than or equal to the finish time of previously 
        selected activity then select this activity and print it.
*/
class Activity {
    private String name;
    private int startTime;
    private int finishTime;

    Activity(String name, int startTime, int finishTime) {
        this.name = name;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    String getName() {
        return name;
    }

    int getStartTime() {
        return startTime;
    }

    int getFinishTime() {
        return finishTime;
    }
    @Override
    public String toString() {
        return "{name: " + name + ", startTime: " + startTime + ", finishTime: " + finishTime + "}";
    }
}

class GA_02_ActivitySelectionProblem {
    static List<Activity> activitySelection(List<Activity> activities) throws NoSuchElementException{

        List<Activity> selectedActivities = new ArrayList<>();
        if (activities == null || activities.size() == 0) {
            return selectedActivities;
        }

        // Sort the activities based on finishTime
        Collections.sort(activities, (x, y) -> x.getFinishTime() - y.getFinishTime());

        Iterator<Activity> activitiesIterator = activities.iterator();

        Activity currentActivity = activitiesIterator.next();
        int currentFinishTime = currentActivity.getFinishTime();
        selectedActivities.add(currentActivity);

        while(activitiesIterator.hasNext()) {
            currentActivity = activitiesIterator.next();
            if (currentActivity.getStartTime() > currentFinishTime) {
                selectedActivities.add(currentActivity);
                currentFinishTime = currentActivity.getFinishTime();
            }
        }

        return selectedActivities;
    }
    public static void main(String[] args) {
        List<Activity> activityList = new ArrayList<Activity>();
		
		//Insert activities in Arraylist
		activityList.add(new Activity("A1", 0, 6));
		activityList.add(new Activity("A2", 3, 4));
		activityList.add(new Activity("A3", 1, 2));
		activityList.add(new Activity("A4", 5, 8));
		activityList.add(new Activity("A5", 5, 7));
        activityList.add(new Activity("A6", 8, 9));
        
        System.out.println("User provided List:\n" + activityList);
		
		//Perform calculation on activities
        List<Activity> recommendedList = activitySelection(activityList);
        System.out.println("\nRecommended Schedule:\n" + recommendedList);
    }
}