# Простой paint

Canvas - это нода, которая позволяет отобразать различный графический контент, как примитива, так и изображения например.

Первое что делаем это создаем canvas.

```kotlin
var canvas = Canvas()
canvas.height=450.0
canvas.width=500.0
```

Чтобы нарисовать что-то в позиции мыши, мы устанавливаем `onMouseDragged` - сработает, когда мы начнем что-то тащить.

```kotlin
canvas.onMouseDragged = EventHandler<MouseEvent>  { e ->
    val size = brushSize // размер кисти
    val x = e.x - size / 2 //пощзиция центра квадрата, который будет нарисован
    val y = e.y - size / 2 //
    if (e.button == MouseButton.SECONDARY) { // если правая кнопка нажата
        g.clearRect(x, y, size, size) //рисуем белый квадрат
    } else {
        g.fill = Color.RED
        g.fillRect(x, y, size, size) //рисуем красный квадрат
    }
}
```
