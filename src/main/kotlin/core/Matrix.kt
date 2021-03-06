package core

class Matrix(row: Int, col: Int) {
    val m: Array<Array<Float>> = Array(row) { Array(col) { 0f } }

    enum class RotationAxis {
        X, Y, Z
    }

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

        return when (tuple) {
            is Vector -> Vector(rm.m[0][0], rm.m[1][0], rm.m[2][0], rm.m[3][0].toByte())
            is Point -> Point(rm.m[0][0], rm.m[1][0], rm.m[2][0], rm.m[3][0].toByte())
            else -> Tuple(rm.m[0][0], rm.m[1][0], rm.m[2][0], rm.m[3][0].toByte())
        }

    }

    fun transpose(): Matrix {
        val transpose = Matrix(this.m[0].size, this.m.size)
        for (r in 0 until this.m.size) {
            for (c in 0 until this.m[0].size) {
                transpose.m[c][r] = this.m[r][c]
            }
        }
        return transpose
    }


    /**
     * Calculate Determinant of 2x2 matrix
     */
    fun determinant(): Float {
        return if (this.m.size == 2 && this.m[0].size == 2) {
            this.m[0][0] * this.m[1][1] - this.m[0][1] * this.m[1][0]
        } else {
            var det = 0f
            for (c in 0 until this.m[0].size) {
                det += this.m[0][c] * cofactor(0, c)
            }
            det
        }
    }

    fun submatrix(rr: Int, cc: Int): Matrix {
        val sub = Matrix(this.m.size - 1, this.m[0].size - 1)
        var row = -1
        for (r in 0 until this.m.size) {
            if (r != rr) row += 1 else continue
            var col = -1
            for (c in 0 until this.m[0].size) {
                if (c != cc) col += 1 else continue
                sub.m[row][col] = this.m[r][c]
            }
        }
        return sub
    }

    fun minor(row: Int, col: Int): Float {
        val subMat = submatrix(row, col)
        return subMat.determinant()
    }

    fun cofactor(row: Int, col: Int): Float {
        val minor = minor(row, col)

        return if ((row + col) % 2 != 0) -minor else minor
    }

    fun isInvertable(): Boolean {
        return determinant() != 0f
    }

    fun inverse(): Matrix {
        if (!isInvertable()) throw IllegalStateException("Matrix cannot be inverted")

        val inverse = Matrix(this.m.size, this.m[0].size)
        val det = determinant()
        for (r in 0 until this.m.size) {
            for (c in 0 until this.m[0].size) {
                val cofactor = cofactor(r, c)
                inverse.m[c][r] = cofactor / det
            }
        }
        return inverse
    }

    fun translate(x: Float, y: Float, z: Float): Matrix = translation(x, y, z) * this

    fun scale(x: Float, y: Float, z: Float): Matrix = scaling(x, y, z) * this

    fun rotate(radians: Double, axis: RotationAxis): Matrix = rotation(radians.toFloat(), axis) * this

    fun shear(xy: Float = 0f, xz: Float = 0f, yx: Float = 0f, yz: Float = 0f, zx: Float = 0f, zy: Float = 0f): Matrix =
            shearing(xy, xz, yx, yz, zx, zy) * this

    companion object {

        fun identity(): Matrix {
            return Matrix(arrayOf(
                    floatArrayOf(1f, 0f, 0f, 0f),
                    floatArrayOf(0f, 1f, 0f, 0f),
                    floatArrayOf(0f, 0f, 1f, 0f),
                    floatArrayOf(0f, 0f, 0f, 1f)
            ))
        }

        fun translation(x: Float, y: Float, z: Float): Matrix {

            val ident = identity()
            ident.m[0][3] = x
            ident.m[1][3] = y
            ident.m[2][3] = z

            return ident
        }

        fun scaling(x: Float, y: Float, z: Float): Matrix {
            val ident = identity()
            ident.m[0][0] = x
            ident.m[1][1] = y
            ident.m[2][2] = z
            return ident
        }


        fun rotation(radians: Float, axis: RotationAxis): Matrix {
            val sin_cos = getSineAndCosine(radians)

            val sin_r = sin_cos.first
            val cos_r = sin_cos.second

            return when (axis) {
                RotationAxis.X -> RotationTransformFactory.X.transform(cos_r, sin_r)
                RotationAxis.Y -> RotationTransformFactory.Y.transform(cos_r, sin_r)
                RotationAxis.Z -> RotationTransformFactory.Z.transform(cos_r, sin_r)
            }
        }

        fun shearing(xy: Float = 0f, xz: Float = 0f, yx: Float = 0f, yz: Float = 0f, zx: Float = 0f, zy: Float = 0f): Matrix {
            val ident = identity()

            ident.m[0][1] = xy
            ident.m[0][2] = xz
            ident.m[1][0] = yx
            ident.m[1][2] = yz
            ident.m[2][0] = zx
            ident.m[2][1] = zy

            return ident
        }

        private fun getSineAndCosine(radians: Float): Pair<Float, Float> {
            val r = radians.toDouble()

            return Pair(Math.sin(r).toFloat(), Math.cos(r).toFloat())
        }


        private enum class RotationTransformFactory {

            X {
                override fun transform(cos_r: Float, sin_r: Float): Matrix {
                    val ident = identity()
                    ident.m[1][1] = cos_r
                    ident.m[1][2] = -sin_r
                    ident.m[2][1] = sin_r
                    ident.m[2][2] = cos_r
                    return ident
                }

            },
            Y {
                override fun transform(cos_r: Float, sin_r: Float): Matrix {
                    val ident = identity()
                    ident.m[0][0] = cos_r
                    ident.m[0][2] = sin_r
                    ident.m[2][0] = -sin_r
                    ident.m[2][2] = cos_r
                    return ident
                }

            },
            Z {
                override fun transform(cos_r: Float, sin_r: Float): Matrix {
                    val ident = identity()
                    ident.m[0][0] = cos_r
                    ident.m[0][1] = -sin_r
                    ident.m[1][0] = sin_r
                    ident.m[1][1] = cos_r
                    return ident
                }
            };

            abstract fun transform(cos_r: Float, sin_r: Float): Matrix
        }


    }


    override fun toString(): String {
        val sb = StringBuilder()
        for (r in 0 until m.size) {
            for (c in 0 until m[0].size) {
                sb.append(this.m[r][c])
                sb.append("\t")
            }
            sb.append("\n")
        }
        return sb.toString()
    }

}


