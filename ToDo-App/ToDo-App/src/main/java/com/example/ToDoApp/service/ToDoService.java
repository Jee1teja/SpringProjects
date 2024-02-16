package com.example.ToDoApp.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.example.ToDoApp.model.ToDo;
import com.example.ToDoApp.repo.IToDoRepo;

@Service
public class ToDoService {
	@Autowired
	IToDoRepo repo;
public List<ToDo> getAllToDoItems(){
	ArrayList<ToDo> toDoList=new ArrayList();
	repo.findAll().forEach(todo -> toDoList.add(todo));
	return toDoList;
}
public ToDo getToDoItemsById(Long id) {
	return repo.findById(id).get();
}
public boolean updateStatus(Long id) {
	ToDo todo=getToDoItemsById(id);
	todo.setStatus("Completed");
	return saveOrupdateToDoItem(todo);
	
}
public boolean saveOrupdateToDoItem(ToDo todo) {
	ToDo updatedObj=repo.save(todo);
	if(getToDoItemsById(updatedObj.getId())!=null) {
		return true;
	}
	return false;
}
public boolean deleteToDoItem(Long id) {
	repo.deleteById(id);
	if(repo.findById(id).isEmpty()) {
		return true;
	}
	return false;
	
}
}
