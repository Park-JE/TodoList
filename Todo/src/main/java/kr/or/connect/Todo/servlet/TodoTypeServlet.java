package kr.or.connect.Todo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.Todo.dao.TodoDao;
import kr.or.connect.Todo.dto.TodoDto;

@WebServlet("/TodoTypeServlet")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoTypeServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TodoDao todoDao = new TodoDao();
		TodoDto todo = new TodoDto();
		Long id = Long.parseLong(request.getParameter("id"));
		String type = request.getParameter("type");
		
		if (type.contentEquals("TODO"))
			type = "DOING";
		else if (type.contentEquals("DOING"))
			type = "DONE";
		
		todo.setType(type);
		todo.setId(id);
		
		todoDao.updateTodo(todo);
		}
}
