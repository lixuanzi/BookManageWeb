package com.ixuanzi;

import com.ixuanzi.entity.Book;
import com.ixuanzi.entity.Borrow;
import com.ixuanzi.entity.Student;
import com.ixuanzi.util.SqlUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.LogManager;

/**
 * Hello world!
 */
@Log
public class Application {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LogManager logManager = LogManager.getLogManager();
            logManager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
            while (true) {
                System.out.println("==========================================");
                System.out.println("1.录入学生信息");
                System.out.println("2.删除学生信息");
                System.out.println("3.修改学生信息");
                System.out.println("4.遍历学生信息");
                System.out.println("5.录入图书信息");
                System.out.println("6.删除图书信息");
                System.out.println("7.修改图书信息");
                System.out.println("8.遍历图书信息");
                System.out.println("9.录入借阅信息");
                System.out.println("10.遍历借阅信息");
                System.out.print("输入需要执行的操作（输入其他数字退出）:");

                int input;
                try {
                    input = scanner.nextInt();
                } catch (Exception e) {
                    return;
                }
                scanner.nextLine();
                switch (input) {
                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        deleteStudent(scanner);
                        break;
                    case 3:
                        addBorrow(scanner);
                        break;
                    case 4:
                        deleteStudent(scanner);
                        break;
                    case 5:
                        addBorrow(scanner);
                        break;
                    case 6:
                        deleteStudent(scanner);
                        break;
                    case 7:
                        addBorrow(scanner);
                        break;
                    case 8:
                        deleteStudent(scanner);
                        break;
                    case 9:
                        addBorrow(scanner);
                        break;
                    case 10:
                        deleteStudent(scanner);
                        break;
                    default:
                        return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(Scanner scanner) {
        System.out.print("请输入学生id：");
        int sid = scanner.nextInt();
        SqlUtil.doSqlWork(bookMapper -> {
            bookMapper.deleteStudentBySid(sid);
        });
    }

    public static void addBorrow(Scanner scanner) {
        System.out.print("请输入学生id：");
        int sid = scanner.nextInt();
        System.out.print("请输入图书id：");
        int bid = scanner.nextInt();

        SqlUtil.doSqlWork(bookMapper -> {
            bookMapper.addBorrow(sid, bid);
        });
    }

    public static void addStudent(Scanner scanner) {
        System.out.print("请输入学生姓名：");
        String name = scanner.nextLine();
        System.out.print("请输入学生性别(男/女)：");
        String sex = scanner.nextLine();
        System.out.print("请输入学生年级：");
        int grade = scanner.nextInt();
        Student student = new Student(name, sex, grade);

        SqlUtil.doSqlWork(bookMapper -> {
            int re = bookMapper.addStudent(student);
            if (re > 0) {
                System.out.println("插入成功！");
                log.info("新增学生信息" + student);
            } else {
                System.out.println("失败！");
            }
        });
    }

    public static void addBook(Scanner scanner) {
        System.out.print("请输入书籍名称：");
        String title = scanner.nextLine();
        System.out.print("请输入书籍简介：");
        String desc = scanner.nextLine();
        System.out.print("请输入书籍价格：");
        Double price = scanner.nextDouble();
        Book book = new Book(title, desc, price);

        SqlUtil.doSqlWork(bookMapper -> {
            int re = bookMapper.addBook(book);
            if (re > 0) {
                System.out.println("插入成功！");
                log.info("新增书籍信息" + book);
            } else {
                System.out.println("失败！");
            }
        });
    }
}
