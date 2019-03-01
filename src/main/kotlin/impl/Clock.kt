package impl

import core.Canvas
import core.Matrix
import core.Point
import core.writeStringToFile
import java.io.File

class Clock(canvasWidth: Int, canvasHeight: Int) {

    private val canvas = Canvas(canvasWidth, canvasHeight)
    private val radius = canvas.width * 3f / 8f
    private val centerX = canvas.width / 2f
    private val centerY = canvas.height / 2f


    fun render() {
        val origin = Point(0f, 0f, 1f)

        val points = (1..12)
                .map { buildTransformation(it) }
                .map { applyTransform(it, origin) }

        val x = points.map { it.x }
        val y = points.map { it.z }

        canvas.draw(x, y)
        writeStringToFile(canvas.canvasToPPM(), "/Users/olivermascarenhas/Desktop/Clock.ppm")

    }


    private fun applyTransform(transform: Matrix, point: Point) = transform * point


    private fun buildTransformation(hour: Int): Matrix {
        return Matrix.identity()
                .rotate((hour * Math.PI / 6), Matrix.RotationAxis.Y)
                .scale(radius, 0f, radius)
                .translate(centerX, 0f, centerY)
    }


}

fun main(args: Array<String>) {

    Clock(100, 100).render()
}
