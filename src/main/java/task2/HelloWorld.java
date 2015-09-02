package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService
public class HelloWorld {
	List<Student> students = new ArrayList<>();
	
	
	public HelloWorld() {
		students.add(new Student("Jano",24));
		students.add(new Student("Jozko",22));
		students.add(new Student("Matko",20));
		students.add(new Student("Brano",28));
	}
	public List<Student> getAllStudents(){

		return students;
	}
	
	public List<Student> getStudentsStartingWith(String prefix){
		return students.stream().filter(s ->s.getName().startsWith(prefix)).collect(Collectors.toList());
	}
	public String sayHello(){
		return "Hello World";
	}
}
