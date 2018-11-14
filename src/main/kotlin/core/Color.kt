package core

class Color(val r: Float, val g: Float, val b: Float) : Tuple(r, g, b) {
    fun product(c2: Color): Color {
        return Color(r * c2.r, g * c2.g, b * c2.b)
    }
}