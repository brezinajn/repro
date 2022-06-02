package com.github.brezinajn.repro

import arrow.optics.optics

@optics
data class Foo(
    val bar: Int,
    val baz: String,
){
    companion object
}