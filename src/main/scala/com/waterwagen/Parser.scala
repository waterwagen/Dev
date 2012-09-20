package com.waterwagen

class Parser
{
	def parse(string:String) : Int =
	{
		val sum_numeric_value_of_chars_func = (sum:Int, char:Char) => if(char.isDigit) sum + char.getNumericValue; else sum
		val add_ints_func = (int1:Int, int2:Int) => int1 + int2
		string.toArray.aggregate(0)(sum_numeric_value_of_chars_func, add_ints_func)
	}
}