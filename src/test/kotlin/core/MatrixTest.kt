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
    fun test_multiply_tuple_with_identity_matrix() {
        val b = Tuple(1f, 2f, 3f, 1)

        assertEquals(b, Matrix.identity() * b)

    }


    @Test
    fun test_matrix_transpose() {
        val M = Matrix(arrayOf(
                floatArrayOf(0f, 9f, 3f, 0f),
                floatArrayOf(9f, 8f, 0f, 8f),
                floatArrayOf(1f, 8f, 5f, 3f),
                floatArrayOf(0f, 0f, 5f, 8f)
        ))

        val expected = Matrix(arrayOf(
                floatArrayOf(0f, 9f, 1f, 0f),
                floatArrayOf(9f, 8f, 8f, 0f),
                floatArrayOf(3f, 0f, 5f, 5f),
                floatArrayOf(0f, 8f, 3f, 8f)
        ))

        assertEquals(expected, M.transpose())

    }


    @Test
    fun test_transpose_of_identoty_matrix() {
        assertEquals(Matrix.identity(), Matrix.identity().transpose())
    }

    @Test
    fun test_calculate_determinant_of_2x2_matrix() {
        val m = Matrix(arrayOf(
                floatArrayOf(1f, 5f),
                floatArrayOf(-3f, 2f)
        ))

        assertEquals(17f, m.determinant())

    }

    @Test
    fun test_submatrix_of_3x3_is_2x2_matrix() {
        val M = Matrix(arrayOf(
                floatArrayOf(1f, 5f, 0f),
                floatArrayOf(-3f, 2f, 7f),
                floatArrayOf(0f, 6f, -3f)
        ))

        val expected = Matrix(arrayOf(
                floatArrayOf(-3f, 2f),
                floatArrayOf(0f, 6f)
        ))

        assertEquals(expected, M.submatrix(0, 2))

    }


    @Test
    fun test_submatrix_of_4x4_is_3x3_matrix() {
        val M = Matrix(arrayOf(
                floatArrayOf(-6f, 1f, 1f, 6f),
                floatArrayOf(-8f, 5f, 8f, 6f),
                floatArrayOf(-1f, 0f, 8f, -2f),
                floatArrayOf(-7f, 1f, -1f, 1f)
        ))

        val expected = Matrix(arrayOf(
                floatArrayOf(-6f, 1f, 6f),
                floatArrayOf(-8f, 8f, 6f),
                floatArrayOf(-7f, -1f, 1f)
        ))

        assertEquals(expected, M.submatrix(2, 1))

    }


    @Test
    fun test_calculate_minor_of_3x3_matrix() {
        val M = Matrix(arrayOf(
                floatArrayOf(3f, 5f, 0f),
                floatArrayOf(2f, -1f, -7f),
                floatArrayOf(6f, -1f, 5f)
        ))

        assertEquals(25f, M.minor(1, 0))
    }

    @Test
    fun test_calculate_cofactor_of_3x3_matrix() {
        val M = Matrix(arrayOf(
                floatArrayOf(3f, 5f, 0f),
                floatArrayOf(2f, -1f, -7f),
                floatArrayOf(6f, -1f, 5f)
        ))

        assertEquals(-12f, M.minor(0, 0))
        assertEquals(-12f, M.cofactor(0, 0))
        assertEquals(25f, M.minor(1, 0))
        assertEquals(-25f, M.cofactor(1, 0))
    }


    @Test
    fun test_calculate_cofactor_of_4x4_matrix() {
        val M = Matrix(arrayOf(
                floatArrayOf(-2f, -8f, 3f, 5f),
                floatArrayOf(-3f, 1f, 7f, 3f),
                floatArrayOf(1f, 2f, -9f, 6f),
                floatArrayOf(-6f, 7f, 7f, -9f)
        ))


        assertEquals(690f, M.cofactor(0, 0))
        assertEquals(447f, M.cofactor(0, 1))
        assertEquals(210f, M.cofactor(0, 2))
        assertEquals(51f, M.cofactor(0, 3))
        assertEquals(-4071f, M.determinant())
    }


    @Test
    fun test_is_matrix_invertable() {
        val M = Matrix(arrayOf(
                floatArrayOf(6f, 4f, 4f, 4f),
                floatArrayOf(5f, 5f, 7f, 6f),
                floatArrayOf(4f, -9f, 3f, -7f),
                floatArrayOf(9f, 1f, 7f, -6f)
        ))


        assertEquals(-2120f, M.determinant())
        assertTrue(M.isInvertable())

    }


    @Test
    fun test_matrix_is_not_invertable() {
        val M = Matrix(arrayOf(
                floatArrayOf(-4f, 2f, -2f, -3f),
                floatArrayOf(9f, 6f, 2f, 6f),
                floatArrayOf(0f, -5f, 1f, -5f),
                floatArrayOf(0f, 0f, 0f, 0f)
        ))

        assertEquals(0f, M.determinant())
        assertFalse(M.isInvertable())
    }


    @Test
    fun test_inverse_4x4_matrix() {

        val M = Matrix(arrayOf(
                floatArrayOf(-5f, 2f, 6f, -8f),
                floatArrayOf(1f, -5f, 1f, 8f),
                floatArrayOf(7f, 7f, -6f, -7f),
                floatArrayOf(1f, -3f, 7f, 4f)
        ))

        val B = M.inverse()

        assertEquals(532f, M.determinant())
        assertEquals(-160f, M.cofactor(2, 3))
        assertEquals(-160f / 532, B.m[3][2])
        assertEquals(105f, M.cofactor(3, 2))
        assertEquals(105f / 532, B.m[2][3])

        val expected = Matrix(arrayOf(
                floatArrayOf(0.21805f, 0.45113f, 0.24060f, -0.04511f),
                floatArrayOf(-0.80827f, -1.45677f, -0.44361f, 0.52068f),
                floatArrayOf(-0.07895f, -0.22368f, -0.05263f, 0.19737f),
                floatArrayOf(-0.52256f, -0.81391f, -0.30075f, 0.30639f)
        ))

        assertEquals(expected, B)


    }


    @Test
    fun test_calculate_inverse() {
        val M = Matrix(arrayOf(
                floatArrayOf(8f, -5f, 9f, 2f),
                floatArrayOf(7f, 5f, 6f, 1f),
                floatArrayOf(-6f, 0f, 9f, 6f),
                floatArrayOf(-3f, 0f, -9f, -4f)
        ))

        val expected = Matrix(arrayOf(
                floatArrayOf(-0.15385f, -0.15385f, -0.28205f, -0.53846f),
                floatArrayOf(-0.07692f, 0.12308f, 0.02564f, 0.03077f),
                floatArrayOf(0.35897f, 0.35897f, 0.43590f, 0.92308f),
                floatArrayOf(-0.69231f, -0.69231f, -0.76923f, -1.92308f)
        ))

        assertEquals(expected, M.inverse())
    }


    @Test
    fun test_calculate_inverse_2() {
        val M = Matrix(arrayOf(
                floatArrayOf(9f, 3f, 0f, 9f),
                floatArrayOf(-5f, -2f, -6f, -3f),
                floatArrayOf(-4f, 9f, 6f, 4f),
                floatArrayOf(-7f, 6f, 6f, 2f)
        ))

        val expected = Matrix(arrayOf(
                floatArrayOf(-0.04074f, -0.07778f, 0.14444f, -0.22222f),
                floatArrayOf(-0.07778f, 0.03333f, 0.36667f, -0.33333f),
                floatArrayOf(-0.02901f, -0.14630f, -0.10926f, 0.12963f),
                floatArrayOf(0.17778f, 0.06667f, -0.26667f, 0.33333f)
        ))


        assertEquals(expected, M.inverse())
    }

    @Test
    fun test_multiply_product_by_inverse() {
        val A = Matrix(arrayOf(
                floatArrayOf(3f, -9f, 7f, 3f),
                floatArrayOf(3f, -8f, 2f, -9f),
                floatArrayOf(-4f, 4f, 4f, 1f),
                floatArrayOf(-6f, 5f, -1f, 1f)
        ))


        val B = Matrix(arrayOf(
                floatArrayOf(8f, 2f, 2f, 2f),
                floatArrayOf(3f, -1f, 7f, 0f),
                floatArrayOf(7f, 0f, 5f, 4f),
                floatArrayOf(6f, -2f, 0f, 5f)
        ))

        val C = A * B

        assertEquals(A, C * B.inverse())


    }


    @Test
    fun test_multiply_by_translation_matrix() {
        val transform = Matrix.translation(5f, -3f, 2f)
        val p = Point(-3f, 4f, 5f)

        assertEquals(Point(2f, 1f, 7f), transform * p)

    }

    @Test
    fun test_multiply_by_inverse_of_translation_matrix() {
        val transform = Matrix.translation(5f, -3f, 2f)
        val p = Point(-3f, 4f, 5f)

        assertEquals(Point(-8f, 7f, 3f), transform.inverse() * p)

    }

    @Test
    fun test_translation_does_not_affect_vectors() {
        val transform = Matrix.translation(5f, -3f, 2f)
        val v = Vector(-3f, 4f, 5f)

        assertEquals(v, transform * v)
    }


    @Test
    fun test_a_scaling_matrix_applied_to_a_point() {
        val transformation = Matrix.scaling(2f, 3f, 4f)
        val p = Point(-4f, 6f, 8f)
        assertEquals(Point(-8f, 18f, 32f), transformation * p)
    }

    @Test
    fun test_a_scaling_matrix_applied_to_a_vector() {
        val transformation = Matrix.scaling(2f, 3f, 4f)
        val v = Vector(-4f, 6f, 8f)
        assertEquals(Vector(-8f, 18f, 32f), transformation * v)
    }


    @Test
    fun test_multiply_by_inverse_of_scaling_matrix() {
        val transformation = Matrix.scaling(2f, 3f, 4f)
        val v = Vector(-4f, 6f, 8f)
        assertEquals(Vector(-2f, 2f, 2f), transformation.inverse() * v)
    }


    @Test
    fun test_reflection_is_scalig_by_negative_value() {
        val transformation = Matrix.scaling(-1f, 1f, 1f)
        val p = Point(2f, 3f, 4f)
        assertEquals(Point(-2f, 3f, 4f), transformation * p)
    }


    @Test
    fun test_rotate_point_around_x_axis() {
        val p = Point(0f, 1f, 0f)
        val halfQuarter = Matrix.rotation((Math.PI / 4).toFloat(), Matrix.RotationAxis.X)
        val fullQuarter = Matrix.rotation((Math.PI / 2).toFloat(), Matrix.RotationAxis.X)

        assertEquals(Point(0f, (Math.sqrt(2.0) / 2).toFloat(), (Math.sqrt(2.0) / 2).toFloat()), halfQuarter * p)
        assertEquals(Point(0f, 0f, 1f), fullQuarter * p)

    }

    @Test
    fun test_inverse_of_x_rotates_in_opposite_direction() {
        val p = Point(0f, 1f, 0f)
        val halfQuarter = Matrix.rotation((Math.PI / 4).toFloat(), Matrix.RotationAxis.X)

        assertEquals(Point(0f, (Math.sqrt(2.0) / 2).toFloat(), -(Math.sqrt(2.0) / 2).toFloat()), halfQuarter.inverse() * p)

    }

    @Test
    fun test_rotate_around_y_axis() {
        val p = Point(0f, 0f, 1f)

        val halfQuarter = Matrix.rotation((Math.PI / 4).toFloat(), Matrix.RotationAxis.Y)
        val fullQuarter = Matrix.rotation((Math.PI / 2).toFloat(), Matrix.RotationAxis.Y)


        assertEquals(Point((Math.sqrt(2.0) / 2).toFloat(), 0f, (Math.sqrt(2.0) / 2).toFloat()), halfQuarter * p)
        assertEquals(Point(1f, 0f, 0f), fullQuarter * p)

    }

    @Test
    fun test_rotate_around_z_axis() {
        val p = Point(0f, 1f, 0f)

        val halfQuarter = Matrix.rotation((Math.PI / 4).toFloat(), Matrix.RotationAxis.Z)
        val fullQuarter = Matrix.rotation((Math.PI / 2).toFloat(), Matrix.RotationAxis.Z)


        assertEquals(Point(-(Math.sqrt(2.0) / 2).toFloat(), (Math.sqrt(2.0) / 2).toFloat(), 0f), halfQuarter * p)
        assertEquals(Point(-1f, 0f, 0f), fullQuarter * p)

    }

    @Test
    fun test_shear_x_in_propotion_to_y() {
        val transform = Matrix.shearing(xy = 1f)
        assertEquals(Point(5f, 3f, 4f), transform * Point(2f, 3f, 4f))
    }

    @Test
    fun test_shear_x_in_propotion_to_z() {
        val transform = Matrix.shearing(xz = 1f)
        assertEquals(Point(6f, 3f, 4f), transform * Point(2f, 3f, 4f))
    }

    @Test
    fun test_shear_y_in_propotion_to_x() {
        val transform = Matrix.shearing(yx = 1f)
        assertEquals(Point(2f, 5f, 4f), transform * Point(2f, 3f, 4f))
    }

    @Test
    fun test_shear_y_in_propotion_to_z() {
        val transform = Matrix.shearing(yz = 1f)
        assertEquals(Point(2f, 7f, 4f), transform * Point(2f, 3f, 4f))
    }

    @Test
    fun test_shear_z_in_propotion_to_x() {
        val transform = Matrix.shearing(zx = 1f)
        assertEquals(Point(2f, 3f, 6f), transform * Point(2f, 3f, 4f))
    }

    @Test
    fun test_shear_z_in_propotion_to_y() {
        val transform = Matrix.shearing(zy = 1f)
        assertEquals(Point(2f, 3f, 7f), transform * Point(2f, 3f, 4f))
    }


    @Test
    fun test_individual_transformations_applied_sequence() {
        val p = Point(1f, 0f, 1f)
        val A = Matrix.rotation((Math.PI / 2).toFloat(), Matrix.RotationAxis.X)
        val B = Matrix.scaling(5f, 5f, 5f)
        val C = Matrix.translation(10f, 5f, 7f)

        val p2 = A * p
        assertEquals(Point(1f, -1f, 0f), p2)

        val p3 = B * p2
        assertEquals(Point(5f, -5f, 0f), p3)

        val p4 = C * p3
        assertEquals(Point(15f, 0f, 7f), p4)

    }

    @Test
    fun test_chained_tranformations_applied_in_reverse_order() {

        val T = Matrix.identity()
                .rotate(Math.PI/2, Matrix.RotationAxis.X)
                .scale(5f,5f,5f)
                .translate(10f,5f,7f)

        assertEquals(Point(15f, 0f, 7f), T * Point(1f, 0f, 1f) )


    }


    @Test
    fun test_translate_points() {

        val p = Point(0f,0f,1f)

        val T = Matrix.identity()
                .translate(50f,0f,50f)

        println(T * p)


    }


}