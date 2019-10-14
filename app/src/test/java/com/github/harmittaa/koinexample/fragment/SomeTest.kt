package com.github.harmittaa.koinexample.fragment

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

open class MyDependency {
    fun doesSomething() = "Original value"
}

class ClassToTest constructor(val dep: MyDependency) {
    fun getVal() = dep.doesSomething()
}

@RunWith(JUnit4::class)
class SomeTest {
    lateinit var classUnderTest: ClassToTest

    @Before
    fun setUp() {

        var dependencyMock = mock(MyDependency::class.java)
        `when`(dependencyMock.doesSomething()).thenReturn("MOCKED")
        classUnderTest = ClassToTest(dependencyMock)
    }

    @Test
    fun testDat() {
        assert("MOCKED" == classUnderTest.getVal())
    }
}