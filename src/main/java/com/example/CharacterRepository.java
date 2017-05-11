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


        public List<Character> friends = new ArrayList<>();

    }


    private List<Character> characters = new ArrayList<>();


    @PostConstruct
    public void init() {
        Character homer = new Character("1", "Homer", "Simpson", true);
        Character marge = new Character("2", "Marge", "Simpson", true);
        Character bart = new Character("3", "Bart", "Simpson", true);
        Character lisa = new Character("4", "Lisa", "Simpson", true);
        Character maggie = new Character("5", "Maggie", "Simpson", true);

        Character carl = new Character("6", "Carl", "Carlson", false);

        characters.add(homer);
        characters.add(marge);
        characters.add(bart);
        characters.add(lisa);
        characters.add(maggie);
        characters.add(carl);
    }


    public Character getCharacter(String id) {
        List<Character> found = characters.stream().filter(character -> character.id.equals(id)).collect(Collectors.toList());
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
