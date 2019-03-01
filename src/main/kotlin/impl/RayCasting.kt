package impl

import core.*

class RayCasting {

    private val rayOrigin = Point(z = -5f)
    private val wall_z = 10f
    private val wallSize = 7f
    private val canvasPixels = 500
    private val pixelSize = wallSize / canvasPixels
    private val half = wallSize / 2


    fun render() {
        val canvas = Canvas(canvasPixels, canvasPixels)
        val red = Color(1f, 0f, 0f)
        val shape = Sphere()

        for (y in 0 until canvasPixels) {
            val world_y = half - pixelSize * y

            for (x in 0 until canvasPixels) {
                val world_x = -half + pixelSize * x
                val position = Point(world_x, world_y, wall_z)
                val r = Ray(rayOrigin, normalize(Vector(position - rayOrigin)))

                val xs = intersect(shape, r)

                if (hit(xs) != null) {
                    canvas.writePixel(y, x, red)
                }

            }
        }

        writeStringToFile(canvas.canvasToPPM(), "/Users/olivermascarenhas/Desktop/RayCast.ppm")

    }
}

fun main(args: Array<String>) {
    val rayCast = RayCasting()
    rayCast.render()
}