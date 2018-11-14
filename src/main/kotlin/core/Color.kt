package core

class Color(val r: Float, val g: Float, val b: Float) : Tuple(r, g, b) {

    companion object {
        fun white(): Color {
            return Color(1f, 1f, 1f)
        }

        fun black(): Color {
            return Color(0f, 0f, 0f)
        }
    }


    fun product(c2: Color): Color {
        return Color(r * c2.r, g * c2.g, b * c2.b)
    }

}