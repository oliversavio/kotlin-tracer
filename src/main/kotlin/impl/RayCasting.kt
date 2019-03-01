package impl

import core.*

class RayCasting {

    private val rayOrigin = Point(z = -5f)
    private val wall_z = 10f
    private val wallSize = 7f
    private val canvasPixels = 200
    private val pixelSize = wallSize / canvasPixels
    private val half = wallSize / 2
    private val canvas = Canvas(canvasPixels, canvasPixels)
    private val red = Color(1f, 0f, 0f)


    fun render(shape: WObject, fileName: String) {
        for (y in 0 until canvasPixels) {
            val world_y = half - pixelSize * y

            for (x in 0 until canvasPixels) {
                val world_x = -half + pixelSize * x
                val position = Point(world_x, world_y, wall_z)
                val r = Ray(rayOrigin, normalize(Vector(position - rayOrigin)))

                val xs = intersect(shape as Sphere, r)

                if (hit(xs) != null) {
                    canvas.writePixel(y, x, red)
                }

            }
        }

        writeStringToFile(canvas.canvasToPPM(), fileName)

    }
}

fun main(args: Array<String>) {
    val rayCast = RayCasting()
    rayCast.render(Sphere(), "/Users/olivermascarenhas/Desktop/Sphere1.ppm")

    var shrinkOnY = Matrix.scaling(1f, 0.5f, 1f)
    rayCast.render(Sphere(transform = shrinkOnY), "/Users/olivermascarenhas/Desktop/ShrinkOnY.ppm")


    var shrinkOnX = Matrix.scaling(0.5f, 1f, 1f)
    rayCast.render(Sphere(transform = shrinkOnX), "/Users/olivermascarenhas/Desktop/ShrinkOnX.ppm")

    var shrinkOnZ = Matrix.identity().rotate(Math.PI / 4, Matrix.RotationAxis.Z).scale(0.5f, 1f, 1f)
    rayCast.render(Sphere(transform = shrinkOnZ), "/Users/olivermascarenhas/Desktop/ShrinkOnRotateZ.ppm")


    var shearScale = Matrix.identity().shear(xy = 1f).scale(0.5f, 1f, 1f)
    rayCast.render(Sphere(transform = shearScale), "/Users/olivermascarenhas/Desktop/Shear.ppm")

}