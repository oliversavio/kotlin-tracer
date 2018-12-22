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


    operator fun times(m2: Matrix): Matrix {
        val c1 = this.m[0].size
        val r2 = m2.m.size

        if (c1 != r2) throw IllegalStateException("Matrices cannot be multiplied")

        val rows = this.m.size
        val cols = m2.m[0].size

        val nm = Matrix(rows, cols)

        for (r in 0 until rows) {
            for (c in 0 until cols) {
                for (k in 0 until c1) {
                    nm.m[r][c] += this.m[r][k] * m2.m[k][c]
                }
            }
        }

        return nm
    }


    operator fun times(tuple: Tuple): Tuple {
        val tm = Matrix(4, 1)
        tm.m[0][0] = tuple.x
        tm.m[1][0] = tuple.y
        tm.m[2][0] = tuple.z
        tm.m[3][0] = tuple.w.toFloat()

        val rm = times(tm)

        return Tuple(rm.m[0][0],
                rm.m[1][0],
                rm.m[2][0],
                rm.m[3][0].toByte())

    }

    companion object {
        private val IDENT: Matrix = Matrix(arrayOf(
                floatArrayOf(1f, 0f, 0f, 0f),
                floatArrayOf(0f, 1f, 0f, 0f),
                floatArrayOf(0f, 0f, 1f, 0f),
                floatArrayOf(0f, 0f, 0f, 1f)
        ))

        fun identity(): Matrix {
            return IDENT
        }
    }


}


