class Main {

    fun listFunctions() {
        val list = listOf("Java")
//    list.add("Kotlin") // cannot edit read-only collection

        val mutableList = mutableListOf("Java")
        mutableList.add("Kotlin")
    }

    fun getMax(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    fun getMaxFunc(a: Int, b: Int) = if (a > b) a else b

    }