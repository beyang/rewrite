package com.netflix.java.refactor.fix

import com.netflix.java.refactor.RefactorTest
import org.junit.Test

class ChangeFieldTest: RefactorTest() {
    
    @Test
    fun changeFieldType() {
        val a = java("""
            |import java.util.List;
            |public class A {
            |   List collection;
            |}
        """)
        
        refactor(a).changeField(List::class.java)
            .refactorType(Collection::class.java)
            .done()
        
        assertRefactored(a, """
            |import java.util.Collection;
            |public class A {
            |   Collection collection;
            |}
        """)
    }
    
    @Test
    fun changeFieldName() {
        val a = java("""
            |import java.util.List;
            |public class A {
            |   List collection = null;
            |}
        """)

        refactor(a).changeField(List::class.java)
                .refactorName("list")
                .done()

        assertRefactored(a, """
            |import java.util.List;
            |public class A {
            |   List list = null;
            |}
        """)
    }
}