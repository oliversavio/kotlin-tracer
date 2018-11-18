package cannon

import core.*
import java.io.File

data class Projectile(val position: Tuple, val velocity: Tuple)

data class World(val gravity: Tuple, val wind: Tuple)


fun main(args: Array<String>) {
    var p = Projectile(point(0f, 1f, 0f), normalize(vector(1f, 1f, 0f)) * 11.25f)
    val world = World(vector(0f, -0.1f, 0f), vector(-0.01f, 0f, 0f))

    val canvas = Canvas(250, 70)

    var count = 0;
    do {
        println("Position: " + p.position)
        drawToCanvas(canvas, p.position)
        p = tick(world, p)
        count++
    } while (p.position.y > 0)

    File("/Users/olivermascarenhas/Desktop/Cannon.ppm").writeText(canvas.canvasToPPM())
}

fun tick(world: World, p: Projectile): Projectile {
    val position = p.position + p.velocity
    val velocity = p.velocity + world.gravity + world.wind
    return Projectile(position, velocity)
}

fun drawToCanvas(canvas: Canvas, position: Tuple) {
    val x = position.x
    val y = canvas.height - position.y

    canvas.writePixel(x.toInt(), y.toInt(), Color.white())
}
