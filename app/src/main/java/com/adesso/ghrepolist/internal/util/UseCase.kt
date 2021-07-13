package com.adesso.ghrepolist.internal.util

import com.adesso.ghrepolist.internal.util.functional.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<out Type : Any, in Params> {

    protected abstract suspend fun buildUseCase(params: Params): Type

    suspend fun run(params: Params): Either<Failure, Type> = withContext(Dispatchers.IO) {
        try {
            Either.Success(buildUseCase(params))
        } catch (failure: Failure) {
            Either.Failure(failure)
        }
    }
}