/*
JSON — текстовый формат обмена данными

Имеет следующий формат

{
    "keyStr": "stringValue",
    "keyNum": 42.0,
    "keyObject": {
        "keyMode": "value"
    },
    "keyArray": [
        41, 42, 43
    ],
    "keyArrayObjects": [
        {
            "key": 42
        },
        {
            "key": 43
        }
    ]
}

Популярные библеотеки: gson, klaxon

Есть как либы, которые используют рефлексию (превращяют json в структуру), так
и более простые, где json парсится в map<key, value>

В kotlin в модуле kotlinx есть своя либа для сереализации

Существуют и другие форматы, например: toml или xml

В новом задании вам нужно будет написать сереализацию для редактора фото.

Можете использовать любую либу. (При установки могут возникнуть проблемы, например из-за разных версий либ или версий самого языка.
Поченить это бывает сложно, самый простой метод это самому написать maven конфиг, соблюдая инструкции либ)

Но в этом задании можно получить на !!10!! баллов больше, если вы сами придумаете свой формат и распарсите его))

Ниже, небольшой пример парсинга сцены
*/

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.modules.*

//Основной класс ноды (У всех нод есть `x` и `y` - позиция на сцене)
@Serializable //Декоратор, говорит о том что класс сереализуемый
abstract class Node {
    abstract val x: Int
    abstract val y: Int
}

@Serializable
@SerialName("FloatNode")//Имя ноды присереализации (поле type в json)
data class FloatNode(override val x:Int, override val y:Int, val value: Float): Node()

@Serializable
@SerialName("StringNode")
data class StringNode(override val x:Int, override val y:Int, val value: String): Node()

//Добавляем модуль, чтобы либа могла срастить, что она парсит объекты типа Node
val module = SerializersModule {
    polymorphic(Node::class) {
        subclass(FloatNode::class)//Перечисляем все классы, что нужно парсить
        subclass(StringNode::class)
    }
}

//Создаем прасер, куда прокидываем наш модуль
val format = Json { serializersModule = module }

fun main() {

    //Парсим json из массива Node разных типов
    val res = format.decodeFromString<List<Node>>("""
        [
            {"type":"FloatNode","x": 0, "y": 0, "value": 42.0},
            {"type":"StringNode","x": 0, "y": 0, "value": "string"} 
        ]
    """)
    println(res)

    //Тут уже объект в json строку
    val str = format.encodeToJsonElement(res)
    println(str)

    //Вывод:
    //[FloatNode(x=0, y=0, value=42.0), StringNode(x=0, y=0, value=string)]
    //[{"type":"FloatNode","x":0,"y":0,"value":42.0},{"type":"StringNode","x":0,"y":0,"value":"string"}]
}