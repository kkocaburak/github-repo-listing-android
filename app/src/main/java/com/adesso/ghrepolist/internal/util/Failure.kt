package com.adesso.ghrepolist.internal.util

import java.io.IOException

sealed class Failure : IOException() {
    class Error(val exception: Exception) : Failure()
}