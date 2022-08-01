class Main {

    fun listFunctions() {
        val list = listOf("Java")
//    list.add("Kotlin") // cannot edit read-only collection

        val mutableList = mutableListOf("Java")
        mutableList.add("Kotlin")
    }
}