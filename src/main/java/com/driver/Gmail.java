package com.driver;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

  Deque<Mail> ibx = new ArrayDeque<>();

  Deque<Mail> trash = new ArrayDeque<>();

    public Gmail(String emailId, int inboxCapacity) {
        this.inboxCapacity = inboxCapacity;
    }

    public Gmail(String emailId) {
        super(emailId);
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(ibx.size() == inboxCapacity)
        {
            Mail m = ibx.pop();
            trash.add(m);
        }
        Mail mail = new Mail(date,sender,message);
        ibx.add(mail);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Mail m : ibx)
        {
            if(m.getMessage().equals(message))
            {
                ibx.pop();
                trash.add(m);
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(ibx.size() == 0) return null;

            Mail m = ibx.peek();
            return m.getMessage();

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(ibx.size() == 0)
            return null;
        Mail m = ibx.getLast();
        return m.getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for(Mail m : ibx)
        {
            if(m.getDate().compareTo(start) >= 0 && m.getDate().compareTo(end) < 0) count++;
        }
         return count;

    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return ibx.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity - ibx.size();
    }
}
