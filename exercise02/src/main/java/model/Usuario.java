package model;

//Classe que manipula dados a serem utilizados no banco de dados
// As instâncias de Usuario serão representações de registros no db
public class Usuario {
    private int code;
    private String login;
    private String password;
    private char gender;
    
    public Usuario() {
        this.code = -1;
        this.login = "";
        this.password = "";
        this.gender = '*';
    }
    
    public Usuario(int code, String login, String password, char gender) {
        this.code = code;
        this.login = login;
        this.password = password;
        this.gender = gender;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Usuario [code=" + code + ", login=" + login + ", password=" + password + ", gender=" + gender + "]";
    }    
}
