/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;

/**
 *
 * @author plata
 */
public class Person {
    String username;
    String firstName;
    String lastName;
    String sport;
    String team;
    String country;
    String message;
    String favouritePlayer;

    public String getSport() {
        return sport;
    }

    public void setValues() {
        if ("tennis".equals(sport)) {
            favouritePlayer = "Tsitsipas";
        } else if ("dallas".equals(team)) {
            favouritePlayer = "Nowitzki";
        } else if ("bucks".equals(team)) {
            favouritePlayer = "Antetokounmpo";
        }
        if ("Greece".equals(country)) {
            message = "Geia sou " + lastName;
        } else {
            message = "Hi " + firstName;
        }
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFavouritePlayer() {
        return favouritePlayer;
    }

    public void setFavouritePlayer(String favouritePlayer) {
        this.favouritePlayer = favouritePlayer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


}
