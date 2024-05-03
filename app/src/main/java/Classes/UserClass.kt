package Classes

class UserClass(){
    lateinit var userEmail: String
    lateinit var userName: String
    lateinit var passWord: String
    val projects = mutableListOf<ProjectClass>()

    companion object {
        @JvmStatic
        val userMutableList = mutableListOf<UserClass>()
        lateinit var loggedUser: UserClass
    }

    constructor(uEmail: String) : this()
    {
        userEmail = uEmail
        //userName = uName
    }
    /*
    constructor(uName: String) : this()
    {

        userName = uName
    }*/
    constructor(uName: String, pWord: String, uEmail: String) : this(uEmail)
    {
        userName = uName
        passWord = pWord
        userEmail = uEmail
    }
    fun addProject(project: ProjectClass) {
        projects.add(project)
    }
/*
    fun storeTolist()
    {

    }
*/
}
