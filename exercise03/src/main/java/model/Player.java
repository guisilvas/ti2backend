package model;

import java.time.LocalDate;

public class Player {
	private int id;
	private String name;
	private LocalDate birthdate;
	private String federation;
	private int rating;
	private int titles;
	
	public Player() {
		id = -1;
		name = "";
		birthdate = LocalDate.now();
		federation = "";
		rating = 0;
		titles = 0;
	}

	public Player(int id, String name, LocalDate birthdate, String federation, int rating, int titles) {
		setId(id);
		setName(name);
		setBirthdate(birthdate);
		setFederation(federation);
		setRating(rating);
		setTitles(titles);
	}		
	
	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getFederation() {
		return federation;
	}
	
	public void setFederation(String federation) {
		this.federation = federation;
	}

	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public int getTitles() {
		return titles;
	}
	
	public void setTitles(int titles) {
		this.titles = titles;
	}

	@Override
	public String toString() {
		//LocalDate date = new LocalDate.now();
		return "Jogador: " + name + "   Idade: " + birthdate + "   Federação.: " + federation + "	Rating: " + rating + "   Títulos mundiais: "
				+ titles;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Player) obj).getID());
	}	
}