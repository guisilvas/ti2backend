// Classe principal
package app;

import java.util.List;
import java.util.Scanner;

import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;

public class Aplicacao {
	
	// Função que mostra o menu
	public static void Menu() {
		System.out.println("\n\n\t\t_____| Back-end com PostgreSQL |_____");
    	System.out.println("\n\n\t Selecione uma das opções abaixo: \n\n");
    	System.out.println("\t1 - Listar\n");
    	System.out.println("\t2 - Inserir\n");
    	System.out.println("\t3 - Excluir\n");
    	System.out.println("\t4 - Atualizar\n");
    	System.out.println("\t5 - Sair\n\n");
    	System.out.println("Opção: ");
	}
    
    public static void main(String[] args) throws Exception {
    	Scanner sc = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();        
        int code, option = 0;
        String login, password;
        char gender;
		
    	while(option != 5) {
    		Menu();
    		option = sc.nextInt();
    		if(option == 1) {
    			System.out.println("\n\n---> Mostrar usuários <---");
    			List<Usuario> usuarios = usuarioDAO.getGenderMasculino();
    			for (Usuario u: usuarios) {
    				System.out.println(u.toString());
    			}
    		} else if(option == 2) {
    			// Inserção de usuário
    			Usuario usuario = new Usuario();
    	        System.out.println("\n\n---> Inserir usuário <---");
    	            
    	        System.out.println("Login: ");
    	        login = sc.nextLine();

    	        System.out.println("Password: ");
    	        password = sc.nextLine();

    	        System.out.println("Gender: ");
    	        gender = sc.next().charAt(0);
    	        
    	        usuario.setLogin(login);
    	        usuario.setPassword(password);
    	        usuario.setGender(gender);
    	        usuarioDAO.update(usuario);

    	        
    	        if (usuarioDAO.insert(usuario)) {
    	            System.out.println("Inserção com sucesso -> " + usuario.toString());
    	        }
    		} else if(option == 3) {
    			// Delete de usuário por code
    			Usuario usuario = new Usuario();
    	        System.out.println("\n\n---> Excluir usuário <---");
    	        System.out.println("Code: ");
    	        
    	        code = sc.nextInt();
    	        usuarioDAO.delete(code); 
    			
    		} else if(option == 4) {
    			// Atualização de dados
    			Usuario usuario = new Usuario();
    	        System.out.println("\n\n----> Atualizar dados <---");
    	        System.out.println("Code: ");
    	        
    	        System.out.println("Login: ");
    	        login = sc.nextLine();
    	        usuario.setLogin(login);

    	        System.out.println("Password: ");
    	        password = sc.nextLine();
    	        usuario.setPassword(password);

    	        System.out.println("Gender: ");
    	        gender = sc.next().charAt(0);
    	        usuario.setGender(gender);

    	        usuarioDAO.update(usuario);
    		} else {
    			System.out.println("Programa encerrado!");
    			option = 5;
    		}
    	}
        
        sc.close();
    }
}
