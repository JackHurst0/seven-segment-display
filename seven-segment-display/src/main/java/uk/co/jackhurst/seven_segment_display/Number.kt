package uk.co.jackhurst.seven_segment_display

sealed class Number(
    val segments: List<Segment>
) {
    class Zero: Number(
        listOf(
            Segment.A(State.ON),
            Segment.B(State.ON),
            Segment.C(State.ON),
            Segment.D(State.ON),
            Segment.E(State.ON),
            Segment.F(State.ON),
            Segment.G(State.OFF),
        )
    )
    class One: Number(
        listOf(
            Segment.A(State.OFF),
            Segment.B(State.ON),
            Segment.C(State.ON),
            Segment.D(State.OFF),
            Segment.E(State.OFF),
            Segment.F(State.OFF),
            Segment.G(State.OFF),
        )
    )
    class Two: Number(
        listOf(
            Segment.A(State.ON),
            Segment.B(State.ON),
            Segment.C(State.OFF),
            Segment.D(State.ON),
            Segment.E(State.ON),
            Segment.F(State.OFF),
            Segment.G(State.ON),
        )
    )
    class Three: Number(
        listOf(
            Segment.A(State.ON),
            Segment.B(State.ON),
            Segment.C(State.ON),
            Segment.D(State.ON),
            Segment.E(State.OFF),
            Segment.F(State.OFF),
            Segment.G(State.ON),
        )
    )
    class Four: Number(
        listOf(
            Segment.A(State.OFF),
            Segment.B(State.ON),
            Segment.C(State.ON),
            Segment.D(State.OFF),
            Segment.E(State.OFF),
            Segment.F(State.ON),
            Segment.G(State.ON),
        )
    )
    class Five: Number(
        listOf(
            Segment.A(State.ON),
            Segment.B(State.OFF),
            Segment.C(State.ON),
            Segment.D(State.ON),
            Segment.E(State.OFF),
            Segment.F(State.ON),
            Segment.G(State.ON),
        )
    )
    class Six: Number(
        listOf(
            Segment.A(State.ON),
            Segment.B(State.OFF),
            Segment.C(State.ON),
            Segment.D(State.ON),
            Segment.E(State.ON),
            Segment.F(State.ON),
            Segment.G(State.ON),
        )
    )
    class Seven: Number(
        listOf(
            Segment.A(State.ON),
            Segment.B(State.ON),
            Segment.C(State.ON),
            Segment.D(State.OFF),
            Segment.E(State.OFF),
            Segment.F(State.OFF),
            Segment.G(State.OFF),
        )
    )
    class Eight: Number(
        listOf(
            Segment.A(State.ON),
            Segment.B(State.ON),
            Segment.C(State.ON),
            Segment.D(State.ON),
            Segment.E(State.ON),
            Segment.F(State.ON),
            Segment.G(State.ON),
        )
    )
    class Nine: Number(
        listOf(
            Segment.A(State.ON),
            Segment.B(State.ON),
            Segment.C(State.ON),
            Segment.D(State.ON),
            Segment.E(State.OFF),
            Segment.F(State.ON),
            Segment.G(State.ON),
        )
    )
    class Undefined: Number(
        listOf(
            Segment.A(State.OFF),
            Segment.B(State.OFF),
            Segment.C(State.OFF),
            Segment.D(State.OFF),
            Segment.E(State.OFF),
            Segment.F(State.OFF),
            Segment.G(State.OFF),
        )
    )
    class Empty: Number(
        listOf(
            Segment.A(State.OFF),
            Segment.B(State.OFF),
            Segment.C(State.OFF),
            Segment.D(State.OFF),
            Segment.E(State.OFF),
            Segment.F(State.OFF),
            Segment.G(State.OFF),
        )
    )

}