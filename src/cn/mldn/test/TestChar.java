package cn.mldn.test;

import org.apache.commons.lang.StringEscapeUtils;

public class TestChar {
	public static void main(String[] args) {
		System.out.println("result:"+StringEscapeUtils.unescapeHtml("!@#$%^&amp;"));
		System.out.println("result:"+StringEscapeUtils.unescapeHtml(null));
	}

}
