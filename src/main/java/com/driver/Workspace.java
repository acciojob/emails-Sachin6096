package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Comparable.*;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar = new ArrayList<>(); // Stores all the meetings

    public Workspace(String emailId) {

        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId);
        this.inboxCapacity = Integer.MAX_VALUE;
    }

//    public Workspace(String emailId, int inboxCapacity, ArrayList<Meeting> calendar) {
//        super(emailId, inboxCapacity);
//        this.calendar = calendar;
//    }



    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
            if (calendar == null || calendar.isEmpty()) {
                return 0; // No meetings to attend
            }

            // Sort meetings by end times in ascending order
            Collections.sort(calendar, Comparator.comparing(Meeting::getEndTime));

            int maxMeetings = 1; // Initialize with the first meeting
            Meeting previousMeeting = calendar.get(0);

            for (int i = 1; i < calendar.size(); i++) {
                Meeting currentMeeting = calendar.get(i);
                if (currentMeeting.getStartTime().compareTo(previousMeeting.getEndTime()) >= 0) {
                    // The current meeting doesn't conflict with the previous one, so attend it
                    maxMeetings++;
                    previousMeeting = currentMeeting;
                }
            }

            return maxMeetings;
        }

    }

