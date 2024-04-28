package com.example.demo;

import com.example.tests.TestBeanShell;
import com.example.tests.TestPoi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.*"})
public class DemoApplication {

	public static void main(String[] args) {
		File currentDirectory = new File(".\\docs");
		String currentPath = currentDirectory.getAbsolutePath();
		System.out.println("当前目录：" + currentPath);
		// 1. java代码调用poi_tl
		TestPoi.main(null);
		// 2. BeanShell 脚本实现具体逻辑操作。
		TestBeanShell.main(null);
		SpringApplication.run(DemoApplication.class, args);
	}

}
