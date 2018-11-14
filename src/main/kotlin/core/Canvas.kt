package core

class Canvas(val width: Int, val height: Int) {
    private val cols = Array(width, { _ -> Color(0f, 0f, 0f) })
    val canvas: Array<Array<Color>> = Array(height, { cols })


    fun pixelAt(row: Int, col: Int): Color {
        validatePixelCoordinates(row, col)
        return canvas[row][col]
    }

    fun writePixel(row: Int, col: Int, color: Color) {
        validatePixelCoordinates(row, col)
        canvas[row][col] = Color(color.r, color.g, color.b)
    }

    private fun validatePixelCoordinates(row: Int, col: Int) {
        if (row >= height || col >= width || row < 0 || col < 0) throw IllegalArgumentException("Invalid pixel coordinates")
    }


}