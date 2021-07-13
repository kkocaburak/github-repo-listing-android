package com.adesso.ghrepolist.internal.util.functional

sealed class Either<out L, out R> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Failure<out L>(val a: L) : Either<L, Nothing>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Success<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Success<R>
    val isLeft get() = this is Failure<L>

    fun <L> left(a: L) = Failure(a)
    fun <R> right(b: R) = Success(b)

    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Failure -> fnL(a)
            is Success -> fnR(b)
        }
}