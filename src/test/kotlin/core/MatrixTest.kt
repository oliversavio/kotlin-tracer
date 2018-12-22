package core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MatrixTest {


    @Test
    fun test_constructing_and_inspecting_a_4x4_matrix() {
        val matrix = Matrix(
                arrayOf(
                        floatArrayOf(1f, 2f, 3f, 4f),
                        floatArrayOf(5.5f, 6.5f, 7.5f, 8.5f),
                        floatArrayOf(9f, 10f, 11f, 12f),
                        floatArrayOf(13.5f, 14.5f, 15.5f, 16.5f)
                )
        )

        assertEquals(1f, matrix.m[0][0])
        assertEquals(4f, matrix.m[0][3])
        assertEquals(5.5f, matrix.m[1][0])
        assertEquals(7.5f, matrix.m[1][2])
        assertEquals(11f, matrix.m[2][2])
        assertEquals(13.5f, matrix.m[3][0])
        assertEquals(15.5f, matrix.m[3][2])
    }


    @Test
    fun test_constructing_and_inspecting_a_2x2_matrix() {
        val matrix = Matrix(
                arrayOf(
                        floatArrayOf(-3f, 5f),
                        floatArrayOf(1f, -2f)
                )
        )

        assertEquals(-3f, matrix.m[0][0])
        assertEquals(5f, matrix.m[0][1])
        assertEquals(1f, matrix.m[1][0])
        assertEquals(-2f, matrix.m[1][1])

    }

    @Test
    fun test_constructing_and_inspecting_a_3x3_matrix() {
        val matrix = Matrix(
                arrayOf(
                        floatArrayOf(-3f, 5f, 0f),
                        floatArrayOf(1f, -2f, 7f),
                        floatArrayOf(0f, 1f, 1f)
                )
        )

        assertEquals(-3f, matrix.m[0][0])
        assertEquals(-2f, matrix.m[1][1])
        assertEquals(1f, matrix.m[2][2])

    }


    @Test
    fun test_matrix_equality_equal_matrices() {
        val M1 = Matrix(arrayOf(
                floatArrayOf(1f, 2f, 3f, 4f),
                floatArrayOf(5f, 6f, 7f, 8f),
                floatArrayOf(9f, 8f, 7f, 6f),
                floatArrayOf(5f, 4f, 3f, 2f)
        ))

        val M2 = Matrix(arrayOf(
                floatArrayOf(1f, 2f, 3f, 4f),
                floatArrayOf(5f, 6f, 7f, 8f),
                floatArrayOf(9f, 8f, 7f, 6f),
                floatArrayOf(5f, 4f, 3f, 2f)
        ))

        assertTrue(M1 == M2)

    }


    @Test
    fun test_matrix_equality_not_equal_matrices() {
        val M1 = Matrix(arrayOf(
                floatArrayOf(1f, 2f, 3f, 4f),
                floatArrayOf(5f, 6f, 7f, 8f),
                floatArrayOf(9f, 8f, 7f, 6f),
                floatArrayOf(5f, 4f, 3f, 2f)
        ))

        val M2 = Matrix(arrayOf(
                floatArrayOf(2f, 3f, 4f, 5f),
                floatArrayOf(6f, 7f, 8f, 9f),
                floatArrayOf(8f, 7f, 6f, 5f),
                floatArrayOf(4f, 3f, 2f, 1f)
        ))

        assertTrue(M1 != M2)

    }

    @Test
    fun test_matrix_multiply() {
        val M1 = Matrix(arrayOf(
                floatArrayOf(1f, 2f, 3f, 4f),
                floatArrayOf(5f, 6f, 7f, 8f),
                floatArrayOf(9f, 8f, 7f, 6f),
                floatArrayOf(5f, 4f, 3f, 2f)
        ))


        val M2 = Matrix(arrayOf(
                floatArrayOf(-2f, 1f, 2f, 3f),
                floatArrayOf(3f, 2f, 1f, -1f),
                floatArrayOf(4f, 3f, 6f, 5f),
                floatArrayOf(1f, 2f, 7f, 8f)
        ))

        val expected = Matrix(arrayOf(
                floatArrayOf(20f, 22f, 50f, 48f),
                floatArrayOf(44f, 54f, 114f, 108f),
                floatArrayOf(40f, 58f, 110f, 102f),
                floatArrayOf(16f, 26f, 46f, 42f)
        ))


        assertEquals(expected, M1 * M2)

    }


    @Test
    fun test_a_matrix_multiplied_by_a_tuple() {
        val M = Matrix(arrayOf(
                floatArrayOf(1f, 2f, 3f, 4f),
                floatArrayOf(2f, 4f, 4f, 2f),
                floatArrayOf(8f, 6f, 4f, 1f),
                floatArrayOf(0f, 0f, 0f, 1f)
        ))

        val b = Tuple(1f, 2f, 3f, 1)

        val expected = Tuple(18f, 24f, 33f, 1)

        assertEquals(expected, M * b)

    }

    @Test
    fun test_multiply_matrix_with_identity_matrix() {
        val M = Matrix(arrayOf(
                floatArrayOf(1f, 2f, 3f, 4f),
                floatArrayOf(2f, 4f, 4f, 2f),
                floatArrayOf(8f, 6f, 4f, 1f),
                floatArrayOf(0f, 0f, 0f, 1f)
        ))

        assertEquals(M, M * Matrix.identity())

    }

    @Test
    fun test_multiply_tupple_with_identity_matrix() {
        val b = Tuple(1f, 2f, 3f, 1)

        assertEquals(b, Matrix.identity() * b)

    }


}