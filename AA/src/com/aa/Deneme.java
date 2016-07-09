package com.aa;

import java.lang.reflect.Field;import sun.misc.*;

public class Deneme {
	 public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		    Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
		    unsafeField.setAccessible(true);
		    Unsafe unsafe = (Unsafe) unsafeField.get(null);
		    System.out.println(unsafe.addressSize());
		  }
}
