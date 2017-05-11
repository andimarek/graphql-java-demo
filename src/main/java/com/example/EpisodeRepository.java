package com.example;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class EpisodeRepository {

    public static enum Season {
        Season1,
        Season2,
        Season3,
        Season4,
        Season5,
        Season6,
        Season7,
        Season8,
        Season9
    }

    public static class Episode {

        public String id;
        public String name;
        public int number;
        public int numberOverall;
        public Season season;


        public Episode(String id, String name, int number, int numberOverall, Season season) {
            this.id = id;
            this.name = name;
            this.number = number;
            this.numberOverall = numberOverall;
            this.season = season;
        }

        public List<CharacterRepository.Character> characters = new ArrayList<>();

    }

    private List<Episode> episodes = new ArrayList<>();

    @PostConstruct
    public void init() {
        Episode ep11 = new Episode("1", "Simpsons Roasting on an Open Fire", 1, 1, Season.Season1);
        Episode ep12 = new Episode("2", "Bart the Genius", 2, 2, Season.Season2);
        Episode ep33 = new Episode("3", "When Flanders Failed", 2, 38, Season.Season3);
        Episode ep418 = new Episode("4", "So It's Come to This: A Simpsons Clip Show", 18, 77, Season.Season4);

        episodes.add(ep11);
        episodes.add(ep12);
        episodes.add(ep33);
        episodes.add(ep418);

    }

    public List<Episode> getAll() {
        return this.episodes;
    }


}
