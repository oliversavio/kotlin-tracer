package cannon

import core.*
import java.io.File
import java.lang.IllegalStateException

data class Projectile(val position: Tuple, val velocity: Tuple)

data class World(val gravity: Tuple, val wind: Tuple)


fun main(args: Array<String>) {
    var p = Projectile(point(0f, 1f, 0f), normalize(vector(1f, 1.8f, 0f)) * 11.25f)
    val world = World(vector(0f, -0.1f, 0f), vector(-0.01f, 0f, 0f))
    val padding = 10
    val xaxis: MutableList<Float> = mutableListOf()
    val yaxis: MutableList<Float> = mutableListOf()
    var count = 0;
    do {
        println("Position: " + p.position)
        xaxis += p.position.x
        yaxis += p.position.y
        p = tick(world, p)
        count++
    } while (p.position.y > 0)

    val canvas = Canvas(xaxis.max()!!.toInt() + padding, yaxis.max()!!.toInt() + padding)
    draw(canvas, xaxis.toList(), yaxis.toList())

    File("/Users/olivermascarenhas/Desktop/Cannon.ppm").writeText(canvas.canvasToPPM())
}

fun draw(canvas: Canvas, xaxis: List<Float>, yaxis: List<Float>) {

    drawToCanvas(canvas, scaleLinear(xaxis, 0f, (xaxis.max()!! - xaxis.min()!!)), scaleLinear(yaxis, 0f, (yaxis.max()!! - yaxis.min()!!)))

}


fun tick(world: World, p: Projectile): Projectile {
    val position = p.position + p.velocity
    val velocity = p.velocity + world.gravity + world.wind
    return Projectile(position, velocity)
}

fun drawToCanvas(canvas: Canvas, xcords: List<Float>, ycords: List<Float>) {

    if (xcords.size != ycords.size) throw IllegalStateException("X and Y values are not of the same length")

    for (i in xcords.indices) {
        val x = xcords[i]
        val y = Math.max(canvas.height - ycords[i] - 1, 0f)

        canvas.writePixel(x.toInt(), y.toInt(), Color.white())
    }


}
