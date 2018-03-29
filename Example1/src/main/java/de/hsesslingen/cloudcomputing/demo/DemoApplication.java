package de.hsesslingen.cloudcomputing.demo;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;

@SpringBootApplication
@RestController
public class DemoApplication {

	private ArrayList<String> list = new ArrayList<>();
	
	@GetMapping("/")
	public String root() {
		return "<h1>Welcome on CloudComputing Demo-Page</h1><a href='index.html'>Go to Homepage</a>";
	}
	
	@GetMapping("/todos")
	public synchronized String todos() {
		JsonArray arr = new JsonArray();
		for(String todo : list) {
			arr.add(todo);
		}
		return arr.toString();
	}
	
	@PutMapping("/todos/{todo}")
	public String addTodo(@PathVariable String todo) {
		synchronized (list) {
			list.add(todo);
		}
		return todos();
	}
	
	@DeleteMapping("/todos/{todo}")
	public String removeTodo(@PathVariable String todo) {
		synchronized (list) {
			list.remove(todo);
		}
		return todos();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
