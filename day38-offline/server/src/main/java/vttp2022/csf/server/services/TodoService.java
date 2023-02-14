package vttp2022.csf.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.csf.server.models.Task;
import vttp2022.csf.server.repositories.TodoRepository;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepo;

    public void addTask(List<Task> tasks){
        todoRepo.add(tasks);
    }
    
}
