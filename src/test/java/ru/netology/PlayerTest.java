package ru.netology;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    GameStore store = new GameStore();
    Player player = new Player("Petya");

    @Test
    public void shouldInstallPlayAndSumGenreOneGame() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        player.installGame(game);
        player.play(game, 3);
        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAddTimeIfPlayAgain() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        player.installGame(game);
        player.play(game, 3);
        int expected = 7;
        int actual = player.play(game, 4);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostGameByGenre() {
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Баттл Онлайн", "Аркады");
        Game game3 = store.publishGame("Баттл", "Шутер");
        player.installGame(game1);
        player.play(game1, 9);
        player.installGame(game2);
        player.play(game2, 1);
        player.installGame(game3);
        player.play(game3, 1);
        Game actual = player.mostGameByGenre("Аркады");
        assertEquals(game1, actual);
    }

    @Test
    public void shouldNullIfMostGameByGenreZero() {
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Баттл Онлайн", "Аркады");
        Game game3 = store.publishGame("Баттл", "Шутер");
        player.installGame(game1);
        player.play(game1, 0);
        player.installGame(game2);
        player.play(game2, 0);
        player.installGame(game3);
        player.play(game3, 0);
        Game actual = player.mostGameByGenre("Аркады");
        assertNull(actual);
    }

    @Test
    public void shouldMostGameByGenreOne() {
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        player.installGame(game1);
        player.play(game1, 5);
        Game actual = player.mostGameByGenre("Аркады");
        assertEquals(game1, actual);
    }

    @Test
    public void shouldSumGenreIfThreeGameMy() {
        Game g1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game g2 = store.publishGame("Нетология", "РПГ");
        Game g3 = store.publishGame("Нетология Баттл Онлайн", "Шутер");
        player.installGame(g1);
        player.play(g1, 3);
        player.installGame(g2);
        player.play(g2, 5);
        player.installGame(g3);
        player.play(g3, 6);

        Integer expected = 5;
        Integer actual = player.sumGenre("РПГ");
        assertEquals(expected, actual);
    }


    @Test
    public void shouldNotInstallGameAgain() {
        Game g1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        player.installGame(g1);
        player.play(g1, 10);
        player.installGame(g1);
        Integer expected = 10;
        Integer actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionForPlayIfGameIsNotInstalled() {
        assertThrows(RuntimeException.class, () -> {
            Game game10 = store.publishGame("Net", "Шутер");
            player.play(game10, 120);
        });
    }

}




