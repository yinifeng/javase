package com.hobart.date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestLocalDateTime {
	
	
	//ZonedDate,ZonedTime,ZonedDateTime
	@Test
	public void Test8(){
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("America/Merida"));
		System.out.println(ldt);
		
		LocalDateTime ldt2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
		ZonedDateTime zd = ldt2.atZone(ZoneId.of("Asia/Shanghai"));//东八区
		System.out.println(zd);
	}
	
	@Test
	public void test7(){
		Set<String> set = ZoneId.getAvailableZoneIds();
		set.forEach(System.out::println);//输出所有时区
	}
	//DateTimeFormatter:格式化时间/日期
	@Test
	public void test6(){
		//ISO-88601格式
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
		LocalDateTime ldt=LocalDateTime.now();
		String strDate = ldt.format(dtf);
		System.out.println(strDate);
		
		System.out.println("-------------------------------------");
		//指定日期格式
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
		String strDate2 = dtf2.format(ldt);
		System.out.println(strDate2);
		//字符串转换时间
		LocalDateTime ldt3 = LocalDateTime.parse(strDate2, dtf2);
		System.out.println(ldt3);
	}
	
	//TemporalAdjuster：时间校正器
	@Test
	public void test5(){
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		//指定月中的10号
		LocalDateTime ldt2 = ldt.withDayOfMonth(10);
		System.out.println(ldt2);
		
		//下个星期天
		LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println(ldt3);
		
		//自定义：下个工作一
		LocalDateTime ldt5 = ldt.with((l)->{
			LocalDateTime ldt4=(LocalDateTime) l;
			DayOfWeek dow = ldt4.getDayOfWeek();
			if(dow.equals(DayOfWeek.MONDAY)){
				return ldt4.plusDays(7);//星期一+3天
			}else if(dow.equals(DayOfWeek.TUESDAY)){
				return ldt4.plusDays(6);
			}else if(dow.equals(DayOfWeek.WEDNESDAY)){
				return ldt4.plusDays(5);
			}else if(dow.equals(DayOfWeek.THURSDAY)){
				return ldt4.plusDays(4);
			}else if(dow.equals(DayOfWeek.FRIDAY)){
				return ldt4.plusDays(3);//星期五+3天
			}else if(dow.equals(DayOfWeek.SATURDAY)){
				return ldt4.plusDays(2);
			}else{
				return ldt4.plusDays(1);
			}
		});
		System.out.println(ldt5);
	}
	
	//计算2个日期之间的间隔
	@Test
	public void test4(){
		LocalDate ld1 = LocalDate.of(2016, 11, 11);
		LocalDate ld2 = LocalDate.now();
		
		Period period = Period.between(ld1, ld2);
		System.out.println(period);
		System.out.println(period.getYears());
		System.out.println(period.getMonths());
		System.out.println(period.getDays());

		System.out.println(period.get(ChronoUnit.MILLIS));
	}
	
	//两个时间的间隔
	@Test
	public void test3(){
		Instant ins1 = Instant.now();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		Instant ins2 = Instant.now();
		Duration duration = Duration.between(ins1, ins2);
		System.out.println(duration.toMillis());//两个时间之间的毫秒间隔
		
		System.out.println("----------------------------------");
		LocalTime lt1 = LocalTime.now();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		LocalTime lt2 = LocalTime.now();
		System.out.println(Duration.between(lt1, lt2).toMillis());
	}
	
	//Instant
	@Test
	public void test2(){
		Instant ins1 = Instant.now();
		System.out.println(ins1);
		
		//偏移时区
		OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
		System.out.println(odt);
		
		System.out.println(ins1.toEpochMilli());//时间戳
		
		//1970-01-01 00:00:00 +60毫秒
		Instant ins2 = Instant.ofEpochMilli(60);
		System.out.println(ins2);
	}
	
	//LocalDate LocalTime LocalDateTime
	//日期  时间 日期和时间  用法都类似
	@Test
	public void test1(){
		//获取当前系统时间
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		//指定时间
		LocalDateTime ldt1 = LocalDateTime.of(2017, 12, 23, 23, 26, 53);
		System.out.println(ldt1);
		
		//时间相加
		LocalDateTime ldt2 = ldt.plusYears(1);//当前年+1
		System.out.println(ldt2);
		
		//时间相减
		LocalDateTime ldt3 = ldt.minusMonths(3);
		System.out.println(ldt3);
		
		//分别获取年，月，日，时，分，秒
		System.out.println(ldt.getYear());
		System.out.println(ldt.getMonthValue());
		System.out.println(ldt.getDayOfMonth());
		System.out.println(ldt.getHour());
		System.out.println(ldt.getMinute());
		System.out.println(ldt.getSecond());
	}
}
