package impl

import core.*

data class RenderCoords(val worldX: Float, val worldY: Float, val px: Int, val py: Int)


class RayCasting(val rayOrigin: Point, val wallZ: Float = 10f, wallSize: Float = 7f,
                 val canvasPixels: Int = 100, val color: Color = Color(1f, 0f, 0f)) {

    private val pixelSize = wallSize / canvasPixels
    private val half = wallSize / 2
    val canvas = Canvas(canvasPixels, canvasPixels)


    private fun calculateWorldXY(): List<RenderCoords> {
        val worldXY = mutableListOf<RenderCoords>()
        val y = mutableListOf<Float>()
        val x = mutableListOf<Float>()
        for (i in 0 until canvasPixels) {
            y.add(half - pixelSize * i)
            x.add(-half + pixelSize * i)
        }

        for (yy in 0 until canvasPixels) {
            for (xx in 0 until canvasPixels) {
                worldXY.add(RenderCoords(x[xx], y[yy], xx, yy))
            }
        }

        return worldXY
    }

    fun render(shape: WObject): Canvas {
        val pixels = calculateWorldXY()
        pixels.parallelStream()
                .forEach {
                    val position = Point(it.worldX, it.worldY, wallZ)
                    val r = Ray(rayOrigin, normalize(Vector(position - rayOrigin)))
                    val xs = intersect(shape as Sphere, r)
                    if (hit(xs) != null) {
                        canvas.writePixel(it.px, it.py, color)
                    }
                }
        return canvas
    }

}

fun main(args: Array<String>) {
    val rayCast = RayCasting(Point(z = -5f))

    for (i in 0 until 5) {
        val s2 = System.currentTimeMillis()
        val canvas = rayCast.render(Sphere())
        writeCanvasToFile(canvas, "/Users/olivermascarenhas/Desktop/Canvas_out_$i.ppm")
    }


    var shrinkOnY = Matrix.scaling(1f, 0.5f, 1f)
    val canvas = RayCasting(Point(z = -5f)).render(Sphere(transform = shrinkOnY))
    writeCanvasToFile(canvas, "/Users/olivermascarenhas/Desktop/ShrinkY.ppm")

    var shrinkOnX = Matrix.scaling(3f, 1f, 1f)
    val c2 = RayCasting(Point(z = -5f)).render(Sphere(transform = shrinkOnX))
    writeCanvasToFile(c2, "/Users/olivermascarenhas/Desktop/ShrinkX.ppm")

    var shrinkOnZ = Matrix.identity().
            rotate(Math.PI / 4, Matrix.RotationAxis.Z)
            .scale(0.2f, 0.2f, 1f)
            .translate(-0.2f,-1.1f,1.4f)
    val c3 = RayCasting(Point(z = -5f)).render(Sphere(transform = shrinkOnZ))
    writeCanvasToFile(c3, "/Users/olivermascarenhas/Desktop/ShrinkZ.ppm")

    var shearScale = Matrix.identity().shear(xy = 1f).scale(0.5f, 1f, 1f)
    val c4 = RayCasting(Point(z = -5f)).render(Sphere(transform = shearScale))
    writeCanvasToFile(c4, "/Users/olivermascarenhas/Desktop/Shear.ppm")
}