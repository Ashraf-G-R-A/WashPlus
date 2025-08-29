import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    private val fullDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val monthYearFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    private val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())


    fun getCurrentDate(): String {
        val date = Date()
        return fullDateFormat.format(date)
    }

    fun getMonthYearFromDate(date: String): String {
        val parsedDate = fullDateFormat.parse(date)
        return monthYearFormat.format(parsedDate!!)
    }

    fun getYearFromDate(date: String): String {
        val parsedDate = fullDateFormat.parse(date)
        return yearFormat.format(parsedDate!!)
    }
}
