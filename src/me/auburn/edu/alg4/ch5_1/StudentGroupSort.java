package me.auburn.edu.alg4.ch5_1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;

class Student{
	String name;
	int groupNumber;
	Student(String name, int id){
		this.name = name; this.groupNumber = id;
	}
	public String toString(){
		return name + ": "+groupNumber;
	}
}
/**
 * 对学生按照组号排序
 */
public class StudentGroupSort {
	public ArrayList<Student> list; // 从文件读入学生数据
	public int groups;				// 组数
	public StudentGroupSort(In in){
		list = new ArrayList<>();
		this.groups = in.readInt();	// 读入组数
		in.readLine();				// 换行
		String line;				
		while((line = in.readLine())!=null){	// 初始化学生list
			String[] temp = line.split("\\s+");
			list.add(new Student(temp[0], Integer.parseInt(temp[1])));
		}
	}
	/**对学生列表进行排序，返回一个学生数组*/
	public Student[] sort(){
		Student[] result = new Student[list.size()];
		int[] count = new int[groups + 2];
		// 统计频率,该循环结束后，每个组号对应的学生数被计算出来[0, 0, 3, 5, 6, 6]
		for(int i = 0;i<list.size(); i++){
			int gId = list.get(i).groupNumber;	// 遍历学生表，得到学生组号
			count[gId + 1]++;		// 组号（组号+1）对应的学生数加一
		}
		// 将频率转换为索引,该循环把组号频率转换为索引值[0, 0, 3, 8, 14, 20]
		for(int i = 0; i< count.length-1; i++){
			count[i+1] += count[i];
		}
		// 把学生遍历一遍，按照索引依次放入结果数组
		for(int i = 0; i < list.size(); i++){
			result[count[list.get(i).groupNumber]++] = list.get(i);
		}
		return result;
	}
	public static void main(String[] args) {
		String dir = StudentGroupSort.class.getPackage().getName().replace(".", "/");
		String path = StudentGroupSort.class.getClassLoader().getResource(dir+"/students.txt").getPath();
		StudentGroupSort sgs = new StudentGroupSort(new In(new File(path)));
		Student[] sortResult = sgs.sort();
		System.out.println(Arrays.toString(sortResult));
	}
}
