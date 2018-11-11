package cannon

import core.Tuple
import core.normalize
import core.point
import core.vector

data class Projectile(val position: Tuple, val velocity: Tuple)

data class World(val gravity: Tuple, val wind: Tuple)


fun main(args: Array<String>) {
    var p = Projectile(point(0f, 1f, 0f), normalize(vector(0.01f, 1f, 0f)))
    val world = World(vector(0f, -0.1f, 0f), vector(-0.01f, 0f, 0f))


    do {
        println("Position: " + p.position)
        p = tick(world, p)
    } while (p.position.y > 0)


}

fun tick(world: World, p: Projectile): Projectile {
    val position = p.position + p.velocity
    val velocity = p.velocity + world.gravity + world.wind
    return Projectile(position, velocity)
}
