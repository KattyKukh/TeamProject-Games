package ru.netology;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {
    private Map<Game, Integer> playedTime = new HashMap<>();
    private List<Game> games = new ArrayList<>();
    GameStore store = new GameStore();
    Player player1 = new Player("Petya");
    Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Player player2 = new Player("Petya");
    Game game2 = store.publishGame("Баттл Онлайн", "Аркады");
    Player player3 = new Player("Petya");
    Game game3 = store.publishGame("Баттл", "Шутер");
    Player player4 = new Player("Petya");
    Game game4 = store.publishGame("Онлайн", "Рпг");

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);
        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGameMostPlayerByGenre() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        player.installGame(game1);
        player.play(game1, 9);
        Game game2 = store.publishGame("Баттл Онлайн", "Аркады");
        player.installGame(game2);
        player.play(game2, 1);
        Game game3 = store.publishGame("Баттл", "Шутер");
        player.installGame(game3);
        player.play(game2, 1);
        String expected = "Нетология Баттл Онлайн";
        player.mostGameByGenre("Аркады");
        Game actual = player.mostGameByGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGameMostPlayerByGenreZero() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        player.installGame(game1);
        player.play(game1, 0);
        Game game2 = store.publishGame("Баттл Онлайн", "Аркады");
        player.installGame(game2);
        player.play(game2, 0);
        Game game3 = store.publishGame("Баттл", "Шутер");
        player.installGame(game3);
        player.play(game3, 0);
        String expected = null;
        player.mostGameByGenre("Аркады");
        Game actual = player.mostGameByGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGameMostPlayerByGenreOne() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        player.installGame(game1);
        player.play(game1, 5);
        player.mostGameByGenre("Аркады");
        String expected = "Нетология Баттл Онлайн";
        Game actual = player.mostGameByGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfThreeGameMy() {
        GameStore store = new GameStore();

        Game g1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game g2 = store.publishGame("Нетология", "Аркады");
        Game g3 = store.publishGame("Нетология Баттл Онлайн", "Шутер");


        Player player = new Player("Petya");


        player.installGame(g1);
        player.play(g1, 3);
        player.installGame(g2);

        player.play(g2, 5);
        player.installGame(g3);
        player.play(g3, 5);

        player.sumGenre("Аркады");
        playedTime.get(g1);
        playedTime.get(g2);
        playedTime.get(g3);
        playedTime.equals("Аркады");
        int expected = 8;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldInstallTwo() {
        Player player = new Player("Vasya");
        Game g1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        playedTime.put(game2, 2);
        int expected = 2;
        Integer actual = playedTime.get(game2);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldInstallZero() {
        Player player = new Player("Vasya");
        Game g1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        playedTime.put(null, 0);
        int expected = 0;
        Integer actual = playedTime.get(null);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldPlay() {
        assertThrows(RuntimeException.class, () -> {
            Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
            player1.installGame(game1);
            Game game10 = store.publishGame("Net", "Шутер");
            player1.play(game10, 120);
        });
    }
}



