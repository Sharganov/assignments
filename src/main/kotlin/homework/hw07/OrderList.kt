package homework.hw07

import java.util.*


abstract class OrderList<T : Comparable<T>> : Comparable<OrderList<out T>>,Iterable<T> {

    override fun iterator(): Iterator<T> {
        return this.iterator()
    }

    override fun hashCode(): Int {
        var answer = 0
        for(item in this) answer =  (answer + item.hashCode()) * 31
        return Math.abs(answer)
    }

    override fun equals(other: Any?): Boolean {

        if (other !is OrderList<*>) return false

        if (size() != other.size()) return false

        val iterator = other.iterator()
        for(item in this) if(item != iterator.next()) return false
        return true
    }

    override fun compareTo(other: OrderList<out T>): Int {
        if (size() > other.size()) return 1
        if (size() < other.size()) return -1

        val iterator = other.iterator()
        for(item in this) {
            val comprassion = item.compareTo(iterator.next())
            if (comprassion != 0) return comprassion
        }
        return 0
    }

    abstract fun add(value: T)

    abstract fun remove(value : T)

    abstract fun size(): Int

    abstract operator fun get(index: Int): T
}


class KotlinAtdOrderList<T : Comparable<T>>() : OrderList<T>() {

    private var count = 0
    private var list: Node<T>? = null

    override fun iterator(): Iterator<T> {
        return listIterator(list)
    }

    private class listIterator<T : Comparable<T>>(private var list : Node<T>?): Iterator<T> {
        override fun hasNext(): Boolean {
            return list != null
        }

        override fun next(): T {
            val temp = list?.value
            list = list?.next
            return temp ?: throw NoSuchElementException()

        }
    }

    internal inner class Node<T : Comparable<T>>(var value: T, var next: Node<T>?)

    override fun add(value: T) {
        list = insert(value, list)
        count++
    }

    private fun insert(value: T, node: Node<T>?): Node<T> {
        when (node) {
            null -> return Node(value, null)
            else -> {
                if (node.value.compareTo(value) >= 0)
                    return Node(value, Node(node.value, node.next))
                return Node(node.value, insert(value, node.next))
            }
        }
    }


    override fun remove(value: T) {
        if (list != null) list = delete(value, list)
    }

    private fun delete(value: T, node: Node<T>?): Node<T>? {
        when (node) {
            null -> return null
            else -> {

                if (node.value.equals(value)) return node.next
                when (node.next) {
                    null -> return null
                    else -> {
                        if (node.next?.value != value) return delete(value, node.next)
                        return Node(node.value, node.next?.next)
                    }
                }
            }
        }
    }

    override fun size(): Int {
        return count
    }

    override fun get(index: Int): T {
        var index = index
        var temp: Node<T>? = list
        while (index > 0) {
            temp = temp?.next
            index--;
        }
        return temp?.value ?: throw NoSuchElementException()
    }

    fun print() {
        var temp: Node<T>? = list
        while (temp != null) {
            println(temp.value)
            temp = temp.next
        }
    }
}

class KotlinArrayOrderList<T : Comparable<T>> : OrderList<T>() {

    private var arr: Array<T> = arrayOfNulls<Comparable<Any>>(10) as Array<T>
    private var count = 0

    override fun iterator(): Iterator<T> {

        return object : Iterator<T> {

            private var index = 0

            override fun hasNext(): Boolean {
                return index < count
            }

            override fun next(): T {
                index++
                return arr[index - 1]
            }
        }
    }

    override fun add(value: T) {
        fun toRight(index: Int) {

            if (count === 0)
                arr[1] = arr[0]
            else
                for (i in count downTo index + 1) {
                    arr[i] = arr[i - 1]
                }
        }

        if (count == arr.size - 1) bigger()
        var i = 0
        while (i < count && arr[i].compareTo(value) == -1) i++
        toRight(i)
        arr[i] = value
        count++
    }

    private fun bigger() {

        val newarr = arrayOfNulls<Comparable<Any>>(arr.size + 10) as Array<T>
        for (i in arr.indices) {
            newarr[i] = arr[i]
        }
        arr = newarr
    }

    override fun remove(value: T) {
        fun toLeft(index: Int) {
            for (i in index..count) {
                arr[i] = arr[i + 1]
            }
        }
        var i = 0
        while (i < count && arr[i] != value) i++
        toLeft(i)
    }

    override fun size(): Int {
        return count
    }

    override fun get(index: Int): T {
        return arr[index]
    }

    fun print() {
        var i = 0
        while (i < size()) {
            println(arr[i])
            i++
        }
    }
}