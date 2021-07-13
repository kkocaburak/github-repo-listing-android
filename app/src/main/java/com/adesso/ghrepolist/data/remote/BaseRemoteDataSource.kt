package com.adesso.ghrepolist.data.remote

import com.adesso.ghrepolist.internal.util.Failure

open class BaseRemoteDataSource {

    suspend fun <O> invoke(serviceFunction: suspend () -> O): O {
        return try {
            serviceFunction()
        } catch (exception: Exception) {
            throw asFailure(exception)
        }
    }

    private fun asFailure(exception: Exception): Failure {
        return Failure.Error(exception)
    }
}