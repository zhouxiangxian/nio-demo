package cn.mldn.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class TestNumber {
	public static void main(String[] args) throws UnsupportedEncodingException {
		// createFileNumber();
		//testDecodeAndEncode();
		Map<String,Object> map=new HashMap<String,Object>();
		System.out.println("Integer:"+map.get("delCount"));

	}

	private static void testDecodeAndEncode() throws UnsupportedEncodingException {
		System.out.println("采用UTF-8字符集进行解码:");
        String keyWord = URLDecoder.decode("%E5%A4%A9%E6%B4%A5%E5%A4%A7%E5%AD%A6+Rico", "UTF-8");
        System.out.println(keyWord);
        System.out.println("\n 采用GBK字符集进行解码:");
        System.out.println(URLDecoder.decode("%E5%A4%A9%E6%B4%A5%E5%A4%A7%E5%AD%A6+Rico", "GBK"));

        // 将普通字符串转换成application/x-www-form-urlencoded字符串
        System.out.println("\n 采用utf-8字符集:");
        String urlStr = URLEncoder.encode("天津大学", "utf-8");
        System.out.println(urlStr);
        System.out.println("\n 采用GBK字符集:");
        String urlStr2 = URLEncoder.encode("天津大学", "GBK");
        System.out.println(urlStr2);
	}

	private static void testReplace() {
		String temp = "dd'''dddddd'''''dd";
		String replaceAll = temp.replaceAll("\'", "\\\\'");
		System.out.println(replaceAll);
		System.out.println("#######");
		String temp2 = "xiao'xian";
		String replaceAll2 = temp2 == null ? "空的" : temp2.replaceAll("'", "''");
		System.out.println(replaceAll2);
	}

	private static void testSet() {
		Set<Integer> staffsSet = new HashSet<>();
		staffsSet.add(3);
		staffsSet.add(4);
		staffsSet.add(5);
		staffsSet.add(1);
		staffsSet.add(2);
		staffsSet.add(11);
		staffsSet.add(10);
		staffsSet.add(9);
		staffsSet.add(7);
		staffsSet.add(6);
		staffsSet.add(8);

		List<Integer> result = new ArrayList<>(staffsSet);

		for (Integer temp : result) {
			System.out.println(temp);
		}
	}

	private static void testSplit() {
		String result = "upload=1&fail=7&filePath=/D:/javatools/tomcat/apache-tomcat-6.0.35/webapps/emp_std/xtgl/statecode/file/\\download\\errormessage\\StateCode_2018121217241755_1001.zip";
		String[] split = result.split("&");
		for (String temp : split) {
			String[] split2 = temp.split("=");
			System.out.println(split2[0]);
			System.out.println(split2[1]);
		}
		System.out.println("======================");
		for (int x = 0; x < split.length; x++) {
			System.out.println(split[x]);
		}
	}

	private static void test1() {
		String taskId = UUID.randomUUID().toString();
		System.out.println(taskId.length());
	}

	private static void validate() {
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{0,20}$";

		String value = "aaa"; // 长度不够
		System.out.println(value + "：" + value.matches(regex));

		value = "1111aaaa1111aaaaa"; // 太长
		System.out.println(value + "：" + value.matches(regex));

		value = "111111111"; // 纯数字
		System.out.println(value + "：" + value.matches(regex));

		value = "aaaaaaaaa"; // 纯字母
		System.out.println(value + "：" + value.matches(regex));

		value = "####@@@@#"; // 特殊字符
		System.out.println(value + "：" + value.matches(regex));

		value = "1111aaaa"; // 数字字母组合
		System.out.println(value + "：" + value.matches(regex));

		value = "aaaa1111"; // 数字字母组合
		System.out.println(value + "：" + value.matches(regex));

		value = "aa1111aa"; // 数字字母组合
		System.out.println(value + "：" + value.matches(regex));

		value = "11aaaa11"; // 数字字母组合
		System.out.println(value + "：" + value.matches(regex));

		value = "aa11aa11"; // 数字字母组合
		System.out.println(value + "：" + value.matches(regex));
	}

	private static void getFileType() {
		String fileCurName = "asdfasdf.jqweqweqwe.xlsx";
		String fileType = fileCurName.substring(fileCurName.lastIndexOf(".") + 1);
		if ("xlsx".equals(fileType)) {// xlsx
			System.out.println(fileType);
		}
	}

	private static void createFileNumber() {
		File file = new File("C:\\Users\\dell\\Desktop\\c.txt");// 创建文件目录对象
		try {
			FileWriter fw = new FileWriter(file);// 创建字符输出流类对象
			BufferedWriter bw = new BufferedWriter(fw);// 创建上一层输出流对象
			for (int x = 0; x < 100; x++) {
				bw.write(String.valueOf(15570895845l + x));
				bw.newLine();
			}
			bw.close();// 关闭上一层输出流
			fw.close();// 关闭输出流
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
