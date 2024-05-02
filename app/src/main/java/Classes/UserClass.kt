package Classes

class UserClass(){
    lateinit var userEmail: String
    lateinit var userName: String
    lateinit var passWord: String



    companion object {
        @JvmStatic
        val userMutableList = mutableListOf<UserClass>()
        lateinit var loggedUser: UserClass
    }
    //MutableList to store user details
    //val userMutableList = mutableListOf<UserClass>()

    constructor(uEmail: String) : this()
    {
        userEmail = uEmail
    }
    constructor(uName: String, pWord: String, uEmail: String) : this(uEmail)
    {
        userName = uName
        passWord = pWord
        userEmail = uEmail
    }

    //method for storing objects to the static list companion object
    fun storeTolist(userObj: UserClass)
    {
        userMutableList.add(userObj)
    }

    //
    fun retrieveFromlist(index: Int): UserClass {
        return userMutableList[index]
    }
}
