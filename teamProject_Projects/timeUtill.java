package teamProject_Projects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class timeUtill extends Thread{

	private int mYear, mMonth, mDay, mHour, mMinute;
	
	public timeUtill(int year, int month, int day, int hour, int minute){

		mYear = year;
		mMonth = month;
		mDay = day;
		mHour = hour;
		mMinute = minute;
	}

	@Override
	public void run() {
		try {
			System.out.println( "i'm work before");
			long l = timeUntil(mYear, mMonth, mDay, mHour, mMinute);
			sleep(timeUntil(mYear, mMonth, mDay, mHour, mMinute));
			// 작업을 수행한다.
			work();
		} catch ( InterruptedException e) {
			e.printStackTrace();
			System.out.println("error 발생");
		}
	}

	public void work(){
		System.out.println("시간됐으니 나와라 제발");
	}

	public long timeUntil( int year, int month, int day, int hour, int minute){
		long sec = 0;
		Calendar now = Calendar.getInstance();
		SimpleDateFormat f = 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
		try {
			String inDay = year+"-"+month+"-"+day+" "+hour+":"+minute+":00";
			Date d1 = f.parse(inDay);
			String nowDay = now.get(Calendar.YEAR)+"-"
					+(now.get(Calendar.MONTH)+1)+"-"
					+now.get(Calendar.DAY_OF_WEEK)+" "
					+now.get(Calendar.HOUR_OF_DAY)+":"
					+now.get(Calendar.MINUTE)+":00";
			Date d2 = f.parse(nowDay);
			long diff = d1.getTime() - d2.getTime();
			sec = diff / 1000;
			System.out.println(now);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sec;

	}
	
	public static void main(String[] args) {
		new timeUtill(2019,10,16,16,11).start();
		
	}
}


