package Classes

class GoalClass
{
    lateinit var maxGoalToday:String
    lateinit var minGoalToday:String
    lateinit var maxGoalWeek:String
    lateinit var minGoalWeek:String

    lateinit var TodayDate:String
    lateinit var WeekStartDate:String
    lateinit var WeekEndDate:String

    //lateinit var goalName:String

    companion object {
        @JvmStatic
        val goalsMutableList = mutableListOf<GoalClass>()
    }
}