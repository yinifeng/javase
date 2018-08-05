package com.hobart.lambda;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Consumer< T>接口接受一个T类型参数，没有返回值。
 * @author hobart
 *
 */
public class TestLamda001 {
	public static void main(String[] args) {
		Arrays.asList("a", "b", "c").forEach(e -> System.out.println(e));
		
		System.out.println("-------------------------");
		User user=new User("tom", 12);
		Consumer<User> userConsumer= u -> u.setName("jack");
		userConsumer.accept(user);
		System.out.println(user);
	}
}
