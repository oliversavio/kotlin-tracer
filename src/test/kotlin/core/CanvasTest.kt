package core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CanvasTest {

    private val headerSplit = """
            P3
            5 3
            255

        """.trimIndent()

    @Test
    fun test_create_canvas() {
        val c = Canvas(10, 20)
        assertEquals(10, c.width)
        assertEquals(20, c.height)

        for (i in 0 until c.height - 1) {
            for (j in 0 until c.width - 1) {
                assertEquals(Color.black(), c.canvas[i][j])
            }
        }
    }

    @Test
    fun test_writing_pixel_to_canvas() {
        val canvas = Canvas(10, 20)
        val red = Color(1f, 0f, 0f)
        canvas.writePixel(3, 2, red)

        assertEquals(red, canvas.pixelAt(2, 3))

    }

    @Test
    fun test_construct_the_ppm_header() {
        val c = Canvas(5, 3)
        c.writePixel(0, 0, Color(1.5f, 0f, 0f))
        c.writePixel(2, 1, Color(0f, 0.5f, 0f))
        c.writePixel(4, 2, Color(-0.5f, 0f, 1f))

        val expected = """
                    255 0 0 0 0 0 0 0 0 0 0 0 0 0 0
                    0 0 0 0 0 0 0 128 0 0 0 0 0 0 0
                    0 0 0 0 0 0 0 0 0 0 0 0 0 0 255

                    """.trimIndent()

        val actual = c.canvasToPPM().split(headerSplit)[1]
        assertEquals(expected, actual)

    }

    @Test
    fun test_splitting_long_lines_in_ppm_file() {
        val c = Canvas(10, 2)

        c.writeAllPixels { _, _ -> Color(1f, 0.8f, 0.6f) }

        val expected = """
            255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204
            153 255 204 153 255 204 153 255 204 153 255 204 153
            255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204
            153 255 204 153 255 204 153 255 204 153 255 204 153

        """.trimIndent()

        val headSplitter = """
            P3
            10 2
            255

        """.trimIndent()

        val actual = c.canvasToPPM().split(headSplitter)[1]
        assertEquals(expected, actual)
    }

    @Test
    fun test_ppm_files_are_terminated_by_a_newline() {
        val c = Canvas(5, 3)
        val s = c.canvasToPPM()
        c.writePixel(2, 2, Color.black())
        assertEquals('\n', s[s.length - 1])
    }


}