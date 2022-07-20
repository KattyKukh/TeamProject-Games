package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameStoreTest {

    GameStore store = new GameStore();

    @Test
    public void shouldAddGame() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.addPlayTime("Vasya", 120);
        Player player1 = new Player("Vasya");
        player1.installGame(game);
        assertTrue(store.containsGame(game));
//      Если был getter для games, можно было бы чище тестировать именно добавление игры в каталог, без использования поиска.
//      Вот так:
//        ArrayList<Game> expected = new ArrayList<>();
//        expected.add(0, new Game("Нетология Баттл Онлайн", "Аркады", store));
//        assertEquals(expected,store.getGames());

    }

    @Test
    public void shouldAddGameZero() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.addPlayTime("Vasya", 0);

        Player player1 = new Player("Vasya");
        player1.installGame(game);
        store.containsGame(null);
        assertTrue(store.containsGame(game));


    }

    @Test
    public void shouldFindGameInStore() {
        store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.publishGame("Нетология Шутинг Гейм", "Шутеры");
        Game game1 = store.publishGame("Netology Adventure", "Приключения");
        store.publishGame("Netology Test Quest", "Квесты");
        assertTrue(store.containsGame(game1));
    }

    @Test
    public void shouldFindFirstGameInStore() {
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.publishGame("Нетология Шутинг Гейм", "Шутеры");
        store.publishGame("Netology Adventure", "Приключения");
        store.publishGame("Netology Test Quest", "Квесты");
        assertTrue(store.containsGame(game1));

    }

    @Test
    public void shouldFindLastGameInStore() {
        store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.publishGame("Нетология Шутинг Гейм", "Шутеры");
        store.publishGame("Netology Adventure", "Приключения");
        Game game1 = store.publishGame("Netology Test Quest", "Квесты");
        assertTrue(store.containsGame(game1));

    }

    @Test
    public void shouldAddPlayTime() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player1 = new Player("First");
        player1.installGame(game);
        player1.play(game, 1);
        store.addPlayTime(player1.getName(), 2);

        int expected = 3;
        int actual = store.getSumPlayedTime();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAddPlayTimeIfNegative() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player1 = new Player("First");
        player1.installGame(game);
        player1.play(game, 1);
        store.addPlayTime(player1.getName(), -2);

        int expected = 1;
        int actual = store.getSumPlayedTime();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindBestPlayer() {
        Game game = store.publishGame("Netology Test Quest", "Квесты");
        Player player1 = new Player("First");
        Player player2 = new Player("Second");
        Player player3 = new Player("Third");
        player1.installGame(game);
        player2.installGame(game);
        player3.installGame(game);
        player1.play(game, 0);
        player2.play(game, 1);
        player3.play(game, 0);

        String expected = "Second";
        String actual = store.getMostPlayer();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindBestPlayerZero() {
        Game game = store.publishGame("Netology Test Quest", "Квесты");
        String expected = null;
        String actual = store.getMostPlayer();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindBestPlayerIfBestResultIsOneHour() {
        Game game = store.publishGame("Netology Test Quest", "Квесты");
        Player player1 = new Player("First");
        Player player2 = new Player("Second");
        Player player3 = new Player("Third");
        player1.installGame(game);
        player2.installGame(game);
        player3.installGame(game);
        player1.play(game, 1);
        player2.play(game, 0);

        String expected = "First";
        String actual = store.getMostPlayer();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumPlayedTime() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player1 = new Player("First");
        Player player2 = new Player("Second");
        Player player3 = new Player("Third");
        player1.installGame(game);
        player2.installGame(game);
        player3.installGame(game);
        player1.play(game, 1);
        player2.play(game, 2);
        player3.play(game, 3);

        int expected = 6;
        int actual = store.getSumPlayedTime();
        assertEquals(expected, actual);
    }


}
