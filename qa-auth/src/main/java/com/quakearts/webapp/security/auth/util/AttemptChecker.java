package com.quakearts.webapp.security.auth.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class AttemptChecker {
	private static final Map<String,AttemptChecker> checkers = new ConcurrentHashMap<String,AttemptChecker>();
	private Map<String, CacheRecord> cache = new ConcurrentHashMap<String, CacheRecord>();
	private int maxAttempts = 4;
	private int lockoutTime = 3600000;
	
	private class CacheRecord{
		int attempts = 1;
		long createtime = new Date().getTime();
	}

	private class CleanerDeamon extends TimerTask{
		@Override
		public void run() {
			cache.clear();
		}
	}
	
	public static AttemptChecker getChecker(String authgrp){
		if(!checkers.containsKey(authgrp)){
			AttemptChecker checker = new AttemptChecker();
			checkers.put(authgrp, checker);
			return checker;
		}else
			return checkers.get(authgrp);
	}
	
	public static void createChecker(String authgrp,int maxAttempts,int lockoutTime){
		AttemptChecker checker;
		if(!checkers.containsKey(authgrp)){
			checker = new AttemptChecker();
			checker.lockoutTime = lockoutTime;
			checker.maxAttempts = maxAttempts;
			checkers.put(authgrp, checker);
		}
	}
	
	private AttemptChecker() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
			
		new Timer().schedule(new CleanerDeamon(), cal.getTime(), 86400000);
	}
		
	public int getMaxAttempts() {
		return maxAttempts;
	}

	public int getLockoutTime() {
		return lockoutTime;
	}
	
	public boolean isLocked(String username){
		CacheRecord rec = cache.get(username);
		if(rec == null){
			rec = new CacheRecord();
			cache.put(username, rec);
			return false;
		}else{
			if(rec.attempts > maxAttempts){
				long now = new Date().getTime();
				if(now - rec.createtime > lockoutTime){
					rec.attempts=1;
					rec.createtime=now;
					return false;
				}else
					return true;
			}else{
				return false;
			}
		}
	}

	public void incrementAttempts(String username){
		CacheRecord rec = cache.get(username);
		if(rec == null){
			rec = new CacheRecord();
			cache.put(username, rec);
		}else{
			if(rec.attempts < Integer.MAX_VALUE)
				rec.attempts += 1;
		}
	}
	
	public void reset(String username){
		CacheRecord rec = cache.get(username);
		if(rec != null){
			rec.attempts = 0;
			rec.createtime = new Date().getTime();;
		}		
	}
	
}
