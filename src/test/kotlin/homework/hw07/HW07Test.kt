package homework.hw07

import org.junit.Test
import kotlin.test.assertEquals


class HW07Test {

    @Test fun hashCodeTest01()
    {
        val list1 = KotlinAtdOrderList<Int>()
        val list2 = KotlinArrayOrderList<Int>()

        for( i in 0..15 )
        {
            list1.add(i)
            list2.add(i)
        }
        assertEquals(list1.hashCode(), list2.hashCode())
    }

    @Test fun hashCodeTes02()
    {
        val list1 = KotlinArrayOrderList<Int>()
        val list2 = KotlinArrayOrderList<Int>()

        for( i in 0..15 )
        {
            list1.add(i)
            list2.add(i)
        }
        assertEquals(list1.hashCode(), list2.hashCode())
    }

    @Test fun hashCodeTes03()
    {
        val list1 = KotlinAtdOrderList<Int>()
        val list2 = AtdOrderList<Int>()

        for( i in 0..15 )
        {
            list1.add(i)
            list2.add(i)
        }
        assertEquals(list1.hashCode(), list2.hashCode())
    }

    @Test fun compareToTest01()
    {

        val list1 = KotlinAtdOrderList<Int>()
        val list2 = KotlinArrayOrderList<Int>()

        for( i in 0..15 )
        {
            list1.add(i)
        }
        assertEquals(1, list1.compareTo(list2))
    }

    @Test fun compareToTest02()
    {

        val list1 = KotlinAtdOrderList<Int>()
        val list2 = KotlinAtdOrderList<Int>()

        for( i in 0..15 )
        {
            list1.add(i)
            list2.add(i+1)
        }
        assertEquals(-1, list1.compareTo(list2))

    }

    @Test fun compareToTest03()
    {

        val list1 = KotlinArrayOrderList<Int>()
        val list2 = KotlinAtdOrderList<Int>()

        for( i in 0..15 )
        {
            list1.add(i)
            list2.add(i)
        }
        assertEquals(0, list1.compareTo(list2))

    }

    @Test fun compareToTest04()
    {

        val list1 = KotlinAtdOrderList<Int>()
        val list2 = AtdOrderList<Int>()

        for( i in 0..15 )
        {
            list1.add(i)
            list2.add(i)
        }
    }

    @Test fun equalsTest01()
    {
        val list1 = KotlinAtdOrderList<Int>()
        val list2 = KotlinAtdOrderList<Int>()

        for(i in 0..15)
        {
            list1.add(i)
            list2.add(i)
        }
        assertEquals(true, list1.equals(list2))
    }

    @Test fun equalsTest02()
    {
        val list1 = KotlinAtdOrderList<Int>()
        assertEquals(false, list1.equals(3))
    }

    @Test fun equalsTest03()
    {
        val list1 = KotlinAtdOrderList<Int>()
        val list2 = KotlinArrayOrderList<Int>()

        for(i in 0..15)
        {
            list1.add(i+1)
            list2.add(i)
        }
        assertEquals(false, list1.equals(list2))
    }



}