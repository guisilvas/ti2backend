package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.util.List;
import dao.PlayerDAO;
import model.Player;
import spark.Request;
import spark.Response;

public class PlayerService {

	private PlayerDAO playerDAO = new PlayerDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NAME = 2;
	private final int FORM_ORDERBY_RATING = 3;
	private final int FORM_ORDERBY_FEDERATION = 4;
	
	public PlayerService() {
		makeForm();
	}
	
	public void makeForm() {
		makeForm(FORM_INSERT, new Player(), FORM_ORDERBY_NAME);
	}
	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Player(), orderBy);
	}
	
	public void makeForm(int tipo, Player player, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umPlayer = "";
		if(tipo != FORM_INSERT) {
			umPlayer += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPlayer += "\t\t<tr>";
			umPlayer += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/player/list/1\">Novo Player</a></b></font></td>";
			umPlayer += "\t\t</tr>";
			umPlayer += "\t</table>";
			umPlayer += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/player/";
			String name, buttonLabel;
			int rating;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Name";
				rating = 0;
				buttonLabel = "Inserir";
			} else {
				action += "update/" + player.getID();
				name = player.getName();
				rating = player.getRating();
				buttonLabel = "Atualizar";
			}
			umPlayer += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umPlayer += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPlayer += "\t\t<tr>";
			umPlayer += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umPlayer += "\t\t</tr>";
			umPlayer += "\t\t<tr>";
			umPlayer += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umPlayer += "\t\t</tr>";
			umPlayer += "\t\t<tr>";
			umPlayer += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"name\" value=\""+ name +"\"></td>";
			umPlayer += "\t\t\t<td>Data de nascimento: <input class=\"input--register\" type=\"text\" name=\"birthdate\" value=\""+ player.getBirthdate() +"\"></td>";
			umPlayer += "\t\t\t<td>Rating: <input class=\"input--register\" type=\"text\" name=\"rating\" value=\""+ player.getRating() +"\"></td>";
			umPlayer += "\t\t</tr>";
			umPlayer += "\t\t<tr>";
			umPlayer += "\t\t\t<td>&nbsp;Federação: <input class=\"input--register\" type=\"text\" name=\"federation\" value=\""+ player.getFederation() + "\"></td>";
			umPlayer += "\t\t\t<td>Títulos mundiais: <input class=\"input--register\" type=\"text\" name=\"titles\" value=\""+ player.getTitles() + "\"></td>";
			umPlayer += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umPlayer += "\t\t</tr>";
			umPlayer += "\t</table>";
			umPlayer += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umPlayer += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPlayer += "\t\t<tr>";
			umPlayer += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Exibir Jogador (ID " + player.getID() + ")</b></font></td>";
			umPlayer += "\t\t</tr>";
			umPlayer += "\t\t<tr>";
			umPlayer += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umPlayer += "\t\t</tr>";
			umPlayer += "\t\t<tr>";
			umPlayer += "\t\t\t<td>&nbsp;Nome: "+ player.getName() +"</td>";
			umPlayer += "\t\t\t<td>Data de nascimento: "+ player.getBirthdate().toString() +"</td>";
			umPlayer += "\t\t\t<td>Rating: "+ player.getRating() +"</td>";
			umPlayer += "\t\t</tr>";
			umPlayer += "\t\t<tr>";
			umPlayer += "\t\t\t<td>&nbsp;Títulos mundiais: "+ player.getTitles() + "</td>";
			umPlayer += "\t\t\t<td>Federação: "+ player.getFederation() + "</td>";
			umPlayer += "\t\t\t<td>&nbsp;</td>";
			umPlayer += "\t\t</tr>";
			umPlayer += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-PLAYER>", umPlayer);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Jogadores</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/player/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/player/list/" + FORM_ORDERBY_NAME + "\"><b>Nome</b></a></td>\n" +
        		//"\t<td><a href=\"/player/list/" + FORM_ORDERBY_BIRTHDATE + "\"><b>Data de nascimento</b></a></td>\n" +
        		"\t<td><a href=\"/player/list/" + FORM_ORDERBY_RATING + "\"><b>Rating</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Exibir</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Player> players;
		if (orderBy == FORM_ORDERBY_ID) {                 	players = playerDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_NAME) {		players = playerDAO.getOrderByName();
		} else if (orderBy == FORM_ORDERBY_RATING) {			players = playerDAO.getOrderByRating();
		} else {											players = playerDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Player p : players) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getID() + "</td>\n" +
            		  "\t<td>" + p.getName() + "</td>\n" +
            		  "\t<td>" + p.getRating() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/player/" + p.getID() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/player/update/" + p.getID() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeletePlayer('" + p.getID() + "', '" + p.getName() + "', '" + p.getRating() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-PLAYER>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String name = request.queryParams("name");
		
		LocalDate birthdate = LocalDate.parse(request.queryParams("birthdate"));
		String federation = request.queryParams("federation");
		int rating = Integer.parseInt(request.queryParams("rating"));
		int titles = Integer.parseInt(request.queryParams("titles"));
		
		String resp = "";
		
		Player player = new Player(-1, name, birthdate, federation, rating, titles);
		
		if(playerDAO.insert(player) == true) {
            resp = "Player (" + name + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Player (" + name + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Player player = (Player) playerDAO.get(id);
		
		if (player != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, player, FORM_ORDERBY_NAME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Player " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Player player = (Player) playerDAO.get(id);
		
		if (player != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, player, FORM_ORDERBY_RATING);
        } else {
            response.status(404); // 404 Not found
            String resp = "Player " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Player player = playerDAO.get(id);
        String resp = "";       

        if (player != null) {
        	player.setName(request.queryParams("name"));
        	player.setBirthdate(LocalDate.parse(request.queryParams("birthdate")));
        	player.setFederation(request.queryParams("federation"));
        	player.setRating(Integer.parseInt(request.queryParams("rating")));
        	player.setTitles(Integer.parseInt(request.queryParams("titles")));
        	playerDAO.update(player);
        	response.status(200); // success
            resp = "Jogador (ID " + player.getID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Jogador (ID \" + player.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Player player = playerDAO.get(id);
        String resp = "";       

        if (player != null) {
            playerDAO.delete(id);
            response.status(200); // success
            resp = "Jogador (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Jogador (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}