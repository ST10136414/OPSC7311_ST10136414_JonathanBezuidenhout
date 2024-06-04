package Classes

class GoalClass
{
    lateinit var maxGoal:String
    lateinit var minGoal:String
    lateinit var goalName:String

    companion object {
        @JvmStatic
        val goalsMutableList = mutableListOf<GoalClass>()
    }
}