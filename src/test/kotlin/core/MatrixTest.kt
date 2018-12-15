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
                        floatArrayOf(1f,-2f)
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
                        floatArrayOf(1f,-2f,7f),
                        floatArrayOf(0f,1f,1f)
                )
        )

        assertEquals(-3f, matrix.m[0][0])
        assertEquals(-2f, matrix.m[1][1])
        assertEquals(1f, matrix.m[2][2])

    }


    @Test
    fun test_matrix_equality_equal_matrices() {
        val M1 = Matrix(arrayOf(
                floatArrayOf(1f,2f,3f,4f),
                floatArrayOf(5f,6f,7f,8f),
                floatArrayOf(9f,8f,7f,6f),
                floatArrayOf(5f,4f,3f,2f)
        ))

        val M2 = Matrix(arrayOf(
                floatArrayOf(1f,2f,3f,4f),
                floatArrayOf(5f,6f,7f,8f),
                floatArrayOf(9f,8f,7f,6f),
                floatArrayOf(5f,4f,3f,2f)
        ))

        assertTrue(M1 == M2)

    }


    @Test
    fun test_matrix_equality_not_equal_matrices() {
        val M1 = Matrix(arrayOf(
                floatArrayOf(1f,2f,3f,4f),
                floatArrayOf(5f,6f,7f,8f),
                floatArrayOf(9f,8f,7f,6f),
                floatArrayOf(5f,4f,3f,2f)
        ))

        val M2 = Matrix(arrayOf(
                floatArrayOf(2f,3f,4f,5f),
                floatArrayOf(6f,7f,8f,9f),
                floatArrayOf(8f,7f,6f,5f),
                floatArrayOf(4f,3f,2f,1f)
        ))

        assertTrue(M1 != M2)

    }

}