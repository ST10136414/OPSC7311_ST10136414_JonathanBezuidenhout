package Classes

class UserClass(){
    lateinit var userEmail: String
    lateinit var userName: String
    lateinit var passWord: String


    val userMutableList = mutableListOf<UserClass>()

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

    fun storeTolist(userObj: UserClass)
    {
        userMutableList.add(userObj)
    }

}
