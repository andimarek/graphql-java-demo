package com.example;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterRepository {


    public static class Character {

        public Character(String id, String firstName, String lastName, boolean family) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.family = family;
        }

        public String id;
        public String firstName;
        public String lastName;
        public boolean family;


    }


    private List<Character> characters = new ArrayList<>();

    private int id = 1;

    private String nextId() {
        return Integer.toString(id++);
    }

    @PostConstruct
    public void init() {
        Character homer = new Character(nextId(), "Homer", "Simpson", true);
        Character marge = new Character(nextId(), "Marge", "Simpson", true);
        Character bart = new Character(nextId(), "Bart", "Simpson", true);
        Character lisa = new Character(nextId(), "Lisa", "Simpson", true);
        Character maggie = new Character(nextId(), "Maggie", "Simpson", true);

        Character carl = new Character(nextId(), "Carl", "Carlson", false);

        characters.add(homer);
        characters.add(marge);
        characters.add(bart);
        characters.add(lisa);
        characters.add(maggie);
        characters.add(carl);
    }

    public List<Character> search(String searchFor) {
        List<Character> found = characters.stream().filter(character ->
                character.firstName.contains(searchFor) || character.lastName.contains(searchFor)
        ).collect(Collectors.toList());
        return found;
    }

    public void addCharacter(String firstName, String lastName, boolean family) {
        characters.add(new Character(nextId(), firstName, lastName, family));
    }


    public Character getCharacter(String id) {
        List<Character> found = characters.stream().filter(character -> character.id.equals(id)).collect(Collectors.toList());
        return found.size() > 0 ? found.get(0) : null;
    }

    public Character getCharacterByName(String firstName) {
        List<Character> found = characters.stream().filter(character -> character.firstName.equals(firstName)).collect(Collectors.toList());
        return found.size() > 0 ? found.get(0) : null;
    }

    public List<Character> getCharactersById(List<String> ids) {
        List<Character> found = characters.stream().filter(character -> ids.contains(character.id)).collect(Collectors.toList());
        return found;
    }

    public List<Character> getAll() {
        return characters;
    }
}
