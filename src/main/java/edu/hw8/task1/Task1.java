package edu.hw8.task1;

/**
 * Разработчика Ваню раздражает его менеджер и он хочет научиться едко отвечать ему в ответ.
 * Для этого Ваня хочет разработать сервер, который будет выдавать известные цитаты по теме в ответ на ключевые слова.
 *
 * Напишите такой сервер и Java-клиент для работы с ним:
 * сервер и клиент должен работать на сырых сокетах
 * используйте пул потоков -- сервер должен уметь принимать несколько соединений
 * если максимальное количество одновременных соединений исчерпано, клиент должен ждать,
 * пока не появится свободное соединение
 *
 * Пример:
 * Ваня: личности
 * Сервер: Не переходи на личности там, где их нет
 * Ваня: оскорбления
 * Сервер: Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами
 * Ваня: глупый
 * Сервер: А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.
 * Ваня: интеллект
 * Сервер: Чем ниже интеллект, тем громче оскорбления
 * Если возникнут сложности при работе с ByteBuffer, можете попробовать использовать ObjectOutputStream.
 */

public class Task1 {

}
