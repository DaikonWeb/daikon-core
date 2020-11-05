package daikon.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PartTest {
    @Test
    fun `same parts are equal`() {
        val part1 = Part("name", null, byteArrayOf(112, 124, 111, 54))
        val part2 = Part("name", null, byteArrayOf(112, 124, 111, 54))

        assertThat(part1).isEqualTo(part2)
    }

    @Test
    fun `parts with different bytearray are different`() {
        val part1 = Part("name", null, byteArrayOf(112, 124, 111, 54))
        val part2 = Part("name", null, byteArrayOf(112, 124, 111, 0))

        assertThat(part1).isNotEqualTo(part2)
    }
}