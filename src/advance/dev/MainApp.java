package advance.dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainApp {
	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(": ");
				String name = parts[0];
				int age = Integer.parseInt(parts[1]);
				String city = parts[2];
				double salary = Double.parseDouble(parts[3].replace(".", ""));
				employees.add(new Employee(name, age, city, salary));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Danh sách nhân viên:");
		for (Employee e : employees) {
			System.out.println(e);
		}

		Collections.sort(employees);

		System.out.println("\nDanh sách nhân viên theo lương tăng dần:");
		for (Employee e : employees) {
			System.out.println(e);
		}

		Employee highestPaid = employees.get(0);
		for (Employee e : employees) {
			if (e.getSalary() > highestPaid.getSalary()) {
				highestPaid = e;
			}
		}

		System.out.println("\nNhân viên có lương cao nhất: " + highestPaid);
	}
}

class Person {
	private String name;
	private int age;
	private String city;

	public Person(String name, int age, String city) {
		this.name = name;
		this.age = age;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return name + " (" + age + ") - " + city;
	}
}

class Employee extends Person implements Comparable<Employee> {
	private double salary;

	public Employee(String name, int age, String city, double salary) {
		super(name, age, city);
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return super.toString() + " - " + salary;
	}

	@Override
	public int compareTo(Employee other) {
		return Double.compare(this.salary, other.salary);
	}
}