package core

class Canvas(val width: Int, val height: Int) {
    private val header = "P3"
    private val space = " "
    private val maxPixelValue = "255"
    val canvas: Array<Array<Color>> = Array(height) { Array(width) { Color.black() } }


    fun pixelAt(row: Int, col: Int): Color {
        validatePixelCoordinates(row, col)
        return canvas[row][col]
    }

    fun writePixel(col: Int, row: Int, color: Color) {
        validatePixelCoordinates(row, col)
        canvas[row][col] = Color(color.r, color.g, color.b)
    }

    fun canvasToPPM(): String {
        val sBuffer = StringBuilder(header)
        sBuffer.append("\n")
        sBuffer.append(width).append(space).append(height)
        sBuffer.append("\n")
        sBuffer.append(maxPixelValue)
        sBuffer.append("\n")

        for (r in 0 until height) {
            val rowBuffer = StringBuilder()
            for (c in 0 until width) {
                rowBuffer.append(convertToPPMScale(canvas[r][c].r))
                rowBuffer.append(space)
                rowBuffer.append(convertToPPMScale(canvas[r][c].g))
                rowBuffer.append(space)
                rowBuffer.append(convertToPPMScale(canvas[r][c].b))
                if (c < width - 1) rowBuffer.append(space)
            }
            sBuffer.append(splitLongLinesForPPM(rowBuffer))
            sBuffer.append("\n")
        }

        return sBuffer.toString()
    }

    private fun validatePixelCoordinates(row: Int, col: Int) {
        if (row >= height || col >= width || row < 0 || col < 0)
            throw IllegalArgumentException("Invalid pixel coordinates [$row,$col]")
    }

    private fun convertToPPMScale(f: Float): String {
        val r = Math.round(f * 255.0).toInt()

        when {
            r > 255 -> return "255"
            r < 0 -> return "0"
            else -> return r.toString()
        }
    }

    private fun splitLongLinesForPPM(buffer: StringBuilder): String {
        val splits = buffer.length / 70
        val factor = 70
        for (i in 1..splits) {
            insertNewline(factor * i - 1, buffer)
        }

        return buffer.toString()
    }

    private fun insertNewline(factor: Int, buffer: StringBuilder) {
        if (buffer[factor] == ' ') {
            buffer.setCharAt(factor, '\n')
        } else {
            insertNewline(factor - 1, buffer)
        }
    }

}