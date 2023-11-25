package main;


import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {
    @Autowired
    ToDoRepository toDoRepository;

    @GetMapping("/todos/")
    public List<ToDo> list() {
        Iterable<ToDo> iterableToDo = toDoRepository.findAll();
        ArrayList<ToDo> toDos = new ArrayList<>();
        for (ToDo toDo : iterableToDo) {
            toDos.add(toDo);
        }
        return toDos;
    }

    @PostMapping("/todos/")
    public int addToDo(ToDo toDo) {
        ToDo newtoDo = toDoRepository.save(toDo);

        return newtoDo.getId();
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity get(@PathVariable int id) {
//        ToDo toDo = ToDoList.getTodo(id);
        Optional<ToDo> getToDo = toDoRepository.findById(id);
        if (!getToDo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(getToDo.get(), HttpStatus.OK);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity delete(@PathVariable int id) {
/*        String toDo = ToDoList.deleteTodo(id);*/
        Optional<ToDo> getToDo = toDoRepository.findById(id);
        if (!getToDo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        toDoRepository.deleteById(id);
        Iterable<ToDo> iterableToDo = toDoRepository.findAll();
        ArrayList<ToDo> toDos = new ArrayList<>();
        for (ToDo toDo : iterableToDo) {
            toDos.add(toDo);
        }
//        return toDos;
        return new ResponseEntity(toDos, HttpStatus.OK);
    }

    @DeleteMapping("/todos/")
    public ResponseEntity deleteAll() {
        String toDo = ToDoList.deleteAllTodo();
        toDoRepository.deleteAll();
//        toDoRepository.findAll().
//        if (toDo == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
        return new ResponseEntity(toDo, HttpStatus.OK);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity put(@PathVariable int id, String name, String toDoList) {
        ToDo putToDo = toDoRepository.findById(id).get();
        putToDo.setName(name);
        putToDo.setToDoList(toDoList);
////        return ToDoList.changeToDo(id, newId, toDo);
//        return "successful" + toDoRepository.findById(id).get().getName() + "\n" + toDoRepository.findById(id).get().getToDoList();

        toDoRepository.save(putToDo);
        ToDo toDo = toDoRepository.findById(id).get();
        return new ResponseEntity("successful\n" + toDo, HttpStatus.OK);
    }

    @PutMapping("/todos/")
    public String putAll() {
        return ToDoList.changeAllToDo();
    }
}
