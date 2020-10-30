package uk.co.jackhurst.seven_segment_display

/***
 * Class representing the segments. We label segments as follows:
 *     =A=
 *    F   B
 *    F   B
 *     =G=
 *    E   C
 *    E   C
 *     =D=
 */
sealed class Segment(
    val orientation: Orientation,
    val positionVertical: PositionVertical,
    val positionHorizontal: PositionHorizontal,
) {
    abstract val state: State

    class A(override  val state: State): Segment(
        Orientation.HORIZONTAL,
        PositionVertical.TOP,
        PositionHorizontal.START,
    )
    class B(override  val state: State): Segment(
        Orientation.VERTICAL,
        PositionVertical.TOP,
        PositionHorizontal.END
    )
    class C(override  val state: State): Segment(
        Orientation.VERTICAL,
        PositionVertical.MIDDLE,
        PositionHorizontal.END
    )
    class D(override  val state: State): Segment(
        Orientation.HORIZONTAL,
        PositionVertical.BOTTOM,
        PositionHorizontal.START
    )
    class E(override  val state: State): Segment(
        Orientation.VERTICAL,
        PositionVertical.MIDDLE,
        PositionHorizontal.START
    )
    class F(override  val state: State): Segment(
        Orientation.VERTICAL,
        PositionVertical.TOP,
        PositionHorizontal.START
    )
    class G(override  val state: State): Segment(
        Orientation.HORIZONTAL,
        PositionVertical.MIDDLE,
        PositionHorizontal.START
    )
}

enum class Orientation {
    VERTICAL,
    HORIZONTAL
}
enum class PositionVertical {
    TOP,
    MIDDLE,
    BOTTOM
}
enum class PositionHorizontal {
    START,
    END
}
enum class State {
    ON,
    OFF,
}
