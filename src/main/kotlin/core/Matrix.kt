package core

class Matrix(row: Int, col: Int) {
    val m: Array<Array<Float>> = Array(row) { Array(col) { 0f } }


    constructor(matrix: Array<FloatArray>) : this(matrix.size, matrix[0].size) {
        for (row in 0 until matrix.size) {
            for (col in 0 until matrix[row].size) {
                m[row][col] = matrix[row][col]
            }
        }
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Matrix

        if (this.m.size != other.m.size) return false
        if (this.m[0].size != other.m[0].size) return false

        for (r in 0 until this.m.size) {
            for (c in 0 until this.m[0].size) if (!eq(this.m[r][c], other.m[r][c])) return false
        }
        return true
    }


    override fun hashCode(): Int {
        return this.m.hashCode()
    }


}


