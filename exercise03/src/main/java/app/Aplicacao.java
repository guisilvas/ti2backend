package app;

import static spark.Spark.*;
import service.PlayerService;


public class Aplicacao {
	
	private static PlayerService playerService = new PlayerService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/player/insert", (request, response) -> playerService.insert(request, response));

        get("/player/:id", (request, response) -> playerService.get(request, response));
        
        get("/player/list/:orderby", (request, response) -> playerService.getAll(request, response));

        get("/player/update/:id", (request, response) -> playerService.getToUpdate(request, response));
        
        post("/player/update/:id", (request, response) -> playerService.update(request, response));
           
        get("/player/delete/:id", (request, response) -> playerService.delete(request, response));

             
    }
}